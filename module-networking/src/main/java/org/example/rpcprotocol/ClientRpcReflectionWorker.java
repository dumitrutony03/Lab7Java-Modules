package org.example.rpcprotocol;

import org.example.dto.DTOUtils;
import org.example.dto.ParticipantDto;
import org.example.dto.PersoanaOficiuDto;
import org.example.models.Participant;
import org.example.models.PersoanaOficiu;
import org.example.services.IServices;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;


public class ClientRpcReflectionWorker implements Runnable {
    private IServices server;
    private Socket connection;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;
    public ClientRpcReflectionWorker(IServices server, Socket connection) {
        this.server = server;
        this.connection = connection;
        try{
            output=new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input=new ObjectInputStream(connection.getInputStream());
            connected=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(connected){
            try {
                Object request=input.readObject();
                Response response=handleRequest((Request)request);
                if (response!=null){
                    sendResponse(response);
                }
            } catch (IOException | ClassNotFoundException e) {
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
            System.out.println("Error "+e);
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

    public void LoginPersoanaOficiu(PersoanaOficiu persoanaOficiu) {
        PersoanaOficiuDto udto= DTOUtils.getDTO(persoanaOficiu);
        Response resp=new Response.Builder().type(ResponseType.PERSOANAOFICIU_LOGGED_IN).data(udto).build();
        System.out.println("Friend logged in "+persoanaOficiu);
        try {
            sendResponse(resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void friendLoggedOut(User friend) throws ChatException {
//        UserDTO udto=DTOUtils.getDTO(friend);
//        Response resp=new Response.Builder().type(ResponseType.FRIEND_LOGGED_OUT).data(udto).build();
//        System.out.println("Friend logged out "+friend);
//        try {
//            sendResponse(resp);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private static Response okResponse=new Response.Builder().type(ResponseType.OK).build();

    private Response handleRequest(Request request){
        Response response=null;
        String handlerName="handle"+(request).type();
        System.out.println("HandlerName "+handlerName);
        try {
            Method method=this.getClass().getDeclaredMethod(handlerName, Request.class);
            response=(Response)method.invoke(this,request);
            System.out.println("Method "+handlerName+ " invoked");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return response;
    }

    private Response handleLOGIN(Request request){
        System.out.println("Login request ..."+request.type());
        PersoanaOficiuDto udto=(PersoanaOficiuDto) request.data();
        PersoanaOficiu user=DTOUtils.getFromDTO(udto);
        try {
            server.LoginPersoanaOficiu(user.getNume(), user.getParola());
            return okResponse;
        } catch (Exception e) {
            connected=false;
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

//    private Response handleLOGOUT(Request request){
//        System.out.println("Logout request...");
//        UserDTO udto=(UserDTO)request.data();
//        User user=DTOUtils.getFromDTO(udto);
//        try {
//            server.logout(user, this);
//            connected=false;
//            return okResponse;
//
//        } catch (ChatException e) {
//            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//        }
//    }
    private Response handleNEW_PARTICIPANT(Request request){
            System.out.println("SendMessageRequest ...");
            ParticipantDto mdto=(ParticipantDto)request.data();
            Participant participant=DTOUtils.getFromDTO(mdto);
            try {
                server.InscrieParticipant(participant.GetNumeParticipant(), participant.GetEchipa().name(), participant.GetCursa().GetCapMotor().name());
                return okResponse;
            } catch (Exception e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
    }

    private void sendResponse(Response response) throws IOException{
        System.out.println("sending response "+response);
        synchronized (output) {
            output.writeObject(response);
            output.flush();
        }
    }
}
