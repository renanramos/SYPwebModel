package sypweb.model.pojo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import sypweb.model.base.BasePOJO;

public class Usuario extends BasePOJO{
    private String nome;
    private String senha;
    private String tipo;
    private String email;
    private String permissao;
    private String regSusep;
    private Boolean status;
    private String dataHoraCadastro;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public String getRegSusep() {
        return regSusep;
    }

    public void setRegSusep(String regSusep) {
        this.regSusep = regSusep;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(Timestamp dataHoraCadastro) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        this.dataHoraCadastro = sdf.format(dataHoraCadastro); 
    }
}
