package Company;

import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import Coupon.CouponDAO;
import Coupon.CouponDBDAO;
import Coupon.CouponType;
import Customer.CustomerDAO;
import Company.CompanyDAO;
import CompanyCoupon.CompanyCoupon;
import CompanyCoupon.CompanyCouponDAO;
import CompanyCoupon.CompanyCouponDBDAO;
import Clients.ClientType;
import Clients.CouponClientFacade;
import Coupon.Coupon;
import MyExceptions.CompanyAlreadyExistsException;
import CustomerCoupon.CustomerCoupon;
import CustomerCoupon.CustomerCouponDAO;
import CustomerCoupon.CustomerCouponDBDAO;
import Main.Database;

/**
 * @author Linoy & Matan
 * @Description: Company Facade- login for Company
 */
public class CompanyFacade implements CouponClientFacade{
	private CompanyDAO companyDAO ;
	private CouponDAO couponDAO ;
	private CompanyCouponDAO companyCouponDAO ;
	private Company company;
	private long companyId;
	private CompanyCoupon companyCoupon;
	private Coupon coupon;

	
	/**
	 * @throws Exception 
	 * @partial CTOR 
	 */
	public CompanyFacade(CompanyDAO companyDAO, CouponDAO couponDAO, CompanyCouponDAO companyCouponDAO) throws Exception {
		this.companyDAO = new CompanyDBDAO();
		this.couponDAO = new CouponDBDAO();
		this.companyCouponDAO = new CompanyCouponDBDAO();
		}

	/**
	 * @throws Exception 
	 * @Empty CTOR
	 */
	public CompanyFacade() throws Exception {
		this.companyDAO = new CompanyDBDAO();
		this.couponDAO = new CouponDBDAO();
		this.companyCouponDAO = new CompanyCouponDBDAO();
	}

//	public void insertCompany(Company company) throws Exception {
//		companyDAO.insertCompany(company);
//	}
//
//	public void removeCompany(Company company) throws Exception {
//		companyDAO.removeCompany(company);
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
	public Set<Company> getAllCompany() throws Exception {
		System.out.println(companyDAO.getAllCompanys());
		return companyDAO.getAllCompanys();
	}

	public void dropTable() throws Exception {
		companyDAO.dropTable();
	}
	
	

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) throws Exception {
		return null;
	}
	
	public void addCoupon(Coupon coupon) throws Exception {
		try {
			Set<Coupon> coupons = couponDAO.getAllCoupons();
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
		} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
	
	public void removeCouponById(long couponId) throws Exception {
		companyDAO.removeCouponFromCompany(couponId);
		
	}	
	
	public void updateCoupon(Coupon coupon, long whatid, Date newEndDate, double newPrice)
			throws Exception {
		coupon.setId(whatid);
		coupon.setEndDate(newEndDate);
		coupon.setPrice(newPrice);
		
		couponDAO.updateCoupon(coupon);
	}
	
	public Company getCompany(long id) throws Exception {
		System.out.println(companyDAO.getCompany(id));
		return companyDAO.getCompany(id);
	}
	
	
	public Company getCouponById(long couponId) throws Exception {
		return companyDAO.getCompany(couponId);
	}
	
	public Set <CompanyCoupon> getAllCompanyCoupons() throws Exception{
		return companyCouponDAO.getAllCompanyCoupon();
	}
	
	
	public List<Coupon> getAllCompanyCoupon(Company company) throws Exception {
		long companyId= company.getId();
		List<Long> coupons = companyDAO.getAllCompanyCoupons(companyId);
		List<Coupon> allcoupons = new ArrayList<Coupon>();
		for (Long id : coupons) {
			allcoupons.add(couponDAO.getCoupon(id));
		}
		return allcoupons;
		

	}
	
	
	
	public List<Coupon> getCouponbyType(Company company, CouponType type) throws Exception{
		List<Coupon> coupons = getAllCompanyCoupon(company);
		List<Coupon> couponByType = new ArrayList<Coupon>();
		try {
			for (Coupon coupon : coupons) {
				if (coupon.getType().equals(type)) {
					couponByType.add(coupon);
				}}}
		catch (Exception e) {
			System.out.println(e);
		}
		return couponByType;
	}
	
//	public Set<Coupon> getAllCouponsByType (CouponType couponType) throws Exception{
////		company = companyDAO.getCompany(company.getId())
//		Set<Coupon> allCoupons = new HashSet<Coupon>();
//		Set<Coupon> coupons = companyDAO.getAllCompanyCoupons(companyId);
////		Set<Coupon> coupons = companyCouponDAO.getAllCompanyCoupon(companyId);
//		for (Coupon coupon : coupons) {
//			if (coupon.getType().equals(couponType)) {
//					allCoupons.add(coupon);
//			}
//		}	
//		return allCoupons;
//}

	
//	public Set<Coupon> getAllCouponsByType(Company company, CouponType couponType)throws Exception{
//		Set<Coupon> allCoupons = couponDAO.getAllCoupons();
//		for (Iterator<Coupon>iterator = allCoupons.iterator(); iterator.hasNext();) {
//			Coupon coupon = iterator.next();
//			if (coupon.getType() != couponType) {
//				iterator.remove();
//			}
//		}
//		return allCoupons;
//	}
	
	
//	public Set<Coupon> moshe() {
//		java.sql.Date inputDate = new java.sql.Date(1000);
//		
//		java.sql.Date mydate = java.sql.Date.valueOf(LocalDate.now());
//		if(inputDate.after(mydate)) {
//			System.out.println("Good");
//		}else {
//			System.out.println("bad");
//		}
//	}
	
	
//	public List<Coupon> getAllCouponsByType (CouponType couponType) throws Exception{
//		List<Long> coupons = companyDAO.getCouponId(this.companyCoupon.getCompanyId());
//		List<Coupon> allCoupons = new ArrayList<>();
//		for(Long couponsIds : coupons) {
//			allCoupons.addAll(couponDAO.getAllCouponsByType(couponType));
//		}
//		if (allCoupons.isEmpty()) {
//			throw new Exception ("Failed to get all coupons");
//		}
//		List<Coupon> newcoupons = allCoupons;
//		for (Coupon c : newcoupons) {
//			System.out.println(c);
//		}
//		return allCoupons;
//	}
	
	
}