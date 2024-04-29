package org.example.protobuffprotocol;

import org.example.dto.PersoanaOficiuDto;
import org.example.models.PersoanaOficiu;

public class ProtoUtils {

    // REQUESTSSSSSSSSSSSS
    public static Protobufs.ClientRequest createLoginRequest(PersoanaOficiu persoanaOficiu){
        Protobufs.PersoanaOficiuDto persoanaOficiuDto=Protobufs.PersoanaOficiuDto.newBuilder().setName(persoanaOficiu.getNume()).setPassword(persoanaOficiu.getParola()).build();
        Protobufs.ClientRequest request= Protobufs.ClientRequest.newBuilder().setType(Protobufs.ClientRequest.Type.LOGIN)
                .setUser(persoanaOficiuDto).build();
        return request;
    }

    public static Protobufs.ClientRequest createLogoutRequest(String name){
//        Protobufs.PersoanaOficiuDto persoanaOficiuDto=Protobufs.PersoanaOficiuDto.newBuilder().setName(persoanaOficiu.getNume()).setPassword(persoanaOficiu.getParola()).build();
        Protobufs.ClientRequest request= Protobufs.ClientRequest.newBuilder().setType(Protobufs.ClientRequest.Type.LOGOUT)
                .setPersoanaOficiuName(name).build();
        return request;
    }

                        ////////////////// RESPONSES
    public static Protobufs.ClientResponse createOkResponse(){
        Protobufs.ClientResponse response=Protobufs.ClientResponse.newBuilder()
                .setType(Protobufs.ClientResponse.Type.OK).build();
        return response;
    }

    public static Protobufs.ClientResponse createErrorResponse(String text){
        Protobufs.ClientResponse response=Protobufs.ClientResponse.newBuilder()
                .setType(Protobufs.ClientResponse.Type.ERROR)
                .setError(text).build();
        return response;
    }


    public static PersoanaOficiu getPersoanaOficiu(Protobufs.ClientRequest request){
        PersoanaOficiu persoanaOficiu=new PersoanaOficiu();
        persoanaOficiu.setNume(request.getUser().getName());
        persoanaOficiu.setParola(request.getUser().getPassword());
        return persoanaOficiu;
    }
//
//    public static String getError(Protobufs.ClientResponse response){
//        String errorMessage=response.getError();
//        return errorMessage;
//    }

}




