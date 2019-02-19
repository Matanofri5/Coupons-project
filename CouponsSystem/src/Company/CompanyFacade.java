package Company;

import java.util.Set;

public class CompanyFacade {
	private CompanyDBDAO companyDBDAO = new CompanyDBDAO();
	private Company company;

	public CompanyFacade(Company company) {
		this.company = company;
	}

	public CompanyFacade() {
	}

	public void insertCompany(Company company) throws Exception {
		companyDBDAO.insertCompany(company);
	}

	public void removeCompany(long id) throws Exception {
		companyDBDAO.removeCompany(id);
	}

	public void updateCompany(Company company, long newId, String newCompanyName, String newPassword, String newEmail) throws Exception {
		company.setId(newId);
		company.setCompanyName(newCompanyName);
		company.setPassword(newPassword);
		company.setEmail(newEmail);
		companyDBDAO.updateCompany(company);
	}

	public Company getCompany(long id) throws Exception {
		return companyDBDAO.getCompany(id);
	}

	public Set<Company> getAllCompany() throws Exception {
//		 CompanyDBDAO comDAO=new CompanyDBDAO();
		return companyDBDAO.getAllCompany();
	}

	public void dropTable () throws Exception{
		companyDBDAO.dropTable();
	}
}