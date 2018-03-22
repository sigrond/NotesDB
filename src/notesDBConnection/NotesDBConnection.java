/**
 * 
 */
package notesDBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		status = "chyba dzia�a";
	}

	public boolean addNote(String note)
	{
		String sql = "INSERT INTO note (NAPIS) values (?)";
		boolean toReturn = false;
		try
		{
			PreparedStatement prst = myConnection.prepareStatement(sql);
			prst.setString(1, note);
			toReturn = prst.executeUpdate() == 1;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return toReturn;
	}

	public boolean createTable()
	{
		String sql = "CREATE TABLE note (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAPIS VARCHAR(255))";
		boolean toReturn = false;
		try
		{
			PreparedStatement prst = myConnection.prepareStatement(sql);
			toReturn = prst.executeUpdate() == 1;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return toReturn;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		NotesDBConnection NDBC = new NotesDBConnection();
		System.out.println("chyba dzia�a");
		boolean response = NDBC.addNote("test note");
		System.out.println("nie by�o crasha: " + response);
		if(!response)
		{
			response = NDBC.createTable();
			System.out.println("create table response: " + response);
		}
	}

}
