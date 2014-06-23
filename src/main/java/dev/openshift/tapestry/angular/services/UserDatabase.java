package dev.openshift.tapestry.angular.services;

import dev.openshift.tapestry.angular.ws.model.User;

import java.util.Date;
import java.util.HashMap;


public interface UserDatabase
{

	User getUserById(Integer id);

	void updateUser(Integer id);
	
	Date getLastModifiedById(Integer id);

}
