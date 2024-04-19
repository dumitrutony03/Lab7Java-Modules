package org.example.dto;

import org.example.models.CapacitateMotor;

import java.io.Serializable;

public class CursaDto implements Serializable {

    private CapacitateMotor capMotor;

    public CursaDto(CapacitateMotor capMotor) {
        this.capMotor = capMotor;
    }

    public CapacitateMotor getCapMotor() {
        return capMotor;
    }

    public void setCapMotor(CapacitateMotor capMotor) {
        this.capMotor = capMotor;
    }

    @Override
    public String toString() {
        return "CursaDto{" +
                "capMotor=" + capMotor +
                '}';
    }
}


