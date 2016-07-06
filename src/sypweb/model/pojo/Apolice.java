package sypweb.model.pojo;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import sypweb.model.base.BasePOJO;

public class Apolice extends BasePOJO {
    
    private String apolice;
    private String sypCode;
    private Boolean sypCodeStatus;
    private String dataHoraCadastro;
    private Long qntOcorrido;

    /**
     * @return the apolice
     */
    public String getApolice() {
        return apolice;
    }

    /**
     * @param apolice the apolice to set
     */
    public void setApolice(String apolice) {
        this.apolice = apolice;
    }

    /**
     * @return the sypCode
     */
    public String getSypCode() {
        return sypCode;
    }

    /**
     * @param sypCode the sypCode to set
     */
    public void setSypCode(String sypCode) {
        this.sypCode = sypCode;
    }

    /**
     * @return the sypCodeStatus
     */
    public Boolean getSypCodeStatus() {
        return sypCodeStatus;
    }

    /**
     * @param sypCodeStatus the sypCodeStatus to set
     */
    public void setSypCodeStatus(Boolean sypCodeStatus) {
        this.sypCodeStatus = sypCodeStatus;
    }

    /**
     * @return the dataHoraCadastro
     */
    public String getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    /**
     * @param dataHoraCadastro the dataHoraCadastro to set
     */
    public void setDataHoraCadastro(Timestamp dataHoraCadastro) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        this.dataHoraCadastro = sdf.format(dataHoraCadastro);         
    }

    /**
     * @return the qntOcorrido
     */
    public Long getQntOcorrido() {
        return qntOcorrido;
    }

    /**
     * @param qntOcorrido the qntdOcorridos to set
     */
    public void setQntOcorrido(Long qntOcorrido) {
        this.qntOcorrido = qntOcorrido;
    }

    
}
