
package entreprise.adminClient;

import entreprise.entity_bean_api.DirectoryManagerRemote;
import entreprise.entity_bean_entity.Customer;

import javax.naming.InitialContext;

public class AdministrationClient {
	public static void main(String args[]) {
		DirectoryManagerRemote sb;
	        try {
			InitialContext ic = new InitialContext();
			sb = (DirectoryManagerRemote) ic.lookup("entreprise.entity_bean_api.DirectoryManagerRemote");
			System.out.println("Test... " + sb.addUser("atef"));


		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
