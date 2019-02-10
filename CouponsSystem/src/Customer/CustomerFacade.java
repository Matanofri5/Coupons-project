package Customer;
import java.util.Set;

public class CustomerFacade {
	private CustomerDBDAO custDAO = new CustomerDBDAO();
	private Customer customer;

	public CustomerFacade(Customer C) {
		this.customer = C;
	}

	public CustomerFacade() {
	}

	public void insertCustomer(Customer customer) throws Exception {
		custDAO.insertCustomer(customer);
	}

	public void removeCustomer(long id) throws Exception {
		custDAO.removeCustomer(id);
	}

	public void updateCustomer(Customer customer, long newId, String newCust_name, String newPassword) throws Exception {
		customer.setId(newId);
		customer.setCust_name(newCust_name);
		customer.setPassword(newPassword);
		custDAO.updateCustomer(customer);
	}

	public Customer getCustomer() {
		return customer;
	}

	public Set<Customer> getAllCustomer() throws Exception {
//		 CustomerCompanyDBDAO cusDAO=new CustomerDBDAO();
		return custDAO.getAllCustomer();
	}

	public void dropTable () throws Exception{
		custDAO.dropTable();
	}
}