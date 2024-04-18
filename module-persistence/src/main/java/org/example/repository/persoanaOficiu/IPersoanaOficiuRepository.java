package org.example.repository.persoanaOficiu;

import org.example.models.PersoanaOficiu;
import org.example.repository.IRepositoryCRUD;

public interface IPersoanaOficiuRepository extends IRepositoryCRUD<Integer, PersoanaOficiu> {
    PersoanaOficiu findByNumeAndParola(String nume, String parola);

    /*
    Pentru login, ați putea genera un token când se apelează metoda de login
    și apoi să trimiteți token-ul pe fiecare request.
    Dar posibil să va spună doamna Grigoreta la curs cum ar vrea dansa să faceți.
    Ce e sigur e ca nu îți trebuie fieldul respectiv
     */
}
