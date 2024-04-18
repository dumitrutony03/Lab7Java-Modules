package org.example.jsonprotocol;


import org.example.models.PersoanaOficiu;
import org.example.dto.DTOUtils;

/*
    *Cream obiecte de tip Response sau Request, pe care le pasam mai departe
    *
 */

public class JsonProtocolUtils {
    public static Response createPersoanaOficiuLoggedInResponse(PersoanaOficiu persoanaOficiu){
        Response resp=new Response();
        resp.setType(ResponseType.FRIEND_LOGGED_IN);
        resp.setPersoanaOficiuDtos(DTOUtils.getDTO(persoanaOficiu));
        return resp;
    }

    public static Response createPersoanaOficiuLoggedOutResponse(PersoanaOficiu persoanaOficiu){
        Response resp=new Response();
        resp.setType(ResponseType.FRIEND_LOGGED_OUT);
        resp.setPersoanaOficiuDtos(DTOUtils.getDTO(persoanaOficiu));
        return resp;
    }

    public static Response createOkResponse(){
        Response resp=new Response();
        resp.setType(ResponseType.OK);
        return resp;
    }

    public static Response createErrorResponse(String errorMessage){
        Response resp=new Response();
        resp.setType(ResponseType.ERROR);
        resp.setErrorMessage(errorMessage);
        return resp;
    }

    public static Request createPersoanaOficiuLoginRequest(PersoanaOficiu persoanaOficiu){
        Request req=new Request();
        req.setType(RequestType.LOGIN);
        req.setPersoanaOficiuDto(DTOUtils.getDTO(persoanaOficiu));

        System.out.println("createLoginRequest " + DTOUtils.getDTO(persoanaOficiu).toString());
        return req;
    }

    public static Request createLogoutRequest(PersoanaOficiu persoanaOficiu){
        Request req=new Request();
        req.setType(RequestType.LOGOUT);
        req.setPersoanaOficiuDto(DTOUtils.getDTO(persoanaOficiu));

        return req;
    }
}
