
package steam_boiler;


public class Planta {

    public static void main(String[] args) {
    
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
                System.out.println(cal.getQ());
                while(cal.getQ()!=0){
                    System.out.println("Esvaziando a caldeira...\n");
                    decremento = cal.getQ() - cal.getVZ();
                    cal.setQ(decremento);
                    System.out.println("Nivel da caldeira: " + cal.getQ() + 'L');
                    //delay
                }
                cal.setLiberar_agua(false);
                modo_operacao="ENCHER";
                
                break;
            case "ENCHER":
                int incremento=0;
                while(cal.getQ() < cal.getN2()){
                    System.out.println("MODO: " + modo_operacao + "\n");
                    System.out.println("Enchendo a Caldeira...\n");
                    bom.setEstado(true);
                    incremento = cal.getQ() + bom.getP();
                    cal.setQ(incremento);
                    System.out.println("Nivel da caldeira: " + cal.getQ() + 'L');
                    //delay
                }
                    
                
                bom.setEstado(false);
                
                modo_operacao="NORMAL";
                break;
            case "DEGRADADO":
                 System.out.println("MODO: " + modo_operacao + "\n");
                //conserta as coisas (programa nao para)
                cal.setFuncionando_sensor_agua(true);
                //delay
                cal.setFuncionando_sensor_vapor(true);
                //delay
                bom.setDefeito(false);
                //delay
                modo_operacao="NORMAL";
                break;
            case "NORMAL":
                System.out.println("MODO: " + modo_operacao + "\n");
                break;

            case "PARADA DE EMERGENCIA":
                System.out.println("MODO: " + modo_operacao + "\n");
                System.out.println("Programa interrompido para recuperação de falhas...\n");
                //conserta as coisas(programa para)
                cal.setFuncionando_sensor_agua(true);
                //delay
                cal.setFuncionando_sensor_vapor(true);
                //delay
                bom.setDefeito(false);
                //delay
                System.out.println("Programa recuperado, voltando pro modo normal...\n");
                //delay
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
