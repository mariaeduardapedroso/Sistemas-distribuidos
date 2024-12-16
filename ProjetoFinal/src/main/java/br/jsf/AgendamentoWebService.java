package br.jsf;

import br.ejb.AgendamentoServiceImpl;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

@Path("/agendamento") // Define o endpoint base da API para agendamentos.
public class AgendamentoWebService {

    // Injeção de dependência do serviço de agendamento.
    @EJB
    AgendamentoServiceImpl service;

    /**
     * Construtor da classe AgendamentoWebService.
     * Este construtor cria uma nova instância do AgendamentoServiceImpl.
     * 
     * @throws RemoteException Caso ocorra um erro na criação do serviço.
     */
    public AgendamentoWebService() throws RemoteException {
        this.service = new AgendamentoServiceImpl(); // Inicializa o serviço de agendamento.
    }

    /**
     * Método GET para listar todas as reservas.
     * Este método retorna uma lista de reservas em formato JSON.
     * 
     * @return Uma lista de objetos Reserva.
     */
    @GET
    @Path("/listar") // Caminho para listar as reservas.
    @Produces(MediaType.APPLICATION_JSON) // Define que o retorno será em formato JSON.
    public List<Reserva> listarReservas() {
        try {
            return service.listarReservas(); // Chama o serviço para listar as reservas.
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException("Erro ao listar reservas.", 500); // Retorna erro 500 caso ocorra uma exceção.
        }
    }

    /**
     * Método POST para reservar uma sala.
     * Recebe os dados da reserva em formato JSON e retorna um valor booleano indicando
     * se a reserva foi realizada com sucesso.
     * 
     * @param request Objeto Reserva com as informações da reserva.
     * @return True se a reserva for bem-sucedida, False caso contrário.
     */
    @POST
    @Path("/reservar") // Caminho para reservar uma sala.
    @Consumes(MediaType.APPLICATION_JSON) // Define que os dados recebidos são em formato JSON.
    public boolean reservarSala(Reserva request) {
        try {
            // Chama o serviço para reservar a sala.
            return service.reservarSala(request.getSalaId(), String(request.getUsuario()), request.getDataHora());
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retorna false em caso de erro.
        }
    }

    /**
     * Método auxiliar para converter um Optional<String> em um String.
     * O método pode ser ajustado conforme necessário, caso você deseje um comportamento
     * específico para lidar com valores vazios em Optional.
     * 
     * @param usuario O Optional<String> com o nome do usuário.
     * @return O nome do usuário ou uma string vazia se não houver valor.
     */
    private String String(Optional<String> usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Esse método precisa ser implementado ou removido.
    }
}
