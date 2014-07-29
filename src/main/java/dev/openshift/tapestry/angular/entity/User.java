package dev.openshift.tapestry.angular.entity;

import org.apache.tapestry5.beaneditor.NonVisual;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Transient
    private int id;
    private String uri;
    private String firstName;
    private String lastName;
    private Date lastModified;
    private String email;
    @Id
    private String login;
    private String password;
    @ElementCollection
    @CollectionTable(name="Roles", joinColumns=@JoinColumn(name="user_login"))
    @Column(name="roles")
    private List<String> roles;

    public User() {}

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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
