package entreprise.entity_bean_ejb;

import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entreprise.entity_bean_api.DirectoryManagerRemote;
import entreprise.entity_bean_api.StatelessSession;
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
        User u = new User();
        u.setFirstname("fghjk");
        u.setAdress("fghjk");
        u.setEmail("fghjk");
        u.setLastname("fghjk");
        u.setPseudo("atef");

        em.persist(u);

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
}
