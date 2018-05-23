package Repository;

import Infra.DbContext;
import Model.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteRepository {

    DbContext db;

    public ClienteRepository() {
        db = new DbContext();
    }

    public boolean salvar(Cliente cliente) {
        try {
            String query = "INSERT INTO CLIENTES "
                    + "(pessoaId, numeroCompras) "
                    + "VALUES (" + cliente.getPessoaId() + ", " + cliente.getNumeroCompras() + ")";

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean atualizar(Cliente cliente) {
        try {
            String query = "UPDATE CLIENTES "
                    + "set pessoaId = " + cliente.getPessoaId() + ", "
                    + "numeroCompras = " + cliente.getNumeroCompras()
                    + "where id = " + cliente.getId();

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean remover(int id) {
        try {
            String query = "DELETE CLIENTES "
                    + "where id = " + id;

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public Cliente obterCliente(int id) {
        try {
            String query = "SELECT * FROM CLIENTES "
                    + "WHERE ID = " + id;

            Cliente cliente = new Cliente();

            ResultSet rs = db.ExecuteQuerySelect(query);
            while (rs.next()) {
                cliente = obterCliente(rs);
            }

            return cliente;

        } catch (SQLException e) {
            try {
                throw e;
            } catch (SQLException ex) {
                Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public List<Cliente> obterCliente() {
        try {
            List<Cliente> clientes = new ArrayList<Cliente>();

            String query = "SELECT * FROM CLIENTES ";

            ResultSet rs = db.ExecuteQuerySelect(query);
            while (rs.next()) {
                Cliente cliente = obterCliente(rs);
                clientes.add(cliente);
            }

            return clientes;
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    private Cliente obterCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("Id"));
        cliente.setDataCriacao(rs.getDate("DataCriacao"));
        cliente.setDataExclusao(rs.getDate("DataExclusao"));
        cliente.setNumeroCompras(rs.getInt("NumeroCompras"));
        cliente.setPessoaId(rs.getInt("PessoaId"));

        return cliente;
    }
}
