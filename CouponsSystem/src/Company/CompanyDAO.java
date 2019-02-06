package Company;
import java.util.Set;

public interface CompanyDAO {
	void insertCompany(Company company) throws Exception;

	void removeCompany(long id) throws Exception;

    void updateCompany(Company company) throws Exception;

	Company getCompany(long id) throws Exception;

	Set<Company> getAllCompany() throws Exception;

	void dropTable() throws Exception;

}