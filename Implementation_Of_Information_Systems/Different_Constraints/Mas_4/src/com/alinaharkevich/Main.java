package com.alinaharkevich;

import com.alinaharkevich.attributeConstarint.Doctor;
import com.alinaharkevich.bag.Enrollment;
import com.alinaharkevich.bag.Intern;
import com.alinaharkevich.bag.Internship;
import com.alinaharkevich.subset.HeadTeam;
import com.alinaharkevich.subset.Supervisor;
import com.alinaharkevich.unique.Patient;
import com.alinaharkevich.xor.Hospital;
import com.alinaharkevich.xor.InsuranceOffice;
import com.alinaharkevich.xor.Person;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Attribute constraint
        System.out.println("Attribute constraint" + "\n");
        Doctor d1 = new Doctor("Alina", 37, 10);
        System.out.println(d1);
        //d1.setQualificationYears(5); // Dynamic constraint error
        //d1.setName("Alina K"); //Static constraint error
        //d1.setAge(2); //Static constraint error
        System.out.println(d1);

        //Unique constraint
        System.out.println("\n" + "Unique constraint" + "\n");
        Patient p1 = new Patient("John", "1234567890");
        //Patient p2 = new Patient("Jane Smith", "0987654321");//invalid format, 1st digit
        //Patient p3 = new Patient("Mike Johnson", "1234567890"); // This will be an exception
        System.out.println(p1);

        // Subset constraint /Ordered constraint
        System.out.println("\n" + "Subset constraint" + "\n");
        Supervisor supervisor1 = new Supervisor("John");
        Supervisor supervisor2 = new Supervisor("Mary");
        HeadTeam team1 = new HeadTeam(1);
        HeadTeam team2 = new HeadTeam(2);

        team1.addSupervisor(supervisor1);
        team1.addSupervisor(supervisor2);

        supervisor1.addBelongsTo(team1);
        supervisor2.addBelongsTo(team1);
        supervisor1.addManages(team1);

        supervisor1.addBelongsTo(team2);
        supervisor1.addManages(team2);

        System.out.println(supervisor1);
        System.out.println(supervisor2);
        System.out.println(team1);
        System.out.println(team2);

        System.out.println(supervisor1.getBelongsTo());
        System.out.println(supervisor1.getManages());

        // Bag constraint
        System.out.println("\n" + "Bag constraint" + "\n");
        Intern i1 = new Intern("Alina");
        Intern i2 = new Intern("Olga");
        Intern i3 = new Intern("Alina");
        Internship is1 = new Internship("Main");
        Internship is2 = new Internship("Additional");
        Internship is3 = new Internship("Main");
        Enrollment e1 = new Enrollment(i1, is1);
        Enrollment e2 = new Enrollment(i2, is1);
        Enrollment e3 = new Enrollment(i1, is1);

        List<Enrollment> enrollments = Enrollment.getEnrollments();
        System.out.println("Existing Enrollments:");
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }

        // Xor constraint/ Special
        System.out.println("\n" + "Xor and Special constraint" + "\n");
        Hospital h1 = new Hospital("Main");
        InsuranceOffice ins1 = new InsuranceOffice("Special" );
        Person per1 = new Person("Alina");
        Person per2 = new Person("Olga");
        per1.addContract(ins1);
        //per2.addContract(ins1);
        per2.setWorksFor(h1);
        //per2.addContract(ins1);
        System.out.println(per1);
        System.out.println(per2);
    }
}
