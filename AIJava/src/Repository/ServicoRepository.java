package Repository;

import Enum.TipoServico;
import Infra.DbContext;
import Model.Servico;
import static Util.Util.obterTipoServico;
import static java.lang.Double.max;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicoRepository {

    DbContext db;

    public ServicoRepository() {
        db = new DbContext();
    }

    public boolean salvar(Servico servico) {
        try {
            String query = "INSERT INTO servico "
                    + "(nome,preco,tiposervico) "
                    + "VALUES ('" + servico.getNome() + "'  ,'" + servico.getPreco()
 + "','" + servico.getTipoServico() + "')";

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean atualizar(Servico servico) {
        try {
            String query = "UPDATE servico set "
                    + " nome = '" + servico.getNome() + "', preco = '" + servico.getPreco()
                    + "', tiposervico = '" + servico.getTipoServico()
                    + "' WHERE id = " + servico.getId();

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean remover(int id) {
        try {
            String query = "DELETE from servico "
                    + "where id = " + id;

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public Servico obterServico(int id) {
        String query = "SELECT * FROM servico "
                + "WHERE ID = " + id;

        Servico servico = new Servico();

        List<Map<String, Object>> result = db.ExecuteQuerySelect(query);

        for (Map<String, Object> i : result) {
            servico = obterServico(i);
        }

        return servico;
    }

    public List<Servico> obterServico() {
        List<Servico> servicos = new ArrayList<Servico>();

        String query = "SELECT * FROM servico ";

        List<Map<String, Object>> result = db.ExecuteQuerySelect(query);

        for (Map<String, Object> i : result) {
            servicos.add(obterServico(i));
        }

        return servicos;
    }

    private Servico obterServico(Map<String, Object> map) {

        Servico servico = new Servico();

        Object test = map.get("id");

        servico.setId(Integer.parseInt(map.get("id").toString()));
        servico.setNome(map.get("nome").toString());
        servico.setPreco(Double.parseDouble(map.get("preco").toString()));
        servico.setTipoServico(obterTipoServico(map.get("tipoServico").toString()));

        return servico;
    }
}
