package sypweb.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import sypweb.model.ConnectionManager;
import sypweb.model.base.service.BaseMensagemService;
import sypweb.model.dao.MensagemApoliceDAO;
import sypweb.model.dao.MensagemDAO;
import sypweb.model.pojo.Mensagem;
import sypweb.model.pojo.MensagemApolice;

public class MensagemService implements BaseMensagemService{

    @Override
    public void create(Mensagem e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            MensagemDAO dao = new MensagemDAO();
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
    public Mensagem readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Mensagem mensagem = null;
        try {
            MensagemDAO dao = new MensagemDAO();
            mensagem = dao.readById(id, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
        return mensagem;
    }
    /*
    @Override
    public List<Mensagem> readByAll() throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Mensagem> lista = null;
        try {
            MensagemDAO dao = new MensagemDAO();            
            lista = dao.readByAll(conn);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return (List) lista;
    }
*/
    @Override
    public List<Mensagem> readByCriteria(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Mensagem> lista = null;
        try {
            MensagemDAO dao = new MensagemDAO();            
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
    public void update(Mensagem e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            MensagemDAO dao = new MensagemDAO();
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
            MensagemDAO dao = new MensagemDAO();
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

    @Override
    public List<Mensagem> readByAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
