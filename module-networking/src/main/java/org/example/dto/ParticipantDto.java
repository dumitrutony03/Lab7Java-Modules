package org.example.dto;

import org.example.models.Echipa;

import java.io.Serializable;

public class ParticipantDto implements Serializable {
    private String nume;
    private Echipa echipa;
    private CursaDto cursa;

    public ParticipantDto(String nume, Echipa echipa, CursaDto cursa) {
        this.nume = nume;
        this.echipa = echipa;
        this.cursa = cursa;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Echipa getEchipa() {
        return echipa;
    }

    public void setEchipa(Echipa echipa) {
        this.echipa = echipa;
    }

    public CursaDto getCursaDto() {
        return cursa;
    }

    public void setCursaDto(CursaDto cursa) {
        this.cursa = cursa;
    }

    @Override
    public String toString() {
        return "ParticipantDto{" +
                "nume='" + nume + '\'' +
                ", echipa=" + echipa +
                ", cursa=" + cursa +
                '}';
    }
}

