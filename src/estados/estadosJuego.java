package estados;

import graficos.Assets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vectores2D;
import objetos.Constantes;
import objetos.Cronometro;
import objetos.MovimientosObjeto;
import objetos.Obtaculos;
import objetos.player;

public class estadosJuego {
    
    private player Jugador;
    private ArrayList<MovimientosObjeto> movimientosObjetos= new ArrayList<MovimientosObjeto>();
    
    private int obstaculos;
    private Cronometro fireRate=new Cronometro();
    
    public estadosJuego() {
              //new player(velocidad(), veloMax, posicion, textura, 0, 0)
              //new player(new Vectores2D(),5,new Vectores2D(100, 500), Assets.player, 40, 40)
        Jugador=new player(new Vectores2D(), Constantes.Playe_Max_Vel,new Vectores2D(Constantes.WIDTH/2, 500), Assets.player,this);
        
        movimientosObjetos.add(Jugador);
        
        obstaculos=1;
        startWave();
    }   
    
    private void startWave(){
        double x,y;
        BufferedImage textura;

            for(int i=0; i<obstaculos&& obstaculos<=8;obstaculos++,i++){
                x= i%2==0?Math.random()*Constantes.WIDTH:0;
                y=i%2==0?0:Math.random()*Constantes.HEIGHT;
            
                int random=(int)(Math.random()*10);
                
                if (random<=6 ) {
                    textura=Assets.rocas;
                    movimientosObjetos.add(new Obtaculos(
                        new Vectores2D(0, 0.4+(Math.random()*Math.PI*2)).setDireccion(1.56),//Math.random()*Math.PI*2), 
                        Constantes.Obtaculo_Vel*Math.random()+1, 
                        new Vectores2D(x, y),
                        textura, 
                        this));
                }
                if (random>6 ) {
                    textura=Assets.arbustos;
                    movimientosObjetos.add(new Obtaculos(
                        new Vectores2D(0, 0.4+(Math.random()*Math.PI*2)).setDireccion(1.6), 
                        Constantes.Obtaculo_Vel*Math.random()+1, 
                        new Vectores2D(x, y),
                        textura, 
                        this));
                }
              
        }
    }
    
    
    public void update(){
        
        for (int i = 0; i < movimientosObjetos.size(); i++) {
            movimientosObjetos.get(i).update();
        }
        
        for (int i = 0; i < movimientosObjetos.size(); i++) {
            if (movimientosObjetos.get(i) instanceof Obtaculos) {
                return ;
            }
            startWave();
        }
        
        
    }
    
    public void draw(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION , RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        
       for (int i = 0; i < movimientosObjetos.size(); i++) {
            movimientosObjetos.get(i).draw(g);
        }
    }

    public ArrayList<MovimientosObjeto> getMovimientosObjetos() {
        return movimientosObjetos;
    }
    
    
}
