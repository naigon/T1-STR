
package steam_boiler;

public class Vapor {
    
    //CONSTANTES
    
    //quantidade maxima de vapor(litros p/ segundo)
    private final int W=4000;
    //gradiente maximo de incremento(litros p/ segundos p/ segundo)
    private final int U1=4000;
    //gradiente maximo de decremento(litros p/ segundos p/ segundo)
    private final int U2=4000;
    
    //VARIAVEIS
    
    //quantidade de vapor existente na caldeira
    private int v;
    //status do sensor que mede o vapor
    private boolean funcionando = true;
    
}
