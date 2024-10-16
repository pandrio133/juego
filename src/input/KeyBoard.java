
package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener{
    
    private boolean []key=new boolean[254];
    public static boolean UP,LEFT,RIGHT,DOWN,CTRL,SPACE,A,S,D,W,Q,E,R,F;
    
    public KeyBoard(){
        UP=false;
        LEFT=false;
        RIGHT =false;
        DOWN =false;
        SPACE=false;
        
        A =false;
        S =false;
        D =false;
        F =false;
        
        Q =false;
        W =false;
        E =false;
        R =false;
    }
    
    public void update(){
        UP=key[KeyEvent.VK_UP];
        LEFT=key[KeyEvent.VK_LEFT];
        RIGHT=key[KeyEvent.VK_RIGHT];
        DOWN=key[KeyEvent.VK_DOWN];
        CTRL=key[KeyEvent.VK_CONTROL];
        SPACE=key[KeyEvent.VK_SPACE];
        
        A=key[KeyEvent.VK_A];
        S=key[KeyEvent.VK_S];
        D=key[KeyEvent.VK_D];
        W=key[KeyEvent.VK_W];
        Q=key[KeyEvent.VK_Q];
        E=key[KeyEvent.VK_E];
        F=key[KeyEvent.VK_F];
        R=key[KeyEvent.VK_R];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        key[e.getKeyCode()]=true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        key[e.getKeyCode()]=false; 
    }
    
    
    
}
