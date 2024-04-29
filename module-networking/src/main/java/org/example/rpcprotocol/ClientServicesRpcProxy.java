package org.example.rpcprotocol;

import org.example.dto.CursaDto;
import org.example.dto.DTOUtils;
import org.example.dto.ParticipantDto;
import org.example.dto.PersoanaOficiuDto;
import org.example.models.*;
import org.example.services.IServices;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientServicesRpcProxy implements IServices {
    private String host;
    private int port;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Response> qresponses;
    private volatile boolean finished;

    public ClientServicesRpcProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses = new LinkedBlockingQueue<Response>();
    }

    @Override
    public boolean LoginPersoanaOficiu(String name, String password) {
        initializeConnection();

        PersoanaOficiu persoanaOficiu = new PersoanaOficiu(name, password);

        PersoanaOficiuDto udto = DTOUtils.getDTO(persoanaOficiu);
        Request req = new Request.Builder().type(RequestType.LOGIN).data(udto).build();
        System.out.println("Urmeaza sa trimitem un request RPC PROXY");
        sendRequest(req);
        System.out.println("Request trimis din UI PROXY");
        Response response = readResponse();
        System.out.println("Raspuns primit din SERVER PROXY");
        if (response.type() == ResponseType.OK) {
            System.out.println("Raspuns OK pentru LOGIN - PersoanaOficiu");
            return true;
        }
        if (response.type() == ResponseType.ERROR) {
            String err = response.data().toString();
            closeConnection();
//            throw new ChatException(err);
            System.out.println("EROARE persoanaOficiuLoggedIn: " + err);
        }
        return false;
    }

    @Override
    public void Logout(String numeParticipant) {
        Request req = new Request.Builder().type(RequestType.LOGOUT).data(numeParticipant).build();
        sendRequest(req);
        Response response = readResponse();
        closeConnection();
        if (response.type() == ResponseType.OK) {
            System.out.println("Raspuns OK pentru LOGOUT - PersoanaOficiu");
        } else if (response.type() == ResponseType.ERROR) {
            String err = response.data().toString();
            System.out.println("EROARE LOGOUT PERSOANA OFICIU: " + err);
        }
    }

    @Override
    public void InscrieParticipant(String numeParticipant, String numeEchipa, String capMotor) {
//        initializeConnection();

        Echipa e1 = Echipa.valueOf(numeEchipa); // Conversie din String în enum Echipa
        Cursa c1 = new Cursa(CapacitateMotor.valueOf(capMotor));
        CursaDto c1Dto = DTOUtils.getDTO(c1);
        ParticipantDto participantDto = new ParticipantDto(numeParticipant, e1, c1Dto);

//        ParticipantDto udto = DTOUtils.getDTO(participant);
        System.out.println("participantdto: " + participantDto);

        Request req = new Request.Builder().type(RequestType.NEW_PARTICIPANT).data(participantDto).build();
        sendRequest(req);
        Response response = readResponse();

        if (response.type() == ResponseType.OK) {
            System.out.println("Raspuns OK pentru LOGIN - PersoanaOficiu");
        }
        if (response.type() == ResponseType.ERROR) {
            String err = response.data().toString();
            closeConnection();
//            throw new ChatException(err);
            System.out.println("EROARE persoanaOficiuLoggedIn: " + err);
        }
    }

    @Override
    public Map<String, Integer> GetNumberOfParticipantsByRace() {
        Request req = new Request.Builder().type(RequestType.NR_PARTICIPANTS_BYRACE).build();
        sendRequest(req);
        Response response = readResponse();
        if (response.type() == ResponseType.ERROR) {
            String err = response.data().toString();
            System.out.println("Ceva eroare la GetNumberOfParticipantsByRace: " + err);
            return null;
        }
        if (response.type() == ResponseType.OK) {
            try {
                Map<String, Integer> result = (Map<String, Integer>) response.data();
                return result;
            } catch (ClassCastException e) {
                System.out.println("Failed to cast response data to Map<String, Integer>");
                return null;
            }
        }
        return null;
    }

    @Override
    public StringBuilder GetTeam_Participants(String numeEchipa) {
        Request req = new Request.Builder().type(RequestType.PARTICIPANTS_BYTEAM).data(numeEchipa).build();
        sendRequest(req);
        Response response = readResponse();
        if (response.type() == ResponseType.ERROR) {
            String err = response.data().toString();
            System.out.println("Ceva eroare la GetTeam_Participants: " + err);
            return null;
        }
        if (response.type() == ResponseType.OK) {
            try {
                StringBuilder result = (StringBuilder) response.data();
                return result;
            } catch (ClassCastException e) {
                System.out.println("Failed to cast response data to StringBuilder");
                return null;
            }
        }
        return null;
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

    private void sendRequest(Request request) {
        try {
            output.writeObject(request);
            output.flush();
        } catch (IOException e) {
//            throw new ChatException("Error sending object " + e);
            System.out.println(e.getMessage());
        }

    }

    private Response readResponse() {
        Response response = null;
        try {

            response = qresponses.take();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void initializeConnection() {
        try {
            connection = new Socket(host, port);
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            finished = false;
            startReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startReader() {
        Thread tw = new Thread(new ReaderThread());
        tw.start();
    }


    private void handleUpdate(Response response) {
        if (response.type() == ResponseType.PERSOANAOFICIU_LOGGED_IN) {
            System.out.println("handleUpdate -- PERSOANAOFICIU_LOGGED_IN => PersoanaOficiu logged in");

            PersoanaOficiu persoanaOficiu = DTOUtils.getFromDTO((PersoanaOficiuDto) response.data());
            System.out.println("Friend logged in " + persoanaOficiu);
            try {
                System.out.println("Persoana oficiu logged in!");
//                client.friendLoggedIn(persoanaOficiu);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (response.type() == ResponseType.PERSOANAOFICIU_LOGGED_OUT) {
            System.out.println("handleUpdate -- PERSOANAOFICIU_LOGGED_OUT => PersoanaOficiu logged out");

            String name = response.data().toString();
            System.out.println("PERSOANAOFICIU_LOGGED_OUT " + name);
            try {
                System.out.println("Persoana oficiu logged out!");
//                client.friendLoggedIn(persoanaOficiu);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (response.type() == ResponseType.NEW_PARTICIPANT) {
            System.out.println("Suntem pe handleUpdate");

            Participant message = DTOUtils.getFromDTO((ParticipantDto) response.data());
            try {
                System.out.println("Participant nou adaugat!");
//                client.messageReceived(message);
            } catch (Exception e) {
                System.out.println("EROARE ADAUGARE PARTICIPANT NOU");
                e.printStackTrace();
            }
        }
        if (response.type() == ResponseType.NR_PARTICIPANTS_BYRACE) {
            System.out.println("Suntem pe handleUpdate");

            // N am trimis niciun fel de data la request
//            Participant message = DTOUtils.getFromDTO((ParticipantDto) response.data());
            try {
                System.out.println("Afisam pentru fiecare cursa, numarul de participanti!");
//                client.messageReceived(message);
            } catch (Exception e) {
                System.out.println("EROARE AFISAREA NRULUI DE PARTICIPANTI PT FIECARE CURSA");
                e.printStackTrace();
            }
        }
    }

    private boolean isUpdate(Response response) {
        return response.type() == ResponseType.PERSOANAOFICIU_LOGGED_OUT || response.type() == ResponseType.PERSOANAOFICIU_LOGGED_IN || response.type() == ResponseType.NEW_PARTICIPANT;
    }

    private class ReaderThread implements Runnable {
        public void run() {
            while (!finished) {
                try {
                    Object response = input.readObject();
                    System.out.println("response received " + response);
                    if (isUpdate((Response) response)) {
                        handleUpdate((Response) response);
                    } else {

                        try {
                            qresponses.put((Response) response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Reading error " + e);
                } catch (ClassNotFoundException e) {
                    System.out.println("Reading error " + e);
                }
            }
        }
    }


    //// TO BE IMPLEMENTED
    @Override
    public void RegisterPersoanaOficiu(String nume, String parola) {

    }
}
