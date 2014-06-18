package dev.openshift.tapestry.angular.services;

import java.util.List;

import dev.openshift.tapestry.angular.data.Comment;
import dev.openshift.tapestry.angular.data.Phone;
import dev.openshift.tapestry.angular.data.PhoneDetails;


public interface PhoneCatalog
{
    /**
     * Provides a list of all phone in an indeterminate order.
     */
    List<Phone> getPhones();

    String getPhonesAsString();

    PhoneDetails getPhonesDetails(String id);

    String getPhonesDetailsAsString(String id);

    void addComment(Comment comment);

    List<Comment> getComment(String id);

    Comment incLike(int commentId);

    Comment deleteComment(int commentId);


}
