package Clients;

import java.util.Set;

import Company.Company;
import Company.CompanyDAO;
import Company.CompanyDBDAO;
import Customer.Customer;
import Customer.CustomerDAO;
import Customer.CustomerDBDAO;
import Coupon.Coupon;
import Coupon.CouponDAO;
import Coupon.CouponDBDAO;

public class AdminFacade implements CouponClientFacade {

	private static final String adminName = "admin";
	private static final String adminPassword = "1234";
	private CompanyDAO companyDAO;
	private CustomerDAO customerDAO;
	private CouponDAO couponDAO;

	public AdminFacade() throws Exception {
		this.companyDAO = new CompanyDBDAO();
		this.customerDAO = new CustomerDBDAO();
		this.couponDAO = new CouponDBDAO();
	}

	@Override
	public CouponClientFacade login(String name, String password) throws Exception {
		if (name.equals(adminName) && password.equals(adminPassword)) {
			return this;
		} else {
			return null;
		}
	}

	public void addCompany(Company company) throws Exception {
		String companyName = company.getCompanyName();
		//for (company.getCompanyName()!= (companyDAO.getAllCompany().getClass())
		//if ((companyDAO.getAllCompany()) != company) {
		if(company.getCompanyName()!= companyDAO.getAllCompany().getClass().getName()){
			try {
				companyDAO.insertCompany(company);
			} catch (Exception e) {
				System.out.println("insert company failed");
			}
		} else {
			System.out.println("this company name exist " + companyName);
		}
	}
	
	public void removeCompany (long companyId) throws Exception {
		
		companyDAO.removeCompany(companyId);
		couponDAO.removeCoupon(companyId);
		
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

	public void addCustomer (Customer customer) {
		
	}
	
	public void removeCustomer (Customer customer) {
		customerDAO.removeCustomer(id);
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
