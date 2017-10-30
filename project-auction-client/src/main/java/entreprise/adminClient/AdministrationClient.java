
package entreprise.adminClient;

import entreprise.entity_bean_api.DirectoryManagerRemote;
import entreprise.entity_bean_entity.User;

import javax.naming.InitialContext;
import java.util.Scanner;
import java.util.Vector;

public class AdministrationClient {
	public static void main(String args[]) {
		DirectoryManagerRemote sb;
	        try {
			InitialContext ic = new InitialContext();
			sb = (DirectoryManagerRemote) ic.lookup("entreprise.entity_bean_api.DirectoryManagerRemote");

				Scanner in = new Scanner(System.in);

				boolean exit = false;

				int choice;

				do {

					System.out.println("\n**************************************");
					System.out.println("*********Administration Client*********");
					System.out.println("**************************************");
					System.out.println("The menu: ");
					System.out.println("1. Add user");
					System.out.println("2. Remove user");
					System.out.println("3. Lookup all users");
					System.out.println("4. Lookup a user rights");
					System.out.println("5. Update a user rights");
					System.out.println("0. Quit");
					System.out.print("Your choice: ");

					choice = in.nextInt();

					switch (choice) {

						case 1:
							System.out.print("Enter username to add: ");
							/*Scanner in1 = new Scanner(System.in);
							String userName1 = in1.nextLine();*/
							sb.addUser("ghjk");
							break;

						case 2:
							System.out.print("Enter a pseudo to delete: ");
							Scanner in2 = new Scanner(System.in);
							String pseudoToDelete = in2.nextLine();
							sb.removeUser(pseudoToDelete);
							break;

						case 3:
							Vector<User> users = sb.lookupAllUsers();
							System.out.println("***Here's the user's list:");
							for (User user : users) {
								System.out.println(user);
							}
							break;

						case 4:
							System.out.print("Enter username: ");
							/*Scanner in3 = new Scanner(System.in);
							String userName4 = in3.nextLine();
							dm.lookupAUserRights(userName4);*/
							break;

						case 5:
							System.out.print("Enter username: ");
//							Scanner in5 = new Scanner(System.in);
//							String userName5 = in5.nextLine();
//							System.out.println("Enter his new rights (rw, r, w or no_rights)");
//							Scanner in6 = new Scanner(System.in);
//							String rights = in6.nextLine();
//							if (rights.equals("rw")) dm.updateAUserRights(userName5, true, true);
//							else if (rights.equals("r")) dm.updateAUserRights(userName5, true, false);
//							else if (rights.equals("w")) dm.updateAUserRights(userName5, false, true);
//							else if (rights.equals("no_rights")) dm.updateAUserRights(userName5, false, false);
//							else System.out.println("Not a valid entry. Operation cancelled!");
							break;

						case 0:
							exit = true;
							break;

						default:
							System.out.println("Invalid choice.");
					}

				} while (!exit);
			System.out.println("Test... " + sb.addUser("atef"));


		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
