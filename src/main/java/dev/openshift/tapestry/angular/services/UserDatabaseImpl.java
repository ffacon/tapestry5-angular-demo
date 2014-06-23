package dev.openshift.tapestry.angular.services;

import dev.openshift.tapestry.angular.data.user.User;

import java.util.Date;
import java.util.HashMap;


public class UserDatabaseImpl implements UserDatabase
{
	public static HashMap<Integer, User> users = new HashMap<Integer, User>();


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
        String[] rolesAdmin = {"ROLE_ADMIN", "ROLE_USER"};
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
        String[] roles =  {"ROLE_USER"};
        user.setRoles(roles);
        users.put(2, user);


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
}
