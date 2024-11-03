// Author: Lucio A. Rocha
// Last update: 02/11/2024
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class UDPCliente implements Runnable {

    private static final int PORTA = 1234;
    private String nomeCliente;

    public UDPCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    // Job
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress IPAdress = InetAddress.getByName("localhost");

            byte[] dadosRecebidos = new byte[128];
            byte[] dadosEnviados = new byte[128];

            // Simulando requisições automáticas
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                String mensagemEnvio = nomeCliente + " - Mensagem " + (i + 1);
                System.out.println(nomeCliente + " enviando: [" + mensagemEnvio + "]");

                dadosEnviados = mensagemEnvio.getBytes();
                DatagramPacket pacoteEnvio = new DatagramPacket(dadosEnviados, dadosEnviados.length, IPAdress, PORTA);
                socket.send(pacoteEnvio);

                DatagramPacket pacoteResposta = new DatagramPacket(dadosRecebidos, dadosRecebidos.length);
                socket.receive(pacoteResposta);

                String mensagemRetorno = new String(pacoteResposta.getData()).trim();
                System.out.println("RESPOSTA DO SERVIDOR para " + nomeCliente + ": " + mensagemRetorno);

                // Pausa aleatória entre as requisições automáticas
                Thread.sleep(random.nextInt(3000) + 1000);
            }

            socket.close();
            System.out.println(nomeCliente + " finalizado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
