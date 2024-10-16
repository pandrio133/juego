
package math;

public class Vectores2D {
    
    private double x,y;
    public Vectores2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
//    public Vectores2D(double x1, double y1,boolean nulo) {
//        this.x1 = x1;
//        this.y1 = y1;
//        
//    }

    public Vectores2D() {
        x=0;
//        x1=0;
        y=0;
//        y1=0;
    }
    
    //metodos espeiciales 
    public Vectores2D add(Vectores2D v){
        
        return new Vectores2D(x+v.getX(), y+v.getY());
    }
    
    public Vectores2D subtraer(Vectores2D v){
        
        return new Vectores2D(x-v.getX(), y-v.getY());
    }
    
    public Vectores2D escala(double value){
        return new Vectores2D(x*value, y*value);
    }
    
    public Vectores2D limit(double value){
//        if(x>value){x=value;}
//        if(x<-value){x=-value;}
//        if(y>value){y=value; }
//        if(y<-value){y=-value; }
        if (getMagnitud()>value) {
            return this.normalizado().escala(value);
        }
    
        return this;
    }
//    double magnitud= getMagnitud();
    public Vectores2D normalizado(){
        double magnitud=getMagnitud();
        return new Vectores2D(x/magnitud, y/magnitud );
    }
    
    public double getMagnitud( ){
       
        return Math.sqrt(x*x + y*y);
    }
    
    public Vectores2D setDireccion(double angulo){
        double magnitud=getMagnitud();
        return new Vectores2D(Math.cos(angulo)*magnitud,Math.sin(angulo)*magnitud);
    }
    
    
    
    //metodos get y set
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y= y;
    }
    //... ... ...
//    public double getX1() {
//        return x1;
//    }
//
//    public void setX1(double x1) {
//        this.x1 = x1;
//    }
//
//    public double getY1() {
//        return y1;
//    }
//
//    public void setY1(double y1) {
//        this.y1 = y1;
//    }
    
    
     
    
}
