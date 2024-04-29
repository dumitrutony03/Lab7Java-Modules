package org.example;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.gui.Form1Controller;
import org.example.gui.Form2Controller;
import org.example.jsonprotocol.ClientServicesJsonProxy;
import org.example.protobuffprotocol.ClientServicesProtoProxy;
import org.example.rpcprotocol.ClientServicesRpcProxy;
import org.example.services.IServices;

import java.io.IOException;
import java.util.Properties;

public class Main extends Application {
    private static int defaultChatPort = 55555;
    private static String defaultServer = "localhost";

    @Override
    public void start(Stage stage) throws IOException {

        System.out.println("In start");
        Properties clientProps = new Properties();
        try {
            clientProps.load(Main.class.getResourceAsStream("/client.properties"));
            System.out.println("Client properties set. ");
            clientProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find client.properties " + e);
            return;
        }
        String serverIP = clientProps.getProperty("server.host", defaultServer);
        int serverPort = defaultChatPort;

        try {
            serverPort = Integer.parseInt(clientProps.getProperty("server.port"));
        } catch (NumberFormatException ex) {
            System.err.println("Wrong port number " + ex.getMessage());
            System.out.println("Using default port: " + defaultChatPort);
        }
        System.out.println("Using server IP " + serverIP);
        System.out.println("Using server port " + serverPort);

//        IServices server = new ClientServicesJsonProxy(serverIP, serverPort);
//        IServices server = new ClientServicesRpcProxy(serverIP, serverPort);
        IServices server = new ClientServicesProtoProxy(serverIP, serverPort);


        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource("./Form1.fxml"));
        Parent root=loader.load();

        Form1Controller ctrl =
                loader.<Form1Controller>getController();
        ctrl.setServer(server);


        FXMLLoader cloader = new FXMLLoader(
                getClass().getClassLoader().getResource("./Form2.fxml"));
        Parent croot=cloader.load();


        Form2Controller chatCtrl =
                cloader.<Form2Controller>getController();
        chatCtrl.setserver(server);

        ctrl.setForm2Controller(chatCtrl);
        ctrl.setParent(croot);

        stage.setTitle("MPP chat");
        stage.setScene(new Scene(root, 300, 130));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}