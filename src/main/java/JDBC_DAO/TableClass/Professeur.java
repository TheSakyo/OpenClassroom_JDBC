package JDBC_DAO.TableClass;

import java.util.HashSet;
import java.util.Set;

public class Professeur {

    //ID
    private int id = 0;

    //Nom du professeur
    private String name= "";

    //Prénamedu professeur
    private String firstname = "";

    //Liste des matières dispensées
    private Set<Matiere> listMaterial = new HashSet<Matiere>();

    public Professeur(){}
    public Professeur(int id, String name, String firstname) {

        this.id = id;
        this.name= name;
        this.firstname = firstname;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getFirstname() { return firstname; }

    public void setFistname(String firstname) { this.firstname = firstname; }

    public Set<Matiere> getListMaterial() { return listMaterial; }

    public void setListMaterial(Set<Matiere> listMaterial) { this.listMaterial = listMaterial; }

    //Ajoute une matière à un professeur
    public void addMaterial(Matiere material){ this.listMaterial.add(material); }

    //Retire une matière à un professeur
    public void removeMaterial(Matiere material) { this.listMaterial.remove(material); }
}