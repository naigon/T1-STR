
package steam_boiler;

public class Caldeira {
    
    //quantidade de vapor existente na caldeira
    private int v=1;
    //status do sensor que mede o vapor
    private boolean funcionando_sensor_vapor = true;
    //quantidade de agua na caldeira
    private int q;
    //estado da valvula de vazao
    private boolean liberar_agua = false;
    //status do sensor de agua
    private boolean funcionando_sensor_agua = true;

    public int getQ() {
        return q;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public boolean getFuncionando_sensor_vapor() {
        return funcionando_sensor_vapor;
    }

    public void setFuncionando_sensor_vapor(boolean funcionando_sensor_vapor) {
        this.funcionando_sensor_vapor = funcionando_sensor_vapor;
    }

    public boolean getFuncionando_sensor_agua() {
        return funcionando_sensor_agua;
    }

    public void setFuncionando_sensor_agua(boolean funcionando_sensor_agua) {
        this.funcionando_sensor_agua = funcionando_sensor_agua;
    }


    public void setQ(int q) {
        this.q = q + this.q;
    }

    public boolean getLiberar_agua() {
        return liberar_agua;
    }

    public void setLiberar_agua(boolean liberar_agua) {
        this.liberar_agua = liberar_agua;
    }

    public boolean getFuncionando() {
        return funcionando_sensor_agua;
    }

    public void setFuncionando(boolean funcionando) {
        this.funcionando_sensor_agua = funcionando;
    }
    public String esfaziar(int vz){
        System.out.println("Esvaziando a caldeira...");
        liberar_agua=true;
        while(this.q>0){
        this.q= this.q - vz;
        //1 segundo
        }
        System.out.println("Caldeira vazia");
        liberar_agua=false;
        String mensagem="STEAM-BOILER WAITING";
        return mensagem;
    }
    public void encher(int p){
        this.q = this.q + p;
        //1 segundo
    }
        
    }
    

 


