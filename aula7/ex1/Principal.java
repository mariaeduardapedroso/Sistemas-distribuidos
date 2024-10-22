/**
 * TODO1: TCPServidor: Permita que o servidor receba varias conexoes
 *        de clientes, sem encerrar a conexao.
 * 
 * TODO2: Principal: Crie 10 (dez) clientes 
 * 
 * TODO3: TCPCliente: Faca que os clientes facam requisicoes automaticas
 *        para o servidor. 
 * 
 */
public class Principal {
    
    public static void main(String [] args){
        
        TCPServidor servidor = new TCPServidor();
        Thread ts = new Thread(servidor);
        ts.start();
        
        int TAM = 10;
        TCPCliente[] lista = new TCPCliente[TAM];
        for(int i=0;i<TAM;i++)
            lista[i] = new TCPCliente();
            
        Thread [] listaTC = new Thread[TAM];
        for(int i=0;i<TAM;i++){
            listaTC[i] = new Thread(lista[i]);
            listaTC[i].start();
        }
        
        
        //Thread tc = new Thread(cliente);
        
        
        //tc.start();
        
    }
    
}
