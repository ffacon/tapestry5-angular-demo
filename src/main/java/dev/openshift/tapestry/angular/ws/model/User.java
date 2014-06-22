package dev.openshift.tapestry.angular.ws.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "user")
public class User implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@XmlAttribute(name = "id")
	private int id;
	
	@XmlAttribute(name="uri")
	private String uri;
		
	@XmlElement(name = "firstName")
	private String firstName;
	
	@XmlElement(name = "lastName")
	private String lastName;
	
	@XmlElement(name="last-modified")
	private Date lastModified;

    @XmlElement(name="email")
    private String email;

    @XmlElement(name="login")
    private String login;



    public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
    public String getEmail() {return email;}
    public void setEmail(String email) {
        this.email = email;
    }
    public String getLogin() {return login;}
    public void setLogin(String login) { this.login = login; }
}
