package com.alinaharkevich.mas_mp5.validation;

import com.alinaharkevich.mas_mp5.model.Adult;
import com.alinaharkevich.mas_mp5.model.Child;
import com.alinaharkevich.mas_mp5.model.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class AtLeastOneSubclassAndAgeValidator implements ConstraintValidator<AtLeastOneSubclassAndAge, Patient> {
    @Override
    public boolean isValid(Patient patient, ConstraintValidatorContext context) {
        if (patient == null) {
            return false;
        }

        boolean hasChild = patient instanceof Child;
        boolean hasAdult = patient instanceof Adult;
        Date dateOfBirth = patient.getDateOfBirth();
        int age = calculateAge(dateOfBirth);

        // Check if at least one subclass exists and handle child turning 18
        if ((hasChild || hasAdult) && (hasChild && age <= 17) || (hasAdult && age >= 18)) {
            if (age >= 18 && hasChild) {
                // Automatically transition from Child to Adult
                Child child = (Child) patient;

                // Create a new Adult object
                Adult adult = new Adult();
                adult.setNip(child.getNip());
                adult.setDiscount(0);
                adult.setMedicalCard(child.getMedicalCard());
                //adult.setParentPhone(null);

                // Perform the deletion and insertion using your data persistence mechanism
                // Obtain the EntityManager instance
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("your-persistence-unit-name");
                EntityManager entityManager = ((EntityManagerFactory) entityManagerFactory).createEntityManager();

                        // Begin a transaction
                EntityTransaction transaction = entityManager.getTransaction();
                transaction.begin();

                try {
                    // Remove the Child object from the database
                    entityManager.remove(child);

                    // Save the Adult object to the database
                    entityManager.persist(adult);

                    // Commit the transaction
                    transaction.commit();
                } catch (Exception e) {
                    // Handle any exceptions that occur during the transaction
                    if (transaction != null && transaction.isActive()) {
                        transaction.rollback();
                    }
                    throw e;
                } finally {
                    // Close the EntityManager
                    entityManager.close();
                }

                // Return false to prevent validation as the object has been modified
                return false;
            }
            return true;
        }
        return false;
    }

        // Implement the logic to calculate the age based on the date of birth
    private int calculateAge(Date dateOfBirth) {
        LocalDate birthDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}

