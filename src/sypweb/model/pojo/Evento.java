package sypweb.model.pojo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import sypweb.model.base.BasePOJO;

public class Evento extends BasePOJO{
    
    private String latitude;
    private String longitude;
    private String precisao;
    private TipoEvento tipoEvento;
    private Apolice apolice;    
    private String dataHoraCadastro;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrecisao() {
        return precisao;
    }

    public void setPrecisao(String precisao) {
        this.precisao = precisao;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Apolice getApolice() {
        return apolice;
    }

    public void setApolice(Apolice apolice) {
        this.apolice = apolice;
    }

    public String getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(Timestamp dataHoraCadastro) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        this.dataHoraCadastro = sdf.format(dataHoraCadastro); 
    }    
}
