package tm.itbachelors.projectstore.controller;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tm.itbachelors.projectstore.model.Client;
import tm.itbachelors.projectstore.model.Employee;
import tm.itbachelors.projectstore.model.Store;
import tm.itbachelors.projectstore.model.Section;
import org.springframework.web.bind.annotation.PathVariable;


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

    private ArrayList<Employee> employeeArrayList;
    private ArrayList<Client> clientArrayList;
    private ArrayList<Store> storeArrayList;

    //Write your code here after this line
    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @GetMapping("/1_newClient")
    public String newClient(Model model) { // Voeg Model toe als parameter
        model.addAttribute("storeArrayList", storeArrayList);
        return "1_newClient";
    }

    @GetMapping("/showNewClient")
    public String showNewClient() {
        return "2_showNewClient";
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
        Integer storeIndex = Integer.parseInt(request.getParameter("storeIndex"));

        // Maak een nieuwe client instantie met de opgehaalde waarden.
        Client client = new Client(name,surname.toUpperCase());
        client.getYearOfBirth();

        Store supermarket = storeArrayList.get(storeIndex);
        supermarket.registerCustomer(client);

        clientArrayList.add(client);

        // Voeg het Client object toe aan het model om te gebruiken in de view.
        model.addAttribute("showNewClient", client);

        // Stuur de gebruiker naar de "showNewClientg" view.
        return "2_showNewClient";
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

    @GetMapping("/5_listEmployee")
    public String staffList(Model model) {
        model.addAttribute("listEmployee", employeeArrayList);
        return "5_listEmployee";
    }

    @GetMapping("/6_listClients")
    public String clientsList(Model model) {
        model.addAttribute("listClients", clientArrayList);
        return "6_listClients";
    }

    @GetMapping("/7_newStore")
    public String newStore() {
        return "7_newStore";
    }

    @RequestMapping("/submitNewStore")
    public String submitStore(HttpServletRequest request, Model model){
        String storeName = request.getParameter("nameStore");

        Store store = new Store(storeName);

        storeArrayList.add(store);  // Voeg de nieuwe winkel toe aan de lijst

        model.addAttribute("store",storeArrayList);
        return "8_showStores";
    }

    @GetMapping("/8_showStores")
    public String showStores(Model model) {
        model.addAttribute("store", storeArrayList);
        return "8_showStores";
    }

    @GetMapping("/9_newSection")
    public String newSection( Model model) {
        model.addAttribute("stores", storeArrayList);
        model.addAttribute("employees", employeeArrayList);
        return "9_newSection";
    }

    @RequestMapping("/submitSection")
    public String submitSection(HttpServletRequest request, Model model) {
        String sectionName = request.getParameter("sectionName");
        String pic = request.getParameter("pic");
        String coolStr = request.getParameter("cool");
        Integer storeIndex = Integer.parseInt(request.getParameter("storeIndex"));
        Integer employeeIndex = Integer.parseInt(request.getParameter("employeeIndex"));


// Check if indices are valid
        if (storeIndex < 0 && employeeIndex < 0) {
            model.addAttribute("errorMessage", "You didn't choose a valid store or employee!");
            return "error";
        } else if (storeIndex < 0) {
            model.addAttribute("errorMessage", "You didn't choose a valid store!");
            return "error";
        } else if (employeeIndex < 0) {
            model.addAttribute("errorMessage", "You didn't choose a valid employee!");
            return "error";
        }

        Store store = storeArrayList.get(storeIndex);
        Employee responsible = employeeArrayList.get(employeeIndex);

        Section section = new Section(sectionName);
        section.setPicture(pic);
        section.setCooled(Boolean.parseBoolean(coolStr));
        section.setResponsible(responsible);
        store.addSection(section);

        model.addAttribute("store", store);
        return "10_showSections";
    }

    @GetMapping("/10_showSections/{storeName}")
    public String showSections(@PathVariable String storeName, Model model) {
        for (Store store : storeArrayList) {
            if (store.getName().equals(storeName)) {
                model.addAttribute("store", store);
                return "10_showSections"; // Make sure the name matches your Thymeleaf template
            }
        }
        return "error"; // Consider creating an error view if no store matches
    }

    @GetMapping("/sectionSearch")
    public String sectionSearch(@RequestParam("sectionName") String sectionName, Model model) {
        for (Store store : storeArrayList) {
            Section section = store.searchSectionByName(sectionName);
            if (section != null) {
                model.addAttribute("section", section);
                return "11_searchSection"; // Make sure this matches your Thymeleaf template name
            }
        }
        model.addAttribute("errorMessage", "There is no department with the name of " + sectionName);
        return "error"; // Redirect to a generic error page
    }

    // You will need these methods in part 3 of the project assignment.
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
        Client client4 = new Client("Tom", "Gijsbers");
        client3.setYearOfBirth(1997);
        clientArrayList.add(client1);
        clientArrayList.add(client2);
        clientArrayList.add(client3);
        clientArrayList.add(client4);
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

    @PostConstruct
    private void initializeArrays() {
        employeeArrayList = fillEmployees();
        clientArrayList = fillClients();
        storeArrayList = fillStores();
    }




}
