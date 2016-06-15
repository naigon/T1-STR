
package steam_boiler;

public class Controle{
    
    public int verifica_nivel_vapor(Caldeira c){
        return c.getV();
        }
    public int verifica_nivel_agua(Caldeira c){
        return c.getQ();
        }
    public int verifica_sensores(Caldeira c, Bomba b){
        if(c.getFuncionando_sensor_agua()==true && c.getFuncionando_sensor_vapor()==true && b.getDefeito()==false)
            return 1;
        else return -1;
        }
    
    public String start(Caldeira c,Bomba b){
        String modo;
        //inicio: se caldeira nao tem agua nem vapor e os dispositivos estiverem funcionando ela enche e começa a funcionar
        if(verifica_nivel_agua(c)==0 && verifica_nivel_vapor(c)==0 && verifica_sensores(c,b)==1){    
            System.out.println("MODO: INICIALIZAÇÃO\n");
            modo="ENCHER";
            return modo;}
        else 
            if(verifica_nivel_agua(c)!=0 || verifica_nivel_vapor(c)!=0 || verifica_sensores(c,b)==-1){
                modo="PARADA DE EMERGENCIA";
                return modo;
            }
        
            else//se tiver muito cheia, tem q esvafizar a caldeira
                if (verifica_nivel_agua(c) > c.getN2()){
                    modo="ESVAZIAR";
                    return modo;
                }
    
                else
                    //se tiver vazia, tem que encher a caldeira
                    if (verifica_nivel_agua(c) < c.getN1()){
                        modo="ENCHER";
                        return modo;
                    }
                    else
                        //se o nivel de agua está ok e as entidades fisicas todas funcionando, vai pro modo normal
                        if (verifica_nivel_agua(c)<c.getN2() && verifica_nivel_agua(c)>c.getN1() && verifica_sensores(c,b)==1){
                            modo="NORMAL";
                            return modo;
                            }
                        else
                            
                            //se o nivel de agua está ok, mas alguma das unidades fisicas apresentar defeito, vai pro modo degradado
                            if (verifica_nivel_agua(c)<c.getN2() && verifica_nivel_agua(c)>c.getN1() && verifica_sensores(c,b)==-1){
                                modo="DEGRADADO";
                                return modo;
                            }
                            else
                                
                                //se os niveis de agua estao proximos da quantidade maxima ou minima vai pro modo parada de emergencia
                                if (verifica_nivel_agua(c)>=c.getM2() || verifica_nivel_agua(c)<=c.getM1()){
                                    modo="PARADA DE EMERGENCIA";
                                    return modo;
                                }
                                else  {                      
                                modo="NORMAL";
                                return modo;}
    }

}
        

    

