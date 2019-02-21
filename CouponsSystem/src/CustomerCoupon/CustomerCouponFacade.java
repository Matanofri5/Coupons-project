package CustomerCoupon;

public class CustomerCouponFacade {

<<<<<<< HEAD
		private CustomerCouponDBDAO customerCouponDBDAO = new CustomerCouponDBDAO();
//		private long customerId;
//		private long couponId;
		private CustomerCoupon customerCoupon;

		public CustomerCouponFacade(CustomerCoupon customerCoupon) {
			this.customerCoupon = customerCoupon;
		}

		public CustomerCouponFacade() {
		}

		public void insertCustomerCoupon(CustomerCoupon customerCoupon) throws Exception {
			customerCouponDBDAO.insertCustomerCoupon(customerCoupon);
		}

		public void removeCustomerCoupon(long id) throws Exception {
			customerCouponDBDAO.removeCustomerCoupon(id);
		}

		public void updateCustomerCoupon(CustomerCoupon customerCoupon, long newcustomerId, long newcouponId ) throws Exception {
			customerCoupon.setCustomerId(newcustomerId);
			customerCoupon.setCouponId(newcouponId);
			
			customerCouponDBDAO.updateCustomerCoupon(customerCoupon);
		}

		public CustomerCoupon getCustomerCoupon(long id) throws Exception {
			return customerCouponDBDAO.getCustomerCoupon(id);
		}

		public Set<CustomerCoupon> getAllCustomerCoupon() throws Exception {
//			 CustomerCouponDBDAO customerCouponDAO=new CustomerCouponDBDAO();
			return customerCouponDBDAO.getAllCustomerCoupon();
		}

		public void dropTable () throws Exception{
			customerCouponDBDAO.dropTable();
		}
	}

=======
}
>>>>>>> parent of 7c38477... customer coupon table finished !
