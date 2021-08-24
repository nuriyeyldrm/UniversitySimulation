package uni.sim.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Teacher extends Person{

    private String fieldOfStudy;

    public Teacher(){

    }

    public Teacher(String name, String surname, String department, String fieldOfStudy) {
        super(name, surname, "Teacher", department);
        this.fieldOfStudy = fieldOfStudy;
    }

    public void work(){
        System.out.println("Teaching");
    }
}
