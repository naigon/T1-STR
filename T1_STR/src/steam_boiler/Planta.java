
package steam_boiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit; 

public class Planta {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
    String modo_operacao;    
    Caldeira cal = new Caldeira();
    Bomba bom = new Bomba();
    File f = new File("log.txt");//cria arquivo
    PrintWriter pw = new PrintWriter(f);//cria interface para escrita
    Controle cont = new Controle();

        while(true){
            modo_operacao=cont.start(cal,bom);
            switch (modo_operacao) {
                
             case "ESVAZIAR":
                
                    System.out.println("MENSAGEM: caldera acima do nivel normal");
                    pw.println("MENSAGEM: caldera acima do nivel normal");
                    System.out.println("MENSAGEM: nivel da caldeira: " + cal.getQ() + 'L');
                    pw.println("MENSAGEM: nivel da caldeira: " + cal.getQ() + 'L');
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("MENSAGEM: preparando para esvaziar...\n");
                    pw.println("MENSAGEM: preparando para esvaziar...\n");
                    pw.println("\n");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("MODO: " + modo_operacao + "\n");
                    pw.println("MODO: " + modo_operacao + "\n");
                    pw.println("\n");
                    cal.setLiberar_agua(true);
                    int decremento;
                    int decremento_vap;
                    System.out.println("MENSAGEM: nivel da caldeira: " + cal.getQ() + 'L');
                    pw.println("MENSAGEM: nivel da caldeira: " + cal.getQ() + 'L');
                    
                    while(cal.getQ() > (cal.getN1()+cal.getN2())/2){
                        System.out.println("MENSAGEM: esvaziando a caldeira...");
                        pw.println("MENSAGEM: esvaziando a caldeira...");
                        decremento = cal.getQ() - cal.getVZ();
                        decremento_vap=decremento/100;
                        cal.setV(decremento_vap);
                        cal.setQ(decremento);
                        System.out.println("MENSAGEM: nivel da caldeira: " + cal.getQ() + 'L' + "\n");
                        pw.println("MENSAGEM: nivel da caldeira: " + cal.getQ() + 'L' + "\n");
                        pw.println("\n");
                        TimeUnit.SECONDS.sleep(1);
                    }
                    cal.setLiberar_agua(false);  
                    pw.flush();
                    break;
                
                case "ENCHER":
                    
                    System.out.println("MENSAGEM: caldera vazia ou abaixo do nivel normal");
                    pw.println("MENSAGEM: caldera vazia ou abaixo do nivel normal");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("MENSAGEM: preparando para encher...");
                    pw.println("MENSAGEM: preparando para encher...");
                    pw.println("\n");
                    TimeUnit.SECONDS.sleep(1);
                    int incremento;
                    int incremento_vap;
                    
                    while(cal.getQ() < (cal.getN1()+cal.getN2())/2){             
                        System.out.println("MODO: " + modo_operacao + "\n");
                        pw.println("\n");
                        pw.println("MODO: " + modo_operacao + "\n");
                        pw.println("\n");
                        System.out.println("MENSAGEM: enchendo a Caldeira...");
                        pw.println("MENSAGEM: enchendo a Caldeira...");
                        bom.setEstado(true);
                        System.out.println("MENSAGEM: nivel da caldeira: " + cal.getQ() + 'L' + "\n");
                        pw.println("MENSAGEM: nivel da caldeira: " + cal.getQ() + 'L' + "\n");
                        incremento = cal.getQ() + bom.getP();
                        incremento_vap=incremento/100;
                        cal.setV(incremento_vap);
                        cal.setQ(incremento);
                        TimeUnit.SECONDS.sleep(1);
                 
                    }
                    pw.println("\n");
                    pw.flush();
                    bom.setEstado(false);
                    break;
                
                case "DEGRADADO":
                    
                    System.out.println("MENSAGEM: nivel de agua ok, mas foi detectado um problema na bomba ou no sensor de vapor..\n");
                    pw.println("MENSAGEM: nivel de agua ok, mas foi detectado um problema na bomba ou no sensor de vapor..\n");
                    System.out.println("MODO: " + modo_operacao + "\n");
                    pw.println("\n");
                    pw.println("MODO: " + modo_operacao + "\n");
                    pw.println("\n");
                    TimeUnit.SECONDS.sleep(1);
                    //conserta as coisas (programa nao para)
                    System.out.println("MENSAGEM: iniciando reparos...");
                    pw.println("MENSAGEM: iniciando reparos...");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("MENSAGEM: reparos feitos com sucesso.");
                    pw.println("MENSAGEM: reparos feitos com sucesso.");
                    cal.setFuncionando_sensor_vapor(true);
                    bom.setDefeito(false);
                    System.out.println("MENSAGEM: voltando pro modo normal...\n");
                    pw.println("MENSAGEM: voltando pro modo normal...\n");
                    pw.println("\n");
                    pw.flush();
                    break;
                
                case "NORMAL":
                    
                    System.out.println("MODO: " + modo_operacao + "\n");
                    pw.println("MODO: " + modo_operacao + "\n");
                    pw.println("\n");
                    System.out.println("QUALTIDADE DE AGUA: " + cal.getQ() + 'L');
                    pw.println("QUANTIDADE DE AGUA: " + cal.getQ() + 'L');
                    System.out.println("QUANTIDADE DE VAPOR: " + cal.getV() + " L/s");
                    pw.println("QUANTIDADE DE VAPOR: " + cal.getV() + " L/s");
                    System.out.println("STATUS DA BOMBA: OK");
                    pw.println("STATUS DA BOMBA: OK");
                    System.out.println("STATUS DO SENSOR DE NIVEL: OK");
                    pw.println("STATUS DO SENSOR DE NIVEL: OK");
                    System.out.println("STATUS DO SENSOR DE VAPOR: OK\n");
                    pw.println("STATUS DO SENSOR DE VAPOR: OK\n");
                    TimeUnit.SECONDS.sleep(1); 
                    pw.println("\n");
                    pw.flush();
                    break;

                case "PARADA DE EMERGENCIA":
                    
                    System.out.println("MENSAGEM: detectado problema em um ou mais dispositivos.\n");
                    pw.println("MENSAGEM: detectado problema em um ou mais dispositivos.\n");
                    TimeUnit.SECONDS.sleep(1);
                    pw.println("\n");
                    System.out.println("MODO: " + modo_operacao + "\n");
                    pw.println("MODO: " + modo_operacao);
                    pw.println("\n");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("MENSAGEM: iniciando reparos...");
                    pw.println("MENSAGEM: iniciando reparos...");
                    //conserta as coisas(programa para)
                    cal.setFuncionando_sensor_agua(true);
                    TimeUnit.SECONDS.sleep(2);
                    cal.setFuncionando_sensor_vapor(true);
                    TimeUnit.SECONDS.sleep(2);
                    bom.setDefeito(false);
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("MENSAGEM: programa recuperado, reiniciando atividades...\n");
                    pw.println("MENSAGEM: programa recuperado, reiniciando atividades...\n");
                    TimeUnit.SECONDS.sleep(1);
                    pw.println("\n");
                    pw.flush();
                    break;
                
                case "RECUPERACAO":
                    
                    System.out.println("MENSAGEM: detectado problema no sensor de nivel de agua...\n");
                    pw.println("MENSAGEM: detectado problema no sensor de nivel de agua...\n");
                    TimeUnit.SECONDS.sleep(1);
                    pw.println("\n");
                    System.out.println("MODO: " + modo_operacao + "\n");
                    pw.println("MODO: " + modo_operacao + "\n");
                    pw.println("\n");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("MENSAGEM: iniciando reparos...");
                    pw.println("MENSAGEM: iniciando reparos...");
                    TimeUnit.SECONDS.sleep(2);
                    cal.setFuncionando_sensor_agua(true);
                    System.out.println("MENSAGEM: reparos finalizados.");
                    pw.println("MENSAGEM: reparos finalizados.");
                    TimeUnit.SECONDS.sleep(1);  
                    pw.println("\n");
                    pw.flush();
                    break;
                    
                default:
                    System.out.println("ERROR\n");
            }
        }
    }    
}
