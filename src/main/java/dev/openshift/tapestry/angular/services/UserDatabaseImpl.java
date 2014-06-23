package dev.openshift.tapestry.angular.services;

import dev.openshift.tapestry.angular.data.user.User;

import java.util.Date;
import java.util.HashMap;


public class UserDatabaseImpl implements UserDatabase
{
	public static HashMap<Integer, User> users = new HashMap<Integer, User>();


    public UserDatabaseImpl()
	{
		User user = new User();
		user.setId(1);
		user.setFirstName("demo");
		user.setLastName("user");
		user.setUri("/user-management/users/1");
		user.setLastModified(new Date());
        user.setEmail("demo@demo.com");
		users.put(1, user);
	}
	
	public  User getUserById(Integer id)
	{
		return users.get(id);
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
