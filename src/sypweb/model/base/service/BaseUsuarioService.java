package sypweb.model.base.service;

import sypweb.model.base.BaseService;
import sypweb.model.pojo.Usuario;

public interface BaseUsuarioService extends BaseService<Usuario> {

    public String encode(String input) throws Exception;

    public Usuario login(String usuario, String senha) throws Exception;
    
     public void recuperaSenhaEmail(Usuario e) throws Exception;
     
     public void enviarEmail(Usuario e) throws Exception;
}
