import lejos.nxt.*;


public class SensorTest {
  static LightSensor lightSensor = new LightSensor(SensorPort.S1);
  static SoundSensor soundSensor = new SoundSensor(SensorPort.S2);
  
    public static void main (String[] args) throws Exception {
		while (!Button.ESCAPE.isDown()) {
			//LCD.clear();
			/*for (int i = 0; i < 100; i++) {
				LCD.setPixel(1, i, 60 - (soundSensor.readValue() / 2));
			}*/
			LCD.drawInt(lightSensor.getLightValue(), 4, 6, 0);
			LCD.drawInt(lightSensor.getNormalizedLightValue(), 4, 6, 1);
			LCD.drawInt(SensorPort.S1.readRawValue(), 4, 6, 2);
			LCD.drawInt(SensorPort.S1.readValue(), 4, 6, 3);
			LCD.drawInt(soundSensor.getNormalizedLightValue(), 4, 6, 4);
			//Thread.sleep(20);
		}
	}

}
