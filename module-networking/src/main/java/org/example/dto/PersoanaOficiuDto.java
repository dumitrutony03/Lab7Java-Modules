package org.example.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class PersoanaOficiuDto implements Serializable {
    private final String name;
    private final String password;

    public PersoanaOficiuDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "PersoanaOficiuDto{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}