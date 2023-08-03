package com.alinaharkevich;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        try {
            Laboratory lab1 = new Laboratory("Star");
            Laboratory lab2 = new Laboratory("Moon");
            Patient pat1 = new Patient("Kharkevich");
            Patient pat2 = new Patient("Smirnova");
            Patient pat3 = new Patient("Andreev");
            Doctor doc1 = new Doctor("Pamorev", pat1);
            Doctor doc2 = new Doctor("Kulikov", pat2);
            Visit v1 = new Visit(lab1,pat1, LocalDate.parse("2023-04-21"),1);
            Visit v2 = new Visit(lab2,pat1, LocalDate.parse("2023-04-21"),2);
            Visit v3 = new Visit(lab2,pat2, LocalDate.parse("2023-04-21"),3);
            //Visit v4 = new Visit(lab2,pat2, LocalDate.parse("2023-04-21"),03); //duplicate Error
            Storage s1 = new Storage("Main");
            Storage s2 = new Storage("Additional");
            MedicalCard c1 = new MedicalCard(1);
            MedicalCard c2 = new MedicalCard(2);
            Prescription prescription = new Prescription(1, LocalDate.parse("2023-04-21"));
            Prescription prescription2 = new Prescription(2, LocalDate.parse("2023-04-21"));
            prescription.addMedicine("Aspirin", 2);
            prescription.addMedicine("Ibuprofen", 1);
            prescription.addMedicine("Paracetamol", 3);
            //prescription.setPrescriptionId(2);//unique id error

            //Composition
            System.out.println("COMPOSITION________________________________");
            System.out.println("Prescription medicines: " + prescription.getMedicines());
            prescription.removeMedicine("Aspirin");
            System.out.println("Prescription medicines after removing Aspirin: " + prescription.getMedicines());
            Prescription.showExtent();

            prescription.removePrescription();
            System.out.println("Prescription extent after removing prescription: ");
            Prescription.showExtent();
            System.out.println("Medicine extent after removing prescription: ");
            Medicine.showExtent();

            //Qualified
            System.out.println("QUALIFIED________________________________");
            s1.addMedicalCard(c1);
            s1.addMedicalCard(c2); //add
            System.out.println(s1);
            System.out.println(s2);

            s2.addMedicalCard(c2); //change from main storage to additional
            System.out.println("Change from main storage to additional");
            System.out.println(s1);
            System.out.println(s2);
            s2.removeMedicalCard(c2); //remove
            System.out.println("Storage Main after removing medical card: " + s2);
            System.out.println("Get by qualifier: " + s1.getCardById(1)); //qualifier method

            //Attribute
            System.out.println("Attribute________________________________");
            System.out.println(pat1);
            System.out.println(v1);
            System.out.println(v2);
            System.out.println(lab1);
            System.out.println(lab2);

            v1.remove();
            System.out.println("Patient Kharkevich after removing visit: " + pat1);
            System.out.println("Laboratory after removing visit: " + lab1);

            //Basic
            System.out.println("Basic________________________________");
            System.out.println(pat1);
            System.out.println(pat2);
            System.out.println(doc1);
            System.out.println(doc2);
            doc2.addPatient(pat3);
            System.out.println("Doctor after adding patient: " + doc2);
            doc2.removePatient(pat3);
            System.out.println("Doctor after removing patient: " + doc2);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
