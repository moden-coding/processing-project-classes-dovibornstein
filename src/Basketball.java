import java.util.ArrayList;

import processing.core.*;

public class Basketball {

    private float bballX;
    private float bballY;
    private float velocity;
    private float accelleration;
    private PApplet canvas;

    public Basketball(PApplet c) {
        canvas = c;
        bballX = 50;
        bballY = 300;
        velocity = 3;
        accelleration = 0.25f;
    }

    public void bballDisplay() {
        canvas.circle(bballX, bballY, 35); // Basketball

    }

    public void bballGravity() { // Gravity for the ball
        velocity += accelleration;
        bballY += velocity;
    }

    public void jump() {
        velocity = -6.5f; // How high the ball jumps
    }

    public void restartBall(){
        bballY = 300;
        velocity = 0;
        accelleration = 0;

    }

    public float getBballX() {// Lets me use ballX in main
        return bballX;
    }

    public float getBballY() {// Lets me use ballY in main
        return bballY;
    }

    public float getvelocity() {
        return velocity;
    }



}
