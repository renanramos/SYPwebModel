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
import static sypweb.model.dao.MensagemDAO.CRITERION_DESC_I_LIKE;
import static sypweb.model.dao.MensagemDAO.CRITERION_USUARIO;
import sypweb.model.pojo.Mensagem;
import sypweb.model.pojo.TipoEvento;
import sypweb.model.pojo.Usuario;

public class MensagemDAO implements BaseDAO<Mensagem>{

    public static final String CRITERION_DESC_I_LIKE = "s";
    public static final String CRITERION_USUARIO = "2";
    public static final String CRITERION_ALL = "1";
    
    @Override
    public void create(Mensagem e, Connection conn) throws Exception {
        String sql = "INSERT INTO mensagem(descricao, usuario_fk) VALUES (?, ?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);        
        int i = 0;
        ps.setString(++i, e.getDescricao());
        ps.setLong(++i, e.getUsuario().getId());
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            e.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }
        
    @Override
    public Mensagem readById(Long id, Connection conn) throws Exception {
        Mensagem e = new Mensagem();
        String sql = "SELECT * FROM mensagem WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Usuario usuario = ServiceLocator.getUsuarioService().readById(rs.getLong("usuario_fk"));
            
            e.setId(rs.getLong("id"));
            e.setDescricao(rs.getString("descricao"));            
            e.setUsuario(usuario);
            e.setDataHoraCadastro(rs.getTimestamp("data_hora_cadastro"));                        
        }
        rs.close();
        ps.close();
        return e;
    }
    
    @Override
    public List<Mensagem> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        List<Mensagem> lista = new ArrayList<Mensagem>();
        String sql = "select count(ma.mensagem_fk) total, m.* from mensagem m left join msgapolice ma "
                    +"on (m.id = ma.mensagem_fk) where (1=1) ";        
        
        String criterionDescILike = (String) criteria.get(CRITERION_DESC_I_LIKE);
        if (criterionDescILike != null && !criterionDescILike.trim().isEmpty()) {
            sql += " AND descricao ILIKE '%" + criterionDescILike + "%'";
        }
        
        Long criterionUsuario = (Long) criteria.get(CRITERION_USUARIO);
        if (criterionUsuario != null && criterionUsuario > 0) {
            sql += " AND usuario_fk = '" + criterionUsuario + "'";
        }
        sql += " group by (m.id)";
        
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {            
            Usuario usuario = ServiceLocator.getUsuarioService().readById(rs.getLong("usuario_fk"));
            
            Mensagem e = new Mensagem();            
            e.setId(rs.getLong("id"));
            e.setDescricao(rs.getString("descricao"));
            e.setDataHoraCadastro(rs.getTimestamp("data_hora_cadastro"));                        
            e.setEnviada(rs.getLong("total"));
            e.setUsuario(usuario);
            lista.add(e);
        }
        rs.close();
        s.close();
        return lista;
    }

    @Override
    public void update(Mensagem e, Connection conn) throws Exception {
        String sql = "UPDATE mensagem SET descricao=? WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, e.getDescricao());           
        ps.setLong(++i, e.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        Statement st = conn.createStatement();
        st.execute("DELETE FROM mensagem WHERE id =" + id + ";");
        st.close();                
    }

}