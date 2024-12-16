package br.ejb;

import br.ejb.AgendamentoServiceImpl;
import br.jsf.Reserva;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.util.List;

/**
 * Bean gerenciado para a interface de agendamento de salas.
 * Responsável por interagir com a lógica de negócio e as páginas JSF.
 * Permite realizar reservas, cancelar reservas e exibir a lista de reservas.
 * 
 * @author Maria Eduarda Pedroso
 */
@Named
@RequestScoped
public class AgendarSalasBean {

    /**
     * Serviço de agendamento injetado via EJB.
     */
    @EJB
    private AgendamentoServiceImpl agendamentoService;

    /**
     * Número da sala a ser reservada.
     */
    private int numeroSala;

    /**
     * Nome do cliente que realiza a reserva.
     */
    private String cliente;

    /**
     * Data e hora desejadas para a reserva.
     */
    private String dataHora;

    /**
     * Lista de reservas disponíveis para exibição.
     */
    private List<Reserva> reservas;

    /**
     * Construtor padrão.
     * Pode ser utilizado para inicializar atributos ou realizar outras configurações necessárias.
     */
    public AgendarSalasBean() {
        // Inicializações, se necessário
    }

    /**
     * Realiza a reserva de uma sala com base nos dados fornecidos.
     * 
     * @return Navega para a página de listagem de reservas em caso de sucesso, ou mantém na mesma página em caso de erro.
     */
    public String reservar() {
        boolean sucesso = agendamentoService.reservarSala(numeroSala, cliente, dataHora);
        if (sucesso) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sala reservada com sucesso!", null));
            return "listar";
        }
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao reservar a sala. Tente novamente.", null));
        return null;
    }

    /**
     * Cancela uma reserva existente.
     * 
     * @param reserva Objeto {@link Reserva} que será cancelado.
     * @return Navega para a página de listagem de reservas em caso de sucesso, ou mantém na mesma página em caso de erro.
     */
    public String desreservar(Reserva reserva) {
        boolean sucesso = agendamentoService.desreservarSala(reserva);
        if (sucesso) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso ao desreservar a sala.", null));
            return "listar";
        }
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao desreservar a sala. Tente novamente.", null));
        return null;
    }

    // Getters e setters para os atributos

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
