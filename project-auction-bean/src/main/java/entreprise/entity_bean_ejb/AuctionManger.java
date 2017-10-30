package entreprise.entity_bean_ejb;

import entreprise.entity_bean_api.AuctionManagerRemote;

public class AuctionManger implements AuctionManagerRemote {
    @Override
    public String startAuction() {
        return "startAuction ok..";
    }
}
