package Controller;

import Model.Cliente;
import Repository.ClienteRepository;
import java.util.List;

public class ClienteController {

    ClienteRepository repo;

    public ClienteController() {
        repo = new ClienteRepository();
    }

    public List<Cliente> SalvarCliente(Cliente cliente) {

        repo.salvar(cliente);

        return ObterListaCliente();
    }

    public List<Cliente> AlterarCliente(Cliente cliente) {

        repo.atualizar(cliente);

        return ObterListaCliente();
    }

    public List<Cliente> ExcluirCliente(int clienteId) {

        repo.remover(clienteId);

        return ObterListaCliente();
    }

    public List<Cliente> ObterListaCliente() {

        return repo.obterCliente();
    }

    public Cliente ObterCliente(int clienteId) {

        return repo.obterCliente(clienteId);
    }
}
