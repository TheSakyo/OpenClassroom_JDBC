package JDBC_DAO.TableClass;

import java.util.HashSet;
import java.util.Set;

public class Professor {

    //ID
    private int id = 0;

    //Nom du professeur
    private String lastname= "";

    //Prénamedu professeur
    private String firstname = "";

    //Liste des matières dispensées
    private Set<Subject> listMaterial = new HashSet<Subject>();

    public Professor(){}
    public Professor(int id, String lastname, String firstname) {

        this.id = id;
        this.lastname= lastname;
        this.firstname = firstname;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getLastName() { return lastname; }

    public void setLastName(String lastname) { this.lastname = lastname; }

    public String getFirstName() { return firstname; }

    public void setFistName(String firstname) { this.firstname = firstname; }

    public Set<Subject> getListMaterial() { return listMaterial; }

    public void setListMaterial(Set<Subject> listMaterial) { this.listMaterial = listMaterial; }

    //Ajoute une matière à un professeur
    public void addMaterial(Subject subject){ this.listMaterial.add(subject); }

    //Retire une matière à un professeur
    public void removeMaterial(Subject subject) { this.listMaterial.remove(subject); }
}