package Model;

public class ServicoVendedor extends BaseModel {
	private int servicoId;
	private int vendedorId;
	private Servico servico;
	private Vendedor vendedor;

	public int getServicoId() {
		return servicoId;
	}

	public void setServicoId(int servicoId) {
		this.servicoId = servicoId;
	}

	public int getVendedorId() {
		return vendedorId;
	}

	public void setVendedorId(int vendedorId) {
		this.vendedorId = vendedorId;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
}
