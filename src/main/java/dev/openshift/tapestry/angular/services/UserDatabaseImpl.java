package dev.openshift.tapestry.angular.services;

import dev.openshift.tapestry.angular.data.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class UserDatabaseImpl implements UserDatabase
{
	public static HashMap<Integer, User> users = new HashMap<Integer, User>();
    public int lastId;

    public UserDatabaseImpl()
	{
		User userAdmin = new User();
        userAdmin.setId(1);
        userAdmin.setFirstName("user");
        userAdmin.setLastName("admin");
        userAdmin.setUri("/user-management/users/1");
        userAdmin.setLastModified(new Date());
        userAdmin.setEmail("admin@user.com");
        userAdmin.setPassword("password");
        userAdmin.setLogin("admin");

        List<String> rolesAdmin = new ArrayList<String>();
        rolesAdmin.add("ADMIN");
        rolesAdmin.add("USER");
        userAdmin.setRoles(rolesAdmin);
		users.put(1, userAdmin);

        User user = new User();
        user.setId(2);
        user.setFirstName("simple");
        user.setLastName("user");
        user.setUri("/user-management/users/2");
        user.setLastModified(new Date());
        user.setEmail("simple.user@user.com");
        user.setPassword("password");
        user.setLogin("user");
        List<String> roles = new ArrayList<String>();
        roles.add("USER");
        user.setRoles(roles);
        users.put(2, user);

        lastId = 2;
    }
	
	public  User getUserById(Integer id)
	{
		return users.get(id);
	}

    public User getUserByLogin(String login) {
        for (User user : users.values()) {
            if(user.getLogin().equals(login)) return user;
        }
        return null;
    }

    public  void updateUser(Integer id)
	{
		User user = users.get(id);
		user.setLastModified(new Date());
	}
	
	public  Date getLastModifiedById(Integer id)
	{
		return users.get(id).getLastModified();
	}

    public User add(User user) {
        lastId++;
        user.setId(lastId);
        user.setUri("/user-management/users/X");
        user.setLastModified(new Date());
        List<String> roles = new ArrayList<String>();
        roles.add("USER");
        user.setRoles(roles);
        users.put(lastId, user);
        return  user;
    }
}
