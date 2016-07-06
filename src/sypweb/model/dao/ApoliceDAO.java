package sypweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sypweb.model.ConnectionManager;
import sypweb.model.base.BaseDAO;
import static sypweb.model.dao.ApoliceDAO.CRITERION_APOLICE;
import static sypweb.model.dao.ApoliceDAO.CRITERION_APOLICES;
import static sypweb.model.dao.ApoliceDAO.CRITERION_SYPCODE;
import sypweb.model.pojo.Apolice;
import sypweb.model.pojo.TipoEvento;

public class ApoliceDAO implements BaseDAO<Apolice> {

    public static final String CRITERION_APOLICE = "1";
    public static final String CRITERION_APOLICES = "2";
    public static final String CRITERION_SYPCODE = "3";
    public static final String CRITERION_ALL = "4";
    public static final String FILTRO_DATA_MENOR = "5";
    public static final String FILTRO_DATA_MAIOR = "6";
    public static final String CRITERION_DATA_INI = "7";
    public static final String CRITERION_DATA_FIM = "8";

    @Override
    public void create(Apolice e, Connection conn) throws Exception {
        String sql = "INSERT INTO apolice(apolice, sypcode, sypcode_status) VALUES (?, ?, ?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, e.getApolice());
        ps.setString(++i, e.getSypCode());
        ps.setBoolean(++i, e.getSypCodeStatus());

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            e.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public Apolice readById(Long id, Connection conn) throws Exception {
        Apolice e = new Apolice();
        String sql = "SELECT * FROM apolice WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            e.setId(rs.getLong("id"));
            e.setApolice(rs.getString("apolice"));
            e.setSypCode(rs.getString("sypcode"));
            e.setDataHoraCadastro(rs.getTimestamp("data_hora_cadastro"));
        }
        rs.close();
        ps.close();
        return e;
    }

    @Override
    public List<Apolice> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        List<Apolice> lista = new ArrayList<Apolice>();
        String sql = "SELECT * FROM apolice WHERE 1=1 ";

        String criterionApolice = (String) criteria.get(CRITERION_APOLICE);
        if (criterionApolice != null && !criterionApolice.trim().isEmpty()) {
            sql += " AND apolice = '" + criterionApolice + "'";
        }

        List<String> criterionApolices = (ArrayList<String>) criteria.get(CRITERION_APOLICES);
        if (criterionApolices != null && criterionApolices.size() > 0) {
            sql += " AND apolice = '" + criterionApolices.get(0) + "'";
            for (int i = 1; i < criterionApolices.size(); i++) {
                sql += " OR apolice = '" + criterionApolices.get(i) + "'";
            }
        }

        String criterionSypCode = (String) criteria.get(CRITERION_SYPCODE);
        if (criterionSypCode != null && !criterionSypCode.trim().isEmpty()) {
            sql += " AND sypcode = '" + criterionSypCode + "'";
        }

        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {
            Apolice e = new Apolice();
            e.setId(rs.getLong("id"));
            e.setApolice(rs.getString("apolice"));
            e.setSypCode(rs.getString("sypcode"));
            e.setDataHoraCadastro(rs.getTimestamp("data_hora_cadastro"));
            e.setSypCodeStatus(rs.getBoolean("sypcode_status"));
            lista.add(e);
        }
        rs.close();
        s.close();
        return lista;
    }

    public List<Apolice> readByGrafico2(Map<String, Object> criteria, Connection conn) throws Exception {
        List<Apolice> lista = new ArrayList<Apolice>();
        Apolice a;
        String sql = "select count(e.id) qnt, a.apolice from evento e join apolice a on (a.id = e.apolice_fk) where (1 = 1) ";

        String criterionDataIni = (String) criteria.get(CRITERION_DATA_INI);
        String criterionDataFim = (String) criteria.get(CRITERION_DATA_FIM);
        if ((criterionDataIni != null && !criterionDataIni.trim().isEmpty()) && (criterionDataFim != null && !criterionDataFim.trim().isEmpty())) {
            sql += " AND e.data_hora_cadastro >=  '" + criterionDataIni + "' AND e.data_hora_cadastro <= '" + criterionDataFim + "' ";
        } else {
            List<Long> criterionApolices = (ArrayList<Long>) criteria.get(CRITERION_APOLICES);
            if (criterionApolices != null && criterionApolices.size() > 0) {
                sql += " AND e.apolice_fk = '" + criterionApolices.get(0) + "'";
                for (int i = 1; i < criterionApolices.size(); i++) {
                    sql += " OR e.apolice_fk = '" + criterionApolices.get(i) + "'";
                }
            }
        }

        sql += " group by (a.apolice) order by (qnt)";
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {
            a = new Apolice();
            a.setApolice(rs.getString("apolice"));
            a.setQntOcorrido(rs.getLong("qnt"));
            lista.add(a);
        }
        rs.close();
        s.close();
        return lista;
    }

    public List<Apolice> filtroDadosGrafico2(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Apolice> lista = new ArrayList<Apolice>();
        Apolice a;
        String sql = "select count(e.id) qnt, a.apolice from evento e join apolice a on (a.id = e.apolice_fk)";

        List<String> criterionApolices = (ArrayList<String>) criteria.get(CRITERION_APOLICES);
        if (criterionApolices != null && criterionApolices.size() > 0) {
            sql += " AND e.apolice_fk = '" + criterionApolices.get(0) + "'";
            for (int i = 1; i < criterionApolices.size(); i++) {
                sql += " OR e.apolice_fk = '" + criterionApolices.get(i) + "'";
            }
        }

        return lista;
    }

    @Override
    public void update(Apolice e, Connection conn) throws Exception {
        String sql = "UPDATE apolice SET apolice=?, sypcode = ? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, e.getApolice());
        ps.setString(++i, e.getSypCode());
        ps.setLong(++i, e.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        Statement st = conn.createStatement();
        st.execute("DELETE FROM apolice WHERE id =" + id + ";");
        st.close();
    }
}
