/**
 * TODO1: UDPServidor: Permita que o servidor receba varias conexoes
 *        de clientes, sem encerrar a conexao.
 * 
 * TODO2: Principal: Crie 10 (dez) clientes 
 * 
 * TODO3: UDPCliente: Faca que os clientes facam requisicoes automaticas
 *        para o servidor. 
 */
public class Principal {

    public static void main(String[] args) {
        UDPServidor servidor = new UDPServidor();
        Thread ts = new Thread(servidor);
        ts.start();

        // Criando 10 clientes
        for (int i = 0; i < 10; i++) {
            UDPCliente cliente = new UDPCliente("Cliente " + (i + 1));
            Thread tc = new Thread(cliente);
            tc.start();
        }
    }
}
