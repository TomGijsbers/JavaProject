package tm.itbachelors.projectstore.controller;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tm.itbachelors.projectstore.model.Client;
import tm.itbachelors.projectstore.model.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class MainController{

    /*  You will need these methods in part 3 of the project assignment.
    private ArrayList<Employee> employeeArrayList;
    private ArrayList<Client> clientArrayList;
    private ArrayList<Store> storeArrayList;
*/

    //Write your code here after this line
    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @GetMapping("/1_newClient")
    public String newClient() {
        return "1_newClient";
    }

    @GetMapping("/showNewClient")
    public String showNewClient() {
        return "showNewClient";
    }

    @GetMapping("/3_newEmployee")
    public String newEmployee() {
        return "3_newEmployee";
    }


    @RequestMapping("/submitNewClient")
    public String submitNewClient(HttpServletRequest request, Model model) {
        // Parameters uit het request halen.

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String startDateStr = request.getParameter("startDate");
        LocalDate startDate = null;
        if (startDateStr != null) {
            startDate = LocalDate.parse(startDateStr);
        }

        boolean student = Boolean.parseBoolean(request.getParameter("student"));

        // Maak een nieuwe client instantie met de opgehaalde waarden.
        Client client = new Client(name,surname.toUpperCase());
        client.getYearOfBirth();



        // Voeg het Client object toe aan het model om te gebruiken in de view.
        model.addAttribute("showNewClient", client);

        // Stuur de gebruiker naar de "showNewClientg" view.
        return "showNewClient";
    }

    @RequestMapping("/submitEmployee")
    public String submitEmployee(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String startDateStr = request.getParameter("startDate");
        boolean isJobStudent = Boolean.parseBoolean(request.getParameter("student"));

        LocalDate startDate = null;
        if (startDateStr != null) {
            startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        Employee employee = new Employee(name, surname);
        employee.setStartDate(startDate);
        employee.setJobStudent(isJobStudent);

        model.addAttribute("employee", employee);
        return "4_showEmployee";
    }



/* You will need these methods in part 3 of the project assignment.
   private ArrayList<Employee> fillEmployees() {
        ArrayList<Employee> employeeArrayList = new ArrayList<>();

        Employee employee1 = new Employee("Johan", "Bertels");
        employee1.setStartDate(LocalDate.of(2002, 5, 1));
        Employee employee2 = new Employee("An", "Van Herck");
        employee2.setStartDate(LocalDate.of(2019, 3, 15));
        employee2.setJobStudent(true);
        Employee employee3 = new Employee("Bruno", "Coenen");
        employee3.setStartDate(LocalDate.of(1995,1,1));
        Employee employee4 = new Employee("Wout", "Dayaert");
        employee4.setStartDate(LocalDate.of(2002, 12, 15));
        Employee employee5 = new Employee("Louis", "Petit");
        employee5.setStartDate(LocalDate.of(2020, 8, 1));
        employee5.setJobStudent(true);
        Employee employee6 = new Employee("Jean", "Pinot");
        employee6.setStartDate(LocalDate.of(1999,4,1));
        Employee employee7 = new Employee("Ahmad", "Bezeri");
        employee7.setStartDate(LocalDate.of(2009, 5, 1));
        Employee employee8 = new Employee("Hans", "Volzky");
        employee8.setStartDate(LocalDate.of(2015, 6, 10));
        employee8.setJobStudent(true);
        Employee employee9 = new Employee("Joachim", "Henau");
        employee9.setStartDate(LocalDate.of(2007,9,18));
        employeeArrayList.add(employee1);
        employeeArrayList.add(employee2);
        employeeArrayList.add(employee3);
        employeeArrayList.add(employee4);
        employeeArrayList.add(employee5);
        employeeArrayList.add(employee6);
        employeeArrayList.add(employee7);
        employeeArrayList.add(employee8);
        employeeArrayList.add(employee9);
        return employeeArrayList;
    }

    private ArrayList<Client> fillClients() {
        ArrayList<Client> clientArrayList = new ArrayList<>();
        Client client1 = new Client("Dominik", "Mioens");
        client1.setYearOfBirth(2001);
        Client client2 = new Client("Zion", "Noops");
        client2.setYearOfBirth(1996);
        Client client3 = new Client("Maria", "Bonetta");
        client3.setYearOfBirth(1998);
        clientArrayList.add(client1);
        clientArrayList.add(client2);
        clientArrayList.add(client3);
        clientArrayList.get(0).addToShoppingList("Butter");
        clientArrayList.get(0).addToShoppingList("Bread");
        clientArrayList.get(1).addToShoppingList("Apple");
        clientArrayList.get(1).addToShoppingList("Banana");
        clientArrayList.get(1).addToShoppingList("Grapes");
        clientArrayList.get(1).addToShoppingList("Oranges");
        clientArrayList.get(2).addToShoppingList("Fish");
        return clientArrayList;
    }

    private ArrayList<Store> fillStores() {
        ArrayList<Store> storeArrayList = new ArrayList<>();
        Store store1 = new Store("Delhaize");
        Store store2 = new Store("Colruyt");
        Store store3 = new Store("Albert Heyn");
        Section section1 = new Section("Fruit");
        Section section2 = new Section("Bread");
        Section section3 = new Section("Vegetables");
        section1.setPicture("/img/fruit.jpg");
        section2.setPicture("/img/bread.jpg");
        section3.setPicture("/img/vegetables.jpg");
        section1.setResponsible(employeeArrayList.get(0));
        section2.setResponsible(employeeArrayList.get(1));
        section3.setResponsible(employeeArrayList.get(2));
        section1.setCooled(true);
        section2.setCooled(true);
        store1.addSection(section1);
        store1.addSection(section2);
        store1.addSection(section3);
        store2.addSection(section1);
        store2.addSection(section2);
        store3.addSection(section1);
        store3.addSection(section3);
        storeArrayList.add(store1);
        storeArrayList.add(store2);
        storeArrayList.add(store3);
        return storeArrayList;
    }
    */


}
