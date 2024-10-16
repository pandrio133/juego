
package objetos;

import estados.estadosJuego;
import graficos.Assets;
import input.KeyBoard;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import math.Vectores2D;

public class player extends MovimientosObjeto{
    
    private Vectores2D direccion;
    private Vectores2D aceleracion;
    
    private boolean Aceleracion=false;
    
    private Cronometro fireRate;
//    private final double AConst=0.2;
//    private final double DeltaAngle=0.1;
    

    public player() {
    }    

//    public player(Vectores2D direccion, Vectores2D aceleracion, Vectores2D velocidad, Vectores2D posicion, BufferedImage textura, int ancho, int alto) {
//        super(velocidad, posicion, textura, ancho, alto);
//        this.direccion = direccion;
//        this.aceleracion = aceleracion;
//    }
    
    public player(Vectores2D velocidad, double maxVel, Vectores2D posicion, BufferedImage textura,estadosJuego EJ) {
        super(velocidad, maxVel, posicion, textura,EJ);
        this.direccion = new Vectores2D(0, 1);
        this.aceleracion = new Vectores2D();
        this.EJuego=EJ;
        this.fireRate=new Cronometro();

   }

    public Vectores2D getDireccion() {
        return direccion;
    }

    public Vectores2D getVelocidad() {
        return velocidad;
    }

    public AffineTransform getAt() {
        return at;
    }

    public double getAngulo() {
        return angulo;
    }

    public BufferedImage getTextura() {
        return textura;
    }

    public Vectores2D getPosicion() {
        return posicion;
    }

//    
//    public Image JEscala(){
//        return getTextura().getScaledInstance(40, 40,10 );
//    }
//    public int getJEscalaH(){
//        return JEscala().getHeight(null);
//    }
//    public int getJEscalaW(){
//        return JEscala().getWidth(null);
//    }
    
    private Image ImgAdap;
    public Image Adaptador(BufferedImage img, int ancho, int alto){
        return (Image)img.getScaledInstance(ancho, alto, 1000);
        }
    //variables para limitar los disparos de escudo
    //    public int disparos;

