package Repository;

import Infra.DbContext;
import Model.ServicoVendedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicoVendedorRepository {

    DbContext db;

    public ServicoVendedorRepository() {
        db = new DbContext();
    }

    public boolean salvar(ServicoVendedor servicoVendedor) {
        try {
            String query = "INSERT INTO SERVICOSVENDEDORESS "
                    + "(quantidadeServicos,servicoId,vendedorId) "
                    + "VALUES (" + servicoVendedor.getQuantidadeServicos() + "," + servicoVendedor.getServicoId()
                    + "," + servicoVendedor.getVendedorId() + ")";

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean atualizar(ServicoVendedor servicoVendedor) {
        try {
            String query = "UPDATE SERVICOSVENDEDORESS set "
                    + " quantidadeServicos = " + servicoVendedor.getQuantidadeServicos()
                    + ", servicoId = " + servicoVendedor.getServicoId()
                    + ", vendedorId = " + servicoVendedor.getVendedorId()
                    + " WHERE id = " + servicoVendedor.getId();

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean remover(int id) {
        try {
            String query = "DELETE SERVICOSVENDEDORESS "
                    + "where id = " + id;

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public ServicoVendedor obterServicoVendedor(int id) {
        try {
            String query = "SELECT * FROM SERVICOSVENDEDORESS "
                    + "WHERE ID = " + id;

            ServicoVendedor servicoVendedor = new ServicoVendedor();

            ResultSet rs = db.ExecuteQuerySelect(query);
            while (rs.next()) {
                servicoVendedor = obterServicoVendedor(rs);
            }

            return servicoVendedor;

        } catch (SQLException e) {
            try {
                throw e;
            } catch (SQLException ex) {
                Logger.getLogger(ServicoVendedorRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public List<ServicoVendedor> obterServicoVendedor() {
        try {
            List<ServicoVendedor> servicoVendedors = new ArrayList<ServicoVendedor>();

            String query = "SELECT * FROM SERVICOSVENDEDORESS ";

            ResultSet rs = db.ExecuteQuerySelect(query);
            while (rs.next()) {
                ServicoVendedor servicoVendedor = obterServicoVendedor(rs);
                servicoVendedors.add(servicoVendedor);
            }

            return servicoVendedors;
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(ServicoVendedorRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    private ServicoVendedor obterServicoVendedor(ResultSet rs) throws SQLException {
        ServicoVendedor servicoVendedor = new ServicoVendedor();
        servicoVendedor.setId(rs.getInt("id"));
        servicoVendedor.setDataCriacao(rs.getDate("dataCriacao"));
        servicoVendedor.setDataExclusao(rs.getDate("dataExclusao"));
        servicoVendedor.setQuantidadeServicos(rs.getInt("quantidadeServicos"));
        servicoVendedor.setServicoId(rs.getInt("servicoId"));
        servicoVendedor.setVendedorId(rs.getInt("vendedorId"));

        return servicoVendedor;
    }
}
