package entreprise.entity_bean_api;


import entreprise.entity_bean_entity.Objet;
import entreprise.entity_bean_entity.User;

import java.util.Vector;

import javax.ejb.Remote;

@Remote
public interface DirectoryManagerRemote {

    public String addUser(String userName);
    public User findUser(String name);
    public void removeUser(String userName);
    public Vector<User> lookupAllUsers();

    public Vector<Objet> getUserObjects(String pseudo);
    public String addObjectToUser(String pseudo, Objet obj);

    /**
    * This method creates the Admin User to test the admin Client
     */
    public void createAdmin();

    /**
    * This method checks if the pseudo is Admin or not
    * @param pseudo the string of the pseudo to check
     * @return true or false
     */
    public boolean checkAdmin(String pseudo);

}
