package org.example.jsonprotocol;

import org.example.dto.PersoanaOficiuDto;

public class Request {
    private RequestType requestType;
    private PersoanaOficiuDto persoanaOficiuDto;
    public RequestType getType() {
        return requestType;
    }

    public void setType(RequestType requestType) {
        this.requestType = requestType;
    }

    public void setPersoanaOficiuDto(PersoanaOficiuDto persoanaOficiuDto) {
        this.persoanaOficiuDto = persoanaOficiuDto;
    }

    public PersoanaOficiuDto getPersoanaOficiuDto() {
        return persoanaOficiuDto;
    }

    @Override
    public String toString() {
        return "RequestDto{" +
                "requestType='" + requestType + '\'' +
                ", persoanaOficiuDto=" + persoanaOficiuDto +
                '}';
    }
}
