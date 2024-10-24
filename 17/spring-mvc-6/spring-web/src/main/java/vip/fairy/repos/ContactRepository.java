package vip.fairy.repos;

import java.util.List;

import vip.fairy.model.Contact;

public interface ContactRepository {
    List<Contact> findAll();
    void save(Contact c);

}
