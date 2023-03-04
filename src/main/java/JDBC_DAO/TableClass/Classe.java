package JDBC_DAO.TableClass;

import java.util.HashSet;
import java.util.Set;

public class Classe {

    //ID
    private int id = 0;

    //Nom du professeur
    private String name = "";

    //Liste des professeurs
    private Set<Professeur> listProfessor = new HashSet<Professeur>();

    //Liste des élèves
    private Set<Eleve> listStudent = new HashSet<Eleve>();


    public Classe(){}
    public Classe(int id, String nom) { this.id = id; this.name = nom; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Set<Professeur> getListProfessor() { return listProfessor; }

    public void setListProfessor(Set<Professeur> listProfessor) { this.listProfessor = listProfessor; }

    @SuppressWarnings("unnecessary")
    public void addProfessor(Professeur prof) { if(!listProfessor.contains(prof)) this.listProfessor.add(prof); }

    public void removeProfesseur(Professeur prof) { this.listProfessor.remove(prof); }

    public Set<Eleve> getListStudent() { return listStudent; }

    public void getListStudent(Set<Eleve> listStudent) { this.listStudent = listStudent; }

    //Ajoute un élève à la classe
    public void addStudent(Eleve student) { if(!listStudent.contains(student)) this.listStudent.add(student); }

    //Retire un élève de la classe
    public void removeStudent(Eleve student) { this.listStudent.remove(student); }

    public boolean equals(Classe cls){ return this.getId() == cls.getId(); }
}
