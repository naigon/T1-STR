
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
    
    public String start(Caldeira c,Bomba b) throws InterruptedException{
        String modo;
        //inicio: se caldeira nao tem agua nem vapor e os dispositivos estiverem funcionando ela enche e começa a funcionar
        
        while (verifica_nivel_agua(c)<c.getN2() && verifica_nivel_agua(c)>c.getN1() && verifica_sensores(c,b)==1){
            modo="NORMAL";
            return modo;
            
        }
        if((verifica_nivel_agua(c)==0 || verifica_nivel_agua(c)<c.getN1()) && verifica_sensores(c,b)==1) {
            modo="ENCHER";
            return modo;
        }  
        if(verifica_nivel_agua(c)>c.getN2() && verifica_sensores(c,b)==1){
            modo="ESVAZIAR";
            return modo;
            }
        //quando o nivel esta muito baixo ou muito alto e tem problema em um dos sensores
        if((c.getQ()>c.getN2() || c.getQ()<c.getN1()) && (verifica_sensores(c,b)==-1)){
            modo="PARADA DE EMERGENCIA";
            return modo;
        }     

        //nivel de agua ideal mas com defeito no medidor de agua
        if(verifica_nivel_agua(c)<c.getN2() && verifica_nivel_agua(c)>c.getN1() && c.getFuncionando_sensor_agua()==false){
                modo="RECUPERACAO";
                return modo;
        }
        // ((se tiver sem agua sem vapor) OR (agua proxima dos niveis maximo e minimo)) AND (problema nos sensores de vapor ou da bomba)
        if((verifica_nivel_agua(c)==0 && verifica_nivel_vapor(c)==0) || (verifica_nivel_agua(c)>=c.getM2() || verifica_nivel_agua(c)<=c.getM1()) && (verifica_sensores(c,b)==-1)){
            modo="PARADA DE EMERGENCIA";
            return modo;
        }
                   
        //se o nivel de agua está ok, mas alguma das unidades fisicas apresentar defeito, vai pro modo degradado
        if (verifica_nivel_agua(c)<c.getN2() && verifica_nivel_agua(c)>c.getN1() && c.getFuncionando_sensor_agua()==true && (c.getFuncionando_sensor_vapor()==false || b.getDefeito()==true)){
            modo="DEGRADADO";
            return modo;
        }
        
            else  {                      
                modo="NORMAL";
                return modo;}
        }
    }

        

    

