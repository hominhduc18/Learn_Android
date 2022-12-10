package com.example.myapplication;

import java.util.Objects;

public class Project {
    private int id;
    private String projectname, deadline, name;

    public Project(int id, String projectname, String deadline, String name) {
        this.id = id;
        this.projectname = projectname;
        this.deadline = deadline;
        this.name = name;
    }

    public Project(String projectname, String deadline, String name) {
        this.projectname = projectname;
        this.deadline = deadline;
        this.name = name;
    }

    public Project() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectname='" + projectname + '\'' +
                ", deadline='" + deadline + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
