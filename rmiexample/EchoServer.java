package rmiexample;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz remota que define el contrato del servicio Echo.
 * Solo los métodos definidos aquí pueden ser invocados remotamente.
 */
public interface EchoServer extends Remote {

    /**
     * Método remoto que recibe una cadena y retorna una respuesta.
     *
     * @param cadena Mensaje enviado por el cliente
     * @return Mensaje procesado por el servidor
     * @throws RemoteException Excepción obligatoria en métodos remotos
     */
    public String echo(String cadena) throws RemoteException;
}
