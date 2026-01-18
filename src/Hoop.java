import java.util.ArrayList;


import processing.core.*;

public class Hoop {

    private float hoopX;
    private float hoopY;
    private float hoopSpeed;
    private PApplet canvas;
    private boolean scorable;
    ArrayList<Hoop> hoops;

    

    public Hoop(PApplet c, ArrayList<Hoop> h){
        hoops = h;
        canvas = c;
        hoopX = 800;
        hoopY = canvas.random(250, 450); //Random number for the height of the hoop
        hoopSpeed = 2; //Speed hoops move
        scorable = true; //Starts the game being able to score

    }

    public void hoopDisplay(){
        canvas.fill(255, 0, 0);//Red rim
        canvas.rect(hoopX, hoopY, 100, 20);

        canvas.fill(130, 130, 130); //Rectangle to block off middle of hoop and make gap
        canvas.rect(hoopX + 20, hoopY, 60, 20);

        canvas.fill(255, 255, 255); //White for the net
        canvas.rect(hoopX + 20, hoopY, 60, 70); //Rectangle for the net

    }

    public void hoopAnimation(){
        hoopX -= hoopSpeed; //The hoopX move left at the same value of hoopSpeed
        hoopRedisplay();
        
        
    }

    public void hoopRedisplay(){ // If the hoop moves left of -100 it gets removed from the list
        if(hoopX < -100){
            hoops.remove(this);
        }

    }

    public float gethoopSpeed(){
        return hoopSpeed;
    }

    public float getHoopX(){ //Lets me use hoopX in main
        return hoopX;
    }
    
    public float getHoopY(){//Lets me use hoopY in main
        return hoopY;
    }

    public boolean getScorable(){//Lets me use scorable in main
        return scorable;
    }
    
    public void stopScorable(){
        scorable = false;
    }
}
