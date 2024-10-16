
package ventana;

import estados.estadosJuego;
import graficos.Assets;
import input.KeyBoard;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import objetos.Constantes;

public class Ventana extends JFrame implements Runnable{
    
    
    private Canvas canvas; 
    private Thread thread;
    private boolean running=false;
    //graficos
    private BufferStrategy bs;
    private Graphics g;
    //fotogramas
    private final int FPS=30;
    //tiempo
    private double TARGETTIME=100000000/FPS;
    private double delta=0;
    private int AVERAGEFPS=FPS;
    //clase de estados
    private estadosJuego estadosJ;
    private KeyBoard keyBoard;
    
    
    public Ventana(){
        setTitle("Juego");
        setSize(Constantes.WIDTH, Constantes.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        canvas= new Canvas();
        keyBoard=new KeyBoard();
        
        canvas.setPreferredSize(new Dimension(Constantes.WIDTH,Constantes.HEIGHT));
        canvas.setMaximumSize(new Dimension(Constantes.WIDTH,Constantes.HEIGHT));
        canvas.setMinimumSize(new Dimension(Constantes.WIDTH,Constantes.HEIGHT));
        canvas.setFocusable(true);
        
        add(canvas);
        canvas.addKeyListener(keyBoard);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Ventana().star();
        
    }
    int x =0;
    private void update(){
        estadosJ.update();
        keyBoard.update();
    }
    
    private void draw(){
        bs=canvas.getBufferStrategy();
        if(bs==null){
            canvas.createBufferStrategy(2);
            return;
        }
        g=bs.getDrawGraphics();
        //-------------------
        /*fondo de pantalla
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);*/
        g.drawImage(Assets.fondo, 0, 0,Constantes.WIDTH, Constantes.HEIGHT,null);
        
        //jugagdor
        estadosJ.draw(g);
        
        g.drawString(""+AVERAGEFPS, 10, 20);
        
        //-------------------
        g.dispose();
        bs.show();
    }
    
    private void init(){
        Assets.init();
        estadosJ = new estadosJuego();
    }
    
    
    @Override
    public void run() {
        
        long now=0;
        long lastTime= System.nanoTime();
        int frames=0;
        long time=0; 
        
        init();
        
        while(running){
            //calcular tiempo
           now=System.nanoTime();
           delta+=(now-lastTime)/TARGETTIME;
           time+=(now-lastTime);
           lastTime=now;
           
            if (delta>=1) {
                update();
                draw();
                delta--;
                frames++; 
            }
            if (time>=1000000000) {
                
                AVERAGEFPS=frames;
                frames=0;
                time=0;
            }
        }
        
        stop();
    }
    
    private void star(){
        thread = new Thread(this);
        thread.start();
        running=true;
        
    }
    private void stop(){
        try {
            thread.join();
            running=false;
        } catch (InterruptedException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
