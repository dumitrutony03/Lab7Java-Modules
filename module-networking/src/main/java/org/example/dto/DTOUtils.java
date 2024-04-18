package org.example.dto;


import org.example.models.Cursa;
import org.example.models.Echipa;
import org.example.models.Participant;
import org.example.models.PersoanaOficiu;

public class DTOUtils {
    public static PersoanaOficiu getFromDTO(PersoanaOficiuDto persoanaOficiuDto){
        String name=persoanaOficiuDto.getName();
        String pass=persoanaOficiuDto.getPassword();
        return new PersoanaOficiu(name, pass);

    }
    public static PersoanaOficiuDto getDTO(PersoanaOficiu persoanaOficiu){
        String name=persoanaOficiu.getNume();
        String pass=persoanaOficiu.getParola();
        return new PersoanaOficiuDto(name, pass);
    }

    public static Participant getFromDTO(ParticipantDto participantDto){
        String name=participantDto.getNume();
        Echipa echipa=participantDto.getEchipa();
        Cursa cursa=participantDto.getCursa();
        return new Participant(name, echipa, cursa);

    }
    public static ParticipantDto getDTO(Participant participant){
        String name=participant.GetNumeParticipant();
        Echipa echipa=participant.GetEchipa();
        Cursa cursa=participant.GetCursa();
        return new ParticipantDto(name, echipa, cursa);
    }
}

