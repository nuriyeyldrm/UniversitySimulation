package uni.sim.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Department {

    private String name;
    private Double midtermScoreRatio;
    private Double finalScoreRatio;
    private Double minimumPassingScore;
    private String university;
    private String students;
    private String teachers;

    public Department(){

    }

    public Department(String name, Double midtermScoreRatio, Double finalScoreRatio,
                      Double minimumPassingScore, String university, String students, String teachers) {
        this.name = name;
        this.midtermScoreRatio = midtermScoreRatio;
        this.finalScoreRatio = finalScoreRatio;
        this.minimumPassingScore = minimumPassingScore;
        this.university = university;
        this.students = students;
        this.teachers = teachers;
    }

    public void fullName(String university, String name){
        System.out.printf("%-40s %s Department\n", university, name);
    }
}
