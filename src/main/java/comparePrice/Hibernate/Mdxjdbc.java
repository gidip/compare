package comparePrice.Hibernate;

import java.sql.DriverManager;

public class Mdxjdbc {

	public static void main(String[] args) {
		String jdbcUrl="jdbc:mysql://localhost:3306/mdxdatabase?useSSL=false";
		String user="root";
		String pass="mdx123";
		
		try {
			System.out.println("Connecting to database:"+jdbcUrl);
			
			DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection Successful!!!");
		}
		catch (Exception exc) {
		exc.printStackTrace();
		}

		}

	}

