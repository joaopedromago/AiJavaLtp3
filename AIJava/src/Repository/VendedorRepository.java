package Repository;

import Infra.DbContext;
import Model.Vendedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendedorRepository {

    DbContext db;

    public VendedorRepository() {
        db = new DbContext();
    }

    public boolean salvar(Vendedor vendedor) {
        try {
            String query = "INSERT INTO vendedor "
                    + "(nome,cpf,rg,endereco,telefone,email) "
                    + "VALUES ('" + vendedor.getNome() + "','" + vendedor.getCpf()
                    + "','" + vendedor.getRg() + "','" + vendedor.getEndereco()
                    + "','" + vendedor.getTelefone() + "','" + vendedor.getEmail()
                    + "')";

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean atualizar(Vendedor vendedor) {
        try {
            String query = "UPDATE vendedor set "
                    + " nome = '" + vendedor.getNome() + "', cpf = '" + vendedor.getCpf()
                    + "', rg = '" + vendedor.getRg() + "', endereco = '" + vendedor.getEndereco()
                    + "', telefone = '" + vendedor.getTelefone() + "', email = '" + vendedor.getEmail()
                    + "' WHERE id = " + vendedor.getId();

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean remover(int id) {
        try {
            String query = "DELETE from vendedor "
                    + "where id = " + id;

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public Vendedor obterVendedor(int id) {
        String query = "SELECT * FROM vendedor "
                + "WHERE ID = " + id;

        Vendedor vendedor = new Vendedor();

        List<Map<String, Object>> result = db.ExecuteQuerySelect(query);

        for (Map<String, Object> i : result) {
            vendedor = obterVendedor(i);
        }

        return vendedor;
    }

    public List<Vendedor> obterVendedor() {
        try {
            List<Vendedor> vendedors = new ArrayList<Vendedor>();

            String query = "SELECT * FROM vendedor ";

            List<Map<String, Object>> result = db.ExecuteQuerySelect(query);

            for (Map<String, Object> i : result) {
                Vendedor vendedor = obterVendedor(i);
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

    private Vendedor obterVendedor(Map<String, Object> map) {
        Vendedor vendedor = new Vendedor();

        vendedor.setId(Integer.parseInt(map.get("id").toString()));
        vendedor.setNome(map.get("nome").toString());
        vendedor.setCpf(map.get("cpf").toString());
        vendedor.setRg(map.get("rg").toString());
        vendedor.setEndereco(map.get("endereco").toString());
        vendedor.setTelefone(map.get("telefone").toString());
        vendedor.setEmail(map.get("email").toString());

        return vendedor;
    }
}
