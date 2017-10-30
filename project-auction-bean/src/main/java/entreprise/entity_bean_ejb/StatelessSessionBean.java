package entreprise.entity_bean_ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entreprise.entity_bean_entity.Customer;

import entreprise.entity_bean_api.StatelessSession;
import entreprise.entity_bean_entity.User;

/**
 * The stateless session bean.
 */
@Stateless
public class StatelessSessionBean implements StatelessSession {

	/**
	 * the reference to the entity manager, which persistence context is "pu1".
	 */
	@PersistenceContext(unitName = "pu1")
	private EntityManager em;

	@Override
	public String testInsert() {
		// Create a new customer
		Customer customer1 = new Customer();
		customer1.setId(1);
		customer1.setName("Bat Man");
		// Persist the customer
		em.persist(customer1);
		// Create 2 orders
//		Order order1 = new Order();
//		order1.setId(1);
//		order1.setAddress("1 Champs-Elysees, Paris, France");
//		Order order2 = new Order();
//		order2.setId(2);
//		order2.setAddress("99 Main Street, London, UK");
//		customer1.getOrders().add(order1);
//		order1.setCustomer(customer1);
//		customer1.getOrders().add(order2);
//		order2.setCustomer(customer1);
		return "OK";
	}

	@Override
	public String testUserInsert(){
		User u = new User();
		u.setFirstname("fghjk");
		u.setAdress("fghjk");
		u.setEmail("fghjk");
		u.setLastname("fghjk");
		u.setPseudo("eeee");

		em.persist(u);

		return "ok user";
	}

	@Override
	public String verifyInsert() {
		Customer c = findCustomer("Bat Man");
//		Collection<Order> orders = c.getOrders();
//		if (orders == null || orders.size() != 2) {
//			throw new RuntimeException(
//					"Unexpected number of orders: " + ((orders == null) ? "null" : "" + orders.size()));
//		}
		return "OK";
	}

	@Override
	public String testDelete(final Customer c) {
		// Merge the customer to the new persistence context
		Customer c0 = em.merge(c);
		// Delete all records.
		em.remove(c0);
		return "OK";
	}

	@Override
	public String verifyDelete() {
		Query q = em.createQuery("select c from Customer c");
		@SuppressWarnings("rawtypes")
		List results = q.getResultList();
		if (results == null || results.size() != 0) {
			throw new RuntimeException("Unexpected number of customers after delete results : " + results.size());
		}
		/*q = em.createQuery("select o from Order o");
		results = q.getResultList();
		if (results == null || results.size() != 0) {
			throw new RuntimeException("Unexpected number of orders after delete");
		}*/
		return "OK";
	}

	@Override
	public Customer findCustomer(final String name) {
		Query q = em.createQuery("select c from Customer c where c.name = :name");
		q.setParameter("name", name);
		return (Customer) q.getSingleResult();
	}
}
