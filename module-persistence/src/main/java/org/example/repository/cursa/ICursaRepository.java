package org.example.repository.cursa;

import org.example.models.Cursa;
import org.example.repository.IRepositoryCRUD;

public interface ICursaRepository extends IRepositoryCRUD<Integer, Cursa> {
    public Cursa findCursaByCapMotor(String capMotor);
}
