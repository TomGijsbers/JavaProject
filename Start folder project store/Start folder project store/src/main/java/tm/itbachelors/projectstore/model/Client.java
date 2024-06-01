package tm.itbachelors.projectstore.model;


import java.util.ArrayList;

public class Client extends Person {
    private String cardNumber;
    private int yearOfBirth;
    private ArrayList<String> shoppingList = new ArrayList<>();

    // Constructor die firstName en lastName accepteert
    public Client(String firstName, String surName) {
        super(firstName, surName);  // Roept de constructor van Person aan
        // Hier kun je andere initialisaties voor Client doen
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = "undefined";
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public ArrayList<String> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ArrayList<String> shoppingList) {
        this.shoppingList = shoppingList;
    }

    // Method to add a product name to the shopping list
    public boolean addToShoppingList(String productName) {
        // Check if the shopping list already contains 5 products
        if (shoppingList.size() < 5) {
            // Check if the product name is already in the list
            if (!shoppingList.contains(productName)) {
                shoppingList.add(productName);
                return true; // Product name was successfully added
            }
        }
        return false; // Product name could not be added because list is full or already contains the product
    }

    // Method to get the number of products on the shopping list
    public int getNumberOnShoppingList() {
        return shoppingList.size();
    }

    public String toString() {
        return "Client "+ getSurName().toUpperCase()+" "+getFirstName() + " with card number "+getCardNumber();
    }
}


