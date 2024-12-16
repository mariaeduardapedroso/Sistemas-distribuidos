package br.ejb;

import br.jsf.Sala;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gerenciador de bloqueios para recursos como salas.
 * Garante que múltiplas operações em uma mesma sala não sejam realizadas simultaneamente.
 * 
 * @author Maria Eduarda Pedroso
 */
public class LockManager {

    /**
     * Mapa que associa cada sala a um bloqueio manual.
     */
    private final Map<Integer, ManualLock> locks = new HashMap<>();

    /**
     * Construtor que inicializa os bloqueios para os recursos fornecidos.
     * 
     * @param resources Lista de objetos {@link Sala} que devem ser gerenciados.
     */
    public LockManager(List<Sala> resources) {
        for (Sala sala : resources) {
            locks.put(sala.getNumero(), new ManualLock()); // Usa o número da sala como chave no mapa.
        }
    }

    /**
     * Tenta adquirir o bloqueio para uma sala específica.
     * 
     * @param salaId Identificador único da sala.
     * @return {@code true} se o bloqueio foi adquirido com sucesso, {@code false} caso contrário.
     */
    public boolean acquireLock(int salaId) {
        ManualLock lock = locks.get(salaId);
        if (lock != null) {
            return lock.tryLock();
        }
        return false;
    }

    /**
     * Libera o bloqueio de uma sala específica.
     * 
     * @param salaId Identificador único da sala.
     */
    public void releaseLock(int salaId) {
        ManualLock lock = locks.get(salaId);
        if (lock != null) {
            lock.unlock();
        }
    }
}
