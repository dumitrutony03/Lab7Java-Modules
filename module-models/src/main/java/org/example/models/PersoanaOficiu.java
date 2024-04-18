package org.example.models;

public class PersoanaOficiu implements Entitate<Integer> // Use 'int' for integer type in C#
{
    private int id;
    private String nume; // Use lowercase 's' for string type in C#
    private String parola;

    public PersoanaOficiu(String numeUtilizator, String parola) {
        this.nume = numeUtilizator;
        this.parola = parola;
    }

    // Getters și setters (same in C#)
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public String toString() {
        return "PersoanaOficiu{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }

    @Override
    public void SetId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer GetId() {
        return id;
    }
}