package dev.openshift.tapestry.angular.data;


public class Hardware {

    private boolean accelerometer;
    private String audioJack;
    private String cpu;
    private boolean fmRadio;
    private boolean physicalKeyboard;
    private String usb;

    public Hardware() {
    }

    public boolean isAccelerometer() {
        return accelerometer;
    }

    public void setAccelerometer(boolean accelerometer) {
        this.accelerometer = accelerometer;
    }

    public String getAudioJack() {
        return audioJack;
    }

    public void setAudioJack(String audioJack) {
        this.audioJack = audioJack;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public boolean isFmRadio() {
        return fmRadio;
    }

    public void setFmRadio(boolean fmRadio) {
        this.fmRadio = fmRadio;
    }

    public boolean isPhysicalKeyboard() {
        return physicalKeyboard;
    }

    public void setPhysicalKeyboard(boolean physicalKeyboard) {
        this.physicalKeyboard = physicalKeyboard;
    }

    public String getUsb() {
        return usb;
    }

    public void setUsb(String usb) {
        this.usb = usb;
    }
}
