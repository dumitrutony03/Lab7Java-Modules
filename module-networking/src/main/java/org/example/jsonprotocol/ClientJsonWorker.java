package org.example.jsonprotocol;

import com.google.gson.Gson;
import org.example.dto.DTOUtils;
import org.example.dto.PersoanaOficiuDto;
import org.example.models.PersoanaOficiu;
import org.example.services.IServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientJsonWorker implements Runnable {
    private IServices server;
    private Socket connection;
    private Gson gsonFormatter;
    private BufferedReader input;
    private PrintWriter output;
    private volatile boolean connected;
    public ClientJsonWorker(IServices server, Socket connection) {
        this.server = server;
        this.connection = connection;
        gsonFormatter=new Gson();
        try{
            output=new PrintWriter(connection.getOutputStream());
            input=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            connected=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(connected){
            try {
                String requestLine=input.readLine();
                Request request=gsonFormatter.fromJson(requestLine, Request.class);
                Response response=handleRequest(request);
                if (response!=null){
                    sendResponse(response);
                }
            } catch (IOException e) {
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

    public void LoginPersoanaOficiu(String username, String password) {
        PersoanaOficiu persoanaOficiu = new PersoanaOficiu(username, password);

        Response resp= JsonProtocolUtils.createPersoanaOficiuLoggedInResponse(persoanaOficiu);
        System.out.println("Friend logged in "+persoanaOficiu);
        try {
            sendResponse(resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void persoanaOficiuLoggedOut(PersoanaOficiu persoanaOficiu) {

        Response resp=JsonProtocolUtils.createPersoanaOficiuLoggedOutResponse(persoanaOficiu);
        System.out.println("Friend logged out "+persoanaOficiu);
        try {
            sendResponse(resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Response okResponse=JsonProtocolUtils.createOkResponse();

    private Response handleRequest(Request request){
        Response response=null;
        if (request.getType()== RequestType.LOGIN){
            System.out.println("Login request ..."+request.getType());
            PersoanaOficiuDto udto=request.getPersoanaOficiuDto();
            PersoanaOficiu persoanaOficiu= DTOUtils.getFromDTO(udto);
            try {
                server.LoginPersoanaOficiu(persoanaOficiu.getNume(), persoanaOficiu.getParola());
                return okResponse;
            }
            catch (Exception e) {
                connected=false;
                return JsonProtocolUtils.createErrorResponse(e.getMessage());
            }
        }

                //// Urmeaza sa fie implementat

//        if (request.getType()== RequestType.LOGOUT){
//            System.out.println("Logout request");
//
//            UserDTO udto=request.getUser();
//            User user=DTOUtils.getFromDTO(udto);
//            try {
//                server.logout(user, this);
//                connected=false;
//                return okResponse;
//
//            } catch (ChatException e) {
//                return JsonProtocolUtils.createErrorResponse(e.getMessage());
//            }
//        }

//        if (request.getType()== RequestType.NEW_PARTICIPANT){
//            System.out.println("SendMessageRequest ...");
//            MessageDTO mdto=(MessageDTO)request.getMessage();
//            Message message=DTOUtils.getFromDTO(mdto);
//            try {
//                server.sendMessage(message);
//                return okResponse;
//            } catch (ChatException e) {
//                return JsonProtocolUtils.createErrorResponse(e.getMessage());
//            }
//        }
        return response;
    }

    private void sendResponse(Response response) throws IOException {
        ResponseType responseLine=response.getType();
        System.out.println("sending response "+responseLine);
        synchronized (output) {
            output.println(responseLine);
            output.flush();
        }
    }
}