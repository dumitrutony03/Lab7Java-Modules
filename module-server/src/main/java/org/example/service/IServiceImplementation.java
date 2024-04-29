package org.example.service;
import org.example.models.Cursa;
import org.example.models.Echipa;
import org.example.models.Participant;
import org.example.models.PersoanaOficiu;
import org.example.repository.cursa.ICursaRepository;
import org.example.repository.participant.IParticipantRepository;
import org.example.repository.persoanaOficiu.IPersoanaOficiuRepository;
import org.example.services.IServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IServiceImplementation implements IServices {
    public ICursaRepository CursaRepository;
    public IParticipantRepository ParticipantRepository;
    public IPersoanaOficiuRepository PersoanaOficiuRepository;

    public IServiceImplementation(ICursaRepository cursaRepository, IParticipantRepository participantRepository, IPersoanaOficiuRepository persoanaOficiuRepository) {
        CursaRepository = cursaRepository;
        ParticipantRepository = participantRepository;
        PersoanaOficiuRepository = persoanaOficiuRepository;
    }

    public boolean LoginPersoanaOficiu(String username, String password) {
        return (PersoanaOficiuRepository.findByNumeAndParola(username, password) != null);
    }

    public void RegisterPersoanaOficiu(String nume, String parola) {
        PersoanaOficiuRepository.Add(new PersoanaOficiu(nume, parola));
    }

    public Map<String, Integer> GetNumberOfParticipantsByRace() {
        List<Participant> participanti = ParticipantRepository.FindAll();
        Map<String, Integer> numarParticipantiPeCursa = new HashMap<>();

        for (Participant participant : participanti) {
            String cursa = String.valueOf(participant.GetCursa());
            numarParticipantiPeCursa.put(cursa, numarParticipantiPeCursa.getOrDefault(cursa, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : numarParticipantiPeCursa.entrySet()) {
            System.out.println("Cursa " + entry.getKey() + " are " + entry.getValue() + " participanti.");
        }

        return numarParticipantiPeCursa;

    }

    public StringBuilder GetTeam_Participants(String echipa) {
        List<Participant> participanti = ParticipantRepository.FindAll();
        Map<String, List<Participant>> teamParticipants;

        StringBuilder rezultate = new StringBuilder();

        for (Participant participant : participanti) {
            if (participant.GetEchipa().toString().equalsIgnoreCase(echipa)) {
                rezultate.append("Participant: ")
                        .append(participant.GetNumeParticipant())
                        .append(", Cursa: ")
                        .append(participant.GetCursa())
                        .append("\n");
            }
        }
        return rezultate;
    }


    public void InscrieParticipant(String numeParticipant, String numeEchipa, String capMotor) {
        /*
         * Verificam mai intai daca exista aceasta Echipa:= numeEchipa si CapMotor:= capMotor in DB
         */

        System.out.println(numeParticipant + " " + numeEchipa + " " + capMotor);

        Echipa e1 = Echipa.valueOf(numeEchipa); // Conversie din String în enum Echipa
        Cursa c1 = CursaRepository.findCursaByCapMotor(capMotor);
        Participant p1 = new Participant(numeParticipant, e1, c1);

        System.out.println(p1);

        ParticipantRepository.Add(p1);
    }

    @Override
    public synchronized void Logout(String numeParticipant) {
        System.out.println(numeParticipant + " Logged Out!");
    }
}
