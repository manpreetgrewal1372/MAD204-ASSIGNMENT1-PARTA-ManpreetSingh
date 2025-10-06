public class TeachingAssistant extends Student {
    private String assignedCourse;

    public TeachingAssistant(int id, String name, int age, String program, int year, double gpa, String assignedCourse) {
        super(id, name, age, program, year, gpa);
        this.assignedCourse = assignedCourse;
    }

    @Override
    public void introduce() {
        System.out.println("Hello, I'm " + name + ", a TA for " + assignedCourse + " with GPA " + super.gpa);
    }

    @Override
    public String evaluatePerformance() {
        if (super.gpa >= 3.0) return "Excellent TA";
        return "Needs Improvement";
    }

    @Override
    public String toString() {
        return super.toString() + ", Assigned Course: " + assignedCourse;
    }
}
