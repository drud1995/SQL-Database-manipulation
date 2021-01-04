import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Split 
{
	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	//private JTable table;
	
	private DisplayTable useTable;
	private Connection connection = null;
	
	// default query retrieves all data from bikes table
    static final String DEFAULT_QUERY = "SELECT * FROM bikes";
    private JTable table_1 = null;
    private JTable table;
    
    public int countHold = 0;
    
    
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sqlGUI window = new sqlGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public sqlGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//MysqlDataSource dataSource = new MysqlDataSource();
		// System.out.println("JDBC Driver name: com.mysql.cj.jdbc.Driver");
		
		
		frame = new JFrame("SQL Client GUI - (DRR - CNT 4714 - Spring 2020)");
		frame.setBounds(100, 100, 885, 603);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextPane textPane = new JTextPane();
		textPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblNewLabel = new JLabel("SQL Execution Result Window");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_1 = new JLabel("No Connection Now");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JButton btnNewButton = new JButton("Connect to Database");
		
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setOpaque(true);
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setForeground(Color.YELLOW);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnNewButton_1 = new JButton("Clear SQL Command");
		
		btnNewButton_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setOpaque(true);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnNewButton_2 = new JButton("Execute SQL Command");
		
		btnNewButton_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setOpaque(true);
		btnNewButton_2.setBackground(Color.GREEN);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnNewButton_3 = new JButton("Clear Result Window");
		
		btnNewButton_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setOpaque(true);
		btnNewButton_3.setBackground(Color.YELLOW);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel lblNewLabel_2 = new JLabel("Enter An SQL Command");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_3 = new JLabel("Enter Database Information");
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_4 = new JLabel("JDBC Driver");
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_5 = new JLabel("Database URL");
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_6 = new JLabel("Username");
		lblNewLabel_6.setOpaque(true);
		lblNewLabel_6.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_7 = new JLabel("Password");
		lblNewLabel_7.setOpaque(true);
		lblNewLabel_7.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.addItem("com.mysql.cj.jdbc.Driver");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_1.addItem("jdbc:mysql://localhost:3306/project3");
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		String chosenJDBC = comboBox.getSelectedItem().toString();
		String chosenURL = comboBox_1.getSelectedItem().toString();
		//System.out.println("chosenURL is: " + chosenURL);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
									.addGap(40)
									.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
									.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_3)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblNewLabel_7, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblNewLabel_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblNewLabel_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblNewLabel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
											.addGap(1)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(passwordField, 264, 264, 264)
												.addComponent(textField, 264, 264, 264)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
													.addComponent(comboBox_1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(comboBox, 0, 264, Short.MAX_VALUE)))))
									.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_2)
										.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(57)
							.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(comboBox, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(lblNewLabel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBox_1, 0, 0, Short.MAX_VALUE)
								.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_3)
					.addGap(9))
		);
		
		
		
		
		
		//CONNECT TO DATABASE
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//without a properties file include these statements
				MysqlDataSource dataSource = new MysqlDataSource();
				
				//System.out.println("chosenJDBC is: " + chosenJDBC);
				try 
				{
					Class.forName(chosenJDBC);
				} 
				catch (ClassNotFoundException e2) 
				{
					e2.printStackTrace();
				}
				
				dataSource.setUser(textField.getText());
				//System.out.println("textField is: " + textField.getText());
				
				String passwordFound = String.copyValueOf(passwordField.getPassword());
				dataSource.setPassword(passwordFound);
				//System.out.println("passwordField is: " + passwordFound);
				
				dataSource.setURL(chosenURL);
				//System.out.println("chosenURL is: " + chosenURL);
				  
				//establish a connection to the dataSource - the database
				
				//Connection connection = null;
				
				
				try 
				{
					connection = dataSource.getConnection();
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
				
				
			    System.out.println("Database connected");
			    
			   
			    /*
				    DatabaseMetaData dbMetaData = null;
				    
					try 
					{
						dbMetaData = connection.getMetaData();
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					
					try 
					{
						System.out.println("JDBC Driver name " + dbMetaData.getDriverName() );
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					try 
					{
						System.out.println("JDBC Driver version " + dbMetaData.getDriverVersion());
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					System.out.println("Driver Major version " +dbMetaData.getDriverMajorVersion());
					System.out.println("Driver Minor version " +dbMetaData.getDriverMinorVersion() );
				*/
				
				lblNewLabel_1.setText("Connected to " + chosenURL);
			}
		});
		
		//CLEAR SQL COMMAND
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				textPane.setText("");
			}
		});
		
		
		//EXECUTE SQL COMMAND
		btnNewButton_2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//string to determine whether to query or update
				String[] checkerString = textPane.getText().split(" ");
				//System.out.println("checkerString 1st string is: " + checkerString[0]);
				
				if(countHold == 0)
				{
					try 
					{
						useTable = new DisplayTable(DEFAULT_QUERY, connection);
					} 
					catch (ClassNotFoundException e) 
					{
						e.printStackTrace();
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					
					//create jtable after establishing connection
					//table_1 = new JTable(useTable);
					//panel.add(table_1);
					
					///////////////////////////////////////////////////////////////////////////////////////////////////////
					//panel = new JPanel();
					//panel.updateUI();
					JScrollPane scrollPane = new JScrollPane();
					GroupLayout gl_panel = new GroupLayout(panel);
					gl_panel.setHorizontalGroup(
						gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
					);
					gl_panel.setVerticalGroup(
						gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
					);
					
					table = new JTable(useTable);
					scrollPane.setViewportView(table);
					panel.setLayout(gl_panel);
					
					panel.updateUI();
					
					countHold++;
					///////////////////////////////////////////////////////////////////////////////////////////////////////
				}
				
				
				try
				{
					
					//check for query case
					if((checkerString[0].equals("select")) || (checkerString[0].equals("select*")) || (checkerString[0].equals("SELECT")) || (checkerString[0].equals("SELECT*")))
						useTable.setQuery(textPane.getText());
					//check for update case
					else if((checkerString[0].equals("INSERT")) || (checkerString[0].equals("UPDATE")) || (checkerString[0].equals("DELETE")) || (checkerString[0].equals("CREATE")) || (checkerString[0].equals("DROP")) || (checkerString[0].equals("insert")) || (checkerString[0].equals("update")) || (checkerString[0].equals("delete")) || (checkerString[0].equals("create")) || (checkerString[0].equals("drop")))
						useTable.setUpdate(textPane.getText());
					//neither case satisfied...
					else
						JOptionPane.showMessageDialog(null, "Invalid SQL Statement Entered");
				}
				catch (SQLException sqlException)
				{
					 JOptionPane.showMessageDialog( null, 
                     sqlException.getMessage(), "Database error", 
                     JOptionPane.ERROR_MESSAGE );
                     
                     // try to recover from invalid user query 
                     // by executing default query
                     try 
                     {
                        useTable.setQuery( DEFAULT_QUERY );
                        //queryArea.setText( DEFAULT_QUERY );
                     } // end try
                     catch ( SQLException sqlException2 ) 
                     {
                        JOptionPane.showMessageDialog( null, 
                           sqlException2.getMessage(), "Database error", 
                           JOptionPane.ERROR_MESSAGE );
         
                        // ensure database connection is closed
                        useTable.disconnectFromDatabase();
         
                        System.exit( 1 ); // terminate application
                     } // end inner catch                   
	              } // end outer catch
				
			}
		});
		
		//CLEAR RESULT WINDOW
		btnNewButton_3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				panel.removeAll();
				panel.updateUI();
				
				JPanel panel = new JPanel();
				panel.updateUI();
				
				countHold = 0;
			}
		});
		
		frame.getContentPane().setLayout(groupLayout);
	
	
	
	
		
	
	}

}
