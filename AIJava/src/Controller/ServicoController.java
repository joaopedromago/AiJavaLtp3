package Controller;

import Model.Servico;
import Repository.ServicoRepository;
import java.util.List;

public class ServicoController {

    ServicoRepository repo;

    public ServicoController() {
        repo = new ServicoRepository();
    }

    public List<Servico> SalvarServico(Servico servico) {

        repo.salvar(servico);

        return ObterListaServico();
    }

    public List<Servico> AlterarServico(Servico servico) {

        repo.atualizar(servico);

        return ObterListaServico();
    }

    public List<Servico> ExcluirServico(int servicoId) {

        repo.remover(servicoId);

        return ObterListaServico();
    }

    public List<Servico> ObterListaServico() {

        return repo.obterServico();
    }

    public Servico ObterServico(int servicoId) {

        return repo.obterServico(servicoId);
    }
}
