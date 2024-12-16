package br.ejb;

/**
 * Implementa uma trava manual para gerenciar acessos concorrentes a recursos compartilhados.
 * Utiliza sincronização para garantir exclusividade.
 * 
 * @author Maria Eduarda Pedroso
 */
class ManualLock {

    /**
     * Indica se o recurso está bloqueado.
     */
    private boolean isLocked = false;

    /**
     * Referência à thread que atualmente possui o bloqueio.
     */
    private Thread lockingThread = null;

    /**
     * Tenta adquirir o bloqueio para o recurso.
     * 
     * @return {@code true} se o bloqueio foi adquirido com sucesso, {@code false} caso contrário.
     */
    public synchronized boolean tryLock() {
        if (!isLocked) {
            isLocked = true;
            lockingThread = Thread.currentThread();
            return true;
        }
        return false;
    }

    /**
     * Libera o bloqueio do recurso.
     * Apenas a thread que possui o bloqueio pode liberá-lo.
     * 
     * @throws IllegalMonitorStateException se uma thread diferente tentar liberar a trava.
     */
    public synchronized void unlock() {
        if (Thread.currentThread() == lockingThread) {
            isLocked = false;
            lockingThread = null;
            notifyAll();
        } else {
            throw new IllegalMonitorStateException("Thread diferente está tentando liberar a trava.");
        }
    }

    /**
     * Verifica se o recurso está bloqueado.
     * 
     * @return {@code true} se o recurso está bloqueado, {@code false} caso contrário.
     */
    public synchronized boolean isLocked() {
        return isLocked;
    }
}
