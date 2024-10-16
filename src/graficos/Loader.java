
package graficos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Loader{
    
    public static BufferedImage ImageLoader(String Path){
        try {
            File archivo=new File(Path);
            return ImageIO.read(archivo);
            
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
