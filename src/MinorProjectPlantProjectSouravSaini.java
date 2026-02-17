import org.firmata4j.I2CDevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.ssd1306.MonochromeCanvas;
import org.firmata4j.ssd1306.SSD1306;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MinorProjectPlantProjectSouravSaini {
    public static void main(String[] args) throws
            IOException, InterruptedException {

        /* Initialize the Board */
        var arduinoObject = new FirmataDevice("COM3");
        arduinoObject.start();
        arduinoObject.ensureInitializationIsDone();

        var WaterPump = arduinoObject.getPin(Pins.D2);
        WaterPump.setMode(Pin.Mode.OUTPUT);

        // 1. Assign memory location to the WaterSensor object
        // 2. Fill the object.

        var WaterSensor = arduinoObject.getPin(Pins.A1);

        var LED = arduinoObject.getPin(Pins.D4);
        LED.setMode(Pin.Mode.OUTPUT);

        //OLED Display
        I2CDevice i2cObject = arduinoObject.getI2CDevice(Pins.I2C0); // Use 0x3C for the Grove OLED
        SSD1306 myOledDisplay; // 128x64 OLED SSD1515
        myOledDisplay = new SSD1306(i2cObject, SSD1306.Size.SSD1306_128_64);

        // Initialize the OLED (SSD1306) object
        myOledDisplay.init();


        // Add an Event Listener to the board.
        // Pass the LEDObject, WaterPumpObject, and WaterSensor to the Listener.

        var timer = new Timer();
        WaterPumpTask WaterPlantPump = new WaterPumpTask(WaterPump, WaterSensor,LED); // Constructor for Water Pump Task

        OLEDTask OledDisplay = new OLEDTask(myOledDisplay,WaterSensor); // Constructor for OLED Display Task

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                OledDisplay.run();
                arduinoObject.addEventListener(WaterPlantPump);

            }
        },0,1);
    }
}