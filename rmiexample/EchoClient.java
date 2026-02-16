package rmiexample;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Cliente RMI que consume el servicio Echo.
 */
public class EchoClient {

    /**
     * Método que localiza el servicio remoto e invoca el método echo.
     *
     * @param ipRmiregistry IP del rmiregistry
     * @param puertoRmiRegistry Puerto del rmiregistry
     * @param nombreServicio Nombre del servicio remoto
     */
    public void ejecutaServicio(String ipRmiregistry,
                                int puertoRmiRegistry,
                                String nombreServicio) {
        try {
            // Se conecta al rmiregistry
            Registry registry =
                    LocateRegistry.getRegistry(ipRmiregistry, puertoRmiRegistry);

            // Busca el servicio remoto por nombre (obtiene el stub)
            EchoServer echoServer =
                    (EchoServer) registry.lookup(nombreServicio);

            // Invoca el método remoto
            System.out.println(
                    echoServer.echo("Hola como estas?")
            );

        } catch (Exception e) {
            System.err.println("Hay un problema:");
            e.printStackTrace();
        }
    }

    /**
     * Método principal del cliente.
     */
    public static void main(String[] args) {
        EchoClient ec = new EchoClient();
        ec.ejecutaServicio("127.0.0.1", 23000, "echoServer");
    }
}
