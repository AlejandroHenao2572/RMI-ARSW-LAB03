package rmichat;

import java.rmi.RemoteException;

/**
 * Implementación del servicio de chat.
 * Este objeto se publica en el rmiregistry.
 */
public class ChatServiceImpl implements ChatService {

    private final String nombreUsuario;

    public ChatServiceImpl(String nombreUsuario) throws RemoteException {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Método remoto que se ejecuta cuando llega un mensaje.
     */
    @Override
    public void recibirMensaje(String mensaje) throws RemoteException {
        System.out.println("\n[Mensaje recibido] " + mensaje);
        System.out.print("> ");
    }
}
