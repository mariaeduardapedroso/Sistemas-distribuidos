 // Peer.java - Modificado para atender os itens 2, 3 e 4
 
 /**
  * Lab05: Sistema P2P
  * 
  * Autor: Lucio A. Rocha
  * Ultima atualizacao: 12/12/2024
  * Por: Maria Eduarda Pedroso
  * 
  * Referencias: 
  * https://docs.oracle.com/javase/tutorial/essential/io
  * http://fortunes.cat-v.org/
  */
 
 import java.rmi.RemoteException;
 import java.rmi.registry.LocateRegistry;
 import java.rmi.registry.Registry;
 import java.rmi.server.UnicastRemoteObject;
 import java.security.SecureRandom;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;
 
 public class Peer implements IMensagem {
 
	 ArrayList<PeerLista> alocados;
 
	 public Peer() {
		 alocados = new ArrayList<>();
	 }
 
	 @Override
	 public Mensagem enviar(Mensagem mensagem) throws RemoteException {
		 Mensagem resposta;
		 try {
			 System.out.println("Mensagem recebida: " + mensagem.getMensagem());
			 resposta = new Mensagem(parserJSON(mensagem.getMensagem()));
		 } catch (Exception e) {
			 e.printStackTrace();
			 resposta = new Mensagem("{\n" + "\"result\": false\n" + "}");
		 }
		 return resposta;
	 }
 
	 public String parserJSON(String json) {
		 String result = "false";
		 String fortune = "-1";
 
		 String[] v = json.split(":");
		 String[] v1 = v[1].split("\"");
		 if (v1[1].equals("write")) {
			 String[] p = json.split("\\[");
			 String[] p1 = p[1].split("]");
			 String[] p2 = p1[0].split("\"");
			 fortune = p2[1];
 
			 Principal pv2 = new Principal();
			 pv2.write(fortune);
		 } else if (v1[1].equals("read")) {
			 Principal pv2 = new Principal();
			 fortune = pv2.read();
		 }
 
		 result = "{\n" + "\"result\": \"" + fortune + "\"" + "}";
		 return result;
	 }
 
	 public void iniciar() {
 
		 try {
			 List<PeerLista> listaPeers = new ArrayList<>();
			 for (PeerLista peer : PeerLista.values())
				 listaPeers.add(peer);
 
			 Registry servidorRegistro;
			 try {
				 servidorRegistro = LocateRegistry.createRegistry(1099);
			 } catch (java.rmi.server.ExportException e) {
				 System.out.print("Registro já iniciado. Usar o ativo.\n");
			 }
			 servidorRegistro = LocateRegistry.getRegistry();
			 String[] listaAlocados = servidorRegistro.list();
 
			 for (int i = 0; i < listaAlocados.length; i++)
				 System.out.println(listaAlocados[i] + " ativo.");
 
			 Scanner scanner = new Scanner(System.in);
			 System.out.println("Digite o nome do Peer para se conectar (PEER1, PEER2, PEER3, PEER4): ");
			 String escolha = scanner.nextLine();
 
			 if (!PeerLista.isPeerAtivo(escolha)) {
				 if (PeerLista.adicionarPeer(escolha)) {
					 IMensagem skeleton = (IMensagem) UnicastRemoteObject.exportObject(this, 0);
					 servidorRegistro.rebind(escolha, skeleton);
					 System.out.print(escolha + " Servidor RMI: Aguardando conexões...");
				 } else {
					 System.out.println("Falha ao adicionar o peer.");
				 }
			 } else {
				 System.out.println("Peer já está ativo.");
			 }
 
			 PeerLista.listarPeersAtivos();
			 new ClienteRMI().iniciarCliente();
 
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
 
	 }
 
	 public static void main(String[] args) {
		 Peer servidor = new Peer();
		 servidor.iniciar();
	 }
 }
 