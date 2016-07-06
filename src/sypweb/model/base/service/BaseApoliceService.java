package sypweb.model.base.service;

import java.util.List;
import java.util.Map;
import sypweb.model.base.BaseService;
import sypweb.model.pojo.Apolice;

public interface BaseApoliceService extends BaseService<Apolice> {
    public List<Apolice> readDadosGrafico2(Map<String, Object> criteria) throws Exception;
    public List<Apolice> filtroDadosGrafico2(Map<String, Object> criteria) throws Exception;
}
