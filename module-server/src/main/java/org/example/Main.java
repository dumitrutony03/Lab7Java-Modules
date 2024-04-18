package org.example;

import org.example.repository.cursa.CursaRepository;
import org.example.repository.cursa.ICursaRepository;
import org.example.repository.participant.IParticipantRepository;
import org.example.repository.participant.ParticipantRepository;
import org.example.repository.persoanaOficiu.IPersoanaOficiuRepository;
import org.example.repository.persoanaOficiu.PersoanaOficiuRepository;
import org.example.utils.AbstractServer;
import org.example.service.IServiceImplementation;
import org.example.services.IServices;
import org.example.utils.ClientJsonConcurrentServer;
import org.example.utils.ClientRpcConcurrentServer;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.Properties;

public class Main {
    private static int defaultPort=55555;
    public static void main(String[] args) {
        Properties props=new Properties();
        try {
            props.load(Main.class.getResourceAsStream("/app.properties"));
            System.out.println("Server properties set. ");
            props.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find server.properties "+e);
            return;
        }
        ICursaRepository cursaRepository = new CursaRepository(props);
        IParticipantRepository participantRepository = new ParticipantRepository(props);
        IPersoanaOficiuRepository persoanaOficiuRepository = new PersoanaOficiuRepository(props);
        IServices mainService = new IServiceImplementation(cursaRepository, participantRepository, persoanaOficiuRepository);

        int serverPort=defaultPort;
        try {
            serverPort = Integer.parseInt(props.getProperty("server.port"));
        }catch (NumberFormatException nef){
            System.err.println("Wrong  Port Number"+nef.getMessage());
            System.err.println("Using default port "+defaultPort);
        }
        System.out.println("Starting server on port: "+serverPort);


//        AbstractServer server = new ClientJsonConcurrentServer(serverPort, mainService);
        AbstractServer server = new ClientRpcConcurrentServer(serverPort, mainService);

        try {
            server.start();
        } catch (ServerException e) {
            System.err.println("Error starting the server" + e.getMessage());
        }

    }
}
