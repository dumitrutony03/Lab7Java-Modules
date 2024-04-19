package org.example.dto;


import org.example.models.*;

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
        CursaDto cursaDto=participantDto.getCursaDto();
        Cursa cursa = DTOUtils.getFromDTO(cursaDto);
        return new Participant(name, echipa, cursa);

    }
    public static ParticipantDto getDTO(Participant participant){
        String name=participant.GetNumeParticipant();
        Echipa echipa=participant.GetEchipa();
        Cursa cursa=participant.GetCursa();
        CursaDto cursaDto=DTOUtils.getDTO(cursa);
        return new ParticipantDto(name, echipa, cursaDto);
    }

    public static Cursa getFromDTO(CursaDto cursaDto){
        CapacitateMotor capMotor=cursaDto.getCapMotor();
        return new Cursa(capMotor);

    }
    public static CursaDto getDTO(Cursa cursa){
        CapacitateMotor capMotor=cursa.GetCapMotor();
        return new CursaDto(capMotor);
    }
}

