import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ConnectionDatabase;

public class ClassTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Company company = new Company.Builder("jo").build();
Computer computer = new Computer.Builder(12l, "apple").dateIntroduced(LocalDate.parse("2012-12-12")).company(company).build();
System.out.println(computer);

try{
	Connection connect = ConnectionDatabase.getInstance();
	Statement state = connect.createStatement();
	state.close();
	ConnectionDatabase.closeConnection();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


	}

}
