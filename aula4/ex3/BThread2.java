class BThread2 implements Runnable { 
	Thread thrd; 
	static BVetorSoma2 sa = new BVetorSoma2(); 
	int a[];
	int resposta;

	BThread2(String name, int nums[]) {
		thrd = new Thread(this, name);  //Thread recebe parametro desta classe que implementa Runnable
		a = nums;  //Thread inicia com acesso ao recurso compartilhado

		//TODO4
		thrd.start();
	}

	public void run() {
		int sum;

		System.out.println(thrd.getName() + " iniciou.");

		//Acesso ah regiao critica
			resposta = sa.somarVetor(a);
		
		System.out.println("Soma para " + thrd.getName() + " eh " + resposta); 
		System.out.println(thrd.getName() + " terminando.");
	}
}
