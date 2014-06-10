package dev.openshift.tapestry.angular.data;

public class Storage {
    private String flash;
    private String ram;

    public Storage() {
    }

    public String getFlash() {
        return flash;
    }

    public void setFlash(String flash) {
        this.flash = flash;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }
}
