package entreprise.entity_bean_ejb;

import entreprise.entity_bean_api.AuctionManagerRemote;
import entreprise.entity_bean_api.DirectoryManagerRemote;
import entreprise.entity_bean_entity.Auction;
import entreprise.entity_bean_entity.Objet;
import entreprise.entity_bean_entity.User;

import javax.ejb.Stateless;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.persistence.NoResultException;
import java.util.Vector;
import javax.naming.InitialContext;

@Stateless
public class AuctionManager implements AuctionManagerRemote {

    @PersistenceContext(unitName = "pu1")
    private EntityManager em;

    @Override
    public  User authenticateUser(String pseudo){

        User u = null;

        try {
            InitialContext ic = new InitialContext();
            DirectoryManagerRemote sb = (DirectoryManagerRemote) ic.lookup("entreprise.entity_bean_api.DirectoryManagerRemote");
            u = sb.findUser(pseudo);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public Auction findAuction(int auctionId){
        Query q = em.createQuery("select a from Auction a where a.id = :id");
        q.setParameter("id", auctionId);
        return (Auction) q.getSingleResult();
    }

    @Override
    public Vector<Objet> getUserObjectsForAuction(String pseudo){
        Vector<Objet> objects = null;
        try {
            InitialContext ic = new InitialContext();
            DirectoryManagerRemote dm = (DirectoryManagerRemote) ic.lookup("entreprise.entity_bean_api.DirectoryManagerRemote");
            objects = dm.getUserObjects(pseudo);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return objects;
    }

    @Override
    public String startAuction(Auction auction, String pseudo, int objectId) {
        Query q = em.createQuery("select u from User u where u.pseudo = :name");
        q.setParameter("name", pseudo);
        User u = (User) q.getSingleResult();

        Query q2 = em.createQuery("select o from Objet o where o.id = :id");
        q2.setParameter("id", objectId);
        Objet obj = (Objet) q2.getSingleResult();
        if (obj == null)
            return "This object's id does not exist";

        u.getAuctions().add(auction);
        auction.setUser(u);
        auction.setObject(obj);

        return "Auction is started..\n";
    }

    @Override
    public String closeAuction(int auctionId){
        Auction auction = findAuction(auctionId);
        if (auction == null)
            return "The chosen auction does not exist !";

        auction.setState("close");

        return "Auction succesfully close";
    }

    @Override
    public Vector<Auction> lookupAllAuctions(){
        Query q = em.createQuery("select a from Auction a");
        return (Vector<Auction>) q.getResultList();
    }

    @Override
    public Vector<Auction> lookupAllOpenAuctions(){
        Query q = em.createQuery("select a from Auction a where a.state = :state");
        q.setParameter("state", "active");
        return (Vector<Auction>) q.getResultList();
    }

}
