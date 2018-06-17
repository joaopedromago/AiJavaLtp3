package Repository;

import Infra.DbContext;
import Model.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

public class ClienteRepository {

    DbContext db;

    public ClienteRepository() {
        db = new DbContext();
    }

    public boolean salvar(Cliente cliente) {
        try {
            String query = "INSERT INTO cliente "
                    + "(nome,cpf,rg,endereco,telefone,email, numeroCompras) "
                    + "VALUES ('" + cliente.getNome() + "','" + cliente.getCpf()
                    + "','" + cliente.getRg() + "','" + cliente.getEndereco()
                    + "','" + cliente.getTelefone() + "','" + cliente.getEmail()
                    + "', '" + cliente.getNumeroCompras() + "')";

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }
    
    public boolean atualizarNumeroCompras(int clienteId, boolean adicionar){
                try {
            String query = "";
            
            if(adicionar){
            query = "UPDATE cliente set "
                    + " numeroCompras = numeroCompras + 1 WHERE id = " + clienteId;
            } else {
            query = "UPDATE cliente set "
                    + " numeroCompras = numeroCompras - 1 WHERE id = " + clienteId;
            }

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }          
    }

    public boolean atualizar(Cliente cliente) {
        try {
            String query = "UPDATE cliente set "
                    + " nome = '" + cliente.getNome() + "', cpf = '" + cliente.getCpf()
                    + "', rg = '" + cliente.getRg() + "', endereço = '" + cliente.getEndereco()
                    + "', telefone = '" + cliente.getTelefone() + "', email = '" + cliente.getEmail()
                    + "', numeroCompras = '" + cliente.getNumeroCompras()
                    + "' WHERE id = " + cliente.getId();

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean remover(int id) {
        try {
            String query = "DELETE from cliente "
                    + "where id = " + id;

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public Cliente obterCliente(int id) {
        try {
            String query = "SELECT * FROM cliente "
                    + "WHERE ID = " + id;

            Cliente cliente = new Cliente();

            ResultSet rs = null; //db.ExecuteQuerySelect(query);
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

            String query = "SELECT * FROM cliente ";

            ResultSet rs = null; //db.ExecuteQuerySelect(query);
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
        cliente.setId(rs.getInt("id"));
        cliente.setDataCriacao(rs.getDate("dataCriacao"));
        cliente.setDataExclusao(rs.getDate("dataExclusao"));
        cliente.setNumeroCompras(rs.getInt("numeroCompras"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setRg(rs.getString("rg"));
        cliente.setEndereco(rs.getString("endereço"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setEmail(rs.getString("email"));

        return cliente;
    }
}
