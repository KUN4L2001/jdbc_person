package jdbc_person_practice;

import java.sql.SQLException;
import java.util.Scanner;

public class PersonController {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner scanner=new Scanner(System.in);
		Person person=new Person();
		PersonCRUD crud=new PersonCRUD();
		
		System.out.println("1.SignUp/n2.Login");
		
		switch (scanner.nextInt()) {
		case 1:
		{
			System.out.print("Enter the id: ");
			int id=scanner.nextInt();
			System.out.print("Enter the name: ");
			String name=scanner.next();
			System.out.print("Enter the phone: ");
			long phone=scanner.nextLong();
			System.out.print("Enter the email: ");
			String email=scanner.next();
			System.out.print("Enter the password: ");
			String password=scanner.next();
			System.out.print("Enter the address: ");
			String address=scanner.next();
			
			person.setId(id);
			person.setName(name);
			person.setPhone(phone);
			person.setEmail(email);
			person.setPassword(password);
			person.setAddress(address);
			
			crud.signUp(person);
			System.out.println("SignUp Successfull!!!");
		}
			break;
			
		case 2:
		{
			System.out.print("Enter the email: ");
			String email=scanner.next();
			System.out.print("Enter the password: ");
			String password=scanner.next();
			
			String passwordDB=crud.getPassword(email, password);
			if(password.equals(passwordDB)) 
			{
				System.out.println("Login Successfull!!!");
				System.out.println("Welcome\n1.Info\n2.Delete Account\n3.Update");
				switch (scanner.nextInt()) {
				case 1:
				{
					Person personDB=crud.getInfo(email);
					System.out.println(personDB.getId());
					System.out.println(personDB.getName());
					System.out.println(personDB.getPhone());
					System.out.println(personDB.getEmail());
					System.out.println(personDB.getPassword());
					System.out.println(personDB.getAddress());
				}
					break;

				case 2:
				{
					int result=crud.delete(email);
					if(result != 0)
					{
						System.out.println("Deleted");
					}
					else {
						System.out.println("Not deleted");
					}
				}
				case 3: 
				{
					Person newPerson=new Person();
					System.out.print("Enter the id: ");
					newPerson.setId(scanner.nextInt());
					System.out.print("Enter the name: ");
					newPerson.setName(scanner.next());
					System.out.print("Enter the phone: ");
					newPerson.setPhone(scanner.nextLong());
					System.out.print("Enter the email: ");
					newPerson.setEmail(scanner.next());
					System.out.print("Enter the password: ");
					newPerson.setPassword(scanner.next());
					System.out.print("Enter the address: ");
					newPerson.setAddress(scanner.next());
					
					crud.update(newPerson, email);
					System.out.println("Updated!!!");
				}
				default:
					break;
				}
			}
			else {
				System.out.println("Login Failed");
			}
		}

		default:
			break;
		}
	}
}
