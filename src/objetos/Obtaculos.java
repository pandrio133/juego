
package objetos;

import estados.estadosJuego;
import graficos.Assets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import math.Vectores2D;

public class Obtaculos extends MovimientosObjeto{

    public Obtaculos() {
    }

    public Obtaculos(Vectores2D velocidad, double maxVel, Vectores2D posicion, BufferedImage textura, estadosJuego EJ) {
        super(velocidad, maxVel, posicion, textura, EJ);
    }
    
    
    
    @Override
    public void update() {
        posicion=posicion.add(velocidad);
        
        //limitador de ventana
        if (posicion.getX()> Constantes.WIDTH) {
            posicion.setX(0);
        }
        if (posicion.getY()> Constantes.HEIGHT) {
            posicion.setY(0);
            posicion.setX(8+(int)(Math.random()*800));
        }
        if (posicion.getX()<0) {
            posicion.setX(Constantes.WIDTH);
        }
        if (posicion.getY()<0) {
            posicion.setY(Constantes.HEIGHT);
        }
        
        
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d= (Graphics2D)g;
        
        
        
       //posicionamiento del obstaculo (roca)
       at=AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());//traslacion de la roca segun direccion 
       //rotacion del obstaculo (roca)
       at.rotate(angulo,angulo,angulo);//rotacion de la roca
       //visualizacion del obstaculo (roca)
        g2d.drawImage( textura, at, null);//roca
//        
//        //posicionamiento de obtaculo
//        at1=AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());//traslacion de la roca segun direccion 
//       //rotacion del obstaculo (arbusto)
//       at1.rotate(angulo,0,0);//rotacion de la roca
//       //visualizacion del obstaculo (arbusto)
//        g2d.drawImage( Assets.arbustos, at, null);//arbusto
    }
    
}
