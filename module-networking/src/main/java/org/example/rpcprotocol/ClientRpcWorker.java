package org.example.rpcprotocol;

import org.example.dto.DTOUtils;
import org.example.dto.ParticipantDto;
import org.example.dto.PersoanaOficiuDto;
import org.example.models.Participant;
import org.example.models.PersoanaOficiu;
import org.example.services.IServices;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;


public class ClientRpcWorker implements Runnable {
    private Socket connection;

    private IServices services;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;

    public ClientRpcWorker(IServices services, Socket connection) {
        this.services = services;
        this.connection = connection;
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (connected) {
            try {
                Object request = input.readObject();
                Response response = handleRequest((Request) request);
                if (response != null) {
                    sendResponse(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

//    public void messageReceived(Message message) throws ChatException {
//        MessageDTO mdto= DTOUtils.getDTO(message);
//        Response resp=new Response.Builder().type(ResponseType.NEW_MESSAGE).data(mdto).build();
//        System.out.println("Message received  "+message);
//        try {
//            sendResponse(resp);
//        } catch (IOException e) {
//            throw new ChatException("Sending error: "+e);
//        }
//    }

//    public void friendLoggedIn(User friend) throws ChatException {
//        UserDTO udto= DTOUtils.getDTO(friend);
//        Response resp=new Response.Builder().type(ResponseType.FRIEND_LOGGED_IN).data(udto).build();
//        System.out.println("Friend logged in "+friend);
//        try {
//            sendResponse(resp);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void LoginPersoanaOficiu(PersoanaOficiu persoanaOficiu) {
        PersoanaOficiuDto udto = DTOUtils.getDTO(persoanaOficiu);
        Response resp = new Response.Builder().type(ResponseType.PERSOANAOFICIU_LOGGED_IN).data(udto).build();
        System.out.println("Friend logged in " + persoanaOficiu);
        try {
            sendResponse(resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void friendLoggedOut(User friend) throws ChatException {
//        UserDTO udto= DTOUtils.getDTO(friend);
//        Response resp=new Response.Builder().type(ResponseType.FRIEND_LOGGED_OUT).data(udto).build();
//        System.out.println("Friend logged out "+friend);
//        try {
//            sendResponse(resp);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private static Response okResponse = new Response.Builder().type(ResponseType.OK).build();

    private Response handleRequest(Request request) {
        Response response = null;
        if (request.type() == RequestType.LOGIN) {
            System.out.println("Login request ..." + request.type());
            PersoanaOficiuDto udto = (PersoanaOficiuDto) request.data();
            PersoanaOficiu persoanaOficiu = DTOUtils.getFromDTO(udto);
            try {
                services.LoginPersoanaOficiu(persoanaOficiu.getNume(), persoanaOficiu.getParola());
                return okResponse;
            } catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
//        if (request.type()== RequestType.LOGOUT){
//            System.out.println("Logout request");
//           // LogoutRequest logReq=(LogoutRequest)request;
//            UserDTO udto=(UserDTO)request.data();
//            User user= DTOUtils.getFromDTO(udto);
//            try {
//                server.logout(user, this);
//                connected=false;
//                return okResponse;
//
//            } catch (ChatException e) {
//                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//            }
//        }
        if (request.type() == RequestType.NEW_PARTICIPANT) {
            System.out.println("NEW PARTICIPANT ADDED ...");
            ParticipantDto mdto = (ParticipantDto) request.data();
            Participant participant = DTOUtils.getFromDTO(mdto);
            try {
                services.InscrieParticipant(participant.GetNumeParticipant(), participant.GetEchipa().name(), participant.GetCursa().GetCapMotor().name());
                return okResponse;
            } catch (Exception e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type() == RequestType.NR_PARTICIPANTS_BYRACE) {
            System.out.println("Nr participants by race request ... " + request.type());
            try {
                Map<String, Integer> udto = services.GetNumberOfParticipantsByRace();
                return new Response.Builder().type(ResponseType.OK).data(udto).build();
            } catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type() == RequestType.PARTICIPANTS_BYTEAM) {
            System.out.println("Nr participants by race request ... " + request.type());
            try {
                StringBuilder udto = services.GetTeam_Participants(request.data().toString());
                return new Response.Builder().type(ResponseType.OK).data(udto).build();
            } catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type() == RequestType.LOGOUT) {
            System.out.println("Logout request");
            try {
                services.Logout(request.data().toString());
                connected = false;
                return okResponse;
            } catch (Exception e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }

//        if (request.type()== RequestType.GET_LOGGED_FRIENDS){
//            System.out.println("GetLoggedFriends Request ...");
//            UserDTO udto=(UserDTO)request.data();
//            User user= DTOUtils.getFromDTO(udto);
//            try {
//                User[] friends=server.getLoggedFriends(user);
//                UserDTO[] frDTO= DTOUtils.getDTO(friends);
//                return new Response.Builder().type(ResponseType.GET_LOGGED_FRIENDS).data(frDTO).build();
//            } catch (ChatException e) {
//                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//            }
//        }
        return response;
    }

    private void sendResponse(Response response) throws IOException {
        System.out.println("sending response " + response);
        synchronized (output) {
            output.writeObject(response);
            output.flush();
        }
    }
}
