package Customer;
import java.util.Set;

public class CustomerFacade {
	private CustomerDBDAO custDAO = new CustomerDBDAO();
	private Customer customer;

	public CustomerFacade(Customer customer) {
		this.customer = customer;
	}

	public CustomerFacade() {
	}

	public void insertCustomer(Customer customer) throws Exception {
		custDAO.insertCustomer(customer);
	}

	public void removeCustomer(long id) throws Exception {
		custDAO.removeCustomer(id);
	}

	public void updateCustomer(Customer customer, long newid, String newcustomerName, String newpassword) throws Exception {
		customer.setId(newid);
		customer.setCustomerName(newcustomerName);
		customer.setPassword(newpassword);
		custDAO.updateCustomer(customer);
	}

	public Customer getCustomer(long id) throws Exception {
		return custDAO.getCustomer(id);
	}

	public Set<Customer> getAllCustomer() throws Exception {
//		 CustomerCompanyDBDAO cusDAO=new CustomerDBDAO();
		return custDAO.getAllCustomer();
	}

	public void dropTable () throws Exception{
		custDAO.dropTable();
	}
}