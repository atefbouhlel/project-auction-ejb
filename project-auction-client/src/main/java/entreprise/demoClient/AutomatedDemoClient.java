package entreprise.demoClient;

import entreprise.entity_bean_api.AuctionManagerRemote;
import entreprise.entity_bean_api.DirectoryManagerRemote;
import entreprise.entity_bean_entity.Auction;
import entreprise.entity_bean_entity.Objet;
import entreprise.entity_bean_entity.User;

import javax.naming.InitialContext;
import java.util.Scanner;
import java.util.Vector;

public class AutomatedDemoClient {
    public static void main(String args[]) {
        DirectoryManagerRemote sb;
        AuctionManagerRemote amc1;
        AuctionManagerRemote amc2;
        AuctionManagerRemote amc3;
        try {
            InitialContext ic = new InitialContext();
            sb = (DirectoryManagerRemote) ic.lookup("entreprise.entity_bean_api.DirectoryManagerRemote");
            amc1 = (AuctionManagerRemote) ic.lookup("entreprise.entity_bean_api.AuctionManagerRemote");
            amc2 = (AuctionManagerRemote) ic.lookup("entreprise.entity_bean_api.AuctionManagerRemote");
            amc3 = (AuctionManagerRemote) ic.lookup("entreprise.entity_bean_api.AuctionManagerRemote");


            System.out.println("\n**************************************");
            System.out.println("*********Administration Client*********");
            System.out.println("**************************************");
            System.out.println("The menu: ");
            System.out.println("1. Add user");
            System.out.println("2. Remove user");
            System.out.println("3. Lookup all users");
            System.out.println("4. add a new object to a user");
            System.out.println("5. Lookup a user objects");
            System.out.println("0. Quit");

            String pseudo1 = "atef";
            String pseudo2 = "bouhlel";
            String pseudo3 = "john";
            String pseudo4 = "atefDeleted";

            System.out.println("-----------------Add a USER-----------------");
            System.out.println("Enter a pseudo to add: " + pseudo1);
            System.out.println(sb.addUser(pseudo1));

            System.out.println("Enter a pseudo to add: " + pseudo2);
            System.out.println(sb.addUser(pseudo2));

            System.out.println("Enter a pseudo to add: " + pseudo3);
            System.out.println(sb.addUser(pseudo3));

            System.out.println("Enter a pseudo to add (it will be removed): " + pseudo4);
            System.out.println(sb.addUser(pseudo4));

            System.out.println("-----------------Remove a USER-----------------");
            System.out.println("Enter a pseudo to delete: " + pseudo4);
            sb.removeUser(pseudo4);


            System.out.println("-----------------USERS List -----------------");
                Vector<User> users = sb.lookupAllUsers();
                for (User user : users) {
                    System.out.println(user);
                }

            System.out.println("-----------------Add an Object to a User1 -----------------");
            System.out.println("Enter a pseudo: "+ pseudo1);

            System.out.println("Enter the details of the new object:");
            String objectName = "Objet1_U1";
            String objectDescription = "Objet11_Description";
            String objectCategory = "Objet11_Category";
            System.out.println("Name: "+ objectName);
            System.out.println("Description: "+ objectDescription);
            System.out.println("Category: "+ objectCategory);

            Objet newObject = new Objet();
            newObject.setName(objectName);
            newObject.setDescription(objectDescription);
            newObject.setCategory(objectCategory);

            System.out.println(sb.addObjectToUser(pseudo1, newObject));

            System.out.println("-----------------Add a second Object to a User1 -----------------");
            System.out.println("Enter a pseudo: "+ pseudo1);

            System.out.println("Enter the details of the new object:");
            String objectName2 = "Objet2_U1";
            String objectDescription2 = "Objet21_Description";
            String objectCategory2 = "Objet21_Category";
            System.out.println("Name: "+ objectName);
            System.out.println("Description: "+ objectDescription);
            System.out.println("Category: "+ objectCategory);

            Objet newObject2 = new Objet();
            newObject2.setName(objectName2);
            newObject2.setDescription(objectDescription2);
            newObject2.setCategory(objectCategory2);

            System.out.println(sb.addObjectToUser(pseudo1, newObject2));

            System.out.println("\n-----------------Add an Object to a User2 -----------------");
            System.out.println("Enter a pseudo: "+ pseudo2);

            System.out.println("Enter the details of the new object:");
            String objectName3 = "Objet1_U2";
            String objectDescription3 = "Objet12_Description";
            String objectCategory3 = "Objet12_Category";
            System.out.println("Name: "+ objectName3);
            System.out.println("Description: "+ objectDescription3);
            System.out.println("Category: "+ objectCategory3);

            Objet newObject3 = new Objet();
            newObject3.setName(objectName3);
            newObject3.setDescription(objectDescription3);
            newObject3.setCategory(objectCategory3);

            System.out.println(sb.addObjectToUser(pseudo2, newObject3));


            System.out.println("\n-----------------User1's Objects -----------------");
                System.out.println("Enter pseudo: " + pseudo1 + "\n");

                Vector<Objet> objects = sb.getUserObjects(pseudo1);
                for (Objet o : objects) {
                    System.out.println(o);
                }

            System.out.println("\n-----------------User2's Objects -----------------");

            System.out.println("Enter pseudo: " + pseudo2 + "\n");

            Vector<Objet> objects2 = sb.getUserObjects(pseudo2);
            for (Objet o : objects2) {
                System.out.println(o);
            }

            System.out.println("\n\n\n**************************************");
            System.out.println("*********Auction Client 1 (Vendor)*********");
            System.out.println("**************************************");

            System.out.println("-----------------Add an Object to Auction -----------------");

//                Vector<Objet> vendorObjects = amc.getUserObjectsForAuction(pseudo1);
            boolean found = amc1.authenticate(pseudo1);
                Vector<Objet> vendorObjects1 = amc1.getMyObjectsForAuction();
                for (Objet object : vendorObjects1) {
                    System.out.println(object.getId() + ". " +object.getName());
                }
            System.out.println("\nEnter the object's ID you want to sell: " + vendorObjects1.firstElement().getId());
            System.out.println("\n The chosen Object is: "+ vendorObjects1.firstElement().getId() + ". " +vendorObjects1.firstElement().getName());


                Auction auction1 = new Auction();
                auction1.setName("Auction Test");
                auction1.setStartPrice(100.0);
                auction1.setDuration(60);
                auction1.setAutoIncrement(10.0);
                auction1.setState("active");
                System.out.println(amc1.startAuction(auction1, vendorObjects1.firstElement().getId()));


            System.out.println("\n\n-----------------List of all the auctions -----------------");
                Vector<Auction> auctionsList = amc1.lookupAllAuctions();
                System.out.println("******Open Auctions list:");
                for (Auction auction : auctionsList) {
                    System.out.println(auction);
                }


            System.out.println("\n\n\n**************************************");
            System.out.println("*********Auction Client 2 (Buyer1)*********");
            System.out.println("**************************************");

            found = amc2.authenticate(pseudo2);
            /*Vector<Objet> vendorObjects2 = amc2.getMyObjectsForAuction();
            for (Objet object : vendorObjects2) {
                System.out.println(object.getId() + ". " +object.getName());
            }*/

            Vector<Auction> openAuctionsList = amc2.lookupAllOpenAuctions();
            System.out.println("******Open Auctions list******");
            for (Auction auction : openAuctionsList) {
                System.out.println(auction.getId() + ". "+ auction.getName());
            }

            System.out.println("Type the auction's id to bid on: " + openAuctionsList.firstElement().getId());

            System.out.print("\nThe auction's details:\n");
            String result = amc2.getAuctionDetailsForBid(openAuctionsList.firstElement().getId());
            System.out.println(result);

                System.out.println("Are you sure you want to bid on this auction:(y/n)" + "y");

                    System.out.println(amc2.sendBid(openAuctionsList.firstElement().getId()));



            System.out.println("\n\n\n**************************************");
            System.out.println("*********Auction Client 3 (Buyer2)*********");
            System.out.println("**************************************");
            found = amc3.authenticate(pseudo3);

            Vector<Auction> openAuctionsList3 = amc3.lookupAllOpenAuctions();
            System.out.println("******Open Auctions list******");
            for (Auction auction : openAuctionsList3) {
                System.out.println(auction.getId() + ". "+ auction.getName());
            }

            System.out.println("Type the auction's id to bid on: " + openAuctionsList3.firstElement().getId());

            System.out.print("\nThe auction's details:\n");
            String result3 = amc2.getAuctionDetailsForBid(openAuctionsList3.firstElement().getId());
            System.out.println(result3);

            System.out.println("Are you sure you want to bid on this auction:(y/n)" + "y");

            System.out.println(amc2.sendBid(openAuctionsList.firstElement().getId()));



            System.out.println("\n\n\n**************************************");
            System.out.println("*********Auction Client 1 (the Vendor for closing the auction)*********");
            System.out.println("**************************************");
            System.out.println("\n\n-----------------Close an Auction -----------------");

                Vector<Auction> auctionsToClose = amc1.lookupAllOpenAuctions();
                for (Auction auction : auctionsToClose) {
                    System.out.println(auction.getId() + ". "+ auction.getName());
                }

            int auctionId = auctionsToClose.firstElement().getId();
            System.out.print("Type the auction's id to close: "+ auctionId);
                System.out.println(amc1.closeAuction(auctionId));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
