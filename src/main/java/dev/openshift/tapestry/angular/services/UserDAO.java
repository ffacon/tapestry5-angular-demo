package dev.openshift.tapestry.angular.services;


import dev.openshift.tapestry.angular.entity.User;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

public interface UserDAO
{
    User getUserByLogin(String login);

    @CommitAfter
    void add(User user);

}
