package sypweb.model.base.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import sypweb.model.base.BaseService;
import sypweb.model.pojo.TipoEvento;

public interface BaseTipoEventoService extends BaseService<TipoEvento> {        
    public List<TipoEvento> filtroByCriteria(Map<String, Object> criteria)throws Exception;    
}
