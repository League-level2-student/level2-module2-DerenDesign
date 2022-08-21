package _08_LeagueSnake;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import processing.core.PApplet;

public class LeagueSnake extends PApplet {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    
    /*
     * Game variables
     * 
     * Put all the game variables here.
     */
    int foodX;
    int foodY;
    int snakeDir = DOWN;
    int foodEaten = 0;
    Segment head = new Segment(50,50);
    ArrayList<Segment> Segments = new ArrayList<Segment>();
    /*
     * Setup methods
     * 
     * These methods are called at the start of the game.
     */
    @Override
    public void settings() {
        size(WIDTH,HEIGHT);
        
    }

    @Override
    public void setup() {
        frameRate(20);
        dropFood();
        
    }

    void dropFood() {
        // Set the food in a new random location
    	drawTail();
    	foodX = ((int)random(50)*10);
    	foodY = ((int)random(50)*10);
    }

    /*
     * Draw Methods
     * 
     * These methods are used to draw the snake and its food
     */

    @Override
    public void draw() {
    	background(0,0,255);
    	drawFood();
    	move();
    	drawSnake();
    	eat();
    }

    void drawFood() {
        // Draw the food
    	fill(0,255,0);
        rect(foodX,foodY, 10, 10);
        
    }

    void drawSnake() {
        // Draw the head of the snake followed by its tail
    	 fill(255,0,0);
         rect(head.x,head.y, 10, 10);
         manageTail();
    }

    void drawTail() {
        // Draw each segment of the tail
    	for(int i = 0; i < Segments.size(); i++){
    	fill(255,0,0);
        rect(Segments.get(i).x,Segments.get(i).y, 10, 10);
    	}
    }

    /*
     * Tail Management methods
     * 
     * These methods make sure the tail is the correct length.
     */

    void manageTail() {
    	checkTailCollision();
    	drawTail();
    	Segments.add(new Segment(head.x,head.y));
    	Segments.remove(0);
    	
        // After drawing the tail, add a new segment at the "start" of the tail and
        // remove the one at the "end"
        // This produces the illusion of the snake tail moving.

    }

    void checkTailCollision() {
        // If the snake crosses its own tail, shrink the tail back to one segment
    	for(int i = 0; i < Segments.size(); i++) {
        if(head.x == Segments.get(i).x &&  head.y == Segments.get(i).y) {
        	foodEaten = 1;
        	Segments.clear();
        	Segments.add(new Segment(head.x,head.y));
        }
    	}
    }

    /*
     * Control methods
     * 
     * These methods are used to change what is happening to the snake
     */

    @Override
    public void keyPressed() {
        // Set the direction of the snake according to the arrow keys pressed
        if(keyCode == UP) {
        	snakeDir = KeyEvent.VK_UP;
        }
        if(keyCode == DOWN) {
        	snakeDir = KeyEvent.VK_DOWN;
        }
        if(keyCode == LEFT) {
        	snakeDir = KeyEvent.VK_LEFT;
        }
        if(keyCode == RIGHT) {
        	snakeDir = KeyEvent.VK_RIGHT;
        }
    }

    void move() {
        // Change the location of the Snake head based on the direction it is moving.
    	
        
        if (snakeDir == UP) {
        	
        	
        	 head.y = head.y -10;
        	 checkBoundaries();
        } else if (snakeDir == DOWN) {
            // Move head down
        	
        	head.y = head.y +10;
        	 checkBoundaries();
        } else if (snakeDir == LEFT) {
        	
        	 head.x = head.x - 10;
        	 checkBoundaries();
        } else if (snakeDir == RIGHT) {
        	
        	 head.x = head.x + 10;
        	 checkBoundaries();
        }
        
    }

    void checkBoundaries() {
        if(head.x > WIDTH) {
        	head.x = 0;
        }
        if(head.x < 0) {
        	head.x = WIDTH;
        }
        if(head.y > HEIGHT) {
        	head.y = 0;
        }
        if(head.y < 0) {
        	head.y = HEIGHT;
        }	
        }
        

    void eat() {
        // When the snake eats the food, its tail should grow and more
        // food appear
    	if(head.x == foodX && head.y == foodY) {
    		foodEaten++;
    		dropFood();
    		Segments.add(new Segment(head.x,head.y));
    	}
    	
        
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
