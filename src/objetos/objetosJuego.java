
package objetos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import math.Vectores2D;

public abstract class objetosJuego {
    
    protected BufferedImage textura;
    protected Vectores2D posicion;

    public objetosJuego(BufferedImage textura, Vectores2D posicion) {
        this.textura = textura;
        this.posicion = posicion;
        
    }
    
    public objetosJuego() {
    }
    
    public abstract void update();
    public abstract void draw(Graphics g);

    public Vectores2D getPosicion() {
        return posicion;
    }

    public void setPosicion(Vectores2D posicion) {
        this.posicion = posicion;
    }
    
    
}
