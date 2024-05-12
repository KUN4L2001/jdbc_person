package jdbc_person_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.spec.PSource;

public class PersonCRUD {

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/person_practice","root","root");
	}
	
	public int signUp(Person person) throws ClassNotFoundException, SQLException {
		Connection connection=getConnection();
		
		PreparedStatement preparedStatement=connection.prepareStatement("insert into person values(?,?,?,?,?,?)");
		
		preparedStatement.setInt(1, person.getId());
		preparedStatement.setString(2, person.getName());
		preparedStatement.setLong(3, person.getPhone());
		preparedStatement.setString(4, person.getEmail());
		preparedStatement.setString(5, person.getPassword());
		preparedStatement.setString(6, person.getAddress());
		
		int result=preparedStatement.executeUpdate();
		connection.close();
		
		return result;
		
	}
	
	public String getPassword(String email,String password) throws ClassNotFoundException, SQLException {
		Connection connection=getConnection();
		
		PreparedStatement preparedStatement=connection.prepareStatement("select * from person where email like ? and password like ?");
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);
		
		ResultSet resultSet=preparedStatement.executeQuery();
		
		String passwordDB=null;
		while(resultSet.next())
		{
			passwordDB=resultSet.getString("password");
		}
		
		return passwordDB;
	}
	
	public Person getInfo(String email) throws ClassNotFoundException, SQLException {
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("select * from person where email like ?");
		preparedStatement.setString(1, email);
		
		ResultSet resultSet=preparedStatement.executeQuery();
		Person person=new Person();
		while(resultSet.next())
		{
			person.setId(resultSet.getInt("id"));
			person.setName(resultSet.getString("name"));
			person.setPhone(resultSet.getLong("phone"));
			person.setEmail(resultSet.getString("email"));
			person.setPassword(resultSet.getString("password"));
			person.setAddress(resultSet.getString("address"));
		}
		
		return person;
	}
	
	public int delete(String email) throws ClassNotFoundException, SQLException {
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("delete from person where email like ?");
		preparedStatement.setString(1, email);
		int result=preparedStatement.executeUpdate();
		return result;
	}
	
	public int update(Person person,String email) throws ClassNotFoundException, SQLException {
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("update person set id=?,name=?,phone=?,email=?,password=?,address=? where email like ?");
		preparedStatement.setInt(1, person.getId());
		preparedStatement.setString(2, person.getName());
		preparedStatement.setLong(3, person.getPhone());
		preparedStatement.setString(4, person.getEmail());
		preparedStatement.setString(5, person.getPassword());
		preparedStatement.setString(6, person.getAddress());
		preparedStatement.setString(7, email);
		
		int result=preparedStatement.executeUpdate();
		
		return result;
	}
}
