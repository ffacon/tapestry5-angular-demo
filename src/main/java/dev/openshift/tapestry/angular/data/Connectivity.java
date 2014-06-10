package dev.openshift.tapestry.angular.data;


public class Connectivity {

    private String bluetooth;
    private String  cell;
    private boolean  gps;
    private boolean  infrared;
    private String  wifi;

    public Connectivity() {
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public boolean isGps() {
        return gps;
    }

    public void setGps(boolean gps) {
        this.gps = gps;
    }

    public boolean isInfrared() {
        return infrared;
    }

    public void setInfrared(boolean infrared) {
        this.infrared = infrared;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }
}
