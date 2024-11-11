/**
 * TODO 1: Compartilhado.java: Crie um atributo 'valor' 
 * TODO 2: Consumidor.java: Leia o atributo 'valor' e mostre na tela
 * TODO 3: Produtor.java: Escreva o atributo 'valor' e mostre na tela
 * TODO 4: Crie 10 (dez) threads 'Produtor' e 10 (dez) threads 'Consumidor'
 */
public class Principal {
		
	public static void main(String args[]) {
        Compartilhado recurso = new Compartilhado();

        // Arrays para armazenar as threads
        int TAM = 10;
        Produtor[] produtores = new Produtor[TAM];
        Consumidor[] consumidores = new Consumidor[TAM];

        // Criando e iniciando 10 threads Produtor
        for (int i = 0; i < TAM; i++) {
            produtores[i] = new Produtor("P" + (i), recurso);
        }

        // Criando e iniciando 10 threads Consumidor
        for (int i = 0; i < TAM; i++) {
            consumidores[i] = new Consumidor("C" + (i), recurso);
        }

        // Esperando todas as threads terminarem
        try {
            for (int i = 0; i < TAM; i++) {
                produtores[i].thrd.join();
                consumidores[i].thrd.join();
            }
        } catch (InterruptedException exc) {
            System.out.println("Thread principal interrompida.");
        }

        System.out.println("Done.");
		/*
		Produtor t1 = new Produtor("Thread1",recurso); 
		Consumidor t2 = new Consumidor("Thread2",recurso);
		
		try { 
			t1.thrd.join(); 
			t2.thrd.join();
		} catch(InterruptedException exc) { 
			System.out.println("Thread principal interrompida.");
		}
		*/
	}

    
}//fim classe

