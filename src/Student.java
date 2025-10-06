public class Student extends Person implements Evaluable {
    private String program;
    private int year;
    public double gpa;

    public Student(int id, String name, int age, String program, int year, double gpa) {
        super(id, name, age);
        this.program = program;
        this.year = year;
        this.gpa = gpa;
    }

    // Overloaded constructor
    public Student(int id, String name, int age) {
        super(id, name, age);
        this.program = "Unknown";
        this.year = 1;
        this.gpa = 0.0;
    }

    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + name + ", a year " + year + " student in " + program + " with GPA " + gpa);
    }

    @Override
    public String evaluatePerformance() {
        if (gpa >= 3.5) return "Excellent";
        if (gpa >= 2.5) return "Good";
        if (gpa >= 1.5) return "Average";
        return "Poor";
    }

    @Override
    public String toString() {
        return super.toString() + ", Program: " + program + ", Year: " + year + ", GPA: " + gpa;
    }
}
