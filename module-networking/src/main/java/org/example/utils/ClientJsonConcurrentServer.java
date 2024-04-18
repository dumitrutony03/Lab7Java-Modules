package org.example.utils;

import org.example.jsonprotocol.ClientJsonWorker;
import org.example.services.IServices;

import java.net.Socket;

public class ClientJsonConcurrentServer extends AbsConcurrentServer {
    private IServices services;
    public ClientJsonConcurrentServer(int port, IServices services) {
        super(port);
        this.services = services;
        System.out.println("Client- ClientJsonConcurrentServer");
    }

    @Override
    protected Thread createWorker(Socket client) {
        ClientJsonWorker worker=new ClientJsonWorker(services, client);

        Thread tw=new Thread(worker);
        return tw;
    }
}