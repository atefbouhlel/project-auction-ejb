
package entreprise.auctionClient;

import javax.naming.InitialContext;

import entreprise.entity_bean_entity.Customer;

import entreprise.entity_bean_api.StatelessSession;
import org.apache.catalina.util.ManifestResource;

public class AuctionClient {
	public static void main(String args[]) {
		StatelessSession sb;
		Customer c;
	        try {
			InitialContext ic = new InitialContext();
			sb = (StatelessSession) ic.lookup("entreprise.entity_bean_api.StatelessSession");
			System.out.println("Inserting Customer and Orders... " + sb.testInsert());
			// Test query and navigation
			System.out.println("Verifying that all are inserted... " + sb.verifyInsert());
			// Get a detached instance 
			c = sb.findCustomer("Bat Man");
			// Remove entity
			System.out.println("Removing entity... " + sb.testDelete(c));
			// Query the results
			System.out.println("Verifying that all are removed... " + sb.verifyDelete());
				System.out.println("... " + sb.testUserInsert());

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
