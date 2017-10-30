package entreprise.entity_bean_entity;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * The entity.
 */
@Entity
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * the identifier of the customer.
	 */
	private int id;
	/**
	 * the name of the customer.
	 */
	private String name;
	/**
	 * the collection of orders.
	 */
	/*private Collection<Order> orders = new ArrayList<Order>();
*/
	/**
	 * gets the identifier.
	 * 
	 * @return the identifier.
	 */
	@Id
	public int getId() {
		return id;
	}

	/**
	 * sets the identifier.
	 * 
	 * @param id
	 *            the new identifier.
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * gets the name of the customer.
	 * 
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name of the customer.
	 * 
	 * @param name
	 *            the new name.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * gets the collection of orders.
	 * 
	 * @return the collection.
	 */
	/*@OneToMany(cascade = ALL, mappedBy = "customer")
	public Collection<Order> getOrders() {
		return orders;
	}*/

	/**
	 * sets the collection of orders.
	 * 
	 * @param newValue
	 *            the new collection.
	 */
	/*public void setOrders(final Collection<Order> newValue) {
		this.orders = newValue;
	}
*/
}
