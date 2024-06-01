package tm.itbachelors.projectstore.model;

import java.util.Locale;

public class Person {
    private String firstName;
    private String surName;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.surName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String lastName) {
        this.surName = lastName;
    }

  public String toString() {
       var txt = getSurName().toUpperCase()+" "+getFirstName();
       return txt;
  }
}
