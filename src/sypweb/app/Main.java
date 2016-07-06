package sypweb.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sypweb.model.ServiceLocator;
import sypweb.model.dao.MensagemApoliceDAO;
import sypweb.model.dao.MensagemDAO;
import sypweb.model.pojo.Mensagem;
import sypweb.model.pojo.MensagemApolice;
import sypweb.model.pojo.Usuario;
import sypweb.model.service.MensagemApoliceService;
import sypweb.model.service.MensagemService;

public class Main {

    public static void main(String[] args) throws Exception {        

    //APOLICE
       
//    ApoliceService obj = (ApoliceService) ServiceLocator.getApoliceService();
//    Apolice a = new Apolice();
    /* 
    a.setApolice("1111INSERT");
    a.setSypCode("11SYPINSERT");
    obj.create(a);
    */
    
    /*a.setApolice("111SYPupdate");
    a.setSypCode("11SYPup");
    a.setId(3l);
    obj.update(a);
    */
    /*
    a = obj.readById(3l);
    System.out.println("APOLICE "+a.getApolice());
    System.out.println("SYP "+a.getSypCode());
    System.out.println("DATA "+a.getDataHoraCadastro());
    System.out.println("ID "+a.getId());
    */
//    
//    Map<String, Object> criteria = new HashMap<String, Object>();
//    criteria.put(ApoliceDAO.CRITERION_APOLICE, "");
//    List<Apolice> lista = obj.readByCriteria(criteria);
//    
//    for (int i=0;i<lista.size();i++){
//        a = lista.get(i);
//        System.out.println("APOLICE "+a.getApolice());
//        System.out.println("SYP "+a.getSypCode());
//        System.out.println("DATA "+a.getDataHoraCadastro());
//        System.out.println("SYP Code Status " + a.getSypCodeStatus());
//        System.out.println("ID "+a.getId());
//        System.out.println("");
//    }
//    
    /*
    Map<String, Object> criteria = new HashMap<String, Object>();
    criteria.put(ApoliceDAO.CRITERION_SYPCODE, "11SYPup");
    List<Apolice> lista = obj.readByCriteria(criteria);
    
    for (int i=0;i<lista.size();i++){
        a = lista.get(i);
        System.out.println("APOLICE "+a.getApolice());
        System.out.println("SYP "+a.getSypCode());
        System.out.println("DATA "+a.getDataHoraCadastro());
        System.out.println("ID "+a.getId());
    }
    
    obj.delete(3l);
    */
        
        
        
        
    //EVENTO
    /*
    EventoService obj = (EventoService) ServiceLocator.getEventoService();
    Evento e = new Evento();
    
    e = obj.readById(1l);
    System.out.println("APOLICE "+e.getApolice().getApolice());
    System.out.println("LATITUDE "+e.getLatitude());
    System.out.println("LONGETUDE "+e.getLongitude());
    System.out.println("PRECISAO "+e.getPrecisao());
    System.out.println("TIPOEVENTO "+e.getTipoEvento().getNome());
    System.out.println("DATA "+e.getDataHoraCadastro());
    */
    
    
    /*
    Map<String, Object> criteria = new HashMap<String, Object>();
    criteria.put(EventoDAO.CRITERION_APOLICE, 1l);
    List<Evento> lista = obj.readByCriteria(criteria);
    
    for (int i=0;i<lista.size();i++){
        e = lista.get(i);
        System.out.println("APOLICE "+e.getApolice().getApolice());
        System.out.println("LATITUDE "+e.getLatitude());
        System.out.println("LONGETUDE "+e.getLongitude());
        System.out.println("PRECISAO "+e.getPrecisao());
        System.out.println("TIPOEVENTO "+e.getTipoEvento().getNome());
        System.out.println("DATA "+e.getDataHoraCadastro());
    }
//    */
//    EventoService obj = (EventoService) ServiceLocator.getEventoService();
//    Evento e = new Evento();
//    
//    Map<String, Object> criteria = new HashMap<String, Object>();
//    criteria.put(EventoDAO.CRITERION_ALL, "");
//    List<Evento> lista = obj.readByCriteria(criteria);
//    
//    for (int i=0;i<lista.size();i++){
//        e = lista.get(i);
//        System.out.println("APOLICE "+e.getApolice().getApolice());
//        System.out.println("LATITUDE "+e.getLatitude());
//        System.out.println("LONGETUDE "+e.getLongitude());
//        System.out.println("PRECISAO "+e.getPrecisao());
//        System.out.println("TIPOEVENTO "+e.getTipoEvento().getNome());
//        System.out.println("DATA "+e.getDataHoraCadastro());
//    }
    
    
    //MENSAGEM
//   
//    MensagemService obj = (MensagemService) ServiceLocator.getMensagemService();
//    Mensagem m = new Mensagem();
//    
//    Usuario u = new Usuario();
//    u.setId(1l);
//    
//    m.setDescricao("Mensagem padrão para teste CREATE");
//    m.setUsuario(u);
//    obj.create(m);
   
    /*
    Usuario u = new Usuario();
    u.setId(2l);
    m.setId(2l);
    m.setDescricao("TESTE UPDATE");
    m.setUsuario(u);
    obj.update(m);
    */
    
    /*    
    m = obj.readById(2l);
    System.out.println("DESCRICAO "+ m.getDescricao());
    System.out.println("USUARIO "+ m.getUsuario().getNome());
    */
    
    /*
    List<Mensagem> lista = obj.readByAll();
    
    for (int i=0;i<lista.size();i++){
        m = lista.get(i);
        System.out.println("DESCRICAO "+ m.getDescricao());
        System.out.println("USUARIO "+ m.getUsuario().getNome());
    }
    */
    /*
    obj.delete(2l);
    */
    
    //MENSAGEM APOLICE
    
    MensagemApoliceService obj = (MensagemApoliceService) ServiceLocator.getMensagemApoliceService();
    MensagemApolice ma = new MensagemApolice();
    Map<String, Object> criteria = new HashMap<String, Object>();
//    criteria.put(MensagemApoliceDAO.CRITERION_APOLICE, 1l);
//    List<MensagemApolice> lista = obj.readByCriteria(criteria);
    List<MensagemApolice> mensagem = new ArrayList<MensagemApolice>();       
    criteria.put(MensagemApoliceDAO.CRITERION_ALL, "");
    mensagem = ServiceLocator.getMensagemApoliceService().readByCriteria(criteria);
    MensagemApolice m = new MensagemApolice();
    
    for (int i=0;i<mensagem.size();i++){
        m = mensagem.get(i);
        System.out.println(m.getDataHoraColeta());
        System.out.println(m.getApolice());
        System.out.println(m.getMensagem());
        System.out.println("");
    }
    
    /*
    Mensagem m = new Mensagem();
    m.setId(1l);
    
    Apolice a = new Apolice();
    a.setId(1l);
    
    ma.setApolice(a);
    ma.setMensagem(m);
    obj.create(ma);
    */
    /*
    Map<String, Object> criteria = new HashMap<String, Object>();
    criteria.put(MensagemApoliceDAO.CRITERION_APOLICE, 1l);
    List<MensagemApolice> lista = obj.readByCriteria(criteria);

    for (int i=0;i<lista.size();i++){
        ma = lista.get(i);
        System.out.println("APOLICE "+ma.getApolice().getId());
        System.out.println("MENSAGEM "+ma.getMensagem().getId());
        System.out.println("MENSAGEM "+ma.getMensagem().getDescricao());
    }
    

    
    //USUARIO
            
//    UsuarioService obj = (UsuarioService) ServiceLocator.getUsuarioService();
//    Usuario u = new Usuario();
//    
//    u.setNome("Teste INSERT");
//    u.setEmail("renan.ramos598@gmail.com");
//    u.setSenha("1231");
//    u.setRegSusep("112");
//    u.setStatus(Boolean.TRUE);
//    u.setPermissao("admin");
//    u.setTipo("a");
//    
//    
//    
    /*
    u.setId(9l);
    u.setNome("NOVONOME");
    u.setEmail("novoemail");
    u.setRegSusep(11l);
    u.setStatus(Boolean.TRUE);
    obj.update(u);
    */
    /*
    u = obj.readById(8l);
    System.out.println("NOME "+u.getNome());
    System.out.println("EMAIL "+u.getEmail());
    System.out.println("SENHA "+u.getSenha());
    System.out.println("RESUSEP "+u.getRegSusep());
    System.out.println("STATUS "+u.getStatus());
    */
    /*
    Map<String, Object> criteria = new HashMap<String, Object>();
    criteria.put(UsuarioDAO.CRITERION_NOME_I_LIKE, "Teste");
    List<Usuario> lista = obj.readByCriteria(criteria);
    
    for (int i=0;i<lista.size();i++){
        u = lista.get(i);
        System.out.println("NOME "+u.getNome());
        System.out.println("EMAIL "+u.getEmail());
        System.out.println("SENHA "+u.getSenha());
        System.out.println("RESUSEP "+u.getRegSusep());
        System.out.println("STATUS "+u.getStatus());
    }
    */
    /*
    obj.delete(10l);
    */   
//    UsuarioService obj = (UsuarioService) ServiceLocator.getUsuarioService();
//    obj.enviarEmail(u);
        
            
//    Map<String, Object> criteria = new HashMap<String, Object>();
//    criteria.put(MensagemApoliceDAO.CRITERION_MENSAGEM, 1l);
//    List<MensagemApolice> lista = obj.readByCriteria(criteria);
//
//    for (int i=0;i<lista.size();i++){
//        ma = lista.get(i);
//        System.out.println("APOLICE "+ma.getApolice().getId());
//        System.out.println("MENSAGEM "+ma.getMensagem().getId());
//        System.out.println("MENSAGEM "+ma.getMensagem().getDescricao());
//        System.out.println(""+ma.getMensagem().getDataHoraCadastro());
//    }
    
//        TipoEvento e = new TipoEvento();
//        TipoEventoService ob = new TipoEventoService();
//        Map<String, Object> criteria = new HashMap<String, Object>();
//        criteria.put(TipoEventoDAO.CRITERION_DATA_INI, "2014-10-07");
//        criteria.put(TipoEventoDAO.CRITERION_DATA_FIM, "2014-10-20");
//        List<TipoEvento> lista = ob.readByCriteria(criteria);
//        
//        for(int i = 0; i < lista.size(); i++){
//            e = lista.get(i);
//            System.out.println(e.getNome()+" "+ e.getQntOcorrido());            
//        }
        
//        Apolice e = new Apolice();
//        ApoliceService ob = new ApoliceService();
//        Map<String, Object> criteria = new HashMap<String, Object>();
//        criteria.put(ApoliceDAO.CRITERION_DATA_INI, "2014-10-07");
//        criteria.put(ApoliceDAO.CRITERION_DATA_FIM, "2014-10-20");
//        List<Apolice> lista = ob.readDadosGrafico2(criteria);
//        
//        for(int i = 0; i < lista.size(); i++){
//            e = lista.get(i);
//            System.out.println(e.getApolice()+" "+ e.getQntOcorrido());            
//        }
    
    }
     
}
