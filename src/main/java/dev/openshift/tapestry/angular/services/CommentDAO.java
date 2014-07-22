package dev.openshift.tapestry.angular.services;


import dev.openshift.tapestry.angular.entity.Comment;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import java.util.List;

public interface CommentDAO
{
    List<Comment> getCommentsByPhoneId(String phoneId);

    @CommitAfter
    void add(Comment comment);

    @CommitAfter
    Comment incLike(int commentId);

    @CommitAfter
    Comment delete(int commentId);

}
