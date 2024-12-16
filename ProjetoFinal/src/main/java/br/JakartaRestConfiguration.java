package br;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Classe de configuração para os serviços RESTful utilizando Jakarta.
 * Configura o caminho base para os recursos REST da aplicação.
 * 
 * @author Maria Eduarda Pedroso
 */
@ApplicationPath("resources")
public class JakartaRestConfiguration extends Application {
    // A classe herda de Application e utiliza a anotação @ApplicationPath
    // para definir o caminho base dos recursos REST.
}
