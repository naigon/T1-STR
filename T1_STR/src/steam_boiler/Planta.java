
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
                
                cal.setLiberar_agua(true);
                while(cal.getQ()>0){
                    System.out.println("Esvaziando a caldeira...\n");
                    cal.setQ(cal.getQ() - cal.getVZ());
                    //delay
                }
                cal.setLiberar_agua(false);
                modo_operacao="PARADA DE EMERGENCIA";
                System.out.println("Caldeira vazia\n");
                break;
            case "ENCHER":
                while(cal.getQ() <= cal.getN2()){
                    System.out.println("Enchendo a Caldeira...\n");
                    bom.setEstado(true);
                    cal.setQ(cal.getQ() + bom.getP());}
                    System.out.println(cal.getQ());
                    //delay
                bom.setEstado(false);
                System.out.println("Caldeira no nível adequado\n");
                break;
            case "DEGRADADO":
                 System.out.println("MODO: " + modo_operacao);
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
                System.out.println("MODO: " + modo_operacao);
                break;

            case "PARADA DE EMERGENCIA":
                 System.out.println("MODO: " + modo_operacao);
                //conserta as coisas(programa para)
                
                break;
            case "RECUPERAÇÃO":
                 System.out.println("MODO: " + modo_operacao);
                
                
                break;
            default:
                 System.out.println("ERROR\n");
         }
    }

    
    
    
    
    }
    
}
