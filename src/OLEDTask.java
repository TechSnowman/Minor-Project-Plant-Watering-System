import	org.firmata4j.Pin;
import	org.firmata4j.ssd1306.MonochromeCanvas;
import	org.firmata4j.ssd1306.SSD1306;
import	java.util.TimerTask;

public	class OLEDTask extends	TimerTask	{

    private	final SSD1306 myOled;
    private final Pin WaterSensorPin;



    // Oled Display Constructor.
    public OLEDTask(SSD1306	display, Pin WaterSensor)	{
        this.myOled = display;
        this.WaterSensorPin = WaterSensor;
    }

    @Override
    public	void run()	{

        double WaterSensorValue = WaterSensorPin.getValue(); // Gets the instantaneous Current Value of the Water Sensor
        String StrWaterSensorValue = String.valueOf(WaterSensorValue);


        myOled.getCanvas().setTextsize(2); // Increases the Size of the Text by a Factor by 2
        myOled.getCanvas().fillRect(0,0,128,30, MonochromeCanvas.Color.DARK);

        if (WaterSensorPin.getValue() > Saturated.MOISTURE_THRESHOLD){ // Turn Water Pump ON Because the Soil is too dry
            myOled.getCanvas().drawString(0,0,"Soil Dry\nPump ON");
        }
        else if(WaterSensorPin.getValue() <= Saturated.MOISTURE_THRESHOLD){  //Turn Water Pump Off Because the Soil is too saturated
            myOled.getCanvas().drawString(0,0,"Soil Wet\nPump OFF");
        }

        myOled.getCanvas().drawHorizontalLine(0,37,128, MonochromeCanvas.Color.DARK);

        myOled.getCanvas().drawHorizontalLine(0,37, ((int)(WaterSensorValue)-500)/2, MonochromeCanvas.Color.BRIGHT);

        myOled.getCanvas().drawString(0,46,StrWaterSensorValue);

        myOled.display();


    }
}