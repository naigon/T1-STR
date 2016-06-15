
package steam_boiler;

import java.util.concurrent.TimeUnit;

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
            System.out.println("MENSAGEM: Caldera vazia ou abaixo do nivel normal\n");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("MENSAGEM: Preparando para encher...\n");
            TimeUnit.SECONDS.sleep(1);
            return modo;
        }  
        if(verifica_nivel_agua(c)>c.getN2() && verifica_sensores(c,b)==1){
            modo="ESVAZIAR";
            System.out.println("MENSAGEM: Caldera acima do nivel normal\n");
            System.out.println("MENSAGEM: Nivel da caldeira: " + verifica_nivel_agua(c) + 'L' + "\n");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("MENSAGEM: Preparando para esvaziar...\n");
            TimeUnit.SECONDS.sleep(1);
            return modo;
            }

        // ((se tiver sem agua sem vapor) OR (agua proxima dos niveis maximo e minimo)) AND (problema nos sensores de vapor ou da bomba)
        if(((verifica_nivel_agua(c)==0 && verifica_nivel_vapor(c)==0) || (verifica_nivel_agua(c)>=c.getM2() || verifica_nivel_agua(c)<=c.getM1())) && (c.getFuncionando_sensor_vapor()==false || b.getDefeito()==true)){
            modo="PARADA DE EMERGENCIA";
            return modo;
        }
     
        //se o nivel de agua está ok, mas alguma das unidades fisicas apresentar defeito, vai pro modo degradado
        if (verifica_nivel_agua(c)<c.getN2() && verifica_nivel_agua(c)>c.getN1() && c.getFuncionando_sensor_agua()==true && (c.getFuncionando_sensor_vapor()==false || b.getDefeito()==true)){
            modo="DEGRADADO";
            return modo;
        }
        else
            if(c.getFuncionando_sensor_agua()==false){
                modo="RECUPERACAO";
                return modo;
            }
        
            else  {                      
                modo="NORMAL";
                return modo;}
        }
    }

        

    

