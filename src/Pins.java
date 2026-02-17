/*Maps the Arduino pin labels to those of Firmata */
public class Pins {
    static final int A0 = 14; // Potentiometer
    static final int A1 = 15; //Water Sensor
    static final int A2 = 16; // Sound

    static final int A6 = 20; //Water Sensor

    static final int D6 = 6; // Button
    static final int D4 = 4; // red LED
    static final int D2 = 2; // Water Pump
    static final int D13 = 13; // default LED on arduino
    static final byte I2C0 = 0x3C; // OLED Display
    private Pins() {
    }
}