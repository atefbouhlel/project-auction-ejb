package entreprise.entity_bean_api;

import javax.ejb.Remote;

import entreprise.entity_bean_entity.Customer;

/**
 * The API of the entity bean.
 */
@Remote
public interface StatelessSession {
	/**
	 * Insert Customer and Orders.
	 * 
	 * @return the string "OK" if there is no problem.
	 */
	String testInsert();

	/**
	 * verifies the insertion.
	 * 
	 * @return the string "OK" if there is no problem.
	 */
	String verifyInsert();

	/**
	 * deletes the given customer.
	 * 
	 * @param c
	 *            the customer and the associated orders.
	 * @return the string "OK" if there is no problem.
	 */
	String testDelete(Customer c);

	/**
	 * verifies the deletion.
	 * 
	 * @return the string "OK" if there is no problem.
	 */
	String verifyDelete();

	/**
	 * gets a detached instance of a Customer.
	 * 
	 * @param name
	 *            the name of the customer to search for.
	 * @return the customer object.
	 */
	Customer findCustomer(String name);
	String testUserInsert();
}