    @Override
    public void update() {
        //movimientos de derecha e izquierda
            //derecha//modificar y separar teclado de letras y fechas movimiento y rotacion
        
        if (KeyBoard.CTRL&&KeyBoard.RIGHT) {
            angulo += Math.PI/70;
        }else if (KeyBoard.CTRL&&KeyBoard.LEFT) {
            angulo -= Math.PI/70;
        }else if (KeyBoard.E) {
            angulo += Constantes.DeltaAngle;//Math.PI/50;
        } else if (KeyBoard.Q) {
            angulo -= Constantes.DeltaAngle;//Math.PI/50;
        }
        
        if (KeyBoard.RIGHT) {
            posicion.setX(posicion.getX()+4);
            //izquierda
        }else if (KeyBoard.LEFT) {
             posicion.setX(posicion.getX()-4);
            //derecha
        }else if (KeyBoard.D) {
             posicion.setX(posicion.getX()+4);
             //izquierda
        }else if (KeyBoard.A) {
             posicion.setX(posicion.getX()-4);
        }
        //movimientos de arriba y abajo
            //arriba
        if (KeyBoard.UP) {
            //aceleracion= direccion.escala(AConst);
            posicion.setY(posicion.getY()-4);
            //abajo
        }else if (KeyBoard.DOWN) {
             
            posicion.setY(posicion.getY()+4);
             //arriba
        }else if (KeyBoard.W) {
             posicion.setY(posicion.getY()-4);
             
             //abajo
        }else if (KeyBoard.S) {
             posicion.setY(posicion.getY()+4);
             
        }
//        Rotación y propulsion
//        if (KeyBoard.E) {
//            angulo += Constantes.DeltaAngle;//Math.PI/50;
//        } else if (KeyBoard.Q) {
//            angulo -= Constantes.DeltaAngle;//Math.PI/50;
//        }
//        
//        //aceleracion direcioanada
//         if (KeyBoard.UP||KeyBoard.W) {
//            aceleracion=direccion.escala(Constantes.AConst);
//            Aceleracion= true;
//        }else{
//             if(velocidad.getMagnitud()!=0){
//                 aceleracion=velocidad.escala(-1).normalizado().escala(Constantes.AConst/2);
//                 Aceleracion=false;
//             }
//        }

        velocidad=velocidad.add(aceleracion);
        velocidad=velocidad.limit(maxVel);
        direccion=direccion.setDireccion(angulo-Math.PI/2);
        posicion= posicion.add(velocidad);
        
//limitador de ventana
        if (posicion.getX()> Constantes.WIDTH) {
            posicion.setX(0);
        }
        if (posicion.getY()> Constantes.HEIGHT) {
            posicion.setY(0);
        }
        if (posicion.getX()<0) {
            posicion.setX(Constantes.WIDTH);
        }
        if (posicion.getY()<-0) {
            posicion.setY(Constantes.HEIGHT);
        }
        
        
//disparar lanzas (laser?)
       //time y lasttime sirven para controlar el tiempo,
       //causan una diferncia de milisegundo mediante resta
        
        //condición con la que controlamos el tiempo
        fireRate.update();
        if (!fireRate.isRunning()){
            if (KeyBoard.SPACE||KeyBoard.F) {
                
            EJuego.getMovimientosObjetos().add(0,new Laser(
                    direccion, 
                    20, 
                    getCenter().add(direccion.escala(width*2)), 
                    Assets.arma, 
                    angulo,
                    EJuego
            ));
            fireRate.run(Constantes.FireRate);
            }
        }
        //disparar escudos limitados por 10 (falta implementar el tiempo entre disparos)
//        if (KeyBoard.CTRL&&KeyBoard.SPACE || KeyBoard.R) {
//            //condicion de contador;
//            if(disparos<=10){
//                
//            
//            EJuego.getMovimientosObjetos().add(new Laser(
//                    direccion, 
//                    8, 
////                    getCenter().add(direccion.escala(getJEscalaW()/2)), 
//                    new Vectores2D(posicion.getX()+60, posicion.getY()),
//                    Assets.escudo, 
//                    angulo                  
//            ));
//            EJuego.getMovimientosObjetos().add(new Laser(
//                    direccion, 
//                    8, 
////                    getCenter().add(direccion.escala(getJEscalaH()/2)),
//                    new Vectores2D(posicion.getX()-80, posicion.getY()),
//                    Assets.escudo, 
//                    angulo                  
//            ));}
//            //contador
//            disparos+=1;
//        }
       // Colision y destrucion de objetos
       colision();
    }   

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d= (Graphics2D)g;
        //posicionamiento de los efectos de velocidad 
        AffineTransform at1=AffineTransform.getTranslateInstance(posicion.getX()+ width/2+5, posicion.getY()+ heigt/2);
        AffineTransform at2=AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY()+ heigt/2);
        //posicionamiento del personaje
        at=AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());//traslacion del personaja segun direccion
        //rotacion de los efectos
        at2.rotate(angulo,width/2,0);
        at1.rotate(angulo, -5,0);
        //rotacion del personaje
        at.rotate(angulo,width/2,heigt/2);//rotacion del personaje
        
       //visualizacion de del personaje
        g2d.drawImage( textura, at, null);//personaje
      //visualizacion de los efectos
        if (Aceleracion) {//solo se ve si se acelera
            ImgAdap=Adaptador(Assets.EfectV, width/2, heigt/2);
        g2d.drawImage(ImgAdap, at2, null);
        g2d.drawImage(ImgAdap, at1, null);
        }
        
//        
    }    
    @Override
    protected Vectores2D getCenter(){
        return new Vectores2D(posicion.getX()+width/2,posicion.getY()+heigt/2);
    }
    
}
