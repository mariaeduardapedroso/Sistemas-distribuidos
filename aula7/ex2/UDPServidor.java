// Author: Lucio A. Rocha
// Last update: 02/11/2024
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServidor implements Runnable {

    private static final int PORTA = 1234;

    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(PORTA);
            System.out.println("Servidor UDP iniciado na porta: " + PORTA);

            byte[] dadosEnviados = new byte[128];
            byte[] dadosRecebidos = new byte[128];

            while (true) {
                System.out.println("Aguardando conexões...");

                DatagramPacket pacoteRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length);
                socket.receive(pacoteRecebido);

                String mensagemRecebida = new String(pacoteRecebido.getData()).trim();
                System.out.println("Mensagem recebida: [" + mensagemRecebida + "]");

                InetAddress IPAdress = pacoteRecebido.getAddress();
                int porta = pacoteRecebido.getPort();

                String novaMensagem = new String("[[" + mensagemRecebida + "]]");
                dadosEnviados = novaMensagem.getBytes();

                DatagramPacket pacoteEnviado = new DatagramPacket(dadosEnviados, dadosEnviados.length, IPAdress, porta);
                socket.send(pacoteEnviado);

                System.out.println("Servidor enviou resposta para o cliente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
