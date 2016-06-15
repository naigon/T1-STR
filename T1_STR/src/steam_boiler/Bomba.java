
package steam_boiler;

import java.util.concurrent.TimeUnit;

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

    public void setEstado(boolean estado) throws InterruptedException {
        this.estado = estado;
        TimeUnit.SECONDS.sleep(1);
    }

    public boolean getDefeito() {
        return defeito;
    }

    public void setDefeito(boolean defeito) {
        this.defeito = defeito;
    }
    
}
