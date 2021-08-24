package uni.sim.main;

import uni.sim.model.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Scenario {

    public static String line = "";

    public static void main (String [] args) throws FileNotFoundException {

        String fileName1 = "src/main/resources/Person.csv";
        String fileName2 = "src/main/resources/Department.csv";
        String fileName3 = "src/main/resources/University.csv";

        ArrayList<Person> persons = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Teacher> teachers = new ArrayList<>();
        ArrayList<Department> departments = new ArrayList<>();
        ArrayList<University> universities = new ArrayList<>();
        ArrayList<String[]> prs = new ArrayList<>();

        try {
            BufferedReader br1 = new BufferedReader(new FileReader(fileName1));
            BufferedReader br2 = new BufferedReader(new FileReader(fileName2));
            BufferedReader br3 = new BufferedReader(new FileReader(fileName3));

            System.out.printf("\n\t%-11s %-15s %s\n", "PersonName", "PersonSurname", "Work");
            int count = 0;
            br1.readLine();
            while ((line = br1.readLine()) != null) {
                String[] attributes = line.split(", ");    // use comma as separator
                Person person = new Person(attributes[0], attributes[1], attributes[2], attributes[3]);
                persons.add(person);
                prs.add(attributes);

                System.out.print(++count + "\t");
                person.printFullName(person.getName(), person.getSurname());

                if (attributes[2].equals("Student")) {
                    Student student = new Student(attributes[0], attributes[1], attributes[3],
                            Integer.parseInt(attributes[5]), Integer.parseInt(attributes[6]));
                    students.add(student);
                    student.work();
                    System.out.println();
                } else {
                    Teacher teacher = new Teacher(attributes[0], attributes[1], attributes[3], attributes[7]);
                    teachers.add(teacher);
                    teacher.work();
                    System.out.println();
                }
            }

            System.out.printf("\n\t%-40s %s", "University Name", "Department Name\n");
            count = 0;
            br2.readLine();
            while ((line = br2.readLine()) != null) {
                String[] attributes = line.split(", ");
                ArrayList<Student> std = new ArrayList<>();
                ArrayList<Teacher> tch = new ArrayList<>();

                for (String[] p : prs) {
                    if (p[2].equals("Student") && p[3].equals(attributes[0]) &&
                            p[4].equals(attributes[4])){
                        Student student = new Student(p[0], p[1], p[3],
                                Integer.parseInt(p[5]), Integer.parseInt(p[6]));
                        std.add(student);
                    }
                    else if (p[2].equals("Teacher") && p[3].equals(attributes[0]) &&
                            p[4].equals(attributes[4])) {
                        Teacher teacher = new Teacher(p[0], p[1], p[3], p[7]);
                        tch.add(teacher);
                    }
                }
                Department department = new Department(attributes[0], Integer.parseInt(attributes[1]),
                        Integer.parseInt(attributes[2]), Integer.parseInt(attributes[3]), attributes[4], std, tch);
                departments.add(department);

                System.out.print(++count + "\t");
                department.fullName();
                System.out.println();
            }

            br3.readLine();
            while ((line = br3.readLine()) != null) {
                String[] attributes = line.split(", ");
                ArrayList<Department> dep = new ArrayList<>();

                for (Department d : departments) {
                    if (d.getUniversity().equals(attributes[0])){
                        Department department = new Department(d.getName(), d.getMidtermScoreRatio(),
                                d.getFinalScoreRatio(), d.getMinimumPassingScore(), d.getUniversity(),
                                d.getStudents(), d.getTeachers());
                        dep.add(department);
                    }
                }
                University university = new University(attributes[0], attributes[1], dep);
                universities.add(university);
            }


            for (University u : universities){
                System.out.println("\nthe students that are belongs to " + u.getName());
                count = 0;
                for (String [] p : prs){
                    if ((p[2].equals("Student") && u.getName().equals(p[4]))){
                        System.out.print(++count + "\t");
                        u.students(p[0] + " " + p[1], u.getName());
                    }
                }

                count = 0;
                System.out.println("\nthe teachers that are belongs to " + u.getName());
                for (String [] p : prs){
                    if (p[2].equals("Teacher") && u.getName().equals(p[4])){
                        System.out.print(++count + "\t");
                        u.teachers(p[0] + " " + p[1], u.getName());
                    }
                }
            }

            for (Department d : departments){
                System.out.println("\n\n\n----------------------------------------------------------");
                d.fullName();
                System.out.println("\n----------------------------------------------------------");

                System.out.printf("\n%-10s %-12s %-14s %s", "Name", "Surname", "Role", "Field of Study");
                System.out.printf("\n%-10s %-12s %-14s %s", "----", "-------", "----", "--------------");
                for (Teacher t : d.getTeachers()){
                    System.out.printf("\n%-10s %-12s %-14s %s\n", t.getName(), t.getSurname(),
                            t.getRole(), t.getFieldOfStudy());
                }

                System.out.printf("\n%-10s %-12s %-12s %-16s %s", "Name", "Surname", "Role", "Average Score", "Is passed");
                System.out.printf("\n%-10s %-12s %-12s %-16s %s", "----", "-------", "----", "-------------", "---------");
                count = 0;
                String isPass = "No";
                for (Student s : d.getStudents()){
                    Integer avgScore = s.averageScore(s.getMidtermScore(), d.getMidtermScoreRatio(),
                            s.getFinalScore(), d.getFinalScoreRatio());

                    if (s.isPassed(d.getMinimumPassingScore(), avgScore)){
                        count++;
                        isPass = "Yes";
                    }

                    System.out.printf("\n%-10s %-12s %-16s %-14s %s", s.getName(), s.getSurname(), s.getRole(), avgScore, isPass);
                }
                System.out.println("\n\n" + count + " students are passed!");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
