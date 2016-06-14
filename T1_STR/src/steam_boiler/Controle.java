
package steam_boiler;

public class Controle{
        
    //CONSTANTES CALDEIRA
    
    //capacidade maxima(litros)
    private final int C=10000;
    
    //limite minimo(litros)
    private final int M1=2000;
    
    //limite maximo(litros)
    private final int M2=8000;
    
    //minimo normal(litros)
    private final int N1=4000;
    
    //maximo normal(litros)
    private final int N2=6000;
    
    //capacidade da valvula de vazao(litros p/ segundo)
    private final int VZ=500;
    
    //CONSTANTES BOMBA
    //capacidade da bomba (litros p/ segundo)
    private final int P=250;
        
    //CONSTANTES VAPOR
    
    //quantidade maxima de vapor(litros p/ segundo)
    private final int W=4000;
    //gradiente maximo de incremento(litros p/ segundos p/ segundo)
    private final int U1=4000;
    //gradiente maximo de decremento(litros p/ segundos p/ segundo)
    private final int U2=4000;
    
    
    public String Inicializacao(Caldeira c,Bomba b){
        if(c.getV()!=0){
            c.setFuncionando_sensor_vapor(false);
            return "MODO: PARADA DE EMERGENCIA";}
        else{if(c.getQ()>N2)
                c.esfaziar(VZ);
             if(c.getQ()<N1){
                b.setEstado(true);
                while (c.getQ()<N2)
                    c.encher(P);
            }  } 
        return "MODO: NORMAL";
    }
    public void Parada_Emergencia(){
        
    }
}