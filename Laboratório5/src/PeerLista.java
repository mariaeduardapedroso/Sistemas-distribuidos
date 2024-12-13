// PeerLista.java - Modificado para atender o item 1, 2

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

 import java.util.HashSet;

public enum PeerLista {
    PEER1 {
        @Override
        public String getNome() {
            return "PEER1";
        }        
    },
    PEER2 {
        public String getNome() {
            return "PEER2";
        }        
    },
    PEER3 {
        public String getNome() {
            return "PEER3";
        }        
    },
    PEER4 {
        public String getNome() {
            return "PEER4";
        }        
    };

    private static HashSet<String> peersAtivos = new HashSet<>(); // Mantém o controle dos peers registrados

    public String getNome() {
        return "NULO";
    }

    // Método para adicionar um peer ativo
    public static boolean adicionarPeer(String peer) {
        if (!peersAtivos.contains(peer)) {
            peersAtivos.add(peer);
            System.out.println(peer + " adicionado com sucesso.");
            return true;
        }
        System.out.println(peer + " já está ativo.");
        return false;
    }

    // Método para remover um peer ativo
    public static void removerPeer(String peer) {
        if (peersAtivos.remove(peer)) {
            System.out.println(peer + " foi removido.");
        } else {
            System.out.println(peer + " não estava ativo.");
        }
    }

    // Método para listar os peers ativos
    public static void listarPeersAtivos() {
        System.out.println("Peers ativos: " + peersAtivos);
    }

    public static boolean isPeerAtivo(String peer) {
        return peersAtivos.contains(peer);
    }
}