package Clients;

import java.util.Iterator;
import java.util.Set;
import Company.Company;
import Company.CompanyDAO;
import Company.CompanyDBDAO;
import Customer.Customer;
import Customer.CustomerDAO;
import Customer.CustomerDBDAO;
import MyExceptions.CompanyAlreadyExists;
import Coupon.Coupon;
import Coupon.CouponDAO;
import Coupon.CouponDBDAO;
import CustomerCoupon.CustomerCoupon;
import CustomerCoupon.CustomerCouponDAO;
import CustomerCoupon.CustomerCouponDBDAO;


public class AdminFacade implements CouponClientFacade {

	private static final String adminName = "admin";
	private static final String adminPassword = "1234";
	private CompanyDAO companyDAO;
	private CustomerDAO customerDAO;
	private CouponDAO couponDAO;
	private CustomerCouponDAO customerCouponDAO;

	public AdminFacade() throws Exception {
		this.companyDAO = new CompanyDBDAO();
		this.customerDAO = new CustomerDBDAO();
		this.couponDAO = new CouponDBDAO();
	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) throws Exception {
		if (name.equals(adminName) && password.equals(adminPassword)) {
			return this;
		} else {
			return null;
		}
	}

	public void addCompany(Company company) throws Exception {
		try {
			Set<Company> companies = companyDAO.getAllCompany();
			Iterator<Company> i = companies.iterator();
			
			while (i.hasNext()) {
				Company current = i.next();
				if (company.getCompanyName().equals(current.getCompanyName())) {
					throw new CompanyAlreadyExists(company);	
				}
			}
			if (!i.hasNext()) {
				companyDAO.insertCompany(company);
				System.out.println("Admin added new company: " + company.getId());
			} 
		}
			catch (CompanyAlreadyExists e) {
				System.out.println(e.getMessage());
			}
			catch (Exception e) {
				throw new Exception("Admin failed to add company");
			}
	}
	
	public void removeCompany (long companyId, long couponId, long customerId) throws Exception {
		
		companyDAO.removeCompany(companyId);
		couponDAO.removeCoupon(couponId);
		customerCouponDAO.removeCustomerCoupon(customerId, couponId);
		
	}
	
	public void updateCompany (Company company, String newPassword, String newEmail) throws Exception {
		company.setPassword(newPassword);
		company.setEmail(newEmail);
		
		companyDAO.updateCompany(company);
	}
	
	public Company getCompany (long id) throws Exception {
		return companyDAO.getCompany(id);
	}
	
	public Set<Company> getAllCompany () throws Exception{
		return companyDAO.getAllCompany();
	}

	public void addCustomer (Customer customer) throws Exception {
		try {
			Set<Customer> customers = customerDAO.getAllCustomer();
			Iterator<Customer> i = customers.iterator();
			
			while (i.hasNext()) {
				Customer current = i.next();
				if (customer.getCustomerName().equals(current.getCustomerName())) {
					throw new Exception("this customer already exists");	
				}
			}
			if (!i.hasNext()) {
				customerDAO.insertCustomer(customer);
				System.out.println("Admin added new customer: " + customer.getId());
			} 
		}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
	
	public void removeCustomer (long customerId, long couponId) throws Exception {
		customerDAO.removeCustomer(customerId);
		couponDAO.removeCoupon(couponId);
	}
	
	public void updateCustomer (Customer customer, String newPassword) throws Exception {
		customer.setPassword(newPassword);
		customerDAO.updateCustomer(customer);
	}
	
	public Set<Customer>getAllCustomer () throws Exception{
		return customerDAO.getAllCustomer();
	}
	
	public Customer getCustomer (long id) throws Exception {
		return customerDAO.getCustomer(id);
	}
}
