package rmiexample;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementación concreta del servidor Echo.
 * Esta clase contiene la lógica real del servicio remoto.
 */
public class EchoServerImpl implements EchoServer {

    /**
     * Constructor del servidor.
     * Se encarga de exportar el objeto remoto y publicarlo en el rmiregistry.
     *
     * @param ipRMIregistry IP donde corre el rmiregistry
     * @param puertoRMIregistry Puerto del rmiregistry
     * @param nombreDePublicacion Nombre con el que se publica el servicio
     */
    public EchoServerImpl(String ipRMIregistry,
                          int puertoRMIregistry,
                          String nombreDePublicacion) {
        try {
            // Exporta el objeto para que pueda recibir llamadas remotas
            EchoServer echoServer =
                    (EchoServer) UnicastRemoteObject.exportObject(this, 0);

            // Obtiene una referencia al rmiregistry
            Registry registry =
                    LocateRegistry.getRegistry(ipRMIregistry, puertoRMIregistry);

            // Publica el objeto remoto con un nombre
            registry.rebind(nombreDePublicacion, echoServer);

            System.out.println("Echo server ready...");

        } catch (Exception e) {
            System.err.println("Echo server exception:");
            e.printStackTrace();
        }
    }

    /**
     * Implementación del método remoto echo.
     * Se ejecuta en el servidor.
     */
    @Override
    public String echo(String cadena) throws RemoteException {
        return "desde el servidor: " + cadena;
    }

    /**
     * Método principal que arranca el servidor.
     */
    public static void main(String[] args) {
        new EchoServerImpl("127.0.0.1", 23000, "echoServer");
    }
}
