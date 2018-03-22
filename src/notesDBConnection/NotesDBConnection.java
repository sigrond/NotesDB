/**
 * 
 */
package notesDBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

	public String getNotes()
	{
		String sql = "SELECT * FROM note";
		String results = "numer | notatka";
		String resultFormatString = "%s | %s";
		try
		{
			PreparedStatement prst = myConnection.prepareStatement(sql);
			ResultSet rs = prst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next())
			{
				results += "\n";
				String[] rowdata = new String[rsmd.getColumnCount()];
				for(int i = 0; i < rsmd.getColumnCount(); ++i)
				{
					rowdata[i] = rs.getString(i + 1);
					// System.out.println("rowdata[" + i + "]: " + rowdata[i]);
				}
				results += String.format(resultFormatString,
						(Object[]) rowdata);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		NotesDBConnection NDBC = new NotesDBConnection();
		System.out.println("chyba dzia³a");
		boolean response = NDBC.addNote("test note");
		System.out.println("nie by³o crasha: " + response);
		if(!response)
		{
			response = NDBC.createTable();
			System.out.println("create table response: " + response);
		}
	}

}
