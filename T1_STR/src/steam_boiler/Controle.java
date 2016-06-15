
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
        if(verifica_nivel_vapor(c)!=0){
            //se a quantidade de vapor for diferente de zero eh pq ta com defeito, assim vai pro modo de emergencia
            c.setFuncionando_sensor_vapor(false);
            modo="PARADA DE EMERGENCIA";
            return modo;
        }
 
        //se tiver defeito na medição da agua ou na bomba tbm vai pro modo parada de emergencia
        if (c.getFuncionando_sensor_agua()==false || b.getDefeito()==true){
            modo="PARADA DE EMERGENCIA";
            return modo;
        }
        
        //se tiver muito cheia, tem q essvafizar a caldeira
        if (verifica_nivel_agua(c) > c.getN2()){
            modo="ESVAZIAR";
            return modo;
        }
    
        //se tiver vazia, tem que encher a caldeira
        if (verifica_nivel_agua(c) < c.getN1()){
            modo="ENCHER";
            return modo;
        }
            
        //se o nivel de agua está ok e as entidades fisicas todas funcionando, vai pro modo normal
        if (verifica_nivel_agua(c)<c.getN2() && verifica_nivel_agua(c)>c.getN1() && verifica_sensores(c,b)==1){
            modo="NORMAL";
            return modo;
        }
        
        //se o nivel de agua está ok, mas alguma das unidades fisicas apresentar defeito, vai pro modo degradado
        if (verifica_nivel_agua(c)<c.getN2() && verifica_nivel_agua(c)>c.getN1() && verifica_sensores(c,b)==-1){
            modo="DEGRADADO";
            return modo;
        }
        
        //se os niveis de agua estao proximos da quantidade maxima ou minima vai pro modo parada de emergencia
        if (verifica_nivel_agua(c)>=c.getM2() || verifica_nivel_agua(c)<=c.getM1()){
            modo="PARADA DE EMERGENCIA";
            return modo;
        }
        modo="NORMAL";
        return modo;
    }

}
        

    

