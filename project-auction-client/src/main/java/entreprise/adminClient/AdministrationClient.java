
package entreprise.adminClient;

import entreprise.entity_bean_api.DirectoryManagerRemote;
import entreprise.entity_bean_entity.Objet;
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

			//This creation of the admin is done only for the test
				sb.createAdmin();

				boolean found = false;

				do {
					Scanner inUser = new Scanner(System.in);
					System.out.println("Type your admin's pseudo:");
					String pseudo = inUser.nextLine();

					found = sb.checkAdmin(pseudo);
					if (found == false)
						System.out.println("Not Found !");
				}while (found == false);

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
					System.out.println("4. add a new object to a user");
					System.out.println("5. Lookup a user objects");
					System.out.println("0. Quit");
					System.out.print("Your choice: ");

					choice = in.nextInt();

					switch (choice) {

						case 1:
							System.out.print("Enter username to add: ");
							Scanner in1 = new Scanner(System.in);
							String userName1 = in1.nextLine();
							System.out.println(sb.addUser(userName1));
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
							User foundUser = null;
							String pseudo5 = "";
							Vector<User> usersList = sb.lookupAllUsers();
							System.out.println("***Here's the user's list:");
							for (User user : usersList) {
								System.out.println(user);
							}

								System.out.print("Enter a pseudo: ");
								Scanner in5 = new Scanner(System.in);
								pseudo5 = in5.nextLine();

								foundUser = sb.findUser(pseudo5);
								if (foundUser == null)
									System.out.println("This user does not exist");
							else {


									System.out.println("Enter the details of the new object:");
									System.out.print("Name:");
									Scanner in6 = new Scanner(System.in);
									String objectName = in6.nextLine();

									Objet newObject = new Objet();
									newObject.setName(objectName);
									newObject.setDescription(objectName + "_Description");
									newObject.setCategory(objectName + "_Category");

									System.out.println(sb.addObjectToUser(pseudo5, newObject));
								}
							break;

						case 5:
							User foundUser5 = null;

							Vector<User> usersList2 = sb.lookupAllUsers();
							System.out.println("***Here's the user's list:");
							for (User user : usersList2) {
								System.out.println(user);
							}

							System.out.println("Enter pseudo: ");
							Scanner in3 = new Scanner(System.in);
							String pseudo = in3.nextLine();

							foundUser5 = sb.findUser(pseudo);
							if (foundUser5 == null)
								System.out.println("This user does not exist");
							else {


								Vector<Objet> objects = sb.getUserObjects(pseudo);
								System.out.println("****Objects list:*********");

								for (Objet o : objects) {
									System.out.println(o);
								}
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
