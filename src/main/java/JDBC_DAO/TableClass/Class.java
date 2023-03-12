package JDBC_DAO.TableClass;

import java.util.HashSet;
import java.util.Set;

public class Class {

    //ID
    private int id = 0;

    //Nom du professeur
    private String name = "";

    //Liste des professeurs
    private Set<Professor> listProfessor = new HashSet<Professor>();

    //Liste des élèves
    private Set<Student> listStudent = new HashSet<Student>();


    public Class(){}
    public Class(int id, String nom) { this.id = id; this.name = nom; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Set<Professor> getListProfessor() { return listProfessor; }

    public void setListProfessor(Set<Professor> listProfessor) { this.listProfessor = listProfessor; }

    @SuppressWarnings("unnecessary")
    public void addProfessor(Professor prof) { if(!listProfessor.contains(prof)) this.listProfessor.add(prof); }

    public void removeProfessor(Professor prof) { this.listProfessor.remove(prof); }

    public Set<Student> getListStudent() { return listStudent; }

    public void getListStudent(Set<Student> listStudent) { this.listStudent = listStudent; }

    //Ajoute un élève à la classe
    public void addStudent(Student student) { if(!listStudent.contains(student)) this.listStudent.add(student); }

    //Retire un élève de la classe
    public void removeStudent(Student student) { this.listStudent.remove(student); }

    public boolean equals(Class cls){ return this.getId() == cls.getId(); }
}
