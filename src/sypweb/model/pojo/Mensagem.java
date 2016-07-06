package sypweb.model.pojo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import sypweb.model.base.BasePOJO;

public class Mensagem extends BasePOJO{
    private String descricao;
    private String dataHoraCadastro;
    private Usuario usuario;
    private Long enviada;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(Timestamp dataHoraCadastro) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        this.dataHoraCadastro = sdf.format(dataHoraCadastro); 
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getEnviada() {
        return enviada;
    }

    public void setEnviada(Long enviada) {
        this.enviada = enviada;
    }
}
