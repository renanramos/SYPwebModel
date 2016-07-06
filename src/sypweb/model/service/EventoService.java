package sypweb.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import sypweb.model.ConnectionManager;
import sypweb.model.base.service.BaseEventoService;
import sypweb.model.dao.EventoDAO;
import sypweb.model.pojo.Evento;

public class EventoService implements BaseEventoService{

    @Override
    public void create(Evento e) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Evento readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Evento evento = null;
        try {
            EventoDAO dao = new EventoDAO();
            evento = dao.readById(id, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
        return evento;
    }

    @Override
    public List<Evento> readByCriteria(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Evento> lista;
        try {
            EventoDAO dao = new EventoDAO();
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
    public void update(Evento e) throws Exception {
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
