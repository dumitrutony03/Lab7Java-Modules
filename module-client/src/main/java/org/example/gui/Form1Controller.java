package org.example.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.example.models.PersoanaOficiu;
import org.example.services.IServices;

public class Form1Controller {

    private IServices service;
    private Form2Controller form2Controller;
    private PersoanaOficiu persoanaOficiu;
    Parent mainChatParent;

    @FXML
    private TextField username;

    @FXML
    private TextField password;


    // Constructor gol pentru FXML
    public Form1Controller() {
        System.out.println("Form1 Controller");
//        connectToServer();
    }

    // Metoda pentru a seta serviciul după inițializare
    public void setServer(IServices service) {
        this.service = service;
    }

    public void setForm2Controller(Form2Controller form2Controller) {
        this.form2Controller = form2Controller;
    }

    public void setParent(Parent p) {
        mainChatParent = p;
    }

    @FXML
    public void handleLoginButtonAction(ActionEvent actionEvent) {

        String name = username.getText().trim();
        String pass = password.getText().trim();

        if (name.isEmpty() || pass.isEmpty()) {
            showAlert("Eroare", "Cel puțin un câmp este gol!");
            return;
        }

        persoanaOficiu = new PersoanaOficiu(name, pass);


        try {
            service.LoginPersoanaOficiu(persoanaOficiu.getNume(), persoanaOficiu.getParola());
            // Util.writeLog("User succesfully logged in "+crtUser.getId());
            Stage stage = new Stage();
            stage.setTitle("Chat Window for " + persoanaOficiu.getNume());
            stage.setScene(new Scene(mainChatParent));

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.out.println("Persoana Oficiu LOGATA CU SUCCES");
                    form2Controller.logout();
                    System.exit(0);
                }
            });

            stage.show();
            form2Controller.setPersoanaOficiu(persoanaOficiu);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("MPP chat");
            alert.setHeaderText("Authentication failure");
            alert.setContentText("Wrong username or password");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleRegisterButtonAction(ActionEvent event) throws Exception {
        String name = username.getText().trim();
        String pass = password.getText().trim();

        if (name.isEmpty() || pass.isEmpty()) {
            showAlert("Eroare", "Cel puțin un câmp este gol!");
            return;
        }

        // Verificam daca persoana exista in DB, altfel, o inregistram.
        boolean exists = service.LoginPersoanaOficiu(name, pass);
        if (!exists) {
            service.RegisterPersoanaOficiu(name, pass);
            showAlert("Succes", "Înregistrare reușita!");
            clearFields();
        } else {
            showAlert("Eroare", "Utilizator existent. Te rog să te autentifici!");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        username.clear();
        password.clear();
    }

    // Presupunând că ai o metodă pentru deschiderea următoarei ferestre
    private void openNextWindow(ActionEvent event) {
        try { // Load the second form (Form2.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Form2.fxml"));
            Parent root = loader.load();

            // Optionally pass the service to Form2Controller
            Form2Controller form2Controller = loader.getController();
            form2Controller.setserver(service); // Assuming you have a setService method in Form2Controller
            form2Controller.setPersoanaOficiu(persoanaOficiu);

            // Get the current stage (window) from the event, happening upon button click
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the current stage
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception as you see fit
        }
    }
}
