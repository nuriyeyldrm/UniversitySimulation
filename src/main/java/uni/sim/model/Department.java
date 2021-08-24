package uni.sim.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Setter
@Getter
@ToString
public class Department {

    private String name;
    private Integer midtermScoreRatio;
    private Integer finalScoreRatio;
    private Integer minimumPassingScore;
    private String university;
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;

    public Department(){

    }

    public Department(String name, Integer midtermScoreRatio, Integer finalScoreRatio, Integer minimumPassingScore,
                      String university, ArrayList<Student> students, ArrayList<Teacher> teachers) {
        this.name = name;
        this.midtermScoreRatio = midtermScoreRatio;
        this.finalScoreRatio = finalScoreRatio;
        this.minimumPassingScore = minimumPassingScore;
        this.university = university;
        this.students = students;
        this.teachers = teachers;
    }

    public void fullName(){
        System.out.printf("%-35s| %s Department", university, name);
    }
}
