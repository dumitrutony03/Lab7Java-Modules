package org.example.protobuffprotocol;

import com.google.protobuf.ServiceException;
import jdk.jshell.spi.ExecutionControl;
import org.example.dto.CursaDto;
import org.example.dto.DTOUtils;
import org.example.dto.ParticipantDto;
import org.example.dto.PersoanaOficiuDto;
import org.example.models.*;
import org.example.rpcprotocol.*;
import org.example.services.IServices;

import java.io.*;
import java.net.Socket;
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
//        sendRequest(ProtoUtils.createLoginRequest(DTOUtils.getDTO(persoanaOficiu)));
        sendRequest(ProtoUtils.createLoginRequest(persoanaOficiu));
        System.out.println("Request trimis din UI PROTO PROXY");
        Protobufs.ClientResponse response = readResponse();
        System.out.println("Raspuns primit din SERVER PROTO PROXY");
        if (response.getType()== OK) {
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
//        sendRequest(ProtoUtils.createLoginRequest(DTOUtils.getDTO(persoanaOficiu)));
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
//        initializeConnection();

//        Echipa e1 = Echipa.valueOf(numeEchipa); // Conversie din String în enum Echipa
//        Cursa c1 = new Cursa(CapacitateMotor.valueOf(capMotor));
//        CursaDto c1Dto = DTOUtils.getDTO(c1);
//        ParticipantDto participantDto = new ParticipantDto(numeParticipant, e1, c1Dto);
//
////        ParticipantDto udto = DTOUtils.getDTO(participant);
//        System.out.println("participantdto: " + participantDto);
//
//        Request req = new Request.Builder().type(RequestType.NEW_PARTICIPANT).data(participantDto).build();
//        sendRequest(req);
//        Response response = readResponse();
//
//        if (response.type() == ResponseType.OK) {
//            System.out.println("Raspuns OK pentru LOGIN - PersoanaOficiu");
//        }
//        if (response.type() == ResponseType.ERROR) {
//            String err = response.data().toString();
//            closeConnection();
////            throw new ChatException(err);
//            System.out.println("EROARE persoanaOficiuLoggedIn: " + err);
//        }
    }

    @Override
    public Map<String, Integer> GetNumberOfParticipantsByRace() {
//        Request req = new Request.Builder().type(RequestType.NR_PARTICIPANTS_BYRACE).build();
//        sendRequest(req);
//        Response response = readResponse();
//        if (response.type() == ResponseType.ERROR) {
//            String err = response.data().toString();
//            System.out.println("Ceva eroare la GetNumberOfParticipantsByRace: " + err);
//            return null;
//        }
//        if (response.type() == ResponseType.OK) {
//            try {
//                Map<String, Integer> result = (Map<String, Integer>) response.data();
//                return result;
//            } catch (ClassCastException e) {
//                System.out.println("Failed to cast response data to Map<String, Integer>");
//                return null;
//            }
//        }
        return null;
    }

    @Override
    public StringBuilder GetTeam_Participants(String numeEchipa) {
//        Request req = new Request.Builder().type(RequestType.PARTICIPANTS_BYTEAM).data(numeEchipa).build();
//        sendRequest(req);
//        Response response = readResponse();
//        if (response.type() == ResponseType.ERROR) {
//            String err = response.data().toString();
//            System.out.println("Ceva eroare la GetTeam_Participants: " + err);
//            return null;
//        }
//        if (response.type() == ResponseType.OK) {
//            try {
//                StringBuilder result = (StringBuilder) response.data();
//                return result;
//            } catch (ClassCastException e) {
//                System.out.println("Failed to cast response data to StringBuilder");
//                return null;
//            }
//        }
        return null;
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

    private void sendRequest(Protobufs.ClientRequest request){
        try {
            System.out.println("Sending request ..."+request);
            //request.writeTo(output);
            request.writeDelimitedTo(output);
            output.flush();
            System.out.println("Request sent.");
        } catch (IOException e) {
            System.out.println("Error sending object "+e);
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

    private class ReaderThread implements Runnable{
        public void run() {
            while(!finished){
                try {
                    Protobufs.ClientResponse response=Protobufs.ClientResponse.parseDelimitedFrom(input);
                    System.out.println("response received into ReaderThread"+response);

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
                    System.out.println("Reading error "+e);
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


