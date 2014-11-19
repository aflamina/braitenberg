import lejos.nxt.Motor;
import lejos.util.Delay;
import lejos.nxt.LCD;
import lejos.nxt.Button;

public class MotorTest {
  static int speed = 360;
  
  public static void main (String[] args) throws Exception {
    LCD.drawString("Motortest", 0, 0);
    Button.waitForAnyPress();
    init();
    Motor.A.rotate(720, true);
    Motor.C.rotate(720, true);
    while(Motor.A.isMoving()||Motor.C.isMoving());
    Button.waitForAnyPress();    
  }
  
  public static void init() {
	LCD.clear();
    Motor.A.setSpeed(speed);
    Motor.C.setSpeed(speed);
  }
}
