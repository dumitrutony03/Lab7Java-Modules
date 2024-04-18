package org.example.services;

import java.util.Map;

public interface IServices {
    public boolean LoginPersoanaOficiu(String username, String password);

    public void RegisterPersoanaOficiu(String nume, String parola);

    public Map<String, Integer> GetNumberOfParticipantsByRace();

    public StringBuilder GetTeam_Participants(String echipa);

    public void InscrieParticipant(String numeParticipant, String numeEchipa, String capMotor);
}
