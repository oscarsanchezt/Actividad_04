import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    final static String HOST = "localhost";
    final static int PUERTO = 5000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Socket socket = new Socket(HOST, PUERTO);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                System.out.println("--- Menu ---");
                System.out.println("1) Piedra\n2) Papel\n3) Tijera");
                System.out.println("Opcion: ");
                int opcion = scanner.nextInt();
                out.writeInt(opcion);

                String resultado = in.readUTF();
                System.out.println("Resultado: " + resultado);

                if (resultado.equals("Ganaste") || resultado.equals("Perdiste")) {
                    break;  
                }
            }

            socket.close();
        } catch (Exception e) {
            System.out.println("Error...");
        }
    }
}

