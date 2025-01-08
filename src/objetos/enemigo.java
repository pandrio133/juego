
package objetos;

import estados.estadosJuego;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vectores2D;


public class enemigo  extends  MovimientosObjeto{
    
    private ArrayList<Vectores2D> path;
    private Vectores2D currentNode;
    private int index;
    private boolean following;
    

    public enemigo(Vectores2D velocidad, double maxVel, Vectores2D posicion, BufferedImage textura, ArrayList<Vectores2D> path,estadosJuego EJ) {
        super(velocidad, maxVel, posicion, textura, EJ);
        this.path=path;
        index=0;
        following=true;
    }
    
    private Vectores2D pathFollowing(){
        currentNode = path.get(index);
        double distanceToNode = currentNode.subtraer(getCenter()).getMagnitud();
        if(distanceToNode < Constantes.NODE_RADIUS){
            index++;
            if(index >= path.size()){
                following = false;
                
            }
        }
        return seekForce(currentNode);
    }
    
    private Vectores2D seekForce(Vectores2D target){
        Vectores2D desiretVelocity = target.subtraer(getCenter());
        desiretVelocity = desiretVelocity.normalizado().escala(maxVel);
        return desiretVelocity.subtraer(velocidad);
        
    }
    public enemigo() {
    }
    
    

    @Override
    public void update() {
        Vectores2D pathFollowing;
        if(following){
            pathFollowing = pathFollowing();
        }else{
            pathFollowing = new Vectores2D();
        }
        pathFollowing = pathFollowing().escala(1/Constantes.ENEMIGO_MASA);
        velocidad = velocidad.add(pathFollowing);
        velocidad = velocidad.limit(maxVel);
        posicion = posicion.add(velocidad);
        
        if(posicion.getX()> Constantes.WIDTH || posicion.getY()>Constantes.HEIGHT ||
           posicion.getX()< 0 || posicion.getY()<0){
            Destruccion();
        }
        angulo += 0.05;
        
        colision();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d= (Graphics2D)g;
        
       at=AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
       at.rotate(angulo,width/2,height/2);
        g2d.drawImage( textura, at, null);
    }
    
}
