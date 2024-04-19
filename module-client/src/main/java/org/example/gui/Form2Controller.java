package org.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.models.PersoanaOficiu;
import org.example.services.IServices;

import java.util.Map;

public class Form2Controller {
    @FXML
    private TextField txtEchipa;
    @FXML
    private ListView<String> listBox1;
    @FXML
    private TextField txtNumeParticipant;
    @FXML
    private TextField txtNumeEchipa;
    @FXML
    private TextField txtCapMotor;

    private IServices service;
    private PersoanaOficiu persoanaOficiu;

    // Constructor gol pentru FXML
    public Form2Controller() {
        System.out.println("Form2 CONSTRUCTOR!");
    }

    // Metoda pentru a seta serviciul după inițializare
    public void setserver(IServices service) {
        this.service = service;
    }
    public void setPersoanaOficiu(PersoanaOficiu persoanaOficiu) {
        this.persoanaOficiu = persoanaOficiu;
    }

    public void onShowClick(ActionEvent event) {
        Map<String, Integer> participantsByRace = service.GetNumberOfParticipantsByRace();

        for (Map.Entry<String, Integer> entry : participantsByRace.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + " participants");
        }

        listBox1.getItems().clear();
        participantsByRace.forEach((race, count) -> listBox1.getItems().add(race + " - " + count + " participants"));
    }

    @FXML
    public javafx.scene.control.TextArea textAreaRezultate;

    @FXML
    protected void onEchipaParticipantiClick(ActionEvent event) {
        String team = txtEchipa.getText();
        StringBuilder participantsByTeam = service.GetTeam_Participants(team);
        textAreaRezultate.setText(participantsByTeam.toString());
    }

    @FXML
    protected void onInscrieParticipantClick(ActionEvent event) {
        String participantName = txtNumeParticipant.getText();
        String teamName = txtNumeEchipa.getText();
        String capMotor = txtCapMotor.getText();
        service.InscrieParticipant(participantName, teamName, capMotor);
    }

    @FXML
    protected void onLogoutClick(ActionEvent actionEvent) {
        logout();
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
//        System.out.println(persoanaOficiu.getNume());
//        openNextWindow(actionEvent);
    }

    void logout() {
        try {
            service.Logout(persoanaOficiu.getNume());
        } catch (Exception e) {
            System.out.println("Logout error " + e);
        }
    }

    private void openNextWindow(ActionEvent event) {
        try { // Load the second form (Form2.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Form1.fxml"));
            Parent root = loader.load();

            // Optionally pass the service to Form2Controller
            Form1Controller form1Controller = loader.getController();
            form1Controller.setServer(service); // Assuming you have a setService method in Form2Controller

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
