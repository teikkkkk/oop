package hus.oop.students;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StudentManager {
    private static StudentManager instance;
    private final List<Student> studentList;

    private StudentManager() {
        studentList = new ArrayList<>();
    }

    // Thread-safe Singleton using initialization-on-demand holder idiom
    private static class SingletonHolder {
        private static final StudentManager INSTANCE = new StudentManager();
    }

    public static StudentManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void append(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        studentList.add(student);
    }

    public void add(Student student, int index) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (index < 0 || index > studentList.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + studentList.size());
        }
        studentList.add(index, student);
    }

    public void remove(int index) {
        if (index < 0 || index >= studentList.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + studentList.size());
        }
        studentList.remove(index);
    }

    public Student studentAt(int index) {
        if (index >= 0 && index < studentList.size()) {
            return studentList.get(index);
        }
        return null;
    }

    public int numberOfStudents() {
        return studentList.size();
    }

    public List<Student> sortStudentsByName() {
        List<Student> sortedList = new ArrayList<>(studentList);
        Collections.sort(sortedList); // Uses compareTo from Student class
        return Collections.unmodifiableList(sortedList);
    }

    public List<Student> sortAverageGradeIncreasing() {
        List<Student> sortedList = new ArrayList<>(studentList);
        Collections.sort(sortedList, (s1, s2) -> Double.compare(s1.getAverageGrade(), s2.getAverageGrade()));
        return Collections.unmodifiableList(sortedList);
    }

    public List<Student> sortAverageGradeDecreasing() {
        List<Student> sortedList = new ArrayList<>(studentList);
        Collections.sort(sortedList, (s1, s2) -> Double.compare(s2.getAverageGrade(), s1.getAverageGrade()));
        return Collections.unmodifiableList(sortedList);
    }

    public List<Student> filterPassStudents() {
        if (studentList == null) {
            return Collections.emptyList();
        }
        return studentList.stream()
                .filter(student -> student != null &&
                        student.getMathsGrade() >= 4.0 &&
                        student.getPhysicsGrade() >= 4.0 &&
                        student.getChemistryGrade() >= 4.0 &&
                        student.getAverageGrade() >= 5.0)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Student> filterFailureStudents(int howMany) {
        if (howMany < 0) {
            throw new IllegalArgumentException("howMany cannot be negative");
        }
        if (studentList == null) {
            return Collections.emptyList();
        }
        return studentList.stream()
                .filter(student -> student != null &&
                        (student.getMathsGrade() < 4.0 ||
                                student.getPhysicsGrade() < 4.0 ||
                                student.getChemistryGrade() < 4.0 ||
                                student.getAverageGrade() < 5.0))
                .limit(howMany)
                .collect(Collectors.toUnmodifiableList());
    }

    public static String idOfStudentsToString(List<Student> studentList) {
        if (studentList == null || studentList.isEmpty()) {
            return "[]";
        }
        StringBuilder idOfStudents = new StringBuilder();
        idOfStudents.append("[");
        for (Student student : studentList) {
            if (student != null) { // Defensive check
                idOfStudents.append(student.getId()).append(" ");
            }
        }
        return idOfStudents.toString().trim() + "]";
    }

    public static void print(List<Student> studentList) {
        if (studentList == null || studentList.isEmpty()) {
            System.out.println("[]");
            return;
        }
        StringBuilder studentsString = new StringBuilder();
        studentsString.append("[\n");
        for (Student student : studentList) {
            if (student != null) { // Defensive check
                studentsString.append(student.toString()).append("\n");
            }
        }
        System.out.print(studentsString.toString().trim() + "\n]");
    }
}