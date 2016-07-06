package sypweb.model.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.apache.commons.mail.HtmlEmail;
//import org.apache.commons.mail.SimpleEmail;

import sypweb.model.ConnectionManager;
import sypweb.model.base.service.BaseUsuarioService;
import sypweb.model.dao.UsuarioDAO;
import sypweb.model.pojo.Usuario;

public class UsuarioService implements BaseUsuarioService {

    @Override
    public void create(Usuario e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
            e.setSenha(encode(e.getSenha()));
            dao.create(e, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
    }

    @Override
    public Usuario readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Usuario usuario = null;
        try {
            UsuarioDAO dao = new UsuarioDAO();
            usuario = dao.readById(id, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
        return usuario;
    }

    @Override
    public List<Usuario> readByCriteria(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Usuario> lista;
        try {
            UsuarioDAO dao = new UsuarioDAO();
            lista = dao.readByCriteria(criteria, conn);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return (List) lista;
    }

    @Override
    public void update(Usuario e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
            dao.update(e, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
            dao.delete(id, conn);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Usuario login(String usuario, String senha) {
        Usuario usuarioLogado = null;
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();
            UsuarioDAO dao = new UsuarioDAO();
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put(UsuarioDAO.CRITERION_USUARIO_EQ, usuario);
            criteria.put(UsuarioDAO.CRITERION_SENHA_EQ, senha);
            List<Usuario> usuarios = dao.readByCriteria(criteria, conn);
            if (usuarios != null && usuarios.size() == 1) {
                usuarioLogado = usuarios.get(0);
                if (!usuarioLogado.getNome().equals(usuario)) {
                    usuarioLogado = null;
                } else {
                    if (!usuarioLogado.getSenha().equals(senha)) {
                        usuarioLogado = null;
                    }
                }
            }
            conn.close();
        } catch (Exception e) {
        }
        return usuarioLogado;
    }

    @Override
    public String encode(String input) {
        input += "sypseguros";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            return number.toString(16);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Map<String, String> validateForCreate(Map<String, Object> properties) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> properties) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void recuperaSenhaEmail(Usuario e) throws Exception {
//        SimpleEmail email = new SimpleEmail();
//        email.setHostName("smtp.gmail.com");
////        email.setSmtpPort(465);
//        email.setAuthentication("sypseguros@gmail.com", "si2014syp");
//        email.setSSLOnConnect(true);
//        email.setFrom("sypseguros@gmail.com");
//        email.setSubject("SYP Seguros - Informa");
//        email.addTo(e.getEmail());//e.getEmail()
//        email.setMsg("Use este link para recuperar sua senha: " + "http://localhost:8084/SYPweb/RedefinirSenha.mtw=?email=" + e.getEmail() + "&senha=" + e.getSenha() + "");
//        email.send();
    }

    public void enviarEmail(Usuario e) throws Exception {

        String html = "<html>\n"
                + "	<head>\n"
                + "		<title>Editor HTML Online</title>\n"
                + "	</head>\n"
                + "	<body>\n"
                + "		<div style=\"text-align: left;\">\n"
                + "			<span style=\"font-family:lucida sans unicode,lucida grande,sans-serif;\"><span style=\"background-color:#afeeee;\">SYP Seguros</span></span></div>\n"
                + "		<div style=\"text-align: left;\">\n"
                + "			&nbsp;</div>\n"
                + "		<div style=\"text-align: left;\">\n"
                + "			<span style=\"font-family:lucida sans unicode,lucida grande,sans-serif;\">Bem vindo <b>" + e.getNome() + "</b>!</span></div>\n"
                + "		<div style=\"text-align: left;\">\n"
                + "			&nbsp;</div>\n"
                + "		<div style=\"text-align: left;\">\n"
                + "			<span style=\"font-size:14px;\"><span style=\"font-family:lucida sans unicode,lucida grande,sans-serif;\">"
                + "Abaixo estão seus dados para primeiro acesso. Posteriormente você deverá alterar sua senha.</span></span></div>\n"
                + "		<div style=\"text-align: left;\">\n"
                + "			&nbsp;</div>\n"
                + "		\n"
                + "			<span>Usuário: </span>" + e.getNome() + "<br> <span>Senha: </span>" + e.getSenha() + ""
                + "		<div style=\"text-align: left;\">\n"
                + "			&nbsp;</div>\n"
                + "		<div style=\"text-align: left;\">\n"
                + "			<span style=\"font-size:14px;\"><span style=\"font-family:lucida sans unicode,lucida grande,sans-serif;\">Atenciosamente,</span></span></div>\n"
                + "		<div style=\"text-align: left;\">\n"
                + "			&nbsp;</div>\n"
                + "		<div style=\"text-align: left;\">\n"
                + "			<strong><span style=\"font-size:14px;\"><span style=\"font-family:lucida sans unicode,lucida grande,sans-serif;\">Equipe SYP Seguros</span></span></strong></div>\n"
                + "		<div style=\"text-align: left;\">\n"
                + "			<span style=\"font-size:14px;\"><span style=\"font-family:lucida sans unicode,lucida grande,sans-serif;\"><span style=\"color:#0000ff;\"><u>www.sypseguros.com:8080</u></span></span></span></div>\n"
                + "		<div style=\"text-align: left;\">\n"
                + "			&nbsp;</div>\n"
                + "	</body>\n"
                + "</html>";


//        HtmlEmail mail = new HtmlEmail();
//        mail.setHostName("smtp.gmail.com");
//        mail.setAuthentication("sypseguros@gmail.com", "si2014syp");
//        mail.setSSLOnConnect(true);
//        mail.setFrom("sypseguros@gmail.com");
//        mail.setSubject("SYP Seguros - Informa");
//        mail.addTo(e.getEmail());//e.getEmail()
//        mail.setHtmlMsg(html);
//        mail.send();
    }
}
