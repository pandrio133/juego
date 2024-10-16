
package objetos;

public class Cronometro {
    //time y lasttime sirven para controlar el tiempo,
    //causan una diferncia de milisegundo mediante resta
    private long delta, lastTime;
    private long time;
    private boolean running;
            
    
    public Cronometro() {
        delta=0;
        lastTime=0;
        running=false;
    }
    
    public void run(long time){
        running=true;
        this.time=time;
               
    }
    
    public void update(){
        if (running) {
            delta += System.currentTimeMillis()-lastTime;
        }
        if (delta>=time) {
            running=false;
            delta=0;
        }
        lastTime=System.currentTimeMillis();
    }
    
    public boolean isRunning(){
        return running;
    }

}
