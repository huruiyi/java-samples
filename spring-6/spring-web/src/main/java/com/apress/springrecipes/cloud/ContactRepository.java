package com.apress.springrecipes.cloud;

import java.util.List;

import com.apress.springrecipes.cloud.model.Contact;

public interface ContactRepository {
    List<Contact> findAll();
    void save(Contact c);

}
