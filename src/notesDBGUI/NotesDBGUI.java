/**
 * 
 */
package notesDBGUI;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import notesDBConnection.NotesDBConnection;

/**
 * @author x
 */
public class NotesDBGUI
{

	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private JButton btnDodaj;
	private NotesDBConnection dbc;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					NotesDBGUI window = new NotesDBGUI();
					window.frame.setVisible(true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NotesDBGUI()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblPoczenieZBaz = new JLabel(
				"Po\u0142\u0105czenie z baz\u0105:");
		lblPoczenieZBaz.setBounds(10, 11, 144, 14);
		frame.getContentPane().add(lblPoczenieZBaz);

		lblStatus = new JLabel("status");
		lblStatus.setBounds(164, 11, 96, 14);
		frame.getContentPane().add(lblStatus);

		JButton btnPocz = new JButton("Po\u0142\u0105cz");
		btnPocz.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				dbc = new NotesDBConnection();
				lblStatus.setText(dbc.status);
			}
		});
		btnPocz.setBounds(296, 7, 89, 23);
		frame.getContentPane().add(btnPocz);

		table = new JTable();
		table.setBounds(20, 36, 365, 157);
		frame.getContentPane().add(table);

		textField = new JTextField();
		textField.setBounds(21, 216, 294, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(335, 215, 89, 23);
		frame.getContentPane().add(btnDodaj);
	}
}
