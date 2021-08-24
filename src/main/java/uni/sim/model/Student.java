package uni.sim.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Student extends Person{

    private Integer midtermScore;
    private Integer finalScore;

    public Student(){

    }

    public Student(String name, String surname, String department, Integer midtermScore, Integer finalScore) {
        super(name, surname, "Student", department);
        this.midtermScore = midtermScore;
        this.finalScore = finalScore;
    }

    public Integer averageScore(Integer midtermScore, Integer midtermScoreRatio,
                                Integer finalScore, Integer finalScoreRatio){
        return (midtermScore * midtermScoreRatio / 100 + finalScore * finalScoreRatio / 100);
    }

    public Boolean isPassed(Integer minimumPassingScore, Integer averageScore){
        return averageScore >= minimumPassingScore;
    }

    public void work(){
        System.out.print("Studying");
    }



}
