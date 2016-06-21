
package steam_boiler;

import javax.realtime.PriorityScheduler;
import javax.realtime.PriorityParameters;
import javax.realtime.PeriodicParameters;
import javax.realtime.RelativeTime;
import javax.realtime.RealtimeThread;

public class Controle{
		public String modo; 
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
    	modo = null;
        int priority1 = PriorityScheduler.instance().getMinPriority()+10;
    	PriorityParameters priorityParameters1 = new PriorityParameters(priority1);
    
    	int priority2 = PriorityScheduler.instance().getMinPriority()+10;
    	PriorityParameters priorityParameters2 = new PriorityParameters(priority2);
		
		int priority3 = PriorityScheduler.instance().getMinPriority()+10;
    	PriorityParameters priorityParameters3 = new PriorityParameters(priority3);
    
    	int priority4 = PriorityScheduler.instance().getMinPriority()+10;
    	PriorityParameters priorityParameters4 = new PriorityParameters(priority4);
		
		int priority5 = PriorityScheduler.instance().getMinPriority()+10;
    	PriorityParameters priorityParameters5 = new PriorityParameters(priority5);
    
    	int priority6 = PriorityScheduler.instance().getMinPriority()+10;
    	PriorityParameters priorityParameters6 = new PriorityParameters(priority6);
    	
    	int priority7 = PriorityScheduler.instance().getMinPriority()+10;
    	PriorityParameters priorityParameters7 = new PriorityParameters(priority7);
    			
   		RelativeTime period1 = new RelativeTime(200 /* ms */, 0 /* ns */);
    	RelativeTime period2 = new RelativeTime(400 /* ms */, 0 /* ns */);
    	RelativeTime period3 = new RelativeTime(600 /* ms */, 0 /* ns */);
    	RelativeTime period4 = new RelativeTime(800 /* ms */, 0 /* ns */);
    	RelativeTime period5 = new RelativeTime(1000 /* ms */, 0 /* ns */);
    	RelativeTime period6 = new RelativeTime(1200 /* ms */, 0 /* ns */);
    	RelativeTime period7 = new RelativeTime(1400 /* ms */, 0 /* ns */);

    	PeriodicParameters periodicParameters1 = new PeriodicParameters(null,period1, null,null,null,null);
    	PeriodicParameters periodicParameters2 = new PeriodicParameters(null,period2, null,null,null,null);
    	PeriodicParameters periodicParameters3 = new PeriodicParameters(null,period3, null,null,null,null);
    	PeriodicParameters periodicParameters4 = new PeriodicParameters(null,period4, null,null,null,null);
    	PeriodicParameters periodicParameters5 = new PeriodicParameters(null,period5, null,null,null,null);
    	PeriodicParameters periodicParameters6 = new PeriodicParameters(null,period6, null,null,null,null);
    	PeriodicParameters periodicParameters7 = new PeriodicParameters(null,period7, null,null,null,null);

        //inicio: se caldeira nao tem agua nem vapor e os dispositivos estiverem funcionando ela enche e começa a funcionar
       RealtimeThread realtimeThread1 = new RealtimeThread(priorityParameters1,periodicParameters1){ 
        public void run(){
     		System.out.println("Thread 1");
        	while (verifica_nivel_agua(c)<c.getN2() && verifica_nivel_agua(c)>c.getN1() && verifica_sensores(c,b)==1){
            	modo="NORMAL";
            	}
           }};
        
        RealtimeThread realtimeThread2 = new RealtimeThread(priorityParameters2,periodicParameters2){
        public void run(){
         System.out.println("Thread 2");
        if((verifica_nivel_agua(c)==0 || verifica_nivel_agua(c)<c.getN1()) && verifica_sensores(c,b)==1) {
            modo="ENCHER";
            }
        }  };
        
        RealtimeThread realtimeThread3 = new RealtimeThread(priorityParameters3,periodicParameters3){
        public void run(){
        System.out.println("Thread 3");
        if(verifica_nivel_agua(c)>c.getN2() && verifica_sensores(c,b)==1){
            modo="ESVAZIAR";
            }
        }};

        RealtimeThread realtimeThread4 = new RealtimeThread(priorityParameters4,periodicParameters4){
        public void run(){
        System.out.println("Thread 4");
        
        //quando o nivel esta muito baixo ou muito alto e tem problema em um dos sensores
        if((c.getQ()>c.getN2() || c.getQ()<c.getN1()) && (verifica_sensores(c,b)==-1)){
            modo="PARADA DE EMERGENCIA";
            
        }     
        }};

		RealtimeThread realtimeThread5 = new RealtimeThread(priorityParameters5,periodicParameters5){
        public void run(){
        System.out.println("Thread 5");
          //nivel de agua ideal mas com defeito no medidor de agua
        if(verifica_nivel_agua(c)<c.getN2() && verifica_nivel_agua(c)>c.getN1() && c.getFuncionando_sensor_agua()==false){
                modo="RECUPERACAO";
                
        }
        }};

      	RealtimeThread realtimeThread6 = new RealtimeThread(priorityParameters6,periodicParameters6){
        public void run(){
        System.out.println("Thread 6");
          // ((se tiver sem agua sem vapor) OR (agua proxima dos niveis maximo e minimo)) AND (problema nos sensores de vapor ou da bomba)
        if((verifica_nivel_agua(c)==0 && verifica_nivel_vapor(c)==0) || (verifica_nivel_agua(c)>=c.getM2() || verifica_nivel_agua(c)<=c.getM1()) && (verifica_sensores(c,b)==-1)){
            modo="PARADA DE EMERGENCIA";
            
        }
        }};
		
		RealtimeThread realtimeThread7 = new RealtimeThread(priorityParameters7,periodicParameters7){
        public void run(){
        System.out.println("Thread 7");
        //se o nivel de agua está ok, mas alguma das unidades fisicas apresentar defeito, vai pro modo degradado
        if (verifica_nivel_agua(c)<c.getN2() && verifica_nivel_agua(c)>c.getN1() && c.getFuncionando_sensor_agua()==true && (c.getFuncionando_sensor_vapor()==false || b.getDefeito()==true)){
            modo="DEGRADADO";
            
		        }
        
            else  {                      
                modo="NORMAL";
                }
      
		        }
		};
	realtimeThread1.start();
    realtimeThread2.start();
    realtimeThread3.start();
    realtimeThread4.start();
    realtimeThread5.start();
    realtimeThread6.start();
    realtimeThread7.start();
   	while(modo==null){
   	System.out.println("resolvendo as thread!");
   	}
    return modo;
	}// fim do metodo start
	

}
