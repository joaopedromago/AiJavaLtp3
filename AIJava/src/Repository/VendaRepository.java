package Repository;

import Infra.DbContext;
import Model.Venda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendaRepository {

    DbContext db;

    public VendaRepository() {
        db = new DbContext();
    }

    public boolean salvar(Venda venda) {
        try {
            String query = "INSERT INTO venda "
                    + "(clienteId,vendedorId,servicoId,dataRealizacao) "
                    + "VALUES ('" + venda.getClienteId()+ "','" + venda.getVendedorId()
                    + "','" + venda.getServicoId()+ "','" + venda.getDataRealizacao()+"')";

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean atualizar(Venda venda) {
        try {
            String query = "UPDATE venda set "
                    + " clienteId = '" + venda.getClienteId() + "', vendedorId = '" + venda.getVendedorId()
                    + "', servicoId = '" + venda.getServicoId() + "', dataRealizacao = '" + venda.getDataRealizacao()
                    + "' WHERE id = " + venda.getId();

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean remover(int id) {
        try {
            String query = "DELETE venda "
                    + "where id = " + id;

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public Venda obterVenda(int id) {
        try {
            String query = "SELECT * FROM venda "
                    + "WHERE ID = " + id;

            Venda venda = new Venda();

            ResultSet rs = null; //db.ExecuteQuerySelect(query);
            while (rs.next()) {
                venda = obterVenda(rs);
            }

            return venda;

        } catch (SQLException e) {
            try {
                throw e;
            } catch (SQLException ex) {
                Logger.getLogger(VendaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public List<Venda> obterVenda() {
        try {
            List<Venda> vendas = new ArrayList<Venda>();

            String query = "SELECT * FROM venda ";

            ResultSet rs = null; //db.ExecuteQuerySelect(query);
            while (rs.next()) {
                Venda venda = obterVenda(rs);
                vendas.add(venda);
            }

            return vendas;
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(VendaRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    private Venda obterVenda(ResultSet rs) throws SQLException {
        Venda venda = new Venda();
        venda.setId(rs.getInt("id"));
        venda.setDataCriacao(rs.getDate("dataCriacao"));
        venda.setDataExclusao(rs.getDate("dataExclusao"));
        venda.setClienteId(rs.getInt("clienteId"));
        venda.setVendedorId(rs.getInt("vendedorId"));
        venda.setServicoId(rs.getInt("servicoId"));
        venda.setDataRealizacao(rs.getDate("dataRealizacao"));

        return venda;
    }
}
