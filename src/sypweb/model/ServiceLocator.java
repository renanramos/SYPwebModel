package sypweb.model;

import sypweb.model.base.service.BaseApoliceService;
import sypweb.model.base.service.BaseEventoService;
import sypweb.model.base.service.BaseMensagemApoliceService;
import sypweb.model.base.service.BaseMensagemService;
import sypweb.model.base.service.BaseTipoEventoService;
import sypweb.model.base.service.BaseUsuarioService;
import sypweb.model.service.ApoliceService;
import sypweb.model.service.EventoService;
import sypweb.model.service.MensagemApoliceService;
import sypweb.model.service.MensagemService;
import sypweb.model.service.TipoEventoService;
import sypweb.model.service.UsuarioService;

public class ServiceLocator {

    public static BaseApoliceService getApoliceService() {
        return new ApoliceService();
    }    
    
    public static BaseEventoService getEventoService() {
        return new EventoService();
    }    
    
    public static BaseMensagemApoliceService getMensagemApoliceService() {
        return new MensagemApoliceService();
    }
    
    public static BaseMensagemService getMensagemService() {
        return new MensagemService();
    }
    
    public static BaseUsuarioService getUsuarioService() {
        return new UsuarioService();
    }
    
    public static BaseTipoEventoService getTipoEventoService() {
        return new TipoEventoService();
    }
}
