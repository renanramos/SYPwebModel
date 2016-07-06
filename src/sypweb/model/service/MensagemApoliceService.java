package sypweb.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import sypweb.model.ConnectionManager;
import sypweb.model.base.service.BaseMensagemApoliceService;
import sypweb.model.dao.MensagemApoliceDAO;
import sypweb.model.pojo.MensagemApolice;

public class MensagemApoliceService implements BaseMensagemApoliceService{

    @Override
    public void create(MensagemApolice e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            MensagemApoliceDAO dao = new MensagemApoliceDAO();
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
    public MensagemApolice readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        MensagemApolice msgApolice = null;
        try {
            MensagemApoliceDAO dao = new MensagemApoliceDAO();
            msgApolice = dao.readById(id, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
        return msgApolice;
    }

    @Override
    public List<MensagemApolice> readByCriteria(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<MensagemApolice> lista;
        try {
            MensagemApoliceDAO dao = new MensagemApoliceDAO();            
            lista = dao.readByCriteria(criteria, conn);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return lista;
    }

    @Override
    public void update(MensagemApolice e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            MensagemApoliceDAO dao = new MensagemApoliceDAO();
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
            MensagemApoliceDAO dao = new MensagemApoliceDAO();
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
    public Map<String, String> validateForCreate(Map<String, Object> properties) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> properties) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
