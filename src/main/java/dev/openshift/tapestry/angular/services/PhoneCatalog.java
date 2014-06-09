package dev.openshift.tapestry.angular.services;

import java.util.List;
import dev.openshift.tapestry.angular.data.Phone;




public interface PhoneCatalog
{
    /**
     * Provides a list of all phone in an indeterminate order.
     */
    List<Phone> getPhones();

    public String getPhonesAsString();

}
