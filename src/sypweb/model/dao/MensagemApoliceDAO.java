package sypweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sypweb.model.ServiceLocator;
import sypweb.model.base.BaseDAO;
import static sypweb.model.dao.MensagemApoliceDAO.CRITERION_APOLICE;
import static sypweb.model.dao.MensagemApoliceDAO.CRITERION_CORRETOR;
import static sypweb.model.dao.MensagemApoliceDAO.CRITERION_MENSAGEM;
import sypweb.model.pojo.Apolice;
import sypweb.model.pojo.Mensagem;
import sypweb.model.pojo.MensagemApolice;
import sypweb.model.pojo.TipoEvento;
import sypweb.model.pojo.Usuario;

public class MensagemApoliceDAO implements BaseDAO<MensagemApolice> {

    public static final String CRITERION_APOLICE = "1";
    public static final String CRITERION_MENSAGEM = "2";
    public static final String CRITERION_MENSAGEM_I_LIKE = "3";
    public static final String CRITERION_CORRETOR = "4";
    public static final String CRITERION_ALL = "5";

    @Override
    public void create(MensagemApolice e, Connection conn) throws Exception {
        String sql = "INSERT INTO msgapolice(mensagem_fk, apolice_fk) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, e.getMensagem().getId());
        ps.setLong(++i, e.getApolice().getId());
        ps.execute();
        ps.close();
    }

    @Override
    public MensagemApolice readById(Long id, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<MensagemApolice> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        List<MensagemApolice> lista = new ArrayList<MensagemApolice>();
        String sql = "SELECT a.id id_apolice, a.apolice, a.sypcode, a.data_hora_cadastro data_hora_cadastro_apolice, "
                + "m.id id_mensagem, m.descricao, m.data_hora_cadastro data_hora_cadastro_msg, m.usuario_fk, "
                + "ma.data_hora_coleta data_hora_coleta "
                + "FROM msgapolice ma join mensagem m on ma.mensagem_fk = m.id join apolice a "
                + "on ma.apolice_fk = a.id WHERE 1=1";

        Long criterionApolice = (Long) criteria.get(CRITERION_APOLICE);
        if (criterionApolice != null && criterionApolice > 0) {
            sql += " AND a.id = '" + criterionApolice + "'";
        }

        Long criterionMensagem = (Long) criteria.get(CRITERION_MENSAGEM);
        if (criterionMensagem != null && criterionMensagem > 0) {
            sql += " AND m.id = '" + criterionMensagem + "'";
        }

        String criterionMensagemILike = (String) criteria.get(CRITERION_MENSAGEM_I_LIKE);
        if (criterionMensagemILike != null && !criterionMensagemILike.isEmpty()) {
            sql += " AND m.descricao ilike '%" + criterionMensagemILike + "%'";
        }

        Long criterionCorretor = (Long) criteria.get(CRITERION_CORRETOR);
        if (criterionCorretor != null && criterionCorretor > 0) {
            sql += " AND m.usuario_fk = '" + criterionCorretor + "'";
        }

        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {
            Apolice a = new Apolice();
            a.setId(rs.getLong("id_apolice"));
            a.setApolice(rs.getString("apolice"));
            a.setSypCode(rs.getString("sypcode"));
            a.setDataHoraCadastro(rs.getTimestamp("data_hora_cadastro_apolice"));

            Mensagem m = new Mensagem();
            m.setId(rs.getLong("id_mensagem"));
            m.setDescricao(rs.getString("descricao"));
            Usuario u = ServiceLocator.getUsuarioService().readById(rs.getLong("usuario_fk"));
            m.setUsuario(u);

            MensagemApolice e = new MensagemApolice();
            e.setDataHoraColeta(rs.getTimestamp("data_hora_coleta"));
            e.setApolice(a);
            e.setMensagem(m);
            lista.add(e);
        }
        rs.close();
        s.close();
        return lista;

    }

    @Override
    public void update(MensagemApolice e, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}