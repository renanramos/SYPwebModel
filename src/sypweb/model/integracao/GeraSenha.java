package sypweb.model.integracao;


import java.util.Random;

public class GeraSenha {

    private String senha = "";
    Random random = new Random();

    public GeraSenha() {
        geraSenha();
    }

    private void geraSenha() {

        String[] letras = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] especiais = {"@","$","%","&","+","?","#"};
        
        for (int i = 0; i < 6; i++) {
            int a = random.nextInt(letras.length);
            senha += letras[a];
        }
        for(int i = 0; i < 2; i++){
            int a = random.nextInt(especiais.length);
            senha += especiais[a];                    
        }
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }
}
