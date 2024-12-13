/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graficos;

import java.awt.image.BufferedImage;
import math.Vectores2D;

/**
 *
 * @author usuario
 */
public class Animation {
    
    private BufferedImage[] frames;
    private int velocidad;
    private int index;
    private boolean runnin;
    private Vectores2D posición;
    private long time, lastTime;
    
    public Animation() {
    }

    public Animation(BufferedImage[] frames, int velocidad, Vectores2D posición) {
         this.frames = frames;
        this.velocidad = velocidad;
        this.posición = posición;
        this.index = 0;
        this.runnin = true;
        this.time = 0;
        this.lastTime = System.currentTimeMillis();
        
    }

    
    public void update(){
        time += System.currentTimeMillis() - lastTime;
        lastTime= System.currentTimeMillis();
        
        if (time > velocidad) {
            time=0;
            index++;
            if (index>=frames.length) {
                runnin=false;
            }
 
        }
    }

    public boolean isRunnin() {
        return runnin;
    }

    public Vectores2D getPosición() {
        return posición;
    }

    public  BufferedImage getCurrentFrame(){
        return frames[index];
    }
    
    
    
}
