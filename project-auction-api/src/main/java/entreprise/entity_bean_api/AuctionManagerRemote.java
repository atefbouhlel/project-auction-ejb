package entreprise.entity_bean_api;

import entreprise.entity_bean_entity.Auction;
import entreprise.entity_bean_entity.Objet;
import entreprise.entity_bean_entity.User;

import java.util.Vector;

import javax.ejb.Remote;

@Remote
public interface AuctionManagerRemote {

    public  User authenticateUser(String pseudo);

    public String startAuction(Auction auction, String pseudo, int objectId);

    public Vector<Auction> lookupAllAuctions();

    public Vector<Auction> lookupAllOpenAuctions();
    public Auction findAuction(int auctionId);
    public String closeAuction(int auctionId);

    public Vector<Objet> getUserObjectsForAuction(String pseudo);
}
