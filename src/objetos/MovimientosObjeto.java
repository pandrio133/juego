
package objetos;

import estados.estadosJuego;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vectores2D;

public abstract class MovimientosObjeto extends objetosJuego{
    
    protected Vectores2D velocidad;
    protected AffineTransform at;
    protected double angulo;
    protected double maxVel;
    protected int width;
    protected int heigt;
    
    protected estadosJuego EJuego;
//    public MovimientosObjeto(Vectores2D velocidad, AffineTransform at, double angulo, BufferedImage textura, Vectores2D posicion, int ancho, int alto) {
//        super(textura, posicion, ancho, alto);
//        this.velocidad = velocidad;
//        this.at = at;
//        this.angulo = angulo;
//    }
   
//    public MovimientosObjeto(Vectores2D velocidad, Vectores2D posicion, BufferedImage textura) {
//        super(textura, posicion);
//        this.velocidad = velocidad;
//        this.angulo = 0;
//    }
        
    public MovimientosObjeto(Vectores2D velocidad,  double maxVel,Vectores2D posicion, BufferedImage textura, estadosJuego EJ) {
        super(textura, posicion);
        
        this.velocidad = velocidad;
        this.maxVel = maxVel;
        this.angulo = 0;
        this.EJuego=EJ;
        heigt=textura.getHeight();
        width=textura.getWidth();
    }

     public MovimientosObjeto() {
    }
     
    protected void colision(){
        ArrayList<MovimientosObjeto> movimientosObjetos= EJuego.getMovimientosObjetos();
        for (int i = 0; i < movimientosObjetos.size(); i++) {
            MovimientosObjeto m=movimientosObjetos.get(i);
            if (m.equals(this)) {
                continue;
            }
            double distancia=m.getCenter().subtraer(getCenter()).getMagnitud();
            if (distancia<m.width/2+width/2 && movimientosObjetos.contains(this)) {
                objetosDeColision(m, this);
            }
        }
    }
    private void objetosDeColision(MovimientosObjeto a, MovimientosObjeto b){
        if (!(a instanceof Obtaculos && b instanceof Obtaculos)){
             a.Destruccion();
             b.Destruccion();
        }
    }
    
    protected void Destruccion(){
        EJuego.getMovimientosObjetos().remove(this);
    }
    protected Vectores2D getCenter(){
        return new Vectores2D(posicion.getX()+width/2,posicion.getY()+width/2);
    }
    
    
}
