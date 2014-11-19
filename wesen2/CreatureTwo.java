import lejos.nxt.*;

public class CreatureTwo {
  static final int LEFT = 0;
  static final int RIGHT = 1;
  static int toggle = 0;

  static LightSensor lightSensor[] = new LightSensor[2];
  static int lightIntensity[] = {0, 0};
  static float batteryVoltage = 0.0f;
  static float speed[] = {0.0f, 0.0f};

	static {
		lightSensor[LEFT] = new LightSensor(SensorPort.S1);
		lightSensor[RIGHT] = new LightSensor(SensorPort.S2);
		
		Button.ENTER.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
				// was version a
				if(toggle == 0) {
					LCD.drawString("---CREATURE2b---", 0, 0);
					CreatureTwo.toggle = 1;
				// was version b
				} else {
					LCD.drawString("---CREATURE2a---", 0, 0);
					CreatureTwo.toggle = 0;
				}
			}

      		public void buttonReleased(Button b) {}
    	});
	}
  
	public static void main(String[] args) throws Exception {
		LCD.drawString("---CREATURE2a---", 0, 0);
		LCD.drawString("toggle w. orange", 0, 1);
		lightSensor[LEFT].setFloodlight(false);
		lightSensor[RIGHT].setFloodlight(false);
		while(!Button.ESCAPE.isDown()) {
			// 0...1023/10
			lightIntensity[LEFT] = lightSensor[LEFT].getNormalizedLightValue()/10;
			lightIntensity[RIGHT] = lightSensor[RIGHT].getNormalizedLightValue()/10;
			batteryVoltage = Battery.getVoltage();
			speed[(LEFT+toggle)%2] = lightIntensity[LEFT] * batteryVoltage;
			speed[(RIGHT+toggle)%2] = lightIntensity[RIGHT] * batteryVoltage;
			LCD.drawString("I_l: " + lightIntensity[LEFT], 0, 2);
			LCD.drawString("I_r: " + lightIntensity[RIGHT], 0, 3);
			LCD.drawString("U_batt[V]: " + batteryVoltage, 0, 4);
			LCD.drawString("w_l['/s]: " + speed[LEFT] , 0, 5);
			LCD.drawString("w_r['/s]: " + speed[RIGHT], 0, 6);
			Motor.A.setSpeed(speed[LEFT]);
			Motor.C.setSpeed(speed[RIGHT]);
			Motor.A.forward();
			Motor.C.forward();
		}
	}
}
