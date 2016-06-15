
package steam_boiler;

public class Bomba {
    
    //capacidade da bomba (litros p/ segundo)
    private final int P = 250;
    //estado bomba(ligada/desligada)
    private boolean estado  = false;
    //variavel p/ defeito na bomba
    private boolean defeito = false;

    public boolean getEstado() {
        return estado;
    }

    public int getP() {
        return P;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
        
        //5 segundos
    }

    public boolean getDefeito() {
        return defeito;
    }

    public void setDefeito(boolean defeito) {
        this.defeito = defeito;
    }
    
}
