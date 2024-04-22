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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;


public class ClientRpcReflectionWorker implements Runnable {
    private IServices server;
    private Socket connection;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;

    public ClientRpcReflectionWorker(IServices server, Socket connection) {
        this.server = server;
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

    private static Response okResponse = new Response.Builder().type(ResponseType.OK).build();

    private Response handleRequest(Request request) {
        Response response = null;
        if (request.type() == RequestType.LOGIN) {
            System.out.println("Login request ..." + request.type());
            PersoanaOficiuDto udto = (PersoanaOficiuDto) request.data();
            PersoanaOficiu persoanaOficiu = DTOUtils.getFromDTO(udto);
            try {
                server.LoginPersoanaOficiu(persoanaOficiu.getNume(), persoanaOficiu.getParola());
                return okResponse;
            } catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type() == RequestType.NEW_PARTICIPANT) {
            System.out.println("NEW PARTICIPANT ADDED ...");
            ParticipantDto mdto = (ParticipantDto) request.data();
            Participant participant = DTOUtils.getFromDTO(mdto);
            try {
                server.InscrieParticipant(participant.GetNumeParticipant(), participant.GetEchipa().name(), participant.GetCursa().GetCapMotor().name());
                return okResponse;
            } catch (Exception e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type() == RequestType.NR_PARTICIPANTS_BYRACE) {
            System.out.println("Nr participants by race request ... " + request.type());
            try {
                Map<String, Integer> udto = server.GetNumberOfParticipantsByRace();
                return new Response.Builder().type(ResponseType.OK).data(udto).build();
            } catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type() == RequestType.PARTICIPANTS_BYTEAM) {
            System.out.println("Nr participants by race request ... " + request.type());
            try {
                StringBuilder udto = server.GetTeam_Participants(request.data().toString());
                return new Response.Builder().type(ResponseType.OK).data(udto).build();
            } catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type() == RequestType.LOGOUT) {
            System.out.println("Logout request");
            try {
                server.Logout(request.data().toString());
                connected = false;
                return okResponse;
            } catch (Exception e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        return response;
    }

    private void sendResponse(Response response) throws IOException {
        System.out.println("sending response " + response);
        synchronized (output) {
            output.writeObject(response);
            output.flush();
        }
    }



    /*public void run() {
        while (connected) {
            try {
                Object request = input.readObject();
                Response response = handleRequest((Request) request);
                if (response != null) {
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
            System.out.println("Error " + e);
        }
    }

    private static Response okResponse = new Response.Builder().type(ResponseType.OK).build();

    private Response handleRequest(Request request) {
        Response response = null;
        String handlerName = "handle" + (request).type();
        System.out.println("HandlerName " + handlerName);
        try {
            Method method = this.getClass().getDeclaredMethod(handlerName, Request.class);
            response = (Response) method.invoke(this, request);
            System.out.println("Method " + handlerName + " invoked");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return response;
    }

    private void sendResponse(Response response) throws IOException {
        System.out.println("sending response " + response);
        synchronized (output) {
            output.writeObject(response);
            output.flush();
        }
    }*/

    //    private Response handleLOGIN(Request request) {
//        System.out.println("Login request ..." + request.type());
//        PersoanaOficiuDto udto = (PersoanaOficiuDto) request.data();
//        PersoanaOficiu user = DTOUtils.getFromDTO(udto);
//        try {
//            server.LoginPersoanaOficiu(user.getNume(), user.getParola());
//            return okResponse;
//        } catch (Exception e) {
//            connected = false;
//            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//        }
//    }
//
//    public Response handleLOGOUT(Request request) {
//        try {
//            server.Logout(request.data().toString());  // Assume this logs the user out
//            return new Response.Builder().type(ResponseType.OK).data("Logged out successfully").build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//        } finally {
//            connected = false;  // Update connection status after response
//        }
//    }
//
//    private Response handleNEW_PARTICIPANT(Request request) {
//        System.out.println("SendMessageRequest ...");
//        ParticipantDto mdto = (ParticipantDto) request.data();
//        Participant participant = DTOUtils.getFromDTO(mdto);
//        try {
//            server.InscrieParticipant(participant.GetNumeParticipant(), participant.GetEchipa().name(), participant.GetCursa().GetCapMotor().name());
//            return okResponse;
//        } catch (Exception e) {
//            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//        }
//    }
//
//    private Response handleNR_PARTICIPANTS_BYRACE(Request request) {
//
//        System.out.println("Nr participants by race request ... " + request.type());
//        try {
//            Map<String, Integer> udto = server.GetNumberOfParticipantsByRace();
//            return new Response.Builder().type(ResponseType.OK).data(udto).build();
//        } catch (Exception e) {
//            connected = false;
//            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//        }
//    }
//
//    private Response handlePARTICIPANTS_BYTEAM(Request request) {
//
//        System.out.println("Nr participants by race request ... " + request.type());
//        try {
//            StringBuilder udto = server.GetTeam_Participants(request.data().toString());
//            return new Response.Builder().type(ResponseType.OK).data(udto).build();
//        } catch (Exception e) {
//            connected = false;
//            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//        }
//    }
}
