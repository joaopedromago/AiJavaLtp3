package Repository;

import Infra.DbContext;
import Model.Vendedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendedorRepository {

    DbContext db;

    public VendedorRepository() {
        db = new DbContext();
    }

    public boolean salvar(Vendedor vendedor) {
        try {
            String query = "INSERT INTO VENDEDORES "
                    + "(nome,cpf,rg,endereco,telefone,email) "
                    + "VALUES (" + vendedor.getNome() + "," + vendedor.getCpf() 
                    + "," + vendedor.getRg() + "," + vendedor.getEndereco() 
                    + "," + vendedor.getTelefone() + "," + vendedor.getEmail() 
                    + ")";

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean atualizar(Vendedor vendedor) {
        try {
            String query = "UPDATE VENDEDORES set "
+ " nome = " + vendedor.getNome() + ", cpf = " + vendedor.getCpf() 
+ ", rg = " + vendedor.getRg() + ", endereço = " + vendedor.getEndereco() 
+ ", telefone = " + vendedor.getTelefone() + ", email = " + vendedor.getEmail()
                    + " WHERE id = " + vendedor.getId();

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean remover(int id) {
        try {
            String query = "DELETE VENDEDORES "
                    + "where id = " + id;

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public Vendedor obterVendedor(int id) {
        try {
            String query = "SELECT * FROM VENDEDORES "
                    + "WHERE ID = " + id;

            Vendedor vendedor = new Vendedor();

            ResultSet rs = db.ExecuteQuerySelect(query);
            while (rs.next()) {
                vendedor = obterVendedor(rs);
            }

            return vendedor;

        } catch (SQLException e) {
            try {
                throw e;
            } catch (SQLException ex) {
                Logger.getLogger(VendedorRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public List<Vendedor> obterVendedor() {
        try {
            List<Vendedor> vendedors = new ArrayList<Vendedor>();

            String query = "SELECT * FROM VENDEDORES ";

            ResultSet rs = db.ExecuteQuerySelect(query);
            while (rs.next()) {
                Vendedor vendedor = obterVendedor(rs);
                vendedors.add(vendedor);
            }

            return vendedors;
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(VendedorRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    private Vendedor obterVendedor(ResultSet rs) throws SQLException {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(rs.getInt("id"));
        vendedor.setDataCriacao(rs.getDate("dataCriacao"));
        vendedor.setDataExclusao(rs.getDate("dataExclusao"));
        vendedor.setNome(rs.getString("nome"));
        vendedor.setCpf(rs.getString("cpf"));
        vendedor.setRg(rs.getString("rg"));
        vendedor.setEndereco(rs.getString("endereço"));
        vendedor.setTelefone(rs.getString("telefone"));
        vendedor.setEmail(rs.getString("email"));

        return vendedor;
    }
}
