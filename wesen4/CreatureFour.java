/*
 * todo: calibration routine at startup for settings light max value
 */

import lejos.nxt.*;

public class CreatureFour {
  static final int LEFT = 0;
  static final int RIGHT = 1;
  static final float MAX_SPEED = Motor.A.getMaxSpeed();
  static final int THRESHOLD = 512; // ca. adc_raw/2

  static int toggle = 0;
  static int lightIntensity[] = {0, 0};
  static float batteryVoltage = 0.0f;
  static float speed[] = {0.0f, 0.0f};
  
  static LightSensor lightSensor[] = new LightSensor[2];

	static {
		lightSensor[LEFT] = new LightSensor(SensorPort.S1);
		lightSensor[RIGHT] = new LightSensor(SensorPort.S2);
		
		Button.ENTER.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
				// was version a
				if(toggle == 0) {
					LCD.drawString("---CREATURE4b---", 0, 0);
					CreatureFour.toggle = 1;
				// was version b
				} else {
					LCD.drawString("---CREATURE4a---", 0, 0);
					CreatureFour.toggle = 0;
				}
			}

      		public void buttonReleased(Button b) {}
    	});
	}

	public static void setSpeed() {
		if(toggle == 0) { // Creature 4a
			for(int i=0; i<2; i++) {
				speed[i] = lightIntensity[i] < THRESHOLD ? lightIntensity[i]/10 * batteryVoltage : (2*THRESHOLD - lightIntensity[i])/10 * batteryVoltage;
			}
		} else { // Creature 4b; see Braitenberg.pdf page 18: dotted line (apprx.)
			for(int i=0; i<2; i++) {
				speed[i] = lightIntensity[i] < THRESHOLD ? 20*batteryVoltage : 80*batteryVoltage;
			}
		}
	}
  
	public static void main(String[] args) throws Exception {
		LCD.drawString("---CREATURE4a---", 0, 0);
		LCD.drawString("toggle w. orange", 0, 1);
		lightSensor[LEFT].setFloodlight(false);
		lightSensor[RIGHT].setFloodlight(false);
		while(!Button.ESCAPE.isDown()) {
			// 0...1023; ADC raw value
			lightIntensity[LEFT] = lightSensor[LEFT].readValue();
			lightIntensity[RIGHT] = lightSensor[RIGHT].readValue();
			batteryVoltage = Battery.getVoltage();

			setSpeed();

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
