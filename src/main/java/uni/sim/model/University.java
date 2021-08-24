package uni.sim.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class University {

    private String name;
    private String dateOfEstablishment;
    private String departments;

    public University(){

    }

    public University(String name, String dateOfEstablishment, String departments) {
        this.name = name;
        this.dateOfEstablishment = dateOfEstablishment;
        this.departments = departments;
    }

    public void students(String student, String name){
        System.out.printf("%-20s %s\n", student, name);
    }

    public void teachers(String teacher, String name){
        System.out.printf("%-20s %s\n", teacher, name);
    }
}
