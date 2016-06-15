
package steam_boiler;

import java.util.concurrent.TimeUnit; 

public class Planta {

    public static void main(String[] args) throws InterruptedException {
    String modo_operacao;    
    Caldeira cal = new Caldeira();
    Bomba bom = new Bomba();
    
    Controle cont = new Controle();

    while(true){
        modo_operacao=cont.start(cal,bom);
        
        switch (modo_operacao) {
                
            case "ESVAZIAR":
                System.out.println("MODO: " + modo_operacao + "\n");
                cal.setLiberar_agua(true);
                int decremento=0;
                System.out.println("MENSAGEM: nivel da caldeira: " + cal.getQ() + 'L');
                while(cal.getQ() > (cal.getN1()+cal.getN2())/2){
                    System.out.println("MENSAGEM: esvaziando a caldeira...");
                    decremento = cal.getQ() - cal.getVZ();
                    cal.setQ(decremento);
                    System.out.println("MENSAGEM: nivel da caldeira: " + cal.getQ() + 'L' + "\n");
                    TimeUnit.SECONDS.sleep(1);
                }
                cal.setLiberar_agua(false);    
                break;
                
            case "ENCHER":
                int incremento=0;
                while(cal.getQ() < (cal.getN1()+cal.getN2())/2){
                    System.out.println("MENSAGEM: nivel da caldeira: " + cal.getQ() + 'L' + "\n");
                    System.out.println("MODO: " + modo_operacao + "\n");
                    System.out.println("MENSAGEM: enchendo a Caldeira...");
                    bom.setEstado(true);
                    incremento = cal.getQ() + bom.getP();
                    cal.setQ(incremento);
                    TimeUnit.SECONDS.sleep(1);
                 
                }
                bom.setEstado(false);
                break;
                
            case "DEGRADADO":
                System.out.println("MENSAGEM: nivel de agua ok, mas foi detectado um problema na bomba ou no sensor de vapor..\n");
                System.out.println("MODO: " + modo_operacao + "\n");
                TimeUnit.SECONDS.sleep(1);
                //conserta as coisas (programa nao para)
                System.out.println("MENSAGEM: iniciando reparos...");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("MENSAGEM: reparos feitos com sucesso.");
                cal.setFuncionando_sensor_vapor(true);
                bom.setDefeito(false);
                System.out.println("MENSAGEM: voltando pro modo normal...\n");
                break;
                
            case "NORMAL":
                System.out.println("MODO: " + modo_operacao + "\n");
                System.out.println("QUALTIDADE DE AGUA: " + cal.getQ() + 'L');
                System.out.println("QUANTIDADE DE VAPOR: " + cal.getV() + " L/s");
                System.out.println("STATUS DA BOMBA: OK");
                System.out.println("STATUS DO SENSOR DE NIVEL: OK");
                System.out.println("STATUS DO SENSOR DE VAPOR: OK\n");
                
                TimeUnit.SECONDS.sleep(1);
                break;

            case "PARADA DE EMERGENCIA":
                System.out.println("MENSAGEM: detectado problema em um ou mais dispositivos.\n");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("MODO: " + modo_operacao);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("MENSAGEM: iniciando reparos...");
                //conserta as coisas(programa para)
                cal.setFuncionando_sensor_agua(true);
                TimeUnit.SECONDS.sleep(2);
                cal.setFuncionando_sensor_vapor(true);
                TimeUnit.SECONDS.sleep(2);
                bom.setDefeito(false);
                TimeUnit.SECONDS.sleep(2);
                System.out.println("MENSAGEM: programa recuperado, reiniciando atividades...\n");
                TimeUnit.SECONDS.sleep(1);
                break;
                
            case "RECUPERACAO":
                System.out.println("MENSAGEM: detectado problema no sensor de nivel de agua...\n");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("MODO: " + modo_operacao + "\n");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("MENSAGEM: iniciando reparos...");
                TimeUnit.SECONDS.sleep(2);
                cal.setFuncionando_sensor_agua(true);
                System.out.println("MENSAGEM: reparos finalizados.");
                TimeUnit.SECONDS.sleep(1);
                break;
            default:
                System.out.println("ERROR\n");
         }
    }

    
    
    
    
    }
    
}
