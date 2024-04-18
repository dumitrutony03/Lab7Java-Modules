package org.example.utils;

import org.example.ClientException;
import org.example.models.PersoanaOficiu;

public interface IClientObserver {
//    void messageReceived(Message var1) throws ChatException;

    void friendLoggedIn(PersoanaOficiu persoanaOficiu) throws ClientException;

    void friendLoggedOut(PersoanaOficiu persoanaOficiu) throws ClientException;
}
