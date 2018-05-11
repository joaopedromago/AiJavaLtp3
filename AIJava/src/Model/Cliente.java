package Model;

public class Cliente extends BaseModel {
	private int numeroCompras;
	private int pessoaId;
	private Pessoa pessoa;

	public int getNumeroCompras() {
		return numeroCompras;
	}

	public void setNumeroCompras(int numeroCompras) {
		this.numeroCompras = numeroCompras;
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
}
