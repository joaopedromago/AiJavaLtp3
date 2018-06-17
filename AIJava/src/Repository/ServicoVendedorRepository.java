package Repository;

import Infra.DbContext;
import Model.ServicoVendedor;
import Model.Vendedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicoVendedorRepository {

    DbContext db;

    public ServicoVendedorRepository() {
        db = new DbContext();
    }

    public boolean salvar(ServicoVendedor servicoVendedor) {
        try {
            String query = "INSERT INTO servicoVendedor "
                    + "(quantidadeServicos,servicoId,vendedorId) "
                    + "VALUES ('" + servicoVendedor.getQuantidadeServicos() + "','"
                    + servicoVendedor.getServicoId()
                    + "','" + servicoVendedor.getVendedorId() + "')";

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }
    
    public boolean atualizarEstoque(int servicoId, int vendedorId, boolean adicionar){
        try {
            String query = "";
            
            if(adicionar){
            query = "UPDATE servicoVendedor set "
                    + " quantidadeServicos = quantidadeServicos + 1 WHERE servicoId = " 
                    + servicoId + " and vendedorId = " + vendedorId;
            } else {
            query = "UPDATE servicoVendedor set "
                    + " quantidadeServicos = quantidadeServicos - 1 WHERE servicoId = " 
                    + servicoId + " and vendedorId = " + vendedorId;
            }

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }     
    }

    public boolean atualizar(ServicoVendedor servicoVendedor) {
        try {
            String query = "UPDATE servicoVendedor set "
                    + " quantidadeServicos = '" + servicoVendedor.getQuantidadeServicos()
                    + "', servicoId = '" + servicoVendedor.getServicoId()
                    + "', vendedorId = '" + servicoVendedor.getVendedorId()
                    + "' WHERE id = " + servicoVendedor.getId();

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean remover(int id) {
        try {
            String query = "DELETE from servicoVendedor "
                    + "where id = " + id;

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public ServicoVendedor obterServicoVendedor(int id) {
        String query = "SELECT * FROM servicoVendedor "
                + "WHERE ID = " + id;

        ServicoVendedor servicoVendedor = new ServicoVendedor();

        List<Map<String, Object>> result = db.ExecuteQuerySelect(query);

        for (Map<String, Object> i : result) {
            servicoVendedor = obterServicoVendedor(i);
        }

        return servicoVendedor;
    }

    public List<ServicoVendedor> obterServicoVendedor() {
        List<ServicoVendedor> servicoVendedors = new ArrayList<ServicoVendedor>();

        String query = "SELECT * FROM servicoVendedor ";

        List<Map<String, Object>> result = db.ExecuteQuerySelect(query);

        for (Map<String, Object> i : result) {
            ServicoVendedor servicoVendedor = obterServicoVendedor(i);
            servicoVendedors.add(servicoVendedor);
        }

        return servicoVendedors;
    }

    private ServicoVendedor obterServicoVendedor(Map<String, Object> map) {
        ServicoVendedor servicoVendedor = new ServicoVendedor();
        
        servicoVendedor.setId(Integer.parseInt(map.get("id").toString()));
        servicoVendedor.setQuantidadeServicos(Integer.parseInt(map.get("quantidadeServicos").toString()));
        servicoVendedor.setServicoId(Integer.parseInt(map.get("servicoId").toString()));
        servicoVendedor.setVendedorId(Integer.parseInt(map.get("vendedorId").toString()));

        return servicoVendedor;
    }
}
