package Company;
import java.util.Set;

public interface CompanyDAO {
	
	public void insertCompany(Company company) throws Exception;

	public void removeCompany(long id) throws Exception;

	public void updateCompany(Company company) throws Exception;

	public Company getCompany(long id) throws Exception;

	public Set<Company> getAllCompany() throws Exception;

	public void dropTable() throws Exception;

}