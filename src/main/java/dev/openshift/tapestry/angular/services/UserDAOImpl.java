package dev.openshift.tapestry.angular.services;

import dev.openshift.tapestry.angular.entity.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAOImpl implements UserDAO
{
    private final Session session;

    public UserDAOImpl(Session session)
    {
        this.session = session;
    }

    public User getUserByLogin(String login){
        return (User)session.createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
    }

    public void add(User user)
    {
        user.setUri("/user-management/users/"+user.getLogin());
        user.setLastModified(new Date());
        List<String> roles = new ArrayList<String>();
        roles.add("USER");
        user.setRoles(roles);
        session.save(user);
    }

}
