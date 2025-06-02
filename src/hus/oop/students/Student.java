package hus.oop.students;

public class Student implements Comparable<Student> {
    private final String id;
    private final String lastname;
    private final String firstname;
    private final int yearOfBirth;
    private final double mathsGrade;
    private final double physicsGrade;
    private final double chemistryGrade;

    private Student(String id, String lastname, String firstname, int yearOfBirth,
                    double mathsGrade, double physicsGrade, double chemistryGrade) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.yearOfBirth = yearOfBirth;
        this.mathsGrade = mathsGrade;
        this.physicsGrade = physicsGrade;
        this.chemistryGrade = chemistryGrade;
    }

    public static class StudentBuilder {
        private String id;
        private String lastname;
        private String firstname;
        private int yearOfBirth;
        private double mathsGrade;
        private double physicsGrade;
        private double chemistryGrade;

        public StudentBuilder(String id) {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("ID cannot be null or empty");
            }
            this.id = id;
        }

        public StudentBuilder withLastname(String lastname) {
            if (lastname == null || lastname.trim().isEmpty()) {
                throw new IllegalArgumentException("Lastname cannot be null or empty");
            }
            this.lastname = lastname;
            return this;
        }

        public StudentBuilder withFirstname(String firstname) {
            if (firstname == null || firstname.trim().isEmpty()) {
                throw new IllegalArgumentException("Firstname cannot be null or empty");
            }
            this.firstname = firstname;
            return this;
        }

        public StudentBuilder withYearOfBirth(int yearOfBirth) {
            if (yearOfBirth < 1900 || yearOfBirth > 2025) { // Reasonable range check
                throw new IllegalArgumentException("Year of birth must be between 1900 and 2025");
            }
            this.yearOfBirth = yearOfBirth;
            return this;
        }

        public StudentBuilder withMathsGrade(double mathsGrade) {
            if (mathsGrade < 0.0 || mathsGrade > 10.0) { // Assuming a 0-10 scale
                throw new IllegalArgumentException("Maths grade must be between 0.0 and 10.0");
            }
            this.mathsGrade = mathsGrade;
            return this;
        }

        public StudentBuilder withPhysicsGrade(double physicsGrade) {
            if (physicsGrade < 0.0 || physicsGrade > 10.0) {
                throw new IllegalArgumentException("Physics grade must be between 0.0 and 10.0");
            }
            this.physicsGrade = physicsGrade;
            return this;
        }

        public StudentBuilder withChemistryGrade(double chemistryGrade) {
            if (chemistryGrade < 0.0 || chemistryGrade > 10.0) {
                throw new IllegalArgumentException("Chemistry grade must be between 0.0 and 10.0");
            }
            this.chemistryGrade = chemistryGrade;
            return this;
        }

        public Student build() {
            if (lastname == null || firstname == null || yearOfBirth == 0 ||
                    Double.isNaN(mathsGrade) || Double.isNaN(physicsGrade) || Double.isNaN(chemistryGrade)) {
                throw new IllegalStateException("All fields must be set before building a Student");
            }
            return new Student(id, lastname, firstname, yearOfBirth, mathsGrade, physicsGrade, chemistryGrade);
        }

        public Object withGrade1(double grade1) {
                return null;
        }
    }

    public String getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public double getMathsGrade() {
        return mathsGrade;
    }

    public double getPhysicsGrade() {
        return physicsGrade;
    }

    public double getChemistryGrade() {
        return chemistryGrade;
    }

    public double getAverageGrade() {
        return (mathsGrade + physicsGrade + chemistryGrade) / 3.0;
    }

    /*
     * Mô tả đối tượng Student theo định dạng: Lastname, Firstname, yearOfBirth, averageGradePoint
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %d, %.2f", lastname, firstname, yearOfBirth, getAverageGrade());
    }

    /*
     * Đưa ra tiêu chí so sánh 2 đối tượng Student, đầu tiên là so sánh theo Firstname, sau đó là Lastname,
     * theo thứ tự tăng dần.
     */
    @Override
    public int compareTo(Student another) {
        int firstNameCompare = this.firstname.compareTo(another.firstname);
        if (firstNameCompare != 0) {
            return firstNameCompare;
        }
        return this.lastname.compareTo(another.lastname);
    }
}