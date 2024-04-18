package org.example.jsonprotocol;

import org.example.dto.PersoanaOficiuDto;

import java.io.Serializable;

public class Response implements Serializable {
    private ResponseType responseType;
    private String message;

    public String getErrorMessage() {
        return message;
    }

    public void setErrorMessage(String message) {
        this.message = message;
    }

    private PersoanaOficiuDto persoanaOficiuDtos;

    public Response(){}

    public void setType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public void setPersoanaOficiuDtos(PersoanaOficiuDto persoanaOficiuDtos) {
        this.persoanaOficiuDtos = persoanaOficiuDtos;
    }

    public ResponseType getType() {
        return responseType;
    }

    public PersoanaOficiuDto getPersoanaOficiuDtos() {
        return persoanaOficiuDtos;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "responseType='" + responseType + '\'' +
                ", message='" + message + '\'' +
                ", persoanaOficiuDtos=" + persoanaOficiuDtos +
                '}';
    }
}
