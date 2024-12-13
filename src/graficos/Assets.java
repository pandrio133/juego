
package graficos;

import java.awt.image.BufferedImage;

public class Assets {
    //personaje
    public static BufferedImage player;
    //fondo
    public static BufferedImage fondo;
    //efectos del juego
    public static BufferedImage EfectV;
    public static BufferedImage[] EfectEx = new BufferedImage[3];
    
    
    //armas
    public static BufferedImage arma;
    public static BufferedImage escudo;
    
    //enemigos
    public static BufferedImage rinoceronte;
    public static BufferedImage jabali;
    public static BufferedImage jabali_p;
    public static BufferedImage liebre;
    public static BufferedImage raton;
    
    //obstaculos
    public static BufferedImage arbustos;
    public static BufferedImage rocas;
    
    
    public static void init(){
        player=Loader.ImageLoader("recursos/imagenes/caballero.png");
        
        fondo=Loader.ImageLoader("recursos/imagenes/tierra.jpg");
        
        EfectV=Loader.ImageLoader("recursos/efectos/fast.png");
        for (int i = 0; i < 3; i++) {
        EfectV=Loader.ImageLoader("recursos/efectos/Ex"+i+".png");
        }
        
        arma=Loader.ImageLoader("recursos/imagenes/arma.png");
        escudo=Loader.ImageLoader("recursos/imagenes/escudo.png");
        
        rinoceronte=Loader.ImageLoader("recursos/imagenes/rinoceronte.png");
        jabali=Loader.ImageLoader("recursos/imagenes/jabali.png");
        jabali_p=Loader.ImageLoader("recursos/imagenes/p_jabali.png");
        liebre=Loader.ImageLoader("recursos/imagenes/liebre.png");
        raton=Loader.ImageLoader("recursos/imagenes/raton.png");
        
        
        rocas=Loader.ImageLoader("recursos/imagenes/rocas.png");
        arbustos=Loader.ImageLoader("recursos/imagenes/arbustos.png");
    }
     
}
