package entreprise.entity_bean_api;

import entreprise.entity_bean_entity.User;

import java.util.Vector;

import javax.ejb.Remote;

@Remote
public interface AuctionManagerRemote {
    public String startAuction();
}
