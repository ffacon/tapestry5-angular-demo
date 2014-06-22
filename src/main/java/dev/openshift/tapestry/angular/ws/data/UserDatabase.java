package dev.openshift.tapestry.angular.ws.data;

import dev.openshift.tapestry.angular.ws.model.User;

import java.util.Date;
import java.util.HashMap;


public class UserDatabase 
{
	public static HashMap<Integer, User> users = new HashMap<Integer, User>();
	
	static 
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
	
	public static User getUserById(Integer id)
	{
		return users.get(id);
	}
	
	public static void updateUser(Integer id)
	{
		User user = users.get(id);
		user.setLastModified(new Date());
	}
	
	public static Date getLastModifiedById(Integer id)
	{
		return users.get(id).getLastModified();
	}
}
