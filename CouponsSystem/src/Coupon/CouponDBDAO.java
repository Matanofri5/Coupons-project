package Coupon;

    import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.HashSet;
	import java.util.Set;
	import Main.*;
	import Main.Database;

	public class CouponDBDAO implements CouponDAO {
		Connection con;

		@Override
		public void insertCoupon(Coupon coupon) throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			String sql = "INSERT INTO Coupon (Title,Start_date,End_date,Amount,Messege,Price,Image,type)  VALUES(?,?,?,?,?,?,?,?)";
			try (PreparedStatement pstmt = con.prepareStatement(sql)) {

				pstmt.setString(1, coupon.getTitle());
				pstmt.setDate(2, (Date) coupon.getStart_date());
                pstmt.setDate(3, (Date) coupon.getEnd_date());
				pstmt.setLong(4, coupon.getAmount());
				pstmt.setString(5, coupon.getMessage());
				pstmt.setDouble(6, coupon.getPrice());
				pstmt.setString(7, coupon.getImage());
                pstmt.setString(8, coupon.getType().name());                
				pstmt.executeUpdate();
				System.out.println("Coupon insert" +" " + coupon.toString());
			} catch (SQLException e) {
				throw new Exception("Coupon insert failed");
			} finally {
				con.close();
			}
		}

		@Override
		public void removeCoupon(Coupon coupon) throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			String pre1 = "DELETE FROM Coupon WHERE id=?";

			try (PreparedStatement pstm1 = con.prepareStatement(pre1);) {
				con.setAutoCommit(false);
				pstm1.setLong(1, coupon.getId());
				pstm1.executeUpdate();
				con.commit();
			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					throw new Exception("Database error");
				}
				throw new Exception("failed to remove customer");
			} finally {
				con.close();
			}
		}

		@Override
		public void updateCoupon(Coupon coupon) throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			try (Statement stm = con.createStatement()) {
				String sql = "UPDATE Coupon " + " SET Title='" + coupon.getTitle() + "', Start_date='" + coupon.getStart_date() +"', End_date='" + coupon.getEnd_date()
						+ "', Amount='" + coupon.getAmount() +"', Message='" + coupon.getMessage() + "', Price='" + coupon.getPrice()
						+ "', Image='" + coupon.getImage() + "' WHERE ID=" + coupon.getId();
				stm.executeUpdate(sql);
			} catch (SQLException e) {
				throw new Exception("update Coupon failed");
			}
		}

		@Override
		public Coupon getCoupon(long id) throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			Coupon coupon = new Coupon();
			try (Statement stm = con.createStatement()) {
				String sql = "SELECT * FROM Coupon WHERE ID=" + id;
				ResultSet rs = stm.executeQuery(sql);
				rs.next();
				coupon.setId(rs.getLong(1));
				coupon.setTitle(rs.getString(2));
				coupon.setStart_date(rs.getDate(3));
				coupon.setEnd_date(rs.getDate(4));
				coupon.setAmount(rs.getLong(5));
				coupon.setPrice(rs.getDouble(6));
				coupon.setImage(rs.getString(7));
				CouponType type = null ;
				switch (type.getClass().getName()) {
				case "food":
					type=CouponType.FOOD;
					break;
				case "Resturans":
					type=CouponType.RESTURANTS;
					break;
				case "Electricity":
					type=CouponType.ELECTRICITY;
					break;
				case "Health":
					type=CouponType.HEALTH;
					break;
				case "Sports":
					type=CouponType.SPORTS;
					break;
				case "Camping":
					type=CouponType.CAMPING;
					break;
				case "Traveling":
					type=CouponType.TRAVELING;
					break;
				default:
					System.out.println("Coupon not existent");
						break;
		}	} catch (SQLException e) {
				throw new Exception("unable to get coupon data");
			} finally {
					con.close();
			}
				return coupon;
		}
		
		@Override
		public synchronized Set<Coupon> getAllCoupon() throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			Set<Coupon> set = new HashSet<>();
			String sql = "SELECT * FROM Coupon";
			try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
				while (rs.next()) {
					long Id = rs.getLong(1);
					String Title = rs.getString(2);
					Date Start_date = rs.getDate(3);
					Date End_date = rs.getDate(4);
//					Integer Amount = rs.getLong(5);
					String Message = rs.getString(6);
					Double Price = rs.getDouble(7);
					String Image = rs.getString(8);
					String CouponType = rs.getString(9);
		
			}
			} catch (SQLException e) {
				System.out.println(e);
				throw new Exception("cannot get Coupon data");
			} finally {
				con.close();
			}
			return set;
		}

		@Override
		public void dropTable() throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			try {
				String sql =  "DROP TABLE Coupon";
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
	}

