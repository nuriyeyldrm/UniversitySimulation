package uni.sim.main;

import uni.sim.model.*;

import java.io.*;
import java.util.ArrayList;


public class Scenario {

    public static void main (String [] args) throws IOException {

        String fileName1 = "src/main/resources/Person.csv";
        ArrayList<ArrayList<String>> persons = readFromFileAddToArray(fileName1);

        String fileName2 = "src/main/resources/Department.csv";
        ArrayList<ArrayList<String>> departments = readFromFileAddToArray(fileName2);

        String fileName3 = "src/main/resources/University.csv";
        ArrayList<ArrayList<String>> universities = readFromFileAddToArray(fileName3);

        Person person = new Person();
        Student student = new Student();
        Teacher teacher = new Teacher();
        University university = new University();
        Department department = new Department();


        for (ArrayList<String> p : persons)
            person.printFullName(p.get(0), p.get(1));

        System.out.println();
        teacher.work();

        System.out.println();
        for (ArrayList<String> d : departments)
            department.fullName(d.get(4), d.get(0));

        System.out.println();
        for (ArrayList<String> u : persons) {
            if (u.get(2).equals("Student"))
                university.students((u.get(0) + " " + u.get(1)), u.get(4));
        }

        System.out.println();
        for (ArrayList<String> s : persons) {
            if (s.get(2).equals("Teacher"))
                university.teachers((s.get(0) + " " + s.get(1)), s.get(4));
        }

        System.out.println();
        ArrayList<Integer> averageScore = new ArrayList<>();
        Integer avgScore;
        for (ArrayList<String> s : persons) {
            for (ArrayList<String> d : departments) {
                if (s.get(2).equals("Student") && s.get(3).equals(d.get(0)) && s.get(4).equals(d.get(4))) {
                    avgScore = student.averageScore(Integer.parseInt(s.get(5)), Integer.parseInt(d.get(1)),
                            Integer.parseInt(s.get(6)), Integer.parseInt((d.get(2))));
                    averageScore.add(avgScore);

                    if (student.isPassed(Integer.parseInt(d.get(3)), avgScore))
                        System.out.printf("Average score is: %d, Is passed: Yes\n", avgScore);

                    else
                        System.out.printf("Average score is: %d, Is passed: No\n", avgScore);
                }
            }
        }



    }

    private static ArrayList<ArrayList<String>> readFromFileAddToArray(String fileName)
            throws IOException {

        BufferedReader br1 = new BufferedReader(new FileReader(fileName));
        int vertexCount = 0;
        while (br1.readLine() != null)
            vertexCount++;
        br1.close();

        String line = "";

        ArrayList<ArrayList<String>> lists = new ArrayList<>();

        for(int i=0; i < vertexCount - 1; i++) {
            lists.add(new ArrayList());
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            int j = 0;
            br.readLine();
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] person = line.split(", ");    // use comma as separator
                for (String s : person) {
                    lists.get(j).add(s);
                }
                j++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return lists;
    }

}
