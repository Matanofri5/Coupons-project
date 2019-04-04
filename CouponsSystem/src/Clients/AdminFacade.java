package Clients;

import java.util.Iterator;
import java.util.Set;
import Company.Company;
import Company.CompanyDAO;
import Company.CompanyDBDAO;
import CompanyCoupon.CompanyCoupon;
import CompanyCoupon.CompanyCouponDAO;
import CompanyCoupon.CompanyCouponDBDAO;
import Customer.Customer;
import Customer.CustomerDAO;
import Customer.CustomerDBDAO;
import Coupon.Coupon;
import Coupon.CouponDAO;
import Coupon.CouponDBDAO;
import CustomerCoupon.CustomerCoupon;
import CustomerCoupon.CustomerCouponDAO;
import CustomerCoupon.CustomerCouponDBDAO;
import MyExceptions.CompanyAlreadyExists;

public class AdminFacade implements CouponClientFacade {

	private static final String adminName = "admin";
	private static final String adminPassword = "1234";
	private CompanyDAO companyDAO;
	private CustomerDAO customerDAO;
	private CouponDAO couponDAO;
	private CustomerCouponDAO customerCouponDAO;
	private CustomerCouponDBDAO customerCouponDBDAO;
	private CompanyCouponDBDAO companyCouponDBDAO;

	public AdminFacade() throws Exception {
		this.companyDAO = new CompanyDBDAO();
		this.customerDAO = new CustomerDBDAO();
		this.couponDAO = new CouponDBDAO();
		this.customerCouponDBDAO = new CustomerCouponDBDAO();
		this.companyCouponDBDAO = new CompanyCouponDBDAO();
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
			Set<Company> companies = companyDAO.getAllCompanys();
			Iterator<Company> i = companies.iterator();
			
			while (i.hasNext()) {
				Company current = i.next();
				if (company.getCompanyName().equals(current.getCompanyName())) {
					throw new CompanyAlreadyExists("This Company already exsist !");	
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
		companyCouponDBDAO.removeCompanyCoupon(companyId, couponId);
		companyDAO.removeCompany(companyId);
//		customerDAO.removeCustomer(customerId);   it"s also need to be removed ?
//		couponDAO.removeCoupon(couponId);   it's also need to be removed ?
//		customerCouponDAO.removeCustomerCoupon(customerId, couponId);
		
	}
	
	public void updateCompany (Company company, String newPassword, String newEmail) throws Exception {
		company.setPassword(newPassword);
		company.setEmail(newEmail);
		
		companyDAO.updateCompany(company);
	}
	
	public Company getCompany (long id) throws Exception {
		return companyDAO.getCompany(id);
	}
	
	public Set<Company> getAllCompanys () throws Exception{
		return companyDAO.getAllCompanys();
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
	
	public void removeCustomer (long customerId,long couponId) throws Exception {
		customerCouponDBDAO.removeCustomerCoupon(customerId, couponId);
		customerDAO.removeCustomer(customerId);
//		couponDAO.removeCoupon(couponId);   it's also need to be removed ?

	}
	
	public void updateCustomer (Customer customer,long whatId, String newPassword) throws Exception {
		customer.setId(whatId);
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
