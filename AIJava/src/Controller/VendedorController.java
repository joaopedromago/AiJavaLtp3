package Controller;

import Model.Servico;
import Model.ServicoVendedor;
import Model.Vendedor;
import Repository.VendedorRepository;
import java.util.ArrayList;
import java.util.List;

public class VendedorController {

    VendedorRepository repo;

    public VendedorController() {
        repo = new VendedorRepository();
    }
    
    public List<Vendedor> SalvarVendedor(Vendedor vendedor) {
        
        repo.salvar(vendedor);
        
        return ObterListaVendedor();
    }
    
    public List<Vendedor> AlterarVendedor(Vendedor vendedor) {
        
        repo.atualizar(vendedor);
        
        return ObterListaVendedor();
    }
    
    public List<Vendedor> ExcluirVendedor(int vendedorId) {
        
        repo.remover(vendedorId);
        
        return ObterListaVendedor();
    }
    
    public List<Vendedor> ObterListaVendedor() {
        List<Vendedor> retorno = repo.obterVendedor();
        
        ServicoVendedorController servicoVendedorController = new ServicoVendedorController();
        List<ServicoVendedor> servicosVendedor = servicoVendedorController.ObterListaServicoVendedor();
        
        retorno.forEach((item) -> {
            List<ServicoVendedor> servicos = new ArrayList<>();
            
            servicosVendedor.stream().filter((itemServico)
                    -> (itemServico.getVendedorId() == item.getId())).forEachOrdered((itemServico) -> {
                servicos.add(itemServico);
            });
            
            item.setServicos(servicos);
        });
        
        return retorno;
    }
    
    public Vendedor ObterVendedor(int vendedorId) {
        
        Vendedor vendedor = repo.obterVendedor(vendedorId);
        
        if (vendedor.getServicos() == null) {
            vendedor.setServicos(new ArrayList<ServicoVendedor>());
        }
        
        return vendedor;
    }
}
