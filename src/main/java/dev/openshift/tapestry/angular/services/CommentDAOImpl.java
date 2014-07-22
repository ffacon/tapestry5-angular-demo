package dev.openshift.tapestry.angular.services;

import dev.openshift.tapestry.angular.entity.Comment;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CommentDAOImpl implements CommentDAO
{
    private final Session session;

    public CommentDAOImpl(Session session)
    {
        this.session = session;
    }

    public List<Comment> getCommentsByPhoneId(String phoneId) {
        return session.createCriteria(Comment.class).add(Restrictions.eq("phoneId", phoneId)).addOrder(Order.desc("id")).list();
    }

    public void add(Comment comment) {
        session.save(comment);
    }

    public Comment incLike(int commentId) {
        Comment comment = (Comment) session.createCriteria(Comment.class).add(Restrictions.eq("id", commentId)).uniqueResult();
        comment.setLikes(comment.getLikes()+1);
        session.save(comment);
        return comment;
    }

    public Comment delete(int commentId) {
        Comment comment = (Comment) session.createCriteria(Comment.class).add(Restrictions.eq("id", commentId)).uniqueResult();
        session.delete(comment);
        return comment;
    }
}
