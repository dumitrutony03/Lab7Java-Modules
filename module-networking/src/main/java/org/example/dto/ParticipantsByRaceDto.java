package org.example.dto;

import java.io.Serializable;

public class ParticipantsByRaceDto implements Serializable {
    private String cursa;
    private int nrParticipanti;

    public ParticipantsByRaceDto(String cursa, int nrParticipanti) {
        this.cursa = cursa;
        this.nrParticipanti = nrParticipanti;
    }
}
