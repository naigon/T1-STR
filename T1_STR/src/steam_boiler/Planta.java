
package steam_boiler;

import java.util.concurrent.TimeUnit; 

public class Planta {

    public static void main(String[] args) throws InterruptedException {
    String modo_operacao;    
    Caldeira cal = new Caldeira();
    Bomba bom = new Bomba();
    
    Controle cont = new Controle();
    modo_operacao=cont.start(cal,bom);
    while(true){
        cont.start(cal,bom);
        
        switch (modo_operacao) {
                
            case "ESVAZIAR":
                System.out.println("MODO: " + modo_operacao + "\n");
                cal.setLiberar_agua(true);
                int decremento=0;
                System.out.println("MENSAGEM: Nivel da caldeira: " + cal.getQ() + 'L');
                while(cal.getQ() > (cal.getN1()+cal.getN2())/2){
                    System.out.println("MENSAGEM: Esvaziando a caldeira...\n");
                    decremento = cal.getQ() - cal.getVZ();
                    cal.setQ(decremento);
                    System.out.println("MENSAGEM: Nivel da caldeira: " + cal.getQ() + 'L');
                    TimeUnit.SECONDS.sleep(2);
                }
                cal.setLiberar_agua(false);
                modo_operacao="NORMAL";    
                break;
                
            case "ENCHER":
                int incremento=0;
                while(cal.getQ() < (cal.getN1()+cal.getN2())/2){
                    System.out.println("MODO: " + modo_operacao + "\n");
                    System.out.println("MENSAGEM: Enchendo a Caldeira...\n");
                    bom.setEstado(true);
                    incremento = cal.getQ() + bom.getP();
                    cal.setQ(incremento);
                    System.out.println("MENSAGEM: Nivel da caldeira: " + cal.getQ() + 'L');
                    TimeUnit.SECONDS.sleep(1);
                 
                }
                bom.setEstado(false);
                modo_operacao="NORMAL";
                break;
                
            case "DEGRADADO":
                 System.out.println("MODO: " + modo_operacao + "\n");
                //conserta as coisas (programa nao para)
                cal.setFuncionando_sensor_agua(true);
                TimeUnit.SECONDS.sleep(1);
                cal.setFuncionando_sensor_vapor(true);
                TimeUnit.SECONDS.sleep(1);
                bom.setDefeito(false);
                TimeUnit.SECONDS.sleep(1);
                modo_operacao="NORMAL";
                break;
                
            case "NORMAL":
                System.out.println("MODO: " + modo_operacao + "\n");
                break;

            case "PARADA DE EMERGENCIA":
                System.out.println("MODO: " + modo_operacao + "\n");
                System.out.println("MENSAGEM: Programa interrompido para recuperação de falhas nos dispositivos...\n");
                //conserta as coisas(programa para)
                cal.setFuncionando_sensor_agua(true);
                TimeUnit.SECONDS.sleep(1);
                cal.setFuncionando_sensor_vapor(true);
                TimeUnit.SECONDS.sleep(1);
                bom.setDefeito(false);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("MENSAGEM: Programa recuperado, voltando pro modo normal...\n");
                TimeUnit.SECONDS.sleep(1);
                modo_operacao="NORMAL";
                break;
                
            case "RECUPERAÇÃO":
                 System.out.println("MODO: " + modo_operacao + "\n");
                
                
                break;
            default:
                 System.out.println("ERROR\n");
         }
    }

    
    
    
    
    }
    
}
