package br.jsf;

import java.util.Optional;

public class Sala {
    
    private int numero; // Número da sala.
    private Optional<Boolean> reservado; // Status de reserva da sala (reservada ou não).
    private Optional<String> cliente; // Nome do cliente que reservou a sala (se houver).

    /**
     * Construtor principal para inicializar uma sala com o número, status de reserva e nome do cliente.
     * 
     * @param numero O número da sala.
     * @param reservado O status de reserva da sala (true ou false), pode ser null.
     * @param cliente O nome do cliente que fez a reserva, pode ser null.
     */
    public Sala(int numero, Boolean reservado, String cliente) {
        this.numero = numero; // Inicializa o número da sala.
        this.reservado = Optional.ofNullable(reservado); // O status de reserva pode ser null ou um valor booleano.
        this.cliente = Optional.ofNullable(cliente); // O nome do cliente pode ser null ou um nome de cliente.
    }

    /**
     * Construtor simplificado para inicializar apenas o número da sala.
     * O status de reserva e o cliente são inicializados como valores vazios (Optional.empty()).
     * 
     * @param numero O número da sala.
     */
    public Sala(int numero) {
        this.numero = numero; // Inicializa o número da sala.
        this.reservado = Optional.empty(); // Inicializa o status de reserva como vazio (sem valor).
        this.cliente = Optional.empty(); // Inicializa o cliente como vazio (sem nome).
    }

    /**
     * Retorna o número da sala.
     * 
     * @return O número da sala.
     */
    public int getNumero() {
        return numero; // Retorna o número da sala.
    }

    /**
     * Define o número da sala.
     * 
     * @param numero O número da sala a ser definido.
     */
    public void setNumero(int numero) {
        this.numero = numero; // Define o número da sala.
    }

    /**
     * Retorna o status de reserva da sala (reservada ou não).
     * 
     * @return Um Optional<Boolean> que representa o status de reserva. Pode ser vazio se não for definido.
     */
    public Optional<Boolean> isReservado() {
        return reservado; // Retorna o status de reserva da sala.
    }

    /**
     * Define o status de reserva da sala.
     * 
     * @param reservado O novo status de reserva da sala (true ou false), pode ser null.
     */
    public void setReservado(Boolean reservado) {
        this.reservado = Optional.ofNullable(reservado); // Define o status de reserva como o valor informado (pode ser null).
    }

    /**
     * Retorna o nome do cliente que reservou a sala, se houver.
     * 
     * @return Um Optional<String> com o nome do cliente, ou vazio se não houver cliente.
     */
    public Optional<String> getCliente() {
        return cliente; // Retorna o nome do cliente, se presente, ou vazio.
    }

    /**
     * Define o nome do cliente que reservou a sala.
     * 
     * @param cliente O nome do cliente, pode ser null.
     */
    public void setCliente(String cliente) {
        this.cliente = Optional.ofNullable(cliente); // Define o nome do cliente, que pode ser null.
    }
}
