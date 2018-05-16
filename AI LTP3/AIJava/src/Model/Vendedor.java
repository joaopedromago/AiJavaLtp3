package Model;

import java.util.List;

public class Vendedor extends BaseModel {
	private int instituicaoId;
	private int pessoaId;
	private Pessoa pessoa;
	private Instituicao instituicao;
	private List<ServicoVendedor> servicos;

	public List<ServicoVendedor> getServicos() {
		return servicos;
	}

	public void setServicos(List<ServicoVendedor> servicos) {
		this.servicos = servicos;
	}

	public int getInstituicaoId() {
		return instituicaoId;
	}

	public void setInstituicaoId(int instituicaoId) {
		this.instituicaoId = instituicaoId;
	}

	public int getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
}
