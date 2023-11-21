import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    final static int PUERTO = 5000;

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                Socket socket = servidor.accept();
                System.out.println("Cliente conectado desde: " + socket.getInetAddress() + ":" + socket.getPort());

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                int opcionCliente = in.readInt();
                int opcionServidor = (int) (Math.random() * 3) + 1;

                String resultado = obtenerResultado(opcionCliente, opcionServidor);
                out.writeUTF(resultado);

                
                System.out.println("Opción Cliente: " + opcionCliente);
                System.out.println("Opción Servidor: " + opcionServidor);
                System.out.println("Resultado: " + resultado);

               
                if (resultado.equals("Ganaste") || resultado.equals("Perdiste")) {
                    socket.close();
                    break;  
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error en el servidor...");
        }
    }

    private static String obtenerResultado(int opcionCliente, int opcionServidor) {
        if (opcionCliente == opcionServidor) {
            return "Empate";
        } else if ((opcionCliente == 1 && opcionServidor == 3) ||
                   (opcionCliente == 2 && opcionServidor == 1) ||
                   (opcionCliente == 3 && opcionServidor == 2)) {
            return "Ganaste";
        } else {
            return "Perdiste";
        }
    }
}

