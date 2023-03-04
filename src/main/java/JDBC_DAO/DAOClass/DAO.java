package JDBC_DAO.DAOClass;

import java.sql.Connection;

public abstract class DAO<T> {

    protected Connection connect;

    public DAO(Connection conn) { this.connect = conn; }

    /**
     * Méthode de création
     * @param obj
     * @return boolean
     */
    public abstract boolean create(T obj);

    /**
     * Méthode pour effacer
     * @param obj
     * @return boolean
     */
    public abstract boolean delete(T obj);

    /**
     * Méthode de mise à jour
     * @param obj
     * @return boolean
     */
    public abstract boolean update(T obj);

    /**
     * Méthode de recherche des informations
     * @param id
     * @return T
     */
    public abstract T find(int id);
}
