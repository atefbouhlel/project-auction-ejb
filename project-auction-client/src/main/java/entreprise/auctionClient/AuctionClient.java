
package entreprise.auctionClient;

import javax.naming.InitialContext;

import entreprise.entity_bean_api.AuctionManagerRemote;

import entreprise.entity_bean_entity.Auction;
import entreprise.entity_bean_entity.Objet;
import entreprise.entity_bean_entity.User;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class AuctionClient {
	public static void main(String args[]) {
//		StatelessSession sb;
//		Customer c;
		AuctionManagerRemote amc;
	        try {


			InitialContext ic = new InitialContext();
			amc = (AuctionManagerRemote) ic.lookup("entreprise.entity_bean_api.AuctionManagerRemote");


				boolean found = false;

				do {
					Scanner inUser = new Scanner(System.in);
					System.out.println("Type your pseudo:");
					String pseudo = inUser.nextLine();
//					activeUser = amc.authenticateUser(pseudo);
					found = amc.authenticate(pseudo);
					if (found == false)
						System.out.println("This user does not exist");
				}while (found == false);

				Scanner in = new Scanner(System.in);

				boolean exit = false;

				int choice;


				do {

					System.out.println("\n**************************************");
					System.out.println("*********Auction Client*********");
					System.out.println("**************************************");
					System.out.println("The menu: ");
					System.out.println("1. Start an auction");
					System.out.println("2. Close an auction");
					System.out.println("3. All open auctions list:");
					System.out.println("4. Send a bid");
					System.out.println("5. Add an object");
					System.out.println("6. My objects List");
					System.out.println("0. Quit");
					System.out.print("Your choice: ");

						choice = in.nextInt();

					switch (choice) {

						case 1:

							Scanner inAuction = new Scanner(System.in);
							System.out.print("\nEnter the auction's name: ");
							String name = inAuction.nextLine();

							System.out.print("Enter the auction's starting Price: ");
							double startPrice = inAuction.nextDouble();

							System.out.print("Enter the auction's auto incrementing Price: ");
							double increment = inAuction.nextDouble();

							System.out.print("Enter the auction's duration (minutes): ");
							int duration = inAuction.nextInt();



//							Vector<Objet> objects = amc.getUserObjectsForAuction(activeUser.getPseudo());

							Vector<Objet> objects = amc.getMyObjectsForAuction();
							System.out.println("\n******the Objects list*******************************************************:");
							for (Objet object : objects) {
								System.out.println(object.getId() + ". " +object.getName());
							}

							System.out.print("\nEnter the object's ID you want to sell: ");

							Scanner in1 = new Scanner(System.in);
							int objectId = in1.nextInt();

							Auction auction1 = new Auction();
							auction1.setName(name);
							auction1.setStartPrice(startPrice);
							auction1.setAutoIncrement(increment);
							auction1.setDuration(duration);
							auction1.setState("active");
							System.out.println(amc.startAuction(auction1, objectId));

							break;

						case 2:
							System.out.println("\n******List of open auctions****** ");
							Vector<Auction> auctions = amc.lookupAllOpenAuctions();
							if (auctions.size() == 0)
								System.out.println("There is no open auction !");
							else {
								for (Auction auction : auctions) {
									System.out.println(auction.getId() + ". " + auction.getName());
								}

								System.out.print("Type the auction's id to close: ");
								Scanner in2 = new Scanner(System.in);
								int auctionId = in2.nextInt();
								System.out.println(amc.closeAuction(auctionId));
							}
							break;

						case 3:
							Vector<Auction> auctionsList = amc.lookupAllOpenAuctions();
							System.out.println("******Open Auctions list:");
							for (Auction auction : auctionsList) {
								System.out.println(auction.getId() + ". " + auction.getName());
							}
							break;

						case 4:

							Vector<Auction> openAuctionsList = amc.lookupAllOpenAuctions();
							System.out.println("******Open Auctions list:");
							for (Auction auction : openAuctionsList) {
								System.out.println(auction.getId() + ". "+ auction.getName());
							}

							System.out.print("Type the auction's id to bid on: ");
							Scanner in4 = new Scanner(System.in);
							int auctionBidId = in4.nextInt();

							System.out.print("\nThe auction's details\n");
							String result = amc.getAuctionDetailsForBid(auctionBidId);
							System.out.println(result);

							if (result.startsWith("Name:")) {
								System.out.print("Are you sure you want to bid on this auction:(y/n)");
								Scanner in44 = new Scanner(System.in);
								String answer = in44.nextLine();

								if (answer.equals("y"))
									System.out.println(amc.sendBid(auctionBidId));
							}

							break;

						case 5:
							System.out.println("Enter the details of the new object:");
							System.out.print("Name:");
							Scanner in5 = new Scanner(System.in);
							String objectName = in5.nextLine();

							Objet newObject = new Objet();
							newObject.setName(objectName);
							newObject.setDescription(objectName + "_Description");
							newObject.setCategory(objectName + "_Category");

							System.out.println(amc.addObject(newObject));
							break;

						case 6:
							Vector<Objet> myObjects = amc.getMyObjectsForAuction();
							System.out.println("****Objects list:*********");

							for (Objet o : myObjects) {
								System.out.println(o);
							}
							break;

						case 0:
							exit = true;
							break;
							
						default:
							System.out.println("Invalid choice.");
					}

				} while (!exit);




		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
