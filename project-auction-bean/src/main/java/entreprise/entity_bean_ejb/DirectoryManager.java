package entreprise.entity_bean_ejb;

import java.util.Vector;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.persistence.NoResultException;

import entreprise.entity_bean_api.DirectoryManagerRemote;
import entreprise.entity_bean_api.StatelessSession;
import entreprise.entity_bean_entity.Objet;
import entreprise.entity_bean_entity.User;

/**
 * The stateless session bean.
 */
@Stateless
public class DirectoryManager implements DirectoryManagerRemote {

    /**
     * the reference to the entity manager, which persistence context is "pu1".
     */
    @PersistenceContext(unitName = "pu1")
    private EntityManager em;

    @Override
    public String addUser(String pseudo) {
            User u;
            u = findUser(pseudo);

            if (u != null)
                return "User already exists !!";

            u = new User();
            u.setFirstname(pseudo + "_FirstName");
            u.setAdress(pseudo + "_Address");
            u.setEmail(pseudo + "@hotmail.com");
            u.setLastname(pseudo+ "_LastName");
            u.setPseudo(pseudo);

            em.persist(u);




        return pseudo + " is successfully added !";
    }

    @Override
    public User findUser(String name) {
        try{
            Query q = em.createQuery("select u from User u where u.pseudo = :name");
            q.setParameter("name", name);
            return (User) q.getSingleResult();
        }catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void removeUser(String userName) {
        User u = findUser(userName);
        if (u != null)
            em.remove(u);
    }

    @Override
    public Vector<User> lookupAllUsers() {
        Query q = em.createQuery("select u from User u where u.role = :role");
        q.setParameter("role", 1);
        return (Vector<User>) q.getResultList();
    }

    @Override
    public Vector<Objet> getUserObjects(String pseudo) {

            User u = findUser(pseudo);
            if (u != null) {

                try {
//                System.out.println("****Objects list:");
                    return (Vector<Objet>) u.getObjects();
                /*for (Objet o : u.getObjects()) {
                    System.out.println("atef");
                    System.out.println(o);
                }*/
                } catch (NoResultException e) {
                    System.out.println(u.getPseudo() + " has no objects !");
                    return null;
                }
            }
            return null;

    }

    @Override
    public String addObjectToUser(String pseudo, Objet obj){
        User user = findUser(pseudo);

        if (user != null) {
            user.getObjects().add(obj);
            obj.setUser(user);

            return obj.getName() + "is added successfully !";
        }

        return "User does not exist!!";
    }

    @Override
    public void createAdmin() {
        User u = findUser("admin");
        if (u == null) {
            u = new User();
            u.setFirstname("Admin");
            u.setAdress("Admin_Address");
            u.setEmail("admin@hotmail.com");
            u.setLastname("Admin_LastName");
            u.setPseudo("admin");
            u.setRole(0);

            em.persist(u);
        }



    }

    @Override
    public boolean checkAdmin(String pseudo){

        try{
            Query q = em.createQuery("select u from User u where u.pseudo = :name and u.role = :role");
            q.setParameter("name", pseudo);
            q.setParameter("role", 0);
            User u = (User) q.getSingleResult();
            return true;
        }catch (NoResultException e) {
            return false;
        }
    }
}
