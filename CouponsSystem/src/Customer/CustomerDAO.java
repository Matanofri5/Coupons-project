package Customer;

import java.util.Set;

public interface CustomerDAO {
	public void insertCustomer(Customer Customer) throws Exception;

	public void removeCustomer(long id) throws Exception;

	public void updateCustomer(Customer Customer) throws Exception;

	public Customer getCustomer(long id) throws Exception;

	public Set<Customer> getAllCustomer() throws Exception;

	public void dropTable() throws Exception;

}