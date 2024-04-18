package org.example.utils;

import org.example.rpcprotocol.ClientRpcReflectionWorker;
import org.example.services.IServices;

import java.net.Socket;

public class ClientRpcConcurrentServer extends AbsConcurrentServer {
    private IServices services;
    public ClientRpcConcurrentServer(int port, IServices chatServer) {
        super(port);
        this.services = chatServer;
        System.out.println("Chat- ChatRpcConcurrentServer");
    }

    @Override
    protected Thread createWorker(Socket client) {
        // ChatClientRpcWorker worker=new ChatClientRpcWorker(chatServer, client);
        ClientRpcReflectionWorker worker=new ClientRpcReflectionWorker(services, client);

        Thread tw=new Thread(worker);
        return tw;
    }

    @Override
    public void stop(){
        System.out.println("Stopping services ...");
    }
}
