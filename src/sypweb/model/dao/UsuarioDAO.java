package sypweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sypweb.model.base.BaseDAO;
import sypweb.model.pojo.TipoEvento;
import sypweb.model.pojo.Usuario;

public class UsuarioDAO implements BaseDAO<Usuario> {

    public static final String TIPO_CORRETOR = "corretor";
    public static final String TIPO_SEGURADOPF = "seguradopf";
    public static final String TIPO_SEGURADOPJ = "seguradopj";
    public static final String TIPO_ADMINISTRADOR = "admin";
        
    public static final String CRITERION_NOME_I_LIKE = "1";
    public static final String CRITERION_USUARIO_EQ = "2";
    public static final String CRITERION_SENHA_EQ = "3";
    public static final String CRITERION_EMAIL_I_LIKE = "4";
    
    @Override
    public void create(Usuario e, Connection conn) throws Exception {
        String sql = "INSERT INTO usuario(nome, tipo, senha, email, permissao, regsusep, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, e.getNome());
        ps.setString(++i, e.getTipo());
        ps.setString(++i, e.getSenha());
        ps.setString(++i, e.getEmail());
        ps.setString(++i, e.getPermissao());
        ps.setString(++i, e.getRegSusep());
        ps.setBoolean(++i, false);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            e.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public Usuario readById(Long id, Connection conn) throws Exception {
        Usuario e = new Usuario();
        String sql = "SELECT * FROM usuario WHERE id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            e.setId(rs.getLong("id"));
            e.setNome(rs.getString("nome"));
            e.setTipo(rs.getString("tipo"));
            e.setEmail(rs.getString("email"));
            e.setSenha(rs.getString("senha"));
            e.setDataHoraCadastro(rs.getTimestamp("data_hora_cadastro"));
            e.setPermissao(rs.getString("permissao"));
            e.setRegSusep(rs.getString("regsusep"));
            e.setStatus(rs.getBoolean("status"));
        }
        rs.close();
        ps.close();
        return e;
    }

    @Override
    public List<Usuario> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
        List<Usuario> lista = new ArrayList<Usuario>();
        String sql = "SELECT * FROM usuario WHERE 1=1 ";

        String criterionNome = (String) criteria.get(CRITERION_NOME_I_LIKE);
        if (criterionNome != null && !criterionNome.trim().isEmpty()) {
            sql += " AND nome ilike '" + criterionNome + "'";
        }

        String criterionUsuario = (String) criteria.get(CRITERION_USUARIO_EQ);
        if (criterionUsuario != null && !criterionUsuario.trim().isEmpty()) {
            sql += " AND nome ilike '" + criterionUsuario + "'";
        }

        String criterionSenha = (String) criteria.get(CRITERION_SENHA_EQ);
        if (criterionSenha != null && !criterionSenha.trim().isEmpty()) {
            sql += " AND senha = '" + criterionSenha + "'";
        }

        String criterionEmail = (String) criteria.get(CRITERION_EMAIL_I_LIKE);
        if(criterionEmail != null && !criterionEmail.trim().isEmpty()){
            sql+= " AND email ilike '"+ criterionEmail +"'";
        }
        
        Usuario e = null;

        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {
            e = new Usuario();
            e.setId(rs.getLong("id"));
            e.setNome(rs.getString("nome"));
            e.setEmail(rs.getString("email"));
            e.setSenha(rs.getString("senha"));
            e.setDataHoraCadastro(rs.getTimestamp("data_hora_cadastro"));
            e.setPermissao(rs.getString("permissao"));
            e.setRegSusep(rs.getString("regsusep"));
            e.setStatus(rs.getBoolean("status"));
            e.setTipo(rs.getString("tipo"));

            lista.add(e);
        }
        rs.close();
        s.close();
        return lista;
    }

    @Override
    public void update(Usuario e, Connection conn) throws Exception {
        String sql = "UPDATE usuario SET nome=?, tipo=?, email = ?, senha = ?, permissao=?, regsusep=?, status=? WHERE id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, e.getNome());
        ps.setString(++i, e.getTipo());
        ps.setString(++i, e.getEmail());
        ps.setString(++i, e.getSenha());
        ps.setString(++i, e.getPermissao());
        ps.setString(++i, e.getRegSusep());
        ps.setBoolean(++i, e.getStatus());
        ps.setLong(++i, e.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Long id, Connection conn) throws Exception {
        Statement st = conn.createStatement();
        st.execute("DELETE FROM usuario WHERE id =" + id + ";");
        st.close();
    }
    
}
