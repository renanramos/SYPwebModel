package sypweb.model.base.service;

import java.util.List;
import java.util.Map;
import sypweb.model.base.BaseService;
import sypweb.model.pojo.Mensagem;

public interface BaseMensagemService extends BaseService<Mensagem> {
    public List<Mensagem> readByAll() throws Exception;
}
