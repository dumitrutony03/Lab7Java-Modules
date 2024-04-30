package org.example.protobuffprotocol;

import com.google.protobuf.ServiceException;
import jdk.jshell.spi.ExecutionControl;
import org.example.dto.*;
import org.example.models.*;
import org.example.rpcprotocol.*;
import org.example.services.IServices;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.example.protobuffprotocol.Protobufs.ClientResponse.Type.*;

public class ClientServicesProtoProxy implements IServices {
    private String host;
    private int port;
    private InputStream input;
    private OutputStream output;
    private Socket connection;

    private BlockingQueue<Protobufs.ClientResponse> qresponses;
    private volatile boolean finished;

    public ClientServicesProtoProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses = new LinkedBlockingQueue<Protobufs.ClientResponse>();
    }

    @Override
    public boolean LoginPersoanaOficiu(String name, String password) {
        System.out.println("PROTO PROXY LOGIN PERS OFICIU");
        initializeConnection();
        System.out.println("LoginPersoanaOFICIU PROTO PROXY");

        PersoanaOficiu persoanaOficiu = new PersoanaOficiu(name, password);

        System.out.println("Urmeaza sa trimitem un request PROTO PROXY");
        sendRequest(ProtoUtils.createLoginRequest(persoanaOficiu));
        System.out.println("Request trimis din UI PROTO PROXY");
        Protobufs.ClientResponse response = readResponse();
        System.out.println("Raspuns primit din SERVER PROTO PROXY");
        if (response.getType() == OK) {
            System.out.println("Raspuns OK pentru LOGIN - PersoanaOficiu");
            return true;
        }
        if (response.getType() == ERROR) {
//            String err = ProtoUtils.(response);
            closeConnection();
            System.out.println("EROARE persoanaOficiuLoggedIn: ");
            return false;
        }
        return false;
    }


    @Override
    public void Logout(String numeParticipant) {
        System.out.println("LogoutPersoanaOFICIU PROTO PROXY");

        System.out.println("Urmeaza sa trimitem un request PROTO PROXY");
        sendRequest(ProtoUtils.createLogoutRequest(numeParticipant));
        System.out.println("Request trimis din UI PROTO PROXY");
        Protobufs.ClientResponse response = readResponse();
        System.out.println("Raspuns primit din SERVER PROTO PROXY");
        if (response.getType() == OK) {
            System.out.println("Raspuns OK pentru LOGOUT - PersoanaOficiu");
        }
        if (response.getType() == ERROR) {
//            String err = ProtoUtils.(response);
            closeConnection();
            System.out.println("EROARE persoanaOficiuLoggedOUT: ");
        }
    }

    @Override
    public void InscrieParticipant(String numeParticipant, String numeEchipa, String capMotor) {
        System.out.println("Request trimis din UI PROTO PROXY - ADD NEW PARTICIPANT");
        sendRequest(ProtoUtils.createNewParticipantRequest(numeParticipant, numeEchipa, capMotor));
        Protobufs.ClientResponse response = readResponse();
        System.out.println("Raspuns primit din SERVER PROTO PROXY");
        if (response.getType() == OK) {
            System.out.println("Raspuns OK pentru ADAUGARE NOU PARTICIPANT - PARTICIPANT");
        }
        if (response.getType() == ERROR) {
//            String err = ProtoUtils.(response);
            closeConnection();
            System.out.println("EROARE PARTICIPANT: ");
        }
    }

    @Override
    public Map<String, Integer> GetNumberOfParticipantsByRace() {
        sendRequest(ProtoUtils.createNR_PARTICIPANTS_BYRACERequest());
        Protobufs.ClientResponse response = readResponse();
        List<Protobufs.ParticipantsByRaceDto> participantsByRaceDtoList = response.getParticipantsByRaceDtoList();

        Map<String, Integer> map = new HashMap<>();
        System.out.println("Raspuns primit din SERVER PROTO PROXY");
        if (response.getType() == OK) {
            System.out.println("Raspuns OK primire - GetNumberOfParticipantsByRace");

            // Populate the map with data from the ParticipantsByRaceDto objects
            for (Protobufs.ParticipantsByRaceDto dto : participantsByRaceDtoList) {
                map.put(dto.getCursa(), dto.getNrParticipanti());
            }
        }
        if (response.getType() == ERROR) {
//            String err = ProtoUtils.(response);
            closeConnection();
            System.out.println("EROARE GetNumberOfParticipantsByRace: ");
        }

        return map;
    }

    @Override
    public StringBuilder GetTeam_Participants(String numeEchipa) {
        sendRequest(ProtoUtils.createPARTICIPANTS_BYTEAMRequest(numeEchipa));

        Protobufs.ClientResponse response = readResponse();
        List<Protobufs.TeamsAndParticipantsDto> participantsByRaceDtoList = response.getTeamsAndParticipantsDtoList();

        StringBuilder sb = new StringBuilder();
        System.out.println("Raspuns primit din SERVER PROTO PROXY");
        if (response.getType() == OK) {
            System.out.println("Raspuns OK primire - GetNumberOfParticipantsByRace");

            // Populate the StringBuilder with data from the ParticipantsByRaceDto objects
            for (Protobufs.TeamsAndParticipantsDto dto : participantsByRaceDtoList) {
                sb.append("Echipa: ").append(dto.getEchipa()).append(", Participant: ").append(dto.getParticipant()).append("\n");
            }
        }
        if (response.getType() == ERROR) {
//            String err = ProtoUtils.(response);
            closeConnection();
            System.out.println("EROARE GetNumberOfParticipantsByRace: ");
        }

        return sb;

    }

    @Override
    public void RegisterPersoanaOficiu(String nume, String parola) {

    }

    private void initializeConnection() {
        try {
            connection = new Socket(host, port);
            output = connection.getOutputStream();
            //output.flush();
            input = connection.getInputStream();     //new ObjectInputStream(connection.getInputStream());
            finished = false;
            startReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        finished = true;
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendRequest(Protobufs.ClientRequest request) {
        try {
            System.out.println("Sending request ..." + request);
            //request.writeTo(output);
            request.writeDelimitedTo(output);
            output.flush();
            System.out.println("Request sent.");
        } catch (IOException e) {
            System.out.println("Error sending object " + e);
        }
    }

    private Protobufs.ClientResponse readResponse() {
        Protobufs.ClientResponse response = null;
        try {
            response = qresponses.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void startReader() {
        Thread tw = new Thread(new ReaderThread());
        tw.start();
    }


    // Este folosit pentru IObserver, ca sa se updat-eze instant,
    // dar noi nu folosim asta, astfel, primim numai OkResponse-uri
    private void handleUpdate(Protobufs.ClientResponse updateResponse){
        System.out.println("HANDLE UPDATE response type got from server side " + updateResponse.getType());
        switch (updateResponse.getType()){
            case PERSOANAOFICIU_LOGGED_IN:{
//                User friend=ProtoUtils.getUser(updateResponse);
                System.out.println("HANDLE UPDATE PROTO PROXY PersOficiu logged in ");
                System.out.println("PersOficiu logged in ");
//                    client.friendLoggedIn(friend);
                break;
            }
        }

    }

    private class ReaderThread implements Runnable {
        public void run() {
            while (!finished) {
                try {
                    Protobufs.ClientResponse response = Protobufs.ClientResponse.parseDelimitedFrom(input);
                    System.out.println("response received into ReaderThread " + response);

                    if (isUpdateResponse(response.getType())){
                        handleUpdate(response);
                    }else{
                    try {
                        System.out.println("Adding in qresponses");
                        qresponses.put(response);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    }
                } catch (IOException e) {
                    System.out.println("Reading error " + e);
                }
            }
        }
    }


    private boolean isUpdateResponse(Protobufs.ClientResponse.Type type) {
        switch (type) {
            case PERSOANAOFICIU_LOGGED_IN:
                return true;
//            case PERSOANAOFICIU_LOGGED_OUT:
//                return true;
//            case NEW_PARTICIPANT:
//                return true;
//            case NR_PARTICIPANTS_BYRACE:
//                return true;
//            case PARTICIPANTS_BYTEAM:
//                return true;
        }
        return false;
    }
}


