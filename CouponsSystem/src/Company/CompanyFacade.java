package Company;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import Coupon.CouponDAO;
import Coupon.CouponDBDAO;
import Coupon.CouponType;
import Coupon.DateUtils;
import Main.CouponSystem;
import Company.CompanyDAO;
import Clients.ClientType;
import Clients.CouponClientFacade;
import Coupon.Coupon;


/**
 * @author Linoy & Matan
 * @Description: Company Facade- login for Company
 */
public class CompanyFacade implements CouponClientFacade{
	private CompanyDAO companyDAO ;
	private CouponDAO couponDAO ;


	/**
	 * @throws Exception 
	 * @partial CTOR 
	 */
	public CompanyFacade(CompanyDAO companyDAO, CouponDAO couponDAO) throws Exception {
		this.companyDAO = new CompanyDBDAO();
		this.couponDAO = new CouponDBDAO();
		}

	/**
	 * @throws Exception 
	 * @Empty CTOR
	 */
	public CompanyFacade() throws Exception {
		this.companyDAO = new CompanyDBDAO();
		this.couponDAO = new CouponDBDAO();
	}
	
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) throws Exception {
		return null;
	}
	
	/**
	 * this method adds a coupon to company table and to companyCoupon table,
	 * but! - A different name. If the name exists - throws exception.
	 * @param coupon
	 * @throws Exception
	 */
	public void createCoupon(Company company, Coupon coupon) throws Exception {
		try {
			Set<Coupon> coupons = couponDAO.getAllCoupons();
			Iterator<Coupon> i = coupons.iterator();
			while (i.hasNext()) {
				Coupon current = i.next();
				if (coupon.getTitle().equals(current.getTitle())) {
					throw new Exception("This coupon with this title already exists");	
				}
			}
			if (!i.hasNext()) {
				couponDAO.insertCoupon(coupon);
				companyDAO.companyCreateCoupon(company, coupon);

			} 
		} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
	
	/**
	 * @removeCouponFromCompany this method delete coupon of Company by DELETE query from two tables.
	 * @param long couponId
	 * @throws Exception
	 */
	public void removeCouponById(long couponId) throws Exception {
		companyDAO.removeCouponFromCompany(couponId);
		
	}	
	
	/**
	 * @update this method update 1 object of coupon, from coupon table.
	 * @param coupon object, newEndDate, newPrice
	 */
	public void updateCoupon(Coupon coupon, Date newEndDate, double newPrice)
			throws Exception {
		coupon.setEndDate(newEndDate);
		coupon.setPrice(newPrice);
		
		couponDAO.updateCoupon(coupon);
	}
	
	/**
	 * @get1 this method get and print 1 object of company by company id, from companies table.
	 * @param long id
	 * @return company object
	 * @throws Exception
	 */
	public Company getCompany(long id) throws Exception {
		return companyDAO.getCompany(id);
	}
	
	
	public Company getCouponById(long couponId) throws Exception {
		return companyDAO.getCompany(couponId);
	}
	
	/**
	 * @getAll CompanyCoupon by company id this method get and print objects of coupons by
	 * company id, from CompanyCoupon table.
	 * @param long companyId
	 * @return list of coupon id
	 * @throws Exception
	 */
	public List<Long> getAllCompanyCoupons(long companyId) throws Exception{
		return companyDAO.getAllCompanyCoupons(companyId);
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
	
	
	public List<Coupon> getCouponByPrice (Company company, double price) throws Exception{
		List<Coupon> coupons = getAllCompanyCoupon(company);
		List<Coupon> couponByPrice = new ArrayList<Coupon>();
		try {
			for (Coupon coupon : coupons) {
				if (coupon.getPrice() <= price) {
					couponByPrice.add(coupon);
				}}}
		catch (Exception e) {
			System.out.println(e);
		}
		return couponByPrice;
	}
	
	public List<Coupon> getCouponByDate (Company company, Date endDate) throws Exception{
		List<Coupon> coupons = getAllCompanyCoupon(company);
		List<Coupon> couponByDate = new ArrayList<Coupon>();
		try {
			for (Coupon coupon : coupons) {
				if (coupon.getEndDate().equals(endDate)|| coupon.getEndDate().before(endDate)) {
					couponByDate.add(coupon);
				}}}
		catch (Exception e) {
			System.out.println(e);
		}
		return couponByDate;
	}
}