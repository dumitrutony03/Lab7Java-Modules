package org.example.utils;


import org.example.protobuffprotocol.ClientProtoReflectionWorker;
import org.example.services.IServices;

import java.net.Socket;

public class ClientProtobuffConcurrentServer extends AbsConcurrentServer {
    private IServices chatServer;
    public ClientProtobuffConcurrentServer(int port, IServices chatServer) {
        super(port);
        this.chatServer = chatServer;
        System.out.println("Chat- ChatProtobuffConcurrentServer");
    }

    @Override
    protected Thread createWorker(Socket client) {
        ClientProtoReflectionWorker worker=new ClientProtoReflectionWorker(chatServer,client);
        Thread tw=new Thread(worker);
        return tw;
    }
}
