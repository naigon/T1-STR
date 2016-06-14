
package steam_boiler;


public class Planta {
    public static void main(String[] args) {
    
    String modo_operacao="BUCETA";    
    Caldeira cal = new Caldeira();
    Bomba bom = new Bomba();
    
    Controle cont = new Controle();
    modo_operacao=cont.Inicializacao(cal,bom);
    System.out.println(modo_operacao);
    }
    
}
