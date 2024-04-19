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
        sendRequest(req);
        Response response = readResponse();
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
    public void InscrieParticipant(String numeParticipant, String numeEchipa, String capMotor) {
        initializeConnection();

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

//    public void sendMessage(Message message) throws ChatException {
//        MessageDTO mdto = DTOUtils.getDTO(message);
//        Request req = new Request.Builder().type(RequestType.SEND_MESSAGE).data(mdto).build();
//        sendRequest(req);
//        Response response = readResponse();
//        if (response.type() == ResponseType.ERROR) {
//            String err = response.data().toString();
//            throw new ChatException(err);
//        }
//    }

//    public void logout(User user, IChatObserver client) throws ChatException {
//        UserDTO udto = DTOUtils.getDTO(user);
//        Request req = new Request.Builder().type(RequestType.LOGOUT).data(udto).build();
//        sendRequest(req);
//        Response response = readResponse();
//        closeConnection();
//        if (response.type() == ResponseType.ERROR) {
//            String err = response.data().toString();
//            throw new ChatException(err);
//        }
//    }


//    public User[] getLoggedFriends(User user) throws ChatException {
//        UserDTO udto = DTOUtils.getDTO(user);
//        Request req = new Request.Builder().type(RequestType.GET_LOGGED_FRIENDS).data(udto).build();
//        sendRequest(req);
//        Response response = readResponse();
//        if (response.type() == ResponseType.ERROR) {
//            String err = response.data().toString();
//            throw new ChatException(err);
//        }
//        UserDTO[] frDTO = (UserDTO[]) response.data();
//        User[] friends = DTOUtils.getFromDTO(frDTO);
//        return friends;
//    }

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
            System.out.println("handleUpdate -- FRIEND_LOGGED_IN => PersoanaOficiu logged in");

            PersoanaOficiu persoanaOficiu = DTOUtils.getFromDTO((PersoanaOficiuDto) response.data());
            System.out.println("Friend logged in " + persoanaOficiu);
            try {
                System.out.println("Persoana oficiu logged in!");
//                client.friendLoggedIn(persoanaOficiu);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        if (response.type() == ResponseType.FRIEND_LOGGED_OUT) {
//            User friend = DTOUtils.getFromDTO((UserDTO) response.data());
//            System.out.println("Friend logged out " + friend);
//            try {
//                client.friendLoggedOut(friend);
//            } catch (ChatException e) {
//                e.printStackTrace();
//            }
//        }
//
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
    }

    private boolean isUpdate(Response response) {
        return response.type() == ResponseType.FRIEND_LOGGED_OUT || response.type() == ResponseType.PERSOANAOFICIU_LOGGED_IN || response.type() == ResponseType.NEW_PARTICIPANT;
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

    @Override
    public Map<String, Integer> GetNumberOfParticipantsByRace() {
        return null;
    }

    @Override
    public StringBuilder GetTeam_Participants(String echipa) {
        return null;
    }
}
