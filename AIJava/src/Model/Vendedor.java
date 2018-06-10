package Model;

import java.util.List;

public class Vendedor extends BaseModel {
        
	private List<ServicoVendedor> servicos;

        private String nome;
	private String cpf;
	private String rg;
	private String endereco;
	private String telefone;
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
        
	public List<ServicoVendedor> getServicos() {
		return servicos;
	}

	public void setServicos(List<ServicoVendedor> servicos) {
		this.servicos = servicos;
	}
}
