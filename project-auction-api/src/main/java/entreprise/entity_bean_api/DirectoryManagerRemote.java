package entreprise.entity_bean_api;


import entreprise.entity_bean_entity.Objet;
import entreprise.entity_bean_entity.User;

import java.util.Vector;

import javax.ejb.Remote;

@Remote
public interface DirectoryManagerRemote {

    public String addUser(String userName);
    public User findUser(final String name);
    public void removeUser(String userName);
    public Vector<User> lookupAllUsers();

    public Vector<Objet> getUserObjects(String pseudo);
    public void addObjectToUser(String pseudo, Objet obj);
    //public NewsGroupRight lookupAUserRights(String userName);
    //public void updateAUserRights(String userName, boolean readRight, boolean writeRight);
}
