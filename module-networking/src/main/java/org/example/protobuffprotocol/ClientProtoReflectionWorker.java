package org.example.protobuffprotocol;

import org.example.dto.DTOUtils;
import org.example.dto.ParticipantDto;
import org.example.dto.PersoanaOficiuDto;
import org.example.models.Participant;
import org.example.models.PersoanaOficiu;
import org.example.rpcprotocol.Request;
import org.example.rpcprotocol.RequestType;
import org.example.rpcprotocol.Response;
import org.example.rpcprotocol.ResponseType;
import org.example.services.IServices;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ClientProtoReflectionWorker implements Runnable {
    private IServices server;
    private Socket connection;

    private InputStream input;
    private OutputStream output;
    private volatile boolean connected;

    public ClientProtoReflectionWorker(IServices server, Socket connection) {
        this.server = server;
        this.connection = connection;
        try {
            output = connection.getOutputStream();//new ObjectOutputStream(connection.getOutputStream());
            input = connection.getInputStream(); //new ObjectInputStream(connection.getInputStream());
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        while (connected) {
            try {
                // Object request=input.readObject();
                System.out.println("Waiting requests ...");
                Protobufs.ClientRequest request = Protobufs.ClientRequest.parseDelimitedFrom(input);
                System.out.println("Request received: " + request);
                Protobufs.ClientResponse response = handleRequest(request);
                if (response != null) {
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
            System.out.println("Error " + e);
        }
    }

    private static Response okResponse = new Response.Builder().type(ResponseType.OK).build();

    private Protobufs.ClientResponse handleRequest(Protobufs.ClientRequest request) {
        Protobufs.ClientResponse response = null;
//        switch (request.getType()) {
//            case LOGIN: {
//                System.out.println("Login request ...");
////                PersoanaOficiuDto udto = (PersoanaOficiuDto) request.data();
////                PersoanaOficiu persoanaOficiu = DTOUtils.getFromDTO(udto);
//                PersoanaOficiu persoanaOficiu = ProtoUtils.getPersoanaOficiu(request);
//                try {
//                    server.LoginPersoanaOficiu(persoanaOficiu.getNume(), persoanaOficiu.getParola());
//                    return ProtoUtils.createOkResponse();
//                } catch (Exception e) {
//                    connected = false;
//                    return ProtoUtils.createErrorResponse(e.getMessage());
//                }
//            }
//        }

//        if (request.type() == RequestType.LOGIN) {
//            System.out.println("Login request ..." + request.getType());
//            PersoanaOficiuDto udto = (PersoanaOficiuDto) request.getPersoanaOficiuDto();
//            PersoanaOficiu persoanaOficiu = DTOUtils.getFromDTO(udto);
//            try {
//                server.LoginPersoanaOficiu(persoanaOficiu.getNume(), persoanaOficiu.getParola());
//                return okResponse;
//            } catch (Exception e) {
//                connected = false;
//                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//            }
//        }
//        if (request.type() == RequestType.NEW_PARTICIPANT) {
//            System.out.println("NEW PARTICIPANT ADDED ...");
//            ParticipantDto mdto = (ParticipantDto) request.data();
//            Participant participant = DTOUtils.getFromDTO(mdto);
//            try {
//                server.InscrieParticipant(participant.GetNumeParticipant(), participant.GetEchipa().name(), participant.GetCursa().GetCapMotor().name());
//                return okResponse;
//            } catch (Exception e) {
//                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//            }
//        }
//        if (request.type() == RequestType.NR_PARTICIPANTS_BYRACE) {
//            System.out.println("Nr participants by race request ... " + request.type());
//            try {
//                Map<String, Integer> udto = server.GetNumberOfParticipantsByRace();
//                return new Response.Builder().type(ResponseType.OK).data(udto).build();
//            } catch (Exception e) {
//                connected = false;
//                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//            }
//        }
//        if (request.type() == RequestType.PARTICIPANTS_BYTEAM) {
//            System.out.println("Nr participants by race request ... " + request.type());
//            try {
//                StringBuilder udto = server.GetTeam_Participants(request.data().toString());
//                return new Response.Builder().type(ResponseType.OK).data(udto).build();
//            } catch (Exception e) {
//                connected = false;
//                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//            }
//        }
//        if (request.type() == RequestType.LOGOUT) {
//            System.out.println("Logout request");
//            try {
//                server.Logout(request.data().toString());
//                connected = false;
//                return okResponse;
//            } catch (Exception e) {
//                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//            }
//        }
        return response;
    }

    private void sendResponse(Protobufs.ClientResponse response) throws IOException{
        System.out.println("sending response "+response);
        synchronized (output){
            response.writeDelimitedTo(output);
            //output.writeObject(response);
            output.flush();
        }
    }
}
