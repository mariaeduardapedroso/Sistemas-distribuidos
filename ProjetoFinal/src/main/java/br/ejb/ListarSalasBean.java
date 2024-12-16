package br.ejb;

import br.ejb.AgendamentoServiceImpl;
import br.jsf.Reserva;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;

/**
 * Bean gerenciado responsável por listar as reservas de salas.
 * Interage com a lógica de negócio para recuperar a lista de reservas disponíveis.
 * 
 * @author Maria Eduarda Pedroso
 */
@Named
@RequestScoped
public class ListarSalasBean {

    /**
     * Serviço de agendamento injetado via EJB.
     */
    @EJB
    private AgendamentoServiceImpl agendamentoService;

    /**
     * Recupera a lista de reservas existentes.
     * 
     * @return Uma lista de objetos {@link Reserva} representando as reservas atuais.
     */
    public List<Reserva> getReservas() {
        return agendamentoService.listarReservas();
    }
}
