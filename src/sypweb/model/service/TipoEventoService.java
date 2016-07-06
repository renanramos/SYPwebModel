package sypweb.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import sypweb.model.ConnectionManager;
import sypweb.model.base.service.BaseTipoEventoService;
import sypweb.model.dao.TipoEventoDAO;
import sypweb.model.pojo.TipoEvento;

public class TipoEventoService implements BaseTipoEventoService{

    @Override
    public void create(TipoEvento e) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TipoEvento readById(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TipoEvento> readByCriteria(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<TipoEvento> lista;
        try {
            TipoEventoDAO dao = new TipoEventoDAO();
            lista = dao.readByCriteria(criteria, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
        return lista;
    }

    @Override
    public List<TipoEvento> filtroByCriteria(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<TipoEvento> lista;
        try{
            TipoEventoDAO dao = new TipoEventoDAO();
            lista = null;///dao.filtroByCriteria(criteria, conn);
            conn.commit();
            conn.close();
        }catch(Exception ex){
            conn.rollback();
            conn.close();
            throw ex;
        }                
        return lista;
    }
    

    @Override
    public void update(TipoEvento e) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, String> validateForCreate(Map<String, Object> properties) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> properties) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
