package sypweb.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import sypweb.model.ConnectionManager;
import sypweb.model.base.service.BaseApoliceService;
import sypweb.model.dao.ApoliceDAO;
import sypweb.model.pojo.Apolice;

public class ApoliceService implements BaseApoliceService {

    @Override
    public void create(Apolice e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ApoliceDAO dao = new ApoliceDAO();
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
    public Apolice readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Apolice apolice = null;
        try {
            ApoliceDAO dao = new ApoliceDAO();
            apolice = dao.readById(id, conn);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
        return apolice;
    }

    @Override
    public List<Apolice> readByCriteria(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Apolice> lista = null;
        try {
            ApoliceDAO dao = new ApoliceDAO();
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
    public List<Apolice> readDadosGrafico2(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Apolice> lista = null;
        try {
            ApoliceDAO dao = new ApoliceDAO();
            lista = dao.readByGrafico2(criteria, conn);
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
    public List<Apolice> filtroDadosGrafico2(Map<String, Object> criteria) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Apolice> lista = null;
        try{
            ApoliceDAO dao = new ApoliceDAO();
            //lista = dao.
        }catch(Exception ex){
            conn.rollback();
            conn.close();
            throw ex;
        }        
        return (List) lista;
    }    

    @Override
    public void update(Apolice e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ApoliceDAO dao = new ApoliceDAO();
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
            ApoliceDAO dao = new ApoliceDAO();
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
