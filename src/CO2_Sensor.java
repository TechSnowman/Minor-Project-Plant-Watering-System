import org.firmata4j.I2CDevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.ssd1306.SSD1306;

import java.io.IOException;



public class CO2_Sensor {
    public static void main(String[] args) throws
            IOException, InterruptedException {

        /* Initialize the Board */
        var arduinoObject = new FirmataDevice("COM3");
        arduinoObject.start();
        arduinoObject.ensureInitializationIsDone();



        var CO2Sensor = arduinoObject.getPin(Pins.A1);

        System.out.println("works");
        while (true){
            double data = CO2Sensor.getValue();
            System.out.println(data);

        }



        // Add an Event Listener to the board.
        // Pass the LEDObject, WaterPumpObject, and WaterSensor to the Listener.


    }
}

