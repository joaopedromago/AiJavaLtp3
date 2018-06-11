package Repository;

import Enum.TipoServico;
import Infra.DbContext;
import Model.Servico;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicoRepository {

    DbContext db;

    public ServicoRepository() {
        db = new DbContext();
    }

    public boolean salvar(Servico servico) {
        try {
            String query = "INSERT INTO SERVICOS "
                    + "(nome,preco,tiposervico) "
                    + "VALUES (" + servico.getNome() + "," + servico.getPreco()
                    + "," + servico.getTipoServico() + ")";

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean atualizar(Servico servico) {
        try {
            String query = "UPDATE SERVICOS set "
                    + " nome = " + servico.getNome() + ", preco = " + servico.getPreco()
                    + ", tiposervico = " + servico.getTipoServico()
                    + " WHERE id = " + servico.getId();

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean remover(int id) {
        try {
            String query = "DELETE SERVICOS "
                    + "where id = " + id;

            return db.ExecuteQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public Servico obterServico(int id) {
        try {
            String query = "SELECT * FROM SERVICOS "
                    + "WHERE ID = " + id;

            Servico servico = new Servico();

            ResultSet rs = db.ExecuteQuerySelect(query);
            while (rs.next()) {
                servico = obterServico(rs);
            }

            return servico;

        } catch (SQLException e) {
            try {
                throw e;
            } catch (SQLException ex) {
                Logger.getLogger(ServicoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public List<Servico> obterServico() {
        try {
            List<Servico> servicos = new ArrayList<Servico>();

            String query = "SELECT * FROM SERVICOS ";

            ResultSet rs = db.ExecuteQuerySelect(query);
            while (rs.next()) {
                Servico servico = obterServico(rs);
                servicos.add(servico);
            }

            return servicos;
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(ServicoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    private Servico obterServico(ResultSet rs) throws SQLException {
        Servico servico = new Servico();
        servico.setId(rs.getInt("id"));
        servico.setDataCriacao(rs.getDate("dataCriacao"));
        servico.setDataExclusao(rs.getDate("dataExclusao"));
        servico.setNome(rs.getString("nome"));
        servico.setPreco(rs.getDouble("preco"));
        servico.setTipoServico(TipoServico.values()[rs.getInt("tipoServico")]);

        return servico;
    }
}
