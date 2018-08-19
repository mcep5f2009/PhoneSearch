package application;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class AddressBook {

    // key is Contact name
    private final Map<String, Contact> contacts = new HashMap<String, Contact>();

    public void addNumber(String name, String number) {
        Contact contact = contacts.get(name);
        if (contact == null) {
            contact = new Contact(name);
        }
        contact.addNumber(number);
        contacts.put(name, contact);
    }

    public Set<String> searchForNumber(String name) {
        Contact contact = contacts.get(name);
        if (contact == null) {
            return null;
        }
        return contact.getNumbers();
    }

    public Set<String> searchForPersonByNumber(String number) {
        Set<String> names = new HashSet<String>();
        for (Contact contact : contacts.values()) {
            if (contact.containsNumber(number)) {
                names.add(contact.getName());
            }
        }
        return names;
    }

    public void addAddress(String name, Address address) {
        Contact contact = contacts.get(name);
        if (contact == null) {
            contact = new Contact(name);
        }
        contact.addAddress(address);
        contacts.put(name, contact);
    }

    public Contact searchForPersonalInformation(String name) {
        return contacts.get(name);
    }

    public void deletePersonalInformation(String name) {
        contacts.remove(name);
    }

    public List<Contact> filteredListing(String keyword) {
        List<Contact> filteredContacts = new ArrayList<Contact>();
        keyword = keyword.trim();
        for (Contact contact : contacts.values()) {
            String name = contact.getName();
            Address address = contact.getAddress();
            if (keyword.isEmpty() || name.contains(keyword) || (address != null && address.toString().contains(keyword))) {
                filteredContacts.add(contact);
            }
        }
        Collections.sort(filteredContacts);
        return filteredContacts;
    }

}
