
package objetos;


import estados.estadosJuego;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import math.Vectores2D;

public class Laser extends MovimientosObjeto{

    public Laser() {
    }

    public Laser(Vectores2D velocidad, double maxVel, Vectores2D posicion, BufferedImage textura, double angulo, estadosJuego EJ) {
        super(velocidad, maxVel, posicion, textura,EJ);
        this.angulo=angulo;
        this.velocidad=velocidad.escala(maxVel);
    }
    
    
    @Override
    public void update() {
        posicion= posicion.add(velocidad);
        if (posicion.getX()<0 || posicion.getX()> Constantes.WIDTH || posicion.getY()<0||posicion.getY()> Constantes.HEIGHT){
            Destruccion();
        }
        colision();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d=(Graphics2D)g;
        at=AffineTransform.getTranslateInstance(posicion.getX(),posicion.getY());
        at.rotate(angulo);
        g2d.drawImage(textura, at,null);
    }
    
    @Override
    protected Vectores2D getCenter(){
        return new Vectores2D(posicion.getX()+width/2,posicion.getY()+width/2);
    }
    
    
    
}
