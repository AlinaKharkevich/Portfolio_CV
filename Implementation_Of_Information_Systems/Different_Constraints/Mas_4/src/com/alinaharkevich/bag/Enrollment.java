package com.alinaharkevich.bag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Enrollment {
    private Intern intern;
    private Internship internship;
    private static List<Enrollment> enrollments = new ArrayList<>(); // bug

    public Enrollment(Intern intern, Internship internship) {
        setStudent(intern);
        setCourse(internship);
        addEnrollment(this);
    }

    public static List<Enrollment> getEnrollments() {
        return Collections.unmodifiableList(enrollments); // safe getter
    }

    public void addEnrollment(Enrollment enrollment) {
        if( enrollment == null){
            throw new IllegalArgumentException("Enrollment is null");
        }
        enrollments.add(enrollment);
    }

    public void removeEnrollment(Enrollment enrollment) {
        if( enrollment == null){
            throw new IllegalArgumentException("Enrollment is null");
        }
        if( !enrollments.contains(enrollment)){
            throw new IllegalArgumentException("Already does not exist");
        }
        enrollments.remove(enrollment);
    }

    public Intern getStudent() {
        return intern;
    }

    public void setStudent(Intern intern) {
        if (intern == null) {
            throw new IllegalArgumentException("Intern cannot be null.");
        }
        this.intern = intern;
    }

    public Internship getCourse() {
        return internship;
    }

    public void setCourse(Internship internship) {
        if (internship == null) {
            throw new IllegalArgumentException("Internship cannot be null.");
        }
        this.internship = internship;
    }

    @Override
    public String toString() {
        return "Enrollment: Intern name - " + intern + ", Internship name - " + internship;
    }
}