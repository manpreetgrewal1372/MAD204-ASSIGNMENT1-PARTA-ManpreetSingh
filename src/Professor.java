public class Professor extends Person implements Evaluable {
    private String department;
    private String title;
    private int coursesTaught;

    public Professor(int id, String name, int age, String department, String title, int coursesTaught) {
        super(id, name, age);
        this.department = department;
        this.title = title;
        this.coursesTaught = coursesTaught;
    }

    @Override
    public void introduce() {
        System.out.println("Hello, I'm " + title + " " + name + " from " + department + " department.");
    }

    @Override
    public String evaluatePerformance() {
        return coursesTaught > 5 ? "Outstanding" : "Good";
    }

    @Override
    public String toString() {
        return super.toString() + ", Department: " + department + ", Title: " + title + ", Courses Taught: " + coursesTaught;
    }
}
