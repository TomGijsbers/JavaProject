package tm.itbachelors.projectstore.model;
import java.util.ArrayList;

public class Store {
    private String name;
    private int numberCustomers;
    private ArrayList<Section> sectionList = new ArrayList<> ();


    public Store(String name) {
        this.name = name;
    }

    public ArrayList<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(ArrayList<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberCustomers() {
        return numberCustomers;
    }

    public void setNumberCustomers(int numberCustomers) {
        this.numberCustomers = numberCustomers;
    }
    public void addSection(Section section){
        sectionList.add(section);
    }

    public int getNumberOfSections() {
        return sectionList.size();
    }

    public Section searchSectionByName(String sectionName) {
        for (Section section : sectionList) {
            if (section.getName().equals(sectionName)) {
                return section;
            }
        }
        return null; // Return null if no section with the given name is found
    }

    public String registerCustomer(Client client) {
        numberCustomers++; // Increment the count of customers first
        if (client.getCardNumber().equals("undefined")) { // Ensure card number is only set if it's undefined
            String cardNumber = name.substring(0, 2) + numberCustomers; // Generate card number
            client.setCardNumber(cardNumber);
            return cardNumber;
        }
        return client.getCardNumber(); // Return existing card number if already defined
    }
}
