package dev.openshift.tapestry.angular.services;


import dev.openshift.tapestry.angular.data.Comment;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import java.util.List;

public interface CommentDAO
{
    List<Comment> getCommentsByPhoneId(int phoneId);

    @CommitAfter
    void add(Comment comment);

}
