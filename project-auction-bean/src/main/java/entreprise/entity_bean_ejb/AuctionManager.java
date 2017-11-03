package entreprise.entity_bean_ejb;

import entreprise.entity_bean_api.AuctionManagerRemote;
import entreprise.entity_bean_api.DirectoryManagerRemote;
import entreprise.entity_bean_entity.Auction;
import entreprise.entity_bean_entity.Objet;
import entreprise.entity_bean_entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.persistence.NoResultException;
import java.util.Vector;
import javax.naming.InitialContext;

@Stateful
public class AuctionManager implements AuctionManagerRemote {

    @PersistenceContext(unitName = "pu1")
    private EntityManager em;

    @EJB
    private DirectoryManagerRemote dm;

    private User activeUser = null;

    public User getActiveUser() {
        return activeUser;
    }

    @Override
    public boolean authenticate(String pseudo) {

            /*Query q = em.createQuery("select u from User u where u.pseudo = :pseudo");
            q.setParameter("pseudo", pseudo);
            this.activeUser = (User) q.getSingleResult();*/
            this.activeUser = dm.findUser(pseudo);

            if (this.activeUser == null)
                return false;

        return true;
    }



    @Override
    public Auction findAuction(int auctionId){
        try{
            Query q = em.createQuery("select a from Auction a where a.id = :id");
            q.setParameter("id", auctionId);
            return (Auction) q.getSingleResult();

        }catch (NoResultException e) {
            return null;
        }
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
    public Vector<Objet> getMyObjectsForAuction(){
        Vector<Objet> objects ;
        if (this.activeUser != null) {
                return (Vector<Objet>) this.activeUser.getObjects();
        }
        return null;
    }


    @Override
    public String startAuction(Auction auction, int objectId) {
        if (this.activeUser == null) {
            return "User is not connected !!!!";
        }

        Query q = em.createQuery("select u from User u where u.pseudo = :name");
        q.setParameter("name", this.activeUser.getPseudo());
        User u = (User) q.getSingleResult();

        Objet obj;
        try{
            Query q2 = em.createQuery("select o from Objet o where o.id = :id");
            q2.setParameter("id", objectId);
            obj = (Objet) q2.getSingleResult();
        }catch (NoResultException e) {
            return "This object's id does not exist";
        }


        u.getAuctions().add(auction);
        auction.setVendor(u);
        auction.setObject(obj);

        return "Auction is started..\n";
    }

    @Override
    public String closeAuction(int auctionId){
        if (this.activeUser == null)
            return "User is not connected !!!!";

        Auction auction = findAuction(auctionId);
        if (auction == null)
            return "The chosen auction does not exist !";

        auction.setState("close");

        return "Auction succesfully close !\n"+
                "The winner of this auction is: \n"+
                auction.getBuyer()+ "\n"+
                "with the price: " + auction.getFinalPrice()+ " euros.";
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

    @Override
    public String getAuctionDetailsForBid(int auctionId){
        if (this.activeUser == null)
            return "User is not connected !!!!";

        Auction auction = findAuction(auctionId);

        if (auction == null)
            return "There is no auction having the typed id";



        if (auction.getState().equals("active")){
            return "Name: "+ auction.getName() + "\n"+
                    "Current Price: "+ auction.getFinalPrice() + "\n"+
                    "Increment Price: "+ auction.getAutoIncrement() + "\n";
        }
        return "There is no auction having the typed id";
    }

    @Override
    public String addObject(Objet obj){
        if (this.activeUser == null)
            return "User is not connected !!!!";

        return dm.addObjectToUser(this.activeUser.getPseudo(), obj);
    }



    @Override
    public String sendBid(int auctionId){
        if (this.activeUser == null)
            return "User is not connected !!!!";

        Auction auction = findAuction(auctionId);
        if (auction == null)
            return "There is no auction having the typed id";



        if (auction.getState().equals("active")){
            if (auction.getFinalPrice() == 0)
                auction.setFinalPrice(auction.getStartPrice()+ auction.getAutoIncrement());
            else
                auction.setFinalPrice(auction.getFinalPrice()+ auction.getAutoIncrement());

            auction.setBuyer(this.activeUser);

            return "You have bidded successfully with " + auction.getFinalPrice() + "euros";
        }
        return "Impossible to bid on this auction";
    }

    public boolean checkRight() {

        if (this.activeUser == null)
            return false;

        User u;

        try {
            Query q = em.createQuery("select u from User u where u.pseudo = :name");
            q.setParameter("name", this.activeUser.getPseudo());
            u = (User) q.getSingleResult();
        } catch (NoResultException e) {
            return false;
        }

        Vector<Auction> myAuctions = null;

        try {
            Query q2 = em.createQuery("SELECT a FROM Auction a WHERE a.vendor = :user and a.state = :state");
            q2.setParameter("user", u);
            q2.setParameter("state", "active");

            myAuctions = (Vector<Auction>) q2.getResultList();

        } catch (NoResultException e) {
            return false;
        }

        return myAuctions.size() < 5;
    }

}
