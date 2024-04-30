package org.example.dto;

import java.io.Serializable;

public class TeamsAndParticipantsDto implements Serializable {
    private String echipa;
    private String participant;

    public TeamsAndParticipantsDto(String echipa, String participant) {
        this.echipa = echipa;
        this.participant = participant;
    }
}
