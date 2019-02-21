package CustomerCoupon;

public class CustomerCouponDBDAO {

<<<<<<< HEAD
public class CustomerCouponDBDAO implements CustomerCouponDAO{
	Connection con;

	@Override
	public void insertCustomerCoupon(CustomerCoupon customerCoupon) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		String sql = "INSERT INTO CustomerCoupon (customerId,couponId)  VALUES(?,?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            
			
			pstmt.setLong(1, customerCoupon.getCustomerId());
			pstmt.setLong(2, customerCoupon.getCouponId());

			pstmt.executeUpdate();
			
			
			System.out.println("CustomerCoupon " + "successefully inserted :) !!!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("CustomerCoupon insert failed");
		} finally {
			con.close();
		}
	}

	@Override
	public void removeCustomerCoupon(long id) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		String pre1 = "DELETE FROM CustomerCoupon WHERE customerId=? and couponId=?";

		try (PreparedStatement pstm1 = con.prepareStatement(pre1);) {
			con.setAutoCommit(false);
			pstm1.setLong(1, id);
			pstm1.executeUpdate();
			con.commit();
			System.out.println("CustomerCoupon " + "removed seccessfully  :) !!!");
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new Exception("Database error");
			}
			throw new Exception("failed to remove CustomerCoupon");
		} finally {
			con.close();
		}
	}

	@Override
	public void updateCustomerCoupon(CustomerCoupon customerCoupon) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		try (Statement stm = con.createStatement()) {
		String sql = "UPDATE CustomerCoupon "
		+ " SET companyId='" +customerCoupon.getCustomerId()
		+ "', couponId='" + customerCoupon.getCouponId()
		+ "' WHERE id=" ;            //by what id ?????????
			
		System.out.println("CustomerCoupon " + "updated successfully !!! :)");
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("update CustomerCoupon failed");
		}
	}

	@Override
	public CustomerCoupon getCustomerCoupon(long id) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		CustomerCoupon customerCoupon = new CustomerCoupon();
		try (Statement stm = con.createStatement()) {
			String sql = "SELECT * FROM CustomerCoupon WHERE ID=" + id;
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
			customerCoupon.setCustomerId(1);
			customerCoupon.setCouponId(2);
			
			}
        
		} catch (SQLException e) {
			throw new Exception("unable to get CustomerCoupon data");
		} finally {	
			con.close();	
			
		}
		return customerCoupon;
	}

	@Override
	public Set<CustomerCoupon> getAllCustomerCoupon() throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		Set<CustomerCoupon> set = new HashSet<>();		
		try {
			Statement stm = con.createStatement();
			String sql = "SELECT * FROM CustomerCoupon";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				
				long customerId = rs.getLong(1);
				long couponId = rs.getLong(2);
				
                
				set.add(new CustomerCoupon(customerId, couponId));
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new Exception("cannot get CustomerCoupon data");
		} finally {
			con.close();
		}
		return set;
	}

	@Override
	public void dropTable() throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		try {
			String sql =  "DROP TABLE CustomerCoupon";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("drop Table success!! :D ");

		}
		catch (SQLException ex) {
			System.err.println("MMMMMMM....dropTableEXCEPTION");
			throw new Exception(ex.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException ex) {
				System.err.println("the connection cannot closed :( "+ ex.getMessage());
			}
		}
		
	
	}
=======
>>>>>>> parent of 7c38477... customer coupon table finished !
}
