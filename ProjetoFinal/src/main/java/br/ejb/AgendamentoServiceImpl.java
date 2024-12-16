package br.ejb;

import br.jsf.Sala;
import br.jsf.Reserva;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço de agendamento de salas.
 * Fornece funcionalidades para:
 * - Reservar salas.
 * - Cancelar reservas.
 * - Listar reservas.
 * 
 * @author Maria Eduarda Pedroso
 */
@Stateless
@LocalBean
public class AgendamentoServiceImpl extends UnicastRemoteObject implements AgendamentoService {

    /**
     * Lista de reservas armazenadas no sistema.
     */
    private List<Reserva> reservas;

    /**
     * Lista de salas disponíveis para reserva.
     */
    private final List<Sala> salas = Arrays.asList(
            new Sala(1),
            new Sala(2),
            new Sala(3)
    );

    /**
     * Gerenciador de bloqueio para controle de acesso às salas.
     */
    private final LockManager lockManager = new LockManager(salas);

    /**
     * Construtor da classe.
     * Inicializa a lista de reservas e configura o objeto remoto.
     * 
     * @throws RemoteException Em caso de falha na exportação do objeto remoto.
     */
    public AgendamentoServiceImpl() throws RemoteException {
        super(); // Chama o construtor de UnicastRemoteObject
        reservas = new ArrayList<>();
    }

    /**
     * Realiza a reserva de uma sala para um usuário em uma data e horário específicos.
     * 
     * @param salaId   Identificador único da sala a ser reservada.
     * @param usuario  Nome ou identificação do usuário que realiza a reserva.
     * @param dataHora Data e horário da reserva no formato especificado.
     * @return {@code true} se a reserva for concluída com sucesso, {@code false} caso contrário.
     */
    @Override
    public boolean reservarSala(int salaId, String usuario, String dataHora) {
        if (!lockManager.acquireLock(salaId)) {
            System.out.println("Sala está trancada.");
            return false; // Sala está trancada.
        }
        try {
            Optional<Reserva> reservaEncontrada = reservas.stream()
                    .filter(reserva -> reserva.getSalaId() == salaId && reserva.getDataHora().equals(dataHora))
                    .findFirst();

            if (reservaEncontrada.isPresent()) {
                System.out.println("Reserva já existe para a sala " + salaId + " por " + usuario + " em " + dataHora);
                return false;
            }

            Sala sala = new Sala(salaId, true, usuario); // Cria a sala com informações atualizadas.
            Reserva reserva = new Reserva(sala, dataHora); // Associa a sala à reserva

            reservas.add(reserva);
            System.out.println("Reserva realizada para a sala " + salaId + " por " + usuario + " em " + dataHora);

            return true;
        } finally {
            lockManager.releaseLock(salaId);
        }
    }

    /**
     * Cancela uma reserva existente.
     * 
     * @param reserva Objeto {@link Reserva} que representa a reserva a ser cancelada.
     * @return {@code true} se a reserva for cancelada com sucesso, {@code false} caso contrário.
     */
    @Override
    public boolean desreservarSala(Reserva reserva) {
        try {
            reservas.remove(reserva);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Retorna a lista de todas as reservas atualmente cadastradas.
     * 
     * @return Uma lista de objetos {@link Reserva} representando as reservas.
     */
    @Override
    public List<Reserva> listarReservas() {
        return reservas;
    }

    /**
     * Inicializa o registro RMI e vincula o serviço.
     * 
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AgendamentoService", new AgendamentoServiceImpl());
            System.out.println("Servidor RMI iniciado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
