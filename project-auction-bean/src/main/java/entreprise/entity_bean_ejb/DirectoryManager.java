package entreprise.entity_bean_ejb;

import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entreprise.entity_bean_api.DirectoryManagerRemote;
import entreprise.entity_bean_api.StatelessSession;
import entreprise.entity_bean_entity.User;

/**
 * The stateless session bean.
 */
@Stateless
public class DirectoryManager implements DirectoryManagerRemote {
    @Override
    public String addUser(String userName) {
        return "okkk";
    }

    @Override
    public void removeUser(String userName) {

    }

    @Override
    public Vector<User> lookupAllUsers() {
        return null;
    }
}
