package dev.openshift.tapestry.angular.services;

import dev.openshift.tapestry.angular.data.Comment;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDAOImpl implements CommentDAO
{
    private final Session session;

    public CommentDAOImpl(Session session)
    {
        this.session = session;
    }

    public List<Comment> getCommentsByPhoneId(int phoneId) {
        return session.createCriteria(Comment.class).add(Restrictions.eq("phoneid", phoneId)).addOrder(Order.desc("id")).list();
    }

    public void add(Comment comment) {
        session.save(comment);
    }
}
