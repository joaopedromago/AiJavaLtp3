package Controller;

import Model.Servico;
import Model.ServicoVendedor;
import Repository.ServicoVendedorRepository;
import java.util.ArrayList;
import java.util.List;

public class ServicoVendedorController {
    ServicoVendedorRepository repo;
    public ServicoVendedorController(){
        repo = new ServicoVendedorRepository();
    }
    
    public List<ServicoVendedor> SalvarServicoVendedor(ServicoVendedor servicoVendedor){
        
        repo.salvar(servicoVendedor);
        
        return ObterListaServicoVendedor();
    }
    
    public List<ServicoVendedor> AlterarServicoVendedor(ServicoVendedor servicoVendedor){
        
        repo.atualizar(servicoVendedor);
        
        return ObterListaServicoVendedor();
    }
    
    public List<ServicoVendedor> ExcluirServicoVendedor(int servicoVendedorId){
        
        repo.remover(servicoVendedorId);
        
        return ObterListaServicoVendedor();
    }
    
    public List<ServicoVendedor> ObterListaServicoVendedor(){
        List<ServicoVendedor>  retorno = repo.obterServicoVendedor();
        
        ServicoController servicoController = new ServicoController();
        List<Servico> servicos = servicoController.ObterListaServico();
        
        retorno.forEach((item) -> {                    
            servicos.stream().filter((itemServico) -> 
                    (itemServico.getId()== item.getServicoId())).forEachOrdered((itemServico) -> {
                        item.setServico(itemServico);
            });
        });
        
        return retorno;
    }
    
    public ServicoVendedor ObterServicoVendedor(int servicoVendedorId){
        
        return repo.obterServicoVendedor(servicoVendedorId);
    }
}
