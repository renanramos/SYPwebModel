package sypweb.model.pojo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import sypweb.model.base.BasePOJO;

public class MensagemApolice extends BasePOJO {

    private Mensagem mensagem;
    private Apolice apolice;
    private String dataHoraColeta;

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public Apolice getApolice() {
        return apolice;
    }

    public void setApolice(Apolice apolice) {
        this.apolice = apolice;
    }

    public String getDataHoraColeta() {
        return dataHoraColeta;
    }

    public void setDataHoraColeta(Timestamp dataHoraColeta) {
        if (dataHoraColeta != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            this.dataHoraColeta = sdf.format(dataHoraColeta);
        }
    }
}
