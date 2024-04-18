package org.example.models;

public class Cursa implements Entitate<Integer> {
    private int id;
    private CapacitateMotor capMotor;

    public Cursa(CapacitateMotor capMotor) {
        this.capMotor = capMotor;
    }

    public CapacitateMotor GetCapMotor() {
        return capMotor;
    }

    public void SetCapMotor(CapacitateMotor capMotor) {
        this.capMotor = capMotor;
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
        return "Cursa{" +
                "id=" + id +
                ", capMotor=" + capMotor +
                '}';
    }
}