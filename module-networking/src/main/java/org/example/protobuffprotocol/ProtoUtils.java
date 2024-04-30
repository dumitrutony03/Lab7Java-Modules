package org.example.protobuffprotocol;

import org.example.dto.CursaDto;
import org.example.dto.DTOUtils;
import org.example.dto.ParticipantDto;
import org.example.dto.PersoanaOficiuDto;
import org.example.models.Cursa;
import org.example.models.Participant;
import org.example.models.PersoanaOficiu;

public class ProtoUtils {

    // REQUESTSSSSSSSSSSSS
    public static Protobufs.ClientRequest createLoginRequest(PersoanaOficiu persoanaOficiu) {
        Protobufs.PersoanaOficiuDto persoanaOficiuDto = Protobufs.PersoanaOficiuDto.newBuilder().setName(persoanaOficiu.getNume()).setPassword(persoanaOficiu.getParola()).build();
        Protobufs.ClientRequest request = Protobufs.ClientRequest.newBuilder().setType(Protobufs.ClientRequest.Type.LOGIN)
                .setUser(persoanaOficiuDto).build();
        return request;
    }

    public static Protobufs.ClientRequest createLogoutRequest(String name) {
//        Protobufs.PersoanaOficiuDto persoanaOficiuDto=Protobufs.PersoanaOficiuDto.newBuilder().setName(persoanaOficiu.getNume()).setPassword(persoanaOficiu.getParola()).build();
        Protobufs.ClientRequest request = Protobufs.ClientRequest.newBuilder().setType(Protobufs.ClientRequest.Type.LOGOUT)
                .setPersoanaOficiuName(name).build();
        return request;
    }

    public static Protobufs.ClientRequest createNewParticipantRequest(String numePatrticipant, String numeEchipa, String numeCursa) {

        Protobufs.ClientRequest request = Protobufs.ClientRequest.newBuilder()
                .setType(Protobufs.ClientRequest.Type.NEW_PARTICIPANT)
                .setNumeParticipant(numePatrticipant).setNumeEchipa(numeEchipa).setNumeCursa(numeCursa)
                .build();
        return request;
    }

    public static Protobufs.ClientRequest createNR_PARTICIPANTS_BYRACERequest() {
        Protobufs.ClientRequest request = Protobufs.ClientRequest.newBuilder()
                .setType(Protobufs.ClientRequest.Type.NR_PARTICIPANTS_BYRACE)
                .build();
        return request;
    }
}


