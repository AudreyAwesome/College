package edu.uw.tacoma.mmuppa.fragmentslabpractice.student;

/**
 * Created by mmuppa on 7/8/15.
 */
public class Student {
    private int id;
    private String name;
    private String major;

    public Student(int id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student " +
                "id=" + id;
    }
}
