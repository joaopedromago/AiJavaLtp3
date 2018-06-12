package Controller;

import Model.Cliente;
import Model.Servico;
import Model.ServicoVendedor;
import Model.Venda;
import Model.Vendedor;
import Repository.VendaRepository;
import java.util.ArrayList;
import java.util.List;

public class VendaController {

    VendaRepository repo;

    public VendaController() {
        repo = new VendaRepository();
    }

    public List<Venda> SalvarVenda(Venda venda) {

        repo.salvar(venda);

        return ObterListaVenda();
    }

    public List<Venda> AlterarVenda(Venda venda) {

        repo.atualizar(venda);

        return ObterListaVenda();
    }

    public List<Venda> ExcluirVenda(int vendaId) {

        repo.remover(vendaId);

        return ObterListaVenda();
    }

    public List<Venda> ObterListaVenda() {
        List<Venda> retorno = repo.obterVenda();

        ServicoController servicoController = new ServicoController();
        List<Servico> servicos = servicoController.ObterListaServico();

        VendedorController vendedorController = new VendedorController();
        List<Vendedor> vendedores = vendedorController.ObterListaVendedor();

        ClienteController clienteController = new ClienteController();
        List<Cliente> clientes = clienteController.ObterListaCliente();

        retorno.forEach((item) -> {
            servicos.stream().filter((servico)
                    -> (servico.getId() == item.getServicoId())).forEachOrdered((servico) -> {
                item.setServico(servico);
            });
            vendedores.stream().filter((vendedor)
                    -> (vendedor.getId() == item.getVendedorId())).forEachOrdered((vendedor) -> {
                item.setVendedor(vendedor);
            });
            clientes.stream().filter((cliente)
                    -> (cliente.getId() == item.getClienteId())).forEachOrdered((cliente) -> {
                item.setCliente(cliente);
            });
        });

        return retorno;
    }

    public Venda ObterVenda(int vendaId) {

        return repo.obterVenda(vendaId);
    }
}
