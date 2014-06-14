package dev.openshift.tapestry.angular.data;


import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import java.io.IOException;

public class PhoneDetails {
    private String additionalFeatures;
    private Android android;
    private String[]  availability;
    private Battery battery;
    private Camera camera;
    private Connectivity connectivity;
    private String description;
    private Display display;
    private Hardware hardware;
    private String id;
    private String[] images;
    private String name;
    private SizeAndWeight sizeAndWeight;
    private Storage storage;

    public PhoneDetails() {
    }

    public String getAdditionalFeatures() {
        return additionalFeatures;
    }

    public void setAdditionalFeatures(String additionalFeatures) {
        this.additionalFeatures = additionalFeatures;
    }

    public Android getAndroid() {
        return android;
    }

    public void setAndroid(Android android) {
        this.android = android;
    }

    public String[] getAvailability() {
        return availability;
    }

    public void setAvailability(String[] availability) {
        this.availability = availability;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Connectivity getConnectivity() {
        return connectivity;
    }

    public void setConnectivity(Connectivity connectivity) {
        this.connectivity = connectivity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SizeAndWeight getSizeAndWeight() {
        return sizeAndWeight;
    }

    public void setSizeAndWeight(SizeAndWeight sizeAndWeight) {
        this.sizeAndWeight = sizeAndWeight;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }


    public JSONObject getJSONObject() {
        String json="";
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        JSONObject ret = new JSONObject();

        ret.put("additionalFeatures",this.additionalFeatures);
        ret.put("description",this.description);
        ret.put("id",this.id);
        ret.put("name",this.name);

        try {
            json = ow.writeValueAsString(this.android);
            ret.put("android",new JSONObject(json));
            json = ow.writeValueAsString(this.availability);
            ret.put("availability",new JSONArray(json));
            json = ow.writeValueAsString(this.battery);
            ret.put("battery",new JSONObject(json));
            json = ow.writeValueAsString(this.camera);
            ret.put("camera",new JSONObject(json));
            json = ow.writeValueAsString(this.connectivity);
            ret.put("connectivity",new JSONObject(json));
            json = ow.writeValueAsString(this.display);
            ret.put("display",new JSONObject(json));
            json = ow.writeValueAsString(this.hardware);
            ret.put("hardware",new JSONObject(json));
            json = ow.writeValueAsString(this.images);
            ret.put("images",new JSONArray(json));
            json = ow.writeValueAsString(this.sizeAndWeight);
            ret.put("sizeAndWeight",new JSONObject(json));
            json = ow.writeValueAsString(this.storage);
            ret.put("storage",new JSONObject(json));


        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return ret;

    }
}
