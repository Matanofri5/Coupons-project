package Company;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import Coupon.CouponDAO;
import Coupon.CouponDBDAO;
import Coupon.CouponType;
import Clients.ClientType;
import Clients.CouponClientFacade;
import Coupon.Coupon;
import MyExceptions.CompanyAlreadyExists;
import CustomerCoupon.CustomerCoupon;
import CustomerCoupon.CustomerCouponDAO;
import CustomerCoupon.CustomerCouponDBDAO;

public class CompanyFacade implements CouponClientFacade{
	private CompanyDBDAO companyDBDAO = new CompanyDBDAO();
	private Company company;
	private CouponDAO couponDAO;
	private CustomerCouponDAO customerCouponDAO;
	private Coupon coupon;
	
	public CompanyFacade(Company company) {
		this.company = company;
	}

	public CompanyFacade() {
	}

//	public void insertCompany(Company company) throws Exception {
//		companyDBDAO.insertCompany(company);
//	}
//
//	public void removeCompany(long id) throws Exception {
//		companyDBDAO.removeCompany(id);
//	}
//
//	public void updateCompany(Company company, long newId, String newCompanyName, String newPassword, String newEmail)
//			throws Exception {
//		company.setId(newId);
//		company.setCompanyName(newCompanyName);
//		company.setPassword(newPassword);
//		company.setEmail(newEmail);
//		companyDBDAO.updateCompany(company);
//	}
//
//	public Company getCompany(long id) throws Exception {
//		return companyDBDAO.getCompany(id);
//	}
//
//	public Set<Company> getAllCompany() throws Exception {
//		// CompanyDBDAO comDAO=new CompanyDBDAO();
//		return companyDBDAO.getAllCompany();
//	}
//
//	public void dropTable() throws Exception {
//		companyDBDAO.dropTable();
//	}
	

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addCoupon(Coupon coupon) throws Exception {
		try {
			Set<Coupon> coupons = couponDAO.getAllCoupon();
			Iterator<Coupon> i = coupons.iterator();
			
			while (i.hasNext()) {
				Coupon current = i.next();
				if (coupon.getTitle().equals(current.getTitle())) {
					throw new Exception("this coupon already exists");	
				}
			}
			if (!i.hasNext()) {
				couponDAO.insertCoupon(coupon);
				System.out.println("company added new coupon: " + coupon.getId());
			} 
		}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
	
	public void removeCouponById(long customerId, long couponId) throws Exception {
		couponDAO.removeCoupon(couponId);
		customerCouponDAO.removeCustomerCoupon(customerId, couponId);
	}	
	
	public void updateCoupon(Date newEndDate, double newPrice) {
		coupon.setEndDate(newEndDate);
		coupon.setPrice(newPrice);
	}
	
	public Company getCouponById(long couponId) throws Exception {
		return companyDBDAO.getCompany(couponId);
	}
	
	public Set<Coupon> getAllCoupon() throws Exception {
		return couponDAO.getAllCoupon();
	}
	
	public Set<Coupon> getCouponsByType(CouponType couponType){
		return null;// couponDAO.get
	}
	public Set<Coupon> moshe() {
		java.sql.Date inputDate = new java.sql.Date(1000);
		
		java.sql.Date mydate = java.sql.Date.valueOf(LocalDate.now());
		if(inputDate.after(mydate)) {
			System.out.println("Good");
		}else {
			System.out.println("bad");
		}
	}
}