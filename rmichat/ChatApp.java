package rmichat;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * Aplicación principal del Chat RMI.
 * Cada instancia actúa como cliente y servidor.
 */
public class ChatApp {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            // Datos locales
            System.out.print("Ingrese su nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Puerto local para publicar el servicio: ");
            int puertoLocal = scanner.nextInt();
            scanner.nextLine();

            // Crear y exportar objeto remoto
            ChatServiceImpl chatLocal = new ChatServiceImpl(nombre);
            ChatService stubLocal =
                    (ChatService) UnicastRemoteObject.exportObject(chatLocal, 0);

            // Crear rmiregistry local
            Registry registryLocal =
                    LocateRegistry.createRegistry(puertoLocal);

            registryLocal.rebind("ChatService", stubLocal);

            System.out.println("Servicio de chat publicado en el puerto " + puertoLocal);

            // Datos del cliente remoto
            System.out.print("IP del otro cliente: ");
            String ipRemota = scanner.nextLine();

            System.out.print("Puerto del otro cliente: ");
            int puertoRemoto = scanner.nextInt();
            scanner.nextLine();

            // Conexión al cliente remoto
            Registry registryRemoto =
                    LocateRegistry.getRegistry(ipRemota, puertoRemoto);

            ChatService chatRemoto =
                    (ChatService) registryRemoto.lookup("ChatService");

            System.out.println("Conectado al chat remoto. Escriba mensajes:");

            // Bucle de chat
            while (true) {
                System.out.print("> ");
                String mensaje = scanner.nextLine();

                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("Saliendo del chat...");
                    break;
                }

                chatRemoto.recibirMensaje(nombre + ": " + mensaje);
            }

        } catch (Exception e) {
            System.err.println("Error en el chat:");
            e.printStackTrace();
        }
    }
}
