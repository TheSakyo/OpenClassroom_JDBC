package JDBC_DAO.TableClass;

public class Eleve {

    //ID
    private int id = 0;

    //Nom de l'élève
    private String name = "";

    //Préname de l'élève
    private String firstname = "";


    public Eleve(){}
    public Eleve(int id, String name, String firstname) {

        this.id = id;
        this.name = name;
        this.firstname = firstname;
    }


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }
}