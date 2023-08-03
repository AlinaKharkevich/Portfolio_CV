package com.alinaharkevich;

import java.util.EnumSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try{
            //Abstract inheritance: Medicine, Antibiotic, PainKiller
            System.out.println("Abstract inheritance: Medicine, Antibiotic, PainKiller"+ "\n");
            Medicine a1 = new Antibiotic("Teraflu", 5.20,3.10);
            Medicine a2 = new Antibiotic("Antik", 6.20,13.10);
            Medicine p1 = new Painkiller("Killer", 7.30, true);
            Medicine p2 = new Painkiller("PainK", 6.30, false);

            System.out.println(a1.isPrescriptionRequired());
            System.out.println(a2.isPrescriptionRequired());
            System.out.println(p1.isPrescriptionRequired());
            System.out.println(p2.isPrescriptionRequired());

            System.out.println(a1.finalPrice(10));
            System.out.println(a2.finalPrice(10));
            System.out.println(p1.finalPrice(10));
            System.out.println(p2.finalPrice(10));

            //Overlapping: EmployeeType, Employee (ASSISTANT, NURSE, SUPERVISOR, DOCTOR)
            System.out.println("\n" + "Abstract inheritance: Medicine, Antibiotic, PainKiller"+ "\n");
            EnumSet<EmployeeType> type1 = EnumSet.of(EmployeeType.ASSISTANT, EmployeeType.NURSE);
            EnumSet<EmployeeType> type2 = EnumSet.of(EmployeeType.ASSISTANT, EmployeeType.DOCTOR);
            Employee e1 = new Employee(type1,"Alina", "HeartOP", 2, null,null);

            System.out.println(e1);
            System.out.println( "Get operation: " + e1.getOperation());
            //System.out.println( "Get field: " + e1.getField()); //wrong type

            e1.setPatient(3);
            System.out.println(e1);
            //e1.getOfficeNum(); //wrong type
            //e1.setOfficeNum(5); //wrong type

            //Multi-inheritance: Building, Hospital, Laboratory, MainHealthCenter
            System.out.println("\n" + "Multi-inheritance: Building, Hospital, Laboratory, MainHealthCenter"+ "\n");
            Building b1 = new MainHealthCenter(123, 4,5,50);
            System.out.println(b1);

            Building b3 = new Building(1);
            Laboratory l1 = new Laboratory(2, 5);
            Hospital h1 = new Hospital(3, 10);
            MainHealthCenter mhc1 = new MainHealthCenter(4, 10, 15, 100);
            Building b2 = new MainHealthCenter(1,5,10,100);
            System.out.println(b1);
            System.out.println(b2);
            System.out.println(b3);
            System.out.println(l1);
            System.out.println(h1);
            System.out.println(mhc1);

            //Dynemic inheritance: Person, Intern, Supervisor
            System.out.println("\n" + "Dynemic inheritance: Person, Intern, Supervisor"+ "\n");
            Person i1 = new Intern("Alina", "123");
            Person i2 = new Intern("Andrey", "12");
            Person s1 = new Supervisor("Olga", "Main");

            //Add
            i1.addFromExtent();
            i2.addFromExtent();
            s1.addFromExtent();
            List<Intern> interns = ObjectPlus.getExtent(Intern.class);
            for (Intern intern : interns) {
                System.out.println("Extent " + intern.toString());
            }
            List<Supervisor> supervisors = ObjectPlus.getExtent(Supervisor.class);
            for (Supervisor supervisor : supervisors) {
                System.out.println("Extent " + supervisor.toString());
            }

            //Remove
            i1.removeFromExtent();
            s1.removeFromExtent();
            for (Intern intern : interns) {
                System.out.println("Extent Remove " + intern.toString());
            }
            for (Supervisor supervisor : supervisors) {
                System.out.println("Extent Remove" + supervisor.toString());
            }

            //Multi-Aspect inheritance: MenuItem, FoodItem, DrinkItem, Waiter, Order
            System.out.println("\n" + "Multi-Aspect inheritance: MenuItem, FoodItem, DrinkItem, Waiter, Order"+ "\n");
            FoodItem burger = new FoodItem("Burger", 10, "American");
            DrinkItem beer = new DrinkItem("Beer", 5, 4.5);
            Waiter john = new Waiter("John");
            Waiter katia = new Waiter("Katia");
            Order order1 = new Order(burger, beer, john);

            System.out.println(burger);
            System.out.println(beer);
            System.out.println(john);
            System.out.println(order1);

            order1.setWaiter(katia);
            System.out.println(order1);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
