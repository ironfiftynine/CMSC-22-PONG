

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
 private static final int DIAMETER = 30;
 
 int x = 200;
 int y = 350;
 int xa = 1;
 int ya = 1;
 private Game game;

 public Ball(Game game) {
  this.game = game;
 }

 void move() {
  boolean changeDirection = true;
  if (x + xa < 0){
   xa = game.speed;
  }
  else if (x + xa > game.getWidth() - DIAMETER)
   xa = -game.speed;
  
  //scoreP1
  else if (y + ya < 0){
   ya = game.speed;
     x = 200;
     y = 350;
     ++game.p2Score;
     //reset ball to the middle if score
     game.ballReset();
     xa = -game.speed;
  }
  //scoresP2
  else if (y + ya > game.getHeight() - DIAMETER){
    ++game.p1Score;
     x = 200;
     y = 350;
     //reset ball to the middle if score
     game.ballReset();
    ya = -game.speed;
  }
  
  
  else if (collision()){
   ya = -game.speed;
   y = game.racquet.getTopY() - DIAMETER;
   game.speed++;   
  }
  
  else if (collision2()){
    ya = game.speed;
    y = game.racquet2.getTopY() + DIAMETER;
    game.speed++; 
  }
  
  else 
   changeDirection = false;
  
  if (changeDirection) 
   Sound.BALL.play();
  x = x + xa;
  y = y + ya;
 }

 private boolean collision() {
  return game.racquet.getBounds().intersects(getBounds());
 }

  private boolean collision2() {
  return game.racquet2.getBounds().intersects(getBounds());
 }
 
 public void paint(Graphics2D g) {
  g.fillOval(x, y, DIAMETER, DIAMETER);
 }

 public Rectangle getBounds() {
  return new Rectangle(x, y, DIAMETER, DIAMETER);
 }
}

