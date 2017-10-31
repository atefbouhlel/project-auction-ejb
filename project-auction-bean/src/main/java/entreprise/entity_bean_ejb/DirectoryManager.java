package entreprise.entity_bean_ejb;

import java.util.Vector;

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
    public String addUser(String userName) {
        try {
            User u = findUser(userName);
            return "User already exists !!";
        }
        catch (NoResultException e) {
            User u = new User();
            u.setFirstname("fghjk");
            u.setAdress("fghjk");
            u.setEmail("fghjk");
            u.setLastname("fghjk");
            u.setPseudo(userName);

            em.persist(u);

        }


        return "ok user";
    }

    @Override
    public User findUser(final String name) {
        Query q = em.createQuery("select u from User u where u.pseudo = :name");
        q.setParameter("name", name);
        return (User) q.getSingleResult();
    }

    @Override
    public void removeUser(String userName) {
        User u = findUser(userName);
        em.remove(u);
    }

    @Override
    public Vector<User> lookupAllUsers() {
//        Vector<User> users = new Vector<User>();

        Query q = em.createQuery("select u from User u");
        return (Vector<User>) q.getResultList();
        //System.out.println("Users successfuly selected!!");
        
//        return users;
    }

    @Override

    public Vector<Objet> getUserObjects(String pseudo) {
        try {
            User u = findUser(pseudo);
            /*Query q = em.createQuery("select o from Objet o where o.user=:user");
            q.setParameter("user", u);*/

            try {
//                System.out.println("****Objects list:");
                return (Vector<Objet>) u.getObjects();
                /*for (Objet o : u.getObjects()) {
                    System.out.println("atef");
                    System.out.println(o);
                }*/
            }
            catch (NoResultException e){
                System.out.println(u.getPseudo() + " has no objects !");
                return null;
            }
        }
        catch (NoResultException e){
            System.out.println("User does not exist!!");
            return null;
        }
    }

    @Override
    public void addObjectToUser(String pseudo, Objet obj){
        User user = findUser(pseudo);
        user.getObjects().add(obj);
        obj.setUser(user);
    }
}
