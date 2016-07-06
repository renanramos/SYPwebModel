package sypweb.model.pojo;

import sypweb.model.base.BasePOJO;

public class TipoEvento extends BasePOJO{
  
    private String nome; 
    private Long qntOcorrido;
        
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }    

    public Long getQntOcorrido() {
        return qntOcorrido;
    }

    public void setQntOcorrido(Long qntOcorrido) {
        this.qntOcorrido = qntOcorrido;
    }
}
