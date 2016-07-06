package sypweb.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sypweb.model.base.BaseDAO;
import sypweb.model.pojo.Apolice;
import sypweb.model.pojo.Evento;
import sypweb.model.pojo.TipoEvento;

public class TipoEventoDAO implements BaseDAO<TipoEvento> {

    public static final String CRITERION_APOLICES = "7";
    public static final String CRITERION_DATA_INI = "8";
    public static final String CRITERION_DATA_FIM = "9";

    @Override
    public void create(TipoEvento e, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TipoEvento readById(Long id, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TipoEvento> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        List<TipoEvento> lista = new ArrayList<TipoEvento>();
        TipoEvento tp;
        String sql = "select tp.nome, count(e.id) qnt from evento e join tipoevento tp "
                + "on (tp.id = e.tipoevento_fk) where (1 = 1) ";

        String criterionDataIni = (String) criteria.get(CRITERION_DATA_INI);
        String criterionDataFim = (String) criteria.get(CRITERION_DATA_FIM);
        if ((criterionDataIni != null && !criterionDataIni.trim().isEmpty()) && (criterionDataFim != null && !criterionDataFim.trim().isEmpty())) {
            sql += " AND data_hora_cadastro >=  '" + criterionDataIni + "' AND data_hora_cadastro <= '" + criterionDataFim + "' ";
        } else {
            List<Long> criterionApolices = (ArrayList<Long>) criteria.get(CRITERION_APOLICES);
            if (criterionApolices != null && criterionApolices.size() > 0) {
                sql += " AND e.apolice_fk = '" + criterionApolices.get(0) + "'";
                for (int i = 1; i < criterionApolices.size(); i++) {
                    sql += " OR e.apolice_fk = '" + criterionApolices.get(i) + "'";
                }
            }
        }


        sql += " group by (tp.nome)";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {
            tp = new TipoEvento();
            // tp.setId(rs.getLong("id"));
            tp.setNome(rs.getString("nome"));
            tp.setQntOcorrido(rs.getLong("qnt"));
            lista.add(tp);
        }
        rs.close();
        s.close();
        return lista;
    }

    @Override
    public void update(TipoEvento e, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
