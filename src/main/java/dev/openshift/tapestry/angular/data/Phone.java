package dev.openshift.tapestry.angular.data;

/**
 "age": 0,
 "id": "motorola-xoom-with-wi-fi",
 "imageUrl": "img/phones/motorola-xoom-with-wi-fi.0.jpg",
 "name": "Motorola XOOM\u2122 with Wi-Fi",
 "snippet": "The Next, Next Generation\r\n\r\nExperience the future with Motorola XOOM with Wi-Fi, the world's first tablet powered by Android 3.0 (Honeycomb)."
 */
public class Phone {

    int age;
    String carrier;
    String id;
    String imageUrl;
    String name;
    String snippet;

    public Phone(int age,String id,String imageUrl,String name,String snippet)
    {
        this.age = age;
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.snippet = snippet;
    }

    public Phone()
    {
    }


    public long getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}
