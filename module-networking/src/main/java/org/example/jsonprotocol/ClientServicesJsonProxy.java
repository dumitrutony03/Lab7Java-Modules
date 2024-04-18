package org.example.jsonprotocol;

import com.google.gson.Gson;
import org.example.models.PersoanaOficiu;
import org.example.services.IServices;
import org.example.dto.DTOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientServicesJsonProxy implements IServices {
    private String host;
    private int port;
    private BufferedReader input;
    private PrintWriter output;
    private Gson gsonFormatter;
    private Socket connection;

    private BlockingQueue<Response> qresponses;
    private volatile boolean finished;

    public ClientServicesJsonProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses = new LinkedBlockingQueue<Response>();
    }

    public boolean LoginPersoanaOficiu(String username, String password /*PersoanaOficiu persoanaOficiu*/) {
        initializeConnection();

        PersoanaOficiu persoanaOficiu = new PersoanaOficiu(username, password);

        Request req = JsonProtocolUtils.createPersoanaOficiuLoginRequest(persoanaOficiu);
        sendRequest(req);
        Response response = readResponse();

        System.out.println("LoginPersoanaOficiu " + "raspuns citit cu succes");
        if (response.getType() == ResponseType.OK) {
            System.out.println("Persoana Oficiu logata cu succes");
//            this.client=client;
            return true;
        }
        if (response.getType() == ResponseType.ERROR) {
            String err = response.getErrorMessage();
            closeConnection();
            System.out.println("Error when LOGINING PersoanaOficiu " + err);
        }
        return false;
    }

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

    @Override
    public void InscrieParticipant(String numeParticipant, String numeEchipa, String capMotor) {

    }
    public void logout(PersoanaOficiu persoanaOficiu) throws Exception {

        Request req = JsonProtocolUtils.createLogoutRequest(persoanaOficiu);
        sendRequest(req);
        Response response = readResponse();
        closeConnection();
        if (response.getType() == ResponseType.ERROR) {
            String err = response.getErrorMessage();//data().toString();
            throw new Exception(err);
        }
    }

    private void closeConnection() {
        finished = true;
        try {
            input.close();
            output.close();
            connection.close();
//            client=null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendRequest(Request request) {
        String reqLine = gsonFormatter.toJson(request);
        try {
            output.println(reqLine);
            output.flush();
        } catch (Exception e) {
//            throw new Exception("Error sending object " + e);
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
            gsonFormatter=new Gson();
            connection=new Socket(host,port);
            output=new PrintWriter(connection.getOutputStream());
            output.flush();
            input=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            finished=false;
            startReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void startReader(){
        Thread tw=new Thread(new ReaderThread());
        tw.start();
    }

    private class ReaderThread implements Runnable{
        public void run() {
            while(!finished){
                try {
                    String responseLine=input.readLine();
                    System.out.println("response received "+responseLine);
                    Response response=gsonFormatter.fromJson(responseLine, Response.class);
                    if (isUpdate(response)){
                        handleUpdate(response);
                    }else{

                        try {
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


    private void handleUpdate(Response response) {
        if (response.getType() == ResponseType.FRIEND_LOGGED_IN) {

            PersoanaOficiu persoanaOficiu = DTOUtils.getFromDTO(response.getPersoanaOficiuDtos());
            System.out.println("PersoanaOficiu logged in " + persoanaOficiu);
//            try {
//                client.friendLoggedIn(persoanaOficiu);
//            } catch (ClientException e) {
//                e.printStackTrace();
//            }
        }
        if (response.getType() == ResponseType.FRIEND_LOGGED_OUT) {
            PersoanaOficiu persoanaOficiu = DTOUtils.getFromDTO(response.getPersoanaOficiuDtos());
            System.out.println("Friend logged out " + persoanaOficiu);
//            try {
//                client.friendLoggedOut(persoanaOficiu);
//            } catch (ClientException e) {
//                e.printStackTrace();
//            }
        }
//
//        if (response.getType()== ResponseType.NEW_PARTICIPANT){
//            Message message=DTOUtils.getFromDTO(response.getMessage());
//            try {
//                client.messageReceived(message);
//            } catch (ChatException e) {
//                e.printStackTrace();
//            }
//        }
    }

    private boolean isUpdate(Response response) {
        return response.getType() == ResponseType.FRIEND_LOGGED_OUT || response.getType() == ResponseType.FRIEND_LOGGED_IN || response.getType() == ResponseType.NEW_MESSAGE;
    }
}
