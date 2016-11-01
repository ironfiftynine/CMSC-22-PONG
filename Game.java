import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {
  
 int p1Score = 0;
 int p2Score = 0;

 Ball ball = new Ball(this);
 Racquet racquet = new Racquet(this, 630);
 Racquet racquet2 = new Racquet(this, 60);
 int speed = 1;

 private int getScore() {
  return speed - 1;
 }
 

 public Game() {
  addKeyListener(new KeyListener() {
   @Override
   public void keyTyped(KeyEvent e) {
   }

   @Override
   public void keyReleased(KeyEvent e) {
    racquet.keyReleased(e);
   }

   @Override
   public void keyPressed(KeyEvent e) {
    racquet.keyPressed(e);
    
   }
  });
    
      addKeyListener(new KeyListener() {
   @Override
   public void keyTyped(KeyEvent f) {
   }

   @Override
   public void keyReleased(KeyEvent f) {
    racquet2.keyReleased(f);
   }

   @Override
   public void keyPressed(KeyEvent f) {
    
    if (f.getKeyCode() == KeyEvent.VK_A)
      racquet2.xa = -speed;
    if (f.getKeyCode() == KeyEvent.VK_D)
      racquet2.xa = speed;
   }
  });
    
  setFocusable(true);
  Sound.BACK.loop();
 }

 private void move() {
  ball.move();
  racquet.move();
  racquet2.move();
 }

 @Override
 public void paint(Graphics g) {
  super.paint(g);
  Graphics2D g2d = (Graphics2D) g;
  g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    RenderingHints.VALUE_ANTIALIAS_ON);
  
  g2d.setColor(Color.BLACK);
  ball.paint(g2d);
  //player 2
  g2d.setColor(Color.RED);
  racquet.paint(g2d);
  //player 1
  g2d.setColor(Color.BLUE);
  racquet2.paint(g2d);

  g2d.setColor(Color.BLACK);
  g2d.setFont(new Font("Verdana", Font.BOLD, 30));
  g2d.drawString("----------------------------", 0, 350);
  g2d.setColor(Color.BLUE);
  g2d.drawString("Jedi", 50, 30);
  g2d.setColor(Color.BLACK);
  g2d.drawString("Score (Player 1)", 115, 30);
  g2d.drawString(String.valueOf(p1Score), 10, 30);
  g2d.setColor(Color.RED);
  g2d.drawString("Sith", 50, 690);
  g2d.setColor(Color.BLACK);
  g2d.drawString("Score (Player 2)", 115, 690);
  g2d.drawString(String.valueOf(p2Score), 10, 690);
 }

 //reset the game
 public void reset(){
   speed = 1;
   ball = new Ball(this);
   racquet = new Racquet(this, 630);
   racquet2 = new Racquet(this, 60);
   p1Score = 0;
   p2Score = 0;
   Sound.BACK.loop();
 }
 
 //reset the ball to the middle
 public void ballReset(){
   speed = 1;
   ball = new Ball(this);
   
 }
 
 public void gameOver(int i) {
  Sound.BACK.stop();
  Sound.GAMEOVER.play();
  //option for rematch
  int n = JOptionPane.showConfirmDialog(this, "Rematch", "Player" + String.valueOf(i) + "Wins",
  JOptionPane.YES_NO_OPTION);
  if(n == JOptionPane.YES_OPTION){
    reset();
  }
  else{
  System.exit(ABORT);
  }
 }
 //check who wins
 public void checkWinner(){
   if(p1Score == 3){
   gameOver(1);
   }
   else if(p2Score == 3){
   gameOver(2);
   }
   else{
   //do nothing
   }
 
 }

 public static void main(String[] args) throws InterruptedException {
  JFrame frame = new JFrame("Tennis Wars");
  Game game = new Game();
  frame.add(game);
  frame.setSize(400, 730);
  frame.setVisible(true);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  while (true) {
   game.move();
   game.checkWinner();
   game.repaint();
   Thread.sleep(10);
  }
 }
}