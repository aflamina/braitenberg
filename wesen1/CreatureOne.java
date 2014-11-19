import lejos.nxt.*;


public class CreatureOne {
  static SoundSensor soundSensor = new SoundSensor(SensorPort.S2);
  static int soundPressure = 0;
  static float batteryVoltage, speed = 0.0f;
  
    public static void main (String[] args) throws Exception {
		while (!Button.ESCAPE.isDown()) {
			soundPressure = soundSensor.readValue();
			batteryVoltage = Battery.getVoltage();
			speed = soundPressure * batteryVoltage;
			LCD.drawString("---CREATURE1---", 0, 0);
			LCD.drawString("SPL[%]: " + soundPressure, 0, 1);
			LCD.drawString("U_batt[V]: " + batteryVoltage, 0, 2);
			LCD.drawString("w['/s]: " + speed, 0, 3);
			Motor.A.setSpeed(speed);
			Motor.C.setSpeed(speed);
			Motor.A.forward();
			Motor.C.forward();
		}
	}

}
