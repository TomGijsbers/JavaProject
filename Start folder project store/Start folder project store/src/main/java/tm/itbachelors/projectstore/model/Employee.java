package tm.itbachelors.projectstore.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Employee extends Person{
    private LocalDate startDate;
    private boolean jobStudent;

    public Employee(String firstName, String surName) {
        super(firstName, surName);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isJobStudent() {
        return jobStudent;
    }

    public void setJobStudent(boolean jobStudent) {
        this.jobStudent = jobStudent;
    }

    public String toString() {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String text = getStartDate().format(formatters);


        if (isJobStudent()){
            return "Employee"+ getSurName().toUpperCase()+getFirstName() + "(job student) is employed since "+text;
        }
        else return "Employee "+ getSurName().toUpperCase()+" "+getFirstName() + " is employed since "+text;

    }
}
