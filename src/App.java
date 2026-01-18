import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.*;

public class App extends PApplet {
    Hoop firstHoop;
    ArrayList<Hoop> hoops;
    Basketball bball;
    boolean scorable;
    int score;
    int highScore;
    int scene;
    boolean gameStarted;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        hoops = new ArrayList<>();
        firstHoop = new Hoop(this, hoops);
        hoops.add(firstHoop);
        bball = new Basketball(this);
        scene = 0;
        gameStarted = false;
        score = 0;
        readHighScore();

    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {

        if (scene == 0 && gameStarted == false) {
            // Screen is frozen, says "Press Space To Start"

            if (keyCode == ' ') {
                scene = 1;
                gameStarted = true;

            } else {
                background(130);
                fill(255, 127, 0);
                bball.bballDisplay();

                fill(0);
                textSize(100);
                text(score, 375, 100);
            }
        }

        else if (scene == 1) {

            background(130);

            for (int i = 0; i < hoops.size(); i++) {// Loop through the hoops
                Hoop h = hoops.get(i);

                h.hoopDisplay();// Draws the hoops
                h.hoopAnimation();// Moves the hoops
                gameScore();// Calls the method that says if the ball goes through the hoop add to the score
            }

            if (frameCount % 100 == 0) { // Respawn hoops after 100 frames
                Hoop tempHoop = new Hoop(this, hoops);
                hoops.add(tempHoop);
            }

            fill(255, 127, 0);
            stroke(0, 0, 0);
            bball.bballDisplay();
            bball.bballGravity();

            fill(0);
            textSize(100);
            text(score, 375, 100);

            if (bball.getBballY() > 650) {
                scene = 2;

            }
        }

        else if (scene == 2) {
            // say press "r" to restart
            textSize(30);
            text("Press R to restart", 300, 200);
            if (key == 'r') { // The game just resumes not restarts
                scene = 1;
                score = 0;
                bball.restartBall();
                restartHoops();
                //freezeHoops(); I wanted the hoops to freeze when you click R until you press space but I couldn't get hoopSpeed

            }
        }
    }

    public void restartHoops() {
        hoops.clear(); // Resets the hoops
    }

    // public void freezeHoops(){
    //     for(Hoop h : hoops){
    //         hoops.gethoopSpeed();
    //         hoopSpeed = 0;
    //     }
    // }

    public void keyPressed() { // Method for ball jumping
        if (keyCode == ' ') {
            bball.jump();
        }
    }

    public void gameScore() {
        for (Hoop nextHoop : hoops) {// Loops through hoop list and allows the hoop to be scored on
            float bballX = bball.getBballX();
            float bballY = bball.getBballY();
            float hoopX = nextHoop.getHoopX();
            float hoopY = nextHoop.getHoopY();

            if (nextHoop.getScorable() && bballX > hoopX + 20 && bballX < hoopX + 80 && bballY > hoopY
                    && bballY < hoopY + 20) {

                score++;// Add to the score when ball goes in hoop
                nextHoop.stopScorable();
                System.out.println("scored");

                // readHighScore();
                if (score > highScore) {
                    highScore = score;
                    saveHighScore();
                }

            }
        }

    }

    public void saveHighScore() {
        try (PrintWriter writer = new PrintWriter("highscore.txt")) {
            writer.println(highScore); // Writes the integer to the file
            writer.close(); // Closes the writer and saves the file

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

    }

    public void readHighScore() {
        try (Scanner scanner = new Scanner(Paths.get("highscore.txt"))) {

            // we read the file until all lines have been read
            while (scanner.hasNextLine()) {
                // we read one line
                String row = scanner.nextLine();
                // we print the line that we read
                highScore = Integer.valueOf(row);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}