
package entreprise.auctionClient;

import javax.naming.InitialContext;

import entreprise.entity_bean_api.AuctionManagerRemote;

import entreprise.entity_bean_entity.Auction;
import entreprise.entity_bean_entity.Objet;
import entreprise.entity_bean_entity.User;


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
				System.out.println("*********Auction Client*********");

				User activeUser = null;

				do {
					Scanner inUser = new Scanner(System.in);
					System.out.println("Type your pseudo:");
					String pseudo = inUser.nextLine();
					activeUser = amc.authenticateUser(pseudo);
					if (activeUser == null)
						System.out.println("This user does not exist");
				}while (activeUser == null);

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
					System.out.println("3. Send a bid");
					System.out.println("0. Quit");
					System.out.print("Your choice: ");

					choice = in.nextInt();

					switch (choice) {

						case 1:
							System.out.print("Enter the object's ID you want to sell: ");

							/*Scanner in1 = new Scanner(System.in);
							String userName1 = in1.nextLine();*/

							Auction auction1 = new Auction();
							auction1.setName("Auction Test");
							auction1.setStartPrice(10.0);
							auction1.setDuration(60);
							auction1.setState("active");
							System.out.println(amc.startAuction(activeUser, auction1));

							break;

						case 2:
							System.out.print("List of open auctions: ");
							Vector<Auction> auctions = amc.lookupAllOpenAuctions();
							System.out.println("******the auctions list:");
							for (Auction auction : auctions) {
								System.out.println(auction);
							}

							System.out.print("Type the auction's id to close: ");
							Scanner in3 = new Scanner(System.in);
							int auctionId = in3.nextInt();
							System.out.println(amc.closeAuction(auctionId));
							break;

						case 3:
							Vector<Auction> auctionsList = amc.lookupAllAuctions();
							System.out.println("******Open Auctions list:");
							for (Auction auction : auctionsList) {
								System.out.println(auction);
							}
							break;

						case 4:

/*System.out.print("Enter pseudo: ");
							Scanner in3 = new Scanner(System.in);
							String pseudo = in3.nextLine();
							Vector<Objet> objects = sb.getUserObjects(pseudo);
							System.out.println("****Objects list:*********");

							for (Objet o : objects) {
								System.out.println(o);
							}*/


							break;

						case 5:
							System.out.print("Enter a pseudo: ");

/*Scanner in5 = new Scanner(System.in);
							String pseudo5 = in5.nextLine();
							System.out.println("Enter the details of the new object:");*/

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
