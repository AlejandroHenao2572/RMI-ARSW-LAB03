package rmichat;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz remota del chat.
 * Define los métodos que pueden invocarse remotamente.
 */
public interface ChatService extends Remote {

    /**
     * Método remoto para recibir un mensaje.
     *
     * @param mensaje Mensaje enviado por el otro cliente
     * @throws RemoteException
     */
    void recibirMensaje(String mensaje) throws RemoteException;
}
