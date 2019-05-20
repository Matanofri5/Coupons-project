package Coupon;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.apache.derby.iapi.db.Factory;

import Main.ConnectionPool;
import Main.Database;

/**
 * @Author - Linoy & Matan
 * @Description: In this class we have to implement all the method in CouponDAO
 * every method getting connection to DB and close when finished, and run an SQL
 * Query by prepareStatement
 */
public class CouponDBDAO implements CouponDAO {
	
	/**
	 * Data Members
	 */
	private ConnectionPool connectionPool;
	private Connection con;

	/**
	 * @throws Exception 
	 * @Empty CTOR
	 */
	public CouponDBDAO() throws Exception {
		try {
			this.connectionPool = ConnectionPool.getInstance();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
	}
	
	/**
	 * @insert
	 * this method Receives data about a new Coupon, And creates it in a table of Coupons.
	 *  @param Coupon object
	 *  @throws Exception
	 */
	@Override
	public void insertCoupon(Coupon coupon) throws Exception {
		try {
			con = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		String sql = "INSERT INTO Coupon (title,startDate,endDate,amount,message,price,image,type)  VALUES(?,?,?,?,?,?,?,?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, coupon.getTitle());
			pstmt.setDate(2, (Date) coupon.getStartDate());
			pstmt.setDate(3, (Date) coupon.getEndDate());
			pstmt.setInt(4, coupon.getAmount());
			pstmt.setString(5, coupon.getMessage());
			pstmt.setDouble(6, coupon.getPrice());
			pstmt.setString(7, coupon.getImage());
			pstmt.setString(8, coupon.getType().name());
			pstmt.executeUpdate();

//			System.out.println("Coupon insert success :D  " + coupon.toString());
		} catch (SQLException e) {
			System.err.println("Coupon insert failed :( ");
			throw new Exception(e.getMessage());
		} finally {
			con.close();
		}
	}

	/**
	 * @remove
	 * this method delete 1 object of Coupon by Coupon id, from Coupons table.
	 *  @param long id
	 *  @throws Exception
	 */
	@Override
	public void removeCoupon(Coupon coupon) throws Exception {
		try {
			con = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		String sql = "DELETE FROM Coupon WHERE id=?";
		try (PreparedStatement pstm1 = con.prepareStatement(sql);) {
			con.setAutoCommit(false);
			pstm1.setLong(1, coupon.getId());
			pstm1.executeUpdate();
			con.commit();
//			System.out.println("remove Coupon success :D ");
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.err.println("remove Coupon failed :( ");
				throw new Exception("Database error");
			}
		} finally {
			con.close();
		}
	}

	/**
	 * @update
	 * this method update 1 object of Coupon, from Coupons table.
	 *  @param Coupon object
	 *  @throws Exception
	 */
	@Override
	public void updateCoupon(Coupon coupon) throws Exception {
		try {
			con = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		try {
			String sql ="UPDATE Coupon SET TITLE=?, STARTDATE=?, ENDDATE=?, AMOUNT=?, MESSAGE=?, PRICE=?, IMAGE=?, TYPE=? WHERE ID=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, coupon.getTitle());
			pstmt.setDate(2,(Date) coupon.getStartDate());
			pstmt.setDate(3,(Date) coupon.getEndDate());
			pstmt.setInt(4, coupon.getAmount());
			pstmt.setString(5, coupon.getMessage());
			pstmt.setDouble(6, coupon.getPrice());
			pstmt.setString(7, coupon.getImage());
			pstmt.setString(8, coupon.getType().toString());
			pstmt.setLong(9, coupon.getId());

			
			pstmt.executeUpdate();
			pstmt.close();
//			System.out.println("Coupon " + coupon.getId()+ " updated succesfully");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("update Coupon failed :( ");
		}
	}

	/**
	 * @get1
	 * this method get and print 1 object of Coupon by Coupon id, from Coupons table.
	 *  @param long id
	 *  @return Coupon object
	 *  @throws Exception
	 */
	@Override
	public Coupon getCoupon(long id) throws Exception {
		Coupon coupon = new Coupon();
		try {
			con = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		//		java.sql.Statement stm = null;

		try (Statement stm = con.createStatement()){
			String sql = "SELECT * FROM Coupon WHERE ID=" + id;
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
			coupon.setId(rs.getLong(1));
			coupon.setTitle(rs.getString(2));
			coupon.setStartDate((Date) rs.getDate(3));
			coupon.setEndDate((Date) rs.getDate(4));
			coupon.setAmount(rs.getInt(5));
			coupon.setMessage(rs.getString(6));
			coupon.setPrice(rs.getDouble(7));
			coupon.setImage(rs.getString(8));
			CouponType type = CouponType.valueOf(rs.getString(9));
			coupon.setType(type);
			}
		} catch (SQLException e) {
			System.err.println("Get coupon failed :(");
			throw new Exception(e.getMessage());
		} finally {
			con.close();
		}
		return coupon;
	}

	/**
	 * @getAll
	 * this method get all and print objects of coupons, from coupon table.
	 *  @return coupon list object
	 *  @throws Exception
	 */
	@Override
	public Set<Coupon> getAllCoupons() throws Exception {
		Coupon coupon;
		Set<Coupon> coupons = new HashSet<Coupon>();
		try {
			con = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		java.sql.Statement stm = null;
		try {
			stm = con.createStatement();
			String sql = "SELECT * FROM Coupon";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				coupon = new Coupon();
				coupon.setId(rs.getLong(1));
				coupon.setTitle(rs.getString(2));
				coupon.setEndDate((Date) rs.getDate(3));
				coupon.setStartDate((Date) rs.getDate(4));
				coupon.setAmount(rs.getInt(5));
				coupon.setMessage(rs.getString(6));
				coupon.setPrice(rs.getDouble(7));
				coupon.setImage(rs.getString(8));
				CouponType type = CouponType.valueOf(rs.getString(9));
				coupon.setType(type);
				coupons.add(coupon);
			}
		} catch (SQLException e) {
			System.err.println("Get all coupon failed :( ");
			throw new Exception(e.getMessage());
		} finally {
			con.close();
		}
		return coupons;
	}
	
	/**
	 * @getAll
	 * this method get all and print objects of coupons, from coupon table, only by Type
	 *  @return coupon list object
	 *  @throws Exception
	 */
	@Override
	public Set<Coupon> getAllCouponsByType(CouponType couponType) throws Exception {
		try {
			con = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		Set<Coupon> CouponByType = new HashSet<Coupon>();
		
			try {
				String sql = "SELECT * FROM coupon WHERE type=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, couponType.toString());
				ResultSet rs = pstmt.executeQuery();
				Coupon coupon = null;
				while (rs.next()) {
					coupon = new Coupon();
					coupon.setId(rs.getLong(1));
					coupon.setTitle(rs.getString(2));
					coupon.setStartDate(rs.getDate(3));
					coupon.setEndDate(rs.getDate(4));
					coupon.setAmount(rs.getInt(5));
					coupon.setMessage(rs.getString(6));
					coupon.setPrice(rs.getDouble(7));
					coupon.setImage(rs.getString(8));
					coupon.setType(CouponType.valueOf(rs.getString(9)));
					
					CouponByType.add(coupon);
				}
				pstmt.close();
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
			finally {
					con.close();
			}
		return CouponByType;
	}
	
	/**
	 * @dropTable
	 * this method delete all the table of coupons.
	 *  @throws Exception
	 */
	@Override
	public void dropTable() throws Exception {
		try {
			con = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		try {
			String sql = "DROP TABLE Coupon";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
//			System.out.println("drop Coupon Table success!! :D ");

		} catch (SQLException ex) {
			System.err.println("MMMMMMM....dropCouponTableEXCEPTION");
			throw new Exception(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException ex) {
				System.err.println("the connection cannot closed :( " + ex.getMessage());
			}
		}
	}
	
	
	
}
