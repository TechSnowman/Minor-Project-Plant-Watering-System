// Interface defined: (https://bit.ly/3IhVg1J)
import org.firmata4j.IODeviceEventListener;
import org.firmata4j.IOEvent;
import org.firmata4j.Pin;
import org.firmata4j.ssd1306.MonochromeCanvas;
import org.firmata4j.ssd1306.SSD1306;

import java.io.IOException;
public class WaterPumpTask implements
        IODeviceEventListener {
    private final Pin WaterPumpPin;
    private final Pin WaterSensorPin;
    private final Pin LED;

    // MinorProject Automatic Watering System constructor
    WaterPumpTask(Pin WaterPumpPin, Pin WaterSensorPin, Pin LED_PIN) {
        this.WaterPumpPin = WaterPumpPin;
        this.WaterSensorPin = WaterSensorPin;
        this.LED = LED_PIN;
    }
    // Define the four Listener methods.
    // Three of which are empty (not used)
    // Only the onPinChange() is completed.
    // Define how onPinChange responds to an event.
    @Override
    public void onPinChange(IOEvent event) {
        // Return right away if the event isn't from the WaterSensor.
        if (event.getPin().getIndex() != WaterSensorPin.getIndex()) {
            return;
            // to do: return;
        }

        double WaterSensorValue = WaterSensorPin.getValue(); // Gets the instantaneous Current Value of the Water Sensor

        if (WaterSensorPin.getValue() > Saturated.MOISTURE_THRESHOLD){ // Turn Water Pump ON Because the Soil is too dry
            try {

                System.out.println("\nToo Dry \nTurn Water Pump On");
                System.out.println(WaterSensorValue);
                LED.setValue(1);
                WaterPumpPin.setValue(1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(WaterSensorPin.getValue() <= Saturated.MOISTURE_THRESHOLD){  //Turn Water Pump Off Because the Soil is too saturated
            try {

                System.out.println("\nToo Much Water \nTurn WaterPump Off");
                System.out.println(WaterSensorValue);
                LED.setValue(0);
                WaterPumpPin.setValue(0);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // These are empty methods (nothing in the curly braces)
    @Override
    public void onStart(IOEvent event) {}
    @Override
    public void onStop(IOEvent event) {}
    @Override
    public void onMessageReceive(IOEvent event, String
            message) {}
}

