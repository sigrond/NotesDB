/**
 * 
 */
package notesDBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author x
 */
public class NotesDBConnection
{

	private Connection myConnection;
	public String status = "0";

	/**
	 * 
	 */
	public NotesDBConnection()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		}
		try
		{
			myConnection = DriverManager.getConnection("jdbc:sqlite:test.db");
		}
		catch(SQLException ex)
		{
			System.out.println("connection error");
			ex.printStackTrace();
			System.exit(1);
		}
		status = "chyba dzia³a";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		NotesDBConnection NDBC = new NotesDBConnection();
		System.out.println("chyba dzia³a");
	}

}
