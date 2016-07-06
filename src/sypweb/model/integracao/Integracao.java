package sypweb.model.integracao;


import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.commons.mail.EmailException;
//import org.apache.commons.mail.SimpleEmail;
import sypseguradora.model.pojo.Apolice;
import sypseguradora.model.pojo.Corretor;
import sypseguradora.model.pojo.Segurado_PF;
import sypseguradora.model.pojo.Segurado_PJ;
import sypweb.model.ServiceLocator;
import sypweb.model.pojo.Usuario;

public class Integracao {

    GeraSenha senha = new GeraSenha();

    public Integracao() {
        sypweb.model.pojo.Usuario usuario = new sypweb.model.pojo.Usuario();
    }

    public boolean insereCorretor(Corretor corretor) throws Exception {

        boolean chave = false;

        sypweb.model.pojo.Usuario usuario = new sypweb.model.pojo.Usuario();

        //verifica se o objeto não está vazio
        if (corretor != null) {

            usuario.setNome(corretor.getUsuario().getNome());
            usuario.setEmail(corretor.getUsuario().getEmail());
//            usuario.setDataHoraCadastro(new Date(123));// verificar como ficará a data de cadastro
            usuario.setPermissao("corretor");
            usuario.setSenha(senha.getSenha());// senha padrão, implementar a possibilidade de troca de senha no 1º acesso
            usuario.setTipo("corretor");
            //usuario.setStatus(false);
            usuario.setRegSusep(corretor.getRegistro());

            try {
                ServiceLocator.getUsuarioService().enviarEmail(usuario);
                ServiceLocator.getUsuarioService().create(usuario);//insere no banco sypweb
//                enviarEmail(usuario);
                chave = true;//retorna verdadeiro para a classe CorretorCreateAction
            } catch (Exception ex) {
                chave = false;
            }

        } else {
            chave = false;//retorna falso para a classe CorretorCreateAction
        }
        return chave;
    }   

//    public void enviarEmail(Usuario usuario) throws EmailException {
//        SimpleEmail email = new SimpleEmail();
//        email.setHostName("smtp.gmail.com");
////        email.setSmtpPort(465);
//        email.setAuthentication("sypseguros@gmail.com", "si2014syp");
//        email.setSSLOnConnect(true);
//        try {
//            email.setFrom("sypseguros@gmail.com");
//        } catch (EmailException ex) {
//            ex.printStackTrace();
//        }
//        email.setSubject("SYP Seguros - Informa");
//        try {
//            email.addTo(usuario.getEmail());//
//        } catch (EmailException ex) {
//            ex.printStackTrace();
//        }
//        try {
//            email.setMsg("<a href=\"http://localhost:8084/SYPweb/RedefinirSenha.mtw=?email=" + usuario.getEmail() + "&senha=" + usuario.getSenha() + "\">RedefinirSenha</a>");           
//        } catch (EmailException ex) {
//            ex.printStackTrace();
//        }
//        try {
//            email.send();
//        } catch (EmailException ex) {
//            ex.printStackTrace();
//        }
//    }
}

