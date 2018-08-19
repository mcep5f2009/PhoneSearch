package application;

import java.util.Set;
import java.util.HashSet;

public class Contact implements Comparable<Contact> {

    private final String name;
    private final Set<String> numbers = new HashSet<String>();
    private Address address;

    public Contact(String name) {
        this.name = name.trim();
    }

    public void addNumber(String number) {
        numbers.add(number.trim());
    }

    public boolean containsNumber(String searchedNumber) {
        for (String number : numbers) {
            if (number.equals(searchedNumber)) {
                return true;
            }
        }
        return false;
    }

    public void addAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Set<String> getNumbers() {
        return numbers;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public int compareTo(Contact o) {
        return name.compareTo(o.name);
    }

}
