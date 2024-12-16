/*
 * Classe que representa uma reserva de sala em um sistema de agendamento.
 */
package br.jsf;

import java.time.LocalDateTime;
import java.util.Optional;

public class Reserva {

    private Sala sala; // A sala que está sendo reservada.
    private String dataHora; // Data e hora da reserva.

    /**
     * Construtor para a criação de uma nova reserva.
     * 
     * @param sala A sala que será reservada.
     * @param dataHora Data e hora da reserva.
     */
    public Reserva(Sala sala, String dataHora) {
        this.sala = sala;
        this.dataHora = dataHora;
    }

    /**
     * Retorna o ID da sala associada à reserva.
     * 
     * @return O número (ID) da sala.
     */
    public int getSalaId() {
        return sala.getNumero(); // Retorna o número da sala associada à reserva.
    }

    /**
     * Retorna o nome do usuário associado à reserva, se houver.
     * 
     * @return Um objeto Optional contendo o nome do usuário, ou um Optional vazio se não houver usuário.
     */
    public Optional<String> getUsuario() {
        return sala.getCliente(); // Retorna o cliente associado à sala, se houver.
    }

    /**
     * Retorna a data e hora da reserva.
     * 
     * @return A data e hora da reserva em formato String.
     */
    public String getDataHora() {
        return dataHora; // Retorna a data e hora da reserva.
    }

    /**
     * Define o número da sala associada à reserva.
     * 
     * @param salaId O ID da nova sala.
     */
    public void setSalaId(int salaId) {
        this.sala.setNumero(salaId); // Define o número da sala para o novo ID.
    }

    /**
     * Define o nome do usuário associado à reserva.
     * 
     * @param usuario O nome do novo usuário.
     */
    public void setUsuario(String usuario) {
        this.sala.setCliente(usuario); // Define o cliente associado à sala.
    }

    /**
     * Define a data e hora da reserva.
     * 
     * @param dataHora A nova data e hora para a reserva.
     */
    public void setDataHora(String dataHora) {
        this.dataHora = dataHora; // Define a nova data e hora da reserva.
    }

    /**
     * Método que retorna uma representação em String da reserva.
     * 
     * @return Uma string formatada representando a reserva.
     */
    @Override
    public String toString() {
        return "ReservaRequest{" +
                "salaId='" + sala.getNumero() + '\'' + // Número da sala
                ", usuario='" + sala.getCliente() + '\'' + // Nome do cliente
                ", dataHora=" + dataHora + // Data e hora da reserva
                '}';
    }
}
