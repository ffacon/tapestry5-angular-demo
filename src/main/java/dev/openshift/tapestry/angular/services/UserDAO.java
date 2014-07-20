package dev.openshift.tapestry.angular.services;


import dev.openshift.tapestry.angular.data.user.User;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import java.util.List;

public interface UserDAO
{
    User getUserByLogin(String login);

    @CommitAfter
    void add(User user);

}
