package Company;
import java.util.Set;

public class CompanyFacade {
	private CompanyDBDAO compDAO = new CompanyDBDAO();
	private Company company;

	public CompanyFacade(Company C) {
		this.company = C;
	}

	public CompanyFacade() {
	}

	public void insertCompany(Company company) throws Exception {
		compDAO.insertCompany(company);
	}

	public void removeCompany(long id) throws Exception {
		compDAO.removeCompany(id);
	}

	public void updateCompany(Company company, long newId, String newComp_name, String newPassword, String newEmail) throws Exception {
		company.setId(newId);
		company.setComp_name(newComp_name);
		company.setPassword(newPassword);
		company.setEmail(newEmail);
		compDAO.updateCompany(company);
	}

	public Company getCompany() {
		return company;
	}

	public Set<Company> getAllCompany() throws Exception {
//		 CompanyDBDAO comDAO=new CompanyDBDAO();
		return compDAO.getAllCompany();
	}

	public void dropTable () throws Exception{
		compDAO.dropTable();
	}
}