public class Lamp {
    private boolean isOn;

    public void turnOn() {
        isOn = true;
        System.out.println("Light on? " + isOn);
    }

    public void turnOff() {
        isOn = false;
        System.out.println("Light on? " + isOn);
    }
}
