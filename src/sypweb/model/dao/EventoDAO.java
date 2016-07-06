package sypweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import sypweb.model.base.BaseDAO;
import static sypweb.model.dao.EventoDAO.CRITERION_APOLICE;
import static sypweb.model.dao.EventoDAO.CRITERION_LATITUDE;
import static sypweb.model.dao.EventoDAO.CRITERION_LONGITUDE;
import static sypweb.model.dao.EventoDAO.CRITERION_PRECISAO;
import static sypweb.model.dao.EventoDAO.CRITERION_SYPCODE;
import static sypweb.model.dao.EventoDAO.CRITERION_TIPO_EVENTO;
import sypweb.model.pojo.Apolice;
import sypweb.model.pojo.Evento;
import sypweb.model.pojo.TipoEvento;

public class EventoDAO implements BaseDAO<Evento> {

    public static final String CRITERION_LATITUDE = "1";
    public static final String CRITERION_LONGITUDE = "2";
    public static final String CRITERION_TIPO_EVENTO = "3";
    public static final String CRITERION_SYPCODE = "4";
    public static final String CRITERION_PRECISAO = "5";
    public static final String CRITERION_APOLICE = "6";
    public static final String CRITERION_APOLICES = "7";
    public static final String CRITERION_ALL = "8";
    public static final String CRITERION_APOLICE_I_LIKE = "9";
    public static final String CRITERION_DATA_INI = "10";
    public static final String CRITERION_DATA_FIM = "11";

    @Override
    public void create(Evento e, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Evento readById(Long id, Connection conn) throws Exception {
        Evento e = null;
        String sql = "SELECT e.*, t.id id_tipo, t.nome nome_tipo, a.id id_apolice, a.apolice, "
                + "a.sypcode, a.data_hora_cadastro data_hora_cadastro_a FROM evento e "
                + "JOIN tipoevento t ON (e.tipoevento_fk = t.id) join apolice a on (e.apolice_fk = a.id) WHERE e.id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            e = new Evento();
            e.setId(rs.getLong("id"));
            e.setLatitude(rs.getString("latitude"));
            e.setLongitude(rs.getString("longitude"));
            e.setPrecisao(rs.getString("precisao"));
            e.setDataHoraCadastro(rs.getTimestamp("data_hora_cadastro"));

            //dados do Tipo
            TipoEvento tipo = new TipoEvento();
            tipo.setId(rs.getLong("id_tipo"));
            tipo.setNome(rs.getString("nome_tipo"));
            e.setTipoEvento(tipo);

            //dados da Apolice
            Apolice apolice = new Apolice();
            apolice.setId(rs.getLong("id_apolice"));
            apolice.setApolice(rs.getString("apolice"));
            apolice.setSypCode(rs.getString("sypcode"));
            apolice.setDataHoraCadastro(rs.getTimestamp("data_hora_cadastro_a"));
            e.setApolice(apolice);
        }
        rs.close();
        ps.close();
        return e;
    }

    @Override
    public List<Evento> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        List<Evento> lista = new ArrayList<Evento>();
        Evento e;
        String sql = "SELECT e.*, t.id id_tipo, t.nome nome_tipo, a.id id_apolice, a.apolice, "
                + "a.sypcode, a.data_hora_cadastro data_hora_cadastro_a FROM evento e "
                + "JOIN tipoevento t ON (e.tipoevento_fk = t.id) join apolice a on (e.apolice_fk = a.id)"
                + "WHERE (1=1)";

        String criterionLatitude = (String) criteria.get(CRITERION_LATITUDE);
        if (criterionLatitude != null && !criterionLatitude.trim().isEmpty()) {
            sql += " AND e.latitude = '" + criterionLatitude + "'";
        }

        String criterionLongitude = (String) criteria.get(CRITERION_LONGITUDE);
        if (criterionLongitude != null && !criterionLongitude.trim().isEmpty()) {
            sql += " AND e.longitude = '" + criterionLongitude + "'";
        }

        Long criterionTipoEvento = (Long) criteria.get(CRITERION_TIPO_EVENTO);
        if (criterionTipoEvento != null && criterionTipoEvento > 0) {
            sql += " AND e.tipoevento_fk = '" + criterionTipoEvento + "'";
        }

        Long criterionSypCode = (Long) criteria.get(CRITERION_SYPCODE);
        if (criterionSypCode != null && criterionSypCode > 0) {
            sql += " AND e.sypcode_fk = '" + criterionSypCode + "'";
        }

        Long criterionPrecisao = (Long) criteria.get(CRITERION_PRECISAO);
        if (criterionPrecisao != null && criterionPrecisao > 0) {
            sql += " AND e.precisao = '" + criterionPrecisao + "'";
        }

        Long criterionApolice = (Long) criteria.get(CRITERION_APOLICE);
        if (criterionApolice != null && criterionApolice > 0) {
            sql += " AND e.apolice_fk = '" + criterionApolice + "'";
        }

        String criterionApoliceIlike = (String) criteria.get(CRITERION_APOLICE_I_LIKE);
        if (criterionApoliceIlike != null && !criterionApoliceIlike.isEmpty()) {
            sql += " AND a.apolice ilike '%" + criterionApoliceIlike + "%'";
        }

        
        String criterionDataIni = (String) criteria.get(CRITERION_DATA_INI);
        String criterionDataFim = (String) criteria.get(CRITERION_DATA_FIM);
        
        if((criterionDataIni != null && !criterionDataIni.trim().isEmpty()) && (criterionDataFim != null && !criterionDataFim.trim().isEmpty())){
            sql += " AND e.data_hora_cadastro >= '" + criterionDataIni +"' AND e.data_hora_cadastro <= '" + criterionDataFim +"'";
        }
        
        List<String> criterionApolices = (ArrayList<String>) criteria.get(CRITERION_APOLICES);
        if (criterionApolices != null && criterionApolices.size() > 0) {
            sql += " AND apolice_fk = '" + criterionApolices.get(0) + "'";
            for (int i = 1; i < criterionApolices.size(); i++) {
                sql += " OR apolice_fk = '" + criterionApolices.get(i) + "'";
            }
        }
        
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {
            e = new Evento();
            e.setId(rs.getLong("id"));
            e.setLatitude(rs.getString("latitude"));
            e.setLongitude(rs.getString("longitude"));
            e.setPrecisao(rs.getString("precisao"));
            e.setDataHoraCadastro(rs.getTimestamp("data_hora_cadastro"));

            //dados do Tipo
            TipoEvento tipo = new TipoEvento();
            tipo.setId(rs.getLong("id_tipo"));
            tipo.setNome(rs.getString("nome_tipo"));
            e.setTipoEvento(tipo);

            //dados da Apolice
            Apolice apolice = new Apolice();
            apolice.setId(rs.getLong("id_apolice"));
            apolice.setApolice(rs.getString("apolice"));
            apolice.setSypCode(rs.getString("sypcode"));
            apolice.setDataHoraCadastro(rs.getTimestamp("data_hora_cadastro_a"));//rs.getDate("data_hora_cadastro_a")
           
            e.setApolice(apolice);
            lista.add(e);
        }
        rs.close();
        s.close();
        return lista;
    }

    @Override
    public void update(Evento e, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
