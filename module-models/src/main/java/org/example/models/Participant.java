package org.example.models;

public class Participant implements Entitate<Integer> // Use 'int' for integer type in C#
{
    private int id;
    private String nume; // Use lowercase 's' for string type in C#
    private Echipa echipa;
    private Cursa cursa;

    public Participant(String numeParticipant, Echipa echipa, Cursa cursa) {
        this.nume = numeParticipant;
        this.echipa = echipa;
        this.cursa = cursa;
    }

    public void SetNumeParticipant(String numeParticipant) {
        this.nume = numeParticipant;
    }

    public void SetEchipa(Echipa echipa) {
        this.echipa = echipa;
    }

    public Cursa GetCursa() {
        return cursa;
    }

    public void SetCursa(Cursa cursa) {
        this.cursa = cursa;
    }

    public String GetNumeParticipant() {
        return nume;
    }

    public Echipa GetEchipa() {
        return echipa;
    }

    @Override
    public void SetId(Integer id) {
        this.id = id;
    }

    public Integer GetId() {
        return id;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", echipa=" + echipa +
                ", cursa=" + cursa +
                '}';
    }
}