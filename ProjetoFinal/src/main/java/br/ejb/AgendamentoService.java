package br.ejb;

import br.jsf.Reserva;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interface para o serviço de agendamento de salas.
 * Define os métodos remotos para reservar, cancelar reservas e listar reservas.
 * 
 * Entrada: Parâmetros específicos para cada método, como detalhes da sala, usuário e horário.
 * Saída: Retorna resultados relacionados ao agendamento, como status da operação ou lista de reservas.
 * 
 * @author Maria Eduarda Pedroso
 */
public interface AgendamentoService extends Remote {

    /**
     * Realiza a reserva de uma sala para um usuário em uma data e horário específicos.
     * 
     * @param salaId   Identificador único da sala.
     * @param usuario  Nome ou identificação do usuário que realiza a reserva.
     * @param dataHora Data e horário da reserva no formato especificado.
     * @return {@code true} se a reserva foi realizada com sucesso, {@code false} caso contrário.
     * @throws RemoteException Em caso de erro na comunicação remota.
     */
    boolean reservarSala(int salaId, String usuario, String dataHora) throws RemoteException;

    /**
     * Cancela uma reserva existente.
     * 
     * @param reserva Objeto que representa a reserva a ser cancelada.
     * @return {@code true} se a reserva foi cancelada com sucesso, {@code false} caso contrário.
     * @throws RemoteException Em caso de erro na comunicação remota.
     */
    boolean desreservarSala(Reserva reserva) throws RemoteException;

    /**
     * Lista todas as reservas existentes.
     * 
     * @return Uma lista de objetos {@link Reserva} representando as reservas.
     * @throws RemoteException Em caso de erro na comunicação remota.
     */
    List<Reserva> listarReservas() throws RemoteException;
}
