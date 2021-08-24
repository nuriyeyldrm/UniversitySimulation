package uni.sim.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Person {

    private String name;
    private String surname;
    private String role;
    private String department;

    public Person(){

    }

    public Person(String name, String surname, String role, String department) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.department = department;
    }

    public void printFullName(String name, String surname){
        System.out.printf("%-11s %-15s", name, surname);
    }

}
