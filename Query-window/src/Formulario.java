import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Formulario extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTable table_2;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formulario frame = new Formulario();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Formulario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 555, 365);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Deportes", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 530, 95);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblResultado_1 = new JLabel("Resultado");
		lblResultado_1.setBounds(304, 222, 184, 15);
		panel.add(lblResultado_1);
		
		JButton btnNewButton = new JButton("Actualizar tabla");
		btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
		Connection conexion;
		try {
		   conexion = DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234");
		   Statement comando=conexion.createStatement();		 		
		   ResultSet registro = comando.executeQuery("select codigo,nombre,precio from deportes" ); 
					
		 		ActualizarTabla(table, registro );
		 		
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		});
		btnNewButton.setBounds(10, 117, 152, 23);
		panel.add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(420, 117, 89, 22);
		panel.add(comboBox);
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234");
			Statement ajena=conexion.createStatement();
			
			ResultSet consultaAjena = ajena.executeQuery("select nombre from deportes" ); 
			
			while(consultaAjena.next()) {
		 comboBox.addItem(consultaAjena.getString("nombre"));
		 
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		JLabel lblNewLabel = new JLabel("Codigo: ");
		lblNewLabel.setBounds(30, 151, 66, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre: ");
		lblNewLabel_1.setBounds(30, 176, 66, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Precio:");
		lblNewLabel_2.setBounds(30, 201, 66, 14);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(114, 149, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(106, 173, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(106, 199, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		
		
		JButton btnNewButton_3 = new JButton("Borrar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 int filaseleccionada;
			 try{
				 		
				 filaseleccionada = table.getSelectedRow();
			 if (filaseleccionada == -1){
			 JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
			 } else {
			
			 String nombre = (String)table.getValueAt(filaseleccionada, 1);
			 
			 textField_1.setText(nombre);
			 
			 Connection borrando=DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234"); 
			 
							Statement borrar=borrando.createStatement(); 
							
				 			ResultSet cantidad = borrar.executeQuery("delete from deportes where nombre='"+textField_1.getText()+"'"); 
				 			  ResultSet registro = borrar.executeQuery("select codigo,nombre,precio from deportes" ); 
				 			ActualizarTabla(table, registro);
				 			
							borrando.close(); 
							}
			 				}catch (HeadlessException ex){
			 				JOptionPane.showMessageDialog(null, "Error: "+ex+"\nInténtelo nuevamente", " .::Error En la Operacion::." ,JOptionPane.ERROR_MESSAGE);
			                }
			 				catch(SQLException ex){

			 				setTitle(ex.toString()); 
			}	
			}
		});
		btnNewButton_3.setBounds(304, 249, 89, 23);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Buscar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conexion;
				try {
					conexion = DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234");
					Statement comando=conexion.createStatement();
		 		
				ResultSet registro = comando.executeQuery("select codigo,nombre,precio from deportes where codigo="+textField_3.getText() ); 
					
					
		 		ActualizarTabla(table, registro );
		 		
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		btnNewButton_4.setBounds(256, 172, 89, 23);
		panel.add(btnNewButton_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(423, 173, 86, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Seleccionar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String ele = comboBox.getSelectedItem().toString();

		 		 for (int i = 0; i < table.getRowCount(); i++) {
				 if (table.getValueAt(i, 1).equals(ele)) {
				 table.changeSelection(i, 1, false, false);
				 break; 
				 } 
		 		 }
				 }});
		btnNewButton_1.setBounds(256, 117, 116, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_15 = new JButton("Alta");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try { 
					Connection conexion=DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234"); 
					Statement comando=conexion.createStatement(); 
					ResultSet alta = comando.executeQuery("insert into deportes(codigo,nombre, precio) values ("+textField.getText()+",'"+textField_1.getText()+"',"+textField_2.getText()+")"); 
					  ResultSet registro = comando.executeQuery("select codigo,nombre,precio from deportes" ); 
					ActualizarTabla(table, registro);
					
					conexion.close(); 
					
					textField.setText(""); 
					textField_1.setText(""); 
					textField_2.setText(""); 
					} catch(SQLException ex){ 
						setTitle(ex.toString()); 
		}
				
			}
		});
		btnNewButton_15.setBounds(30, 227, 89, 23);
		panel.add(btnNewButton_15);
		JButton btnAntesModif = new JButton("Antes de Modificar");
		btnAntesModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 int filaseleccionada;
				 try{
	
	filaseleccionada = table.getSelectedRow();
				if (filaseleccionada == -1){
				JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
				 } else {
				 
				int codigo = (int)table.getValueAt(filaseleccionada, 0);
				 String nombre = (String)table.getValueAt(filaseleccionada, 1);
				 String precio = (String)table.getValueAt(filaseleccionada, 2);
				

				 textField.setText(""+codigo);
				 textField_1.setText(nombre);
				 textField_2.setText(precio);
				 

				 }
				 }catch (HeadlessException ex){
				 JOptionPane.showMessageDialog(null, "Error: "+ex+"\nInténtelo nuevamente", " .::Error En la Operacion::." ,JOptionPane.ERROR_MESSAGE);
				 } 
			}
		});
		btnAntesModif.setBounds(30, 266, 170, 25);
		panel.add(btnAntesModif);
		
		JButton btnNewButton_2 = new JButton("Modificar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
			 
							Connection modificando=DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234"); 
							Statement modificar=modificando.createStatement(); 
							
				 			ResultSet cantidad = modificar.executeQuery("update deportes set codigo='" + textField.getText() + "', nombre='" + textField_1.getText() +"', precio=" + textField_2.getText() + " where codigo="+textField.getText()); 
				 			  ResultSet registro = modificar.executeQuery("select codigo,nombre,precio from deportes" ); 
				 			ActualizarTabla(table, registro );
							
								textField.setText(""); 
								textField_1.setText(""); 
								textField_2.setText(""); 
						
									 modificar.close(); 
			 
				}catch (HeadlessException ex){
						JOptionPane.showMessageDialog(null, "Error: "+ex+"\nInténtelo nuevamente", " .::Error En la Operacion::." ,JOptionPane.ERROR_MESSAGE);
					}
					catch(SQLException ex){

						setTitle(ex.toString()); 
			}
				
			}
		});
		btnNewButton_2.setBounds(30, 303, 89, 23);
		panel.add(btnNewButton_2);
		
		
		
		
		
		
		
		//segundo panel INSCRITOS
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Inscritos", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 530, 99);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		JButton btnNewButton_5 = new JButton("Actualizar tabla");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conexion;
				try {
					conexion = DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234");
					Statement comando=conexion.createStatement();
		 		
				ResultSet registro = comando.executeQuery("select codigo,dni,codigodeporte,curso,cuota from inscritos" ); 
				
		 		ActualizarTabla_1(table_1, registro );
		 		
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		btnNewButton_5.setBounds(20, 121, 174, 23);
		panel_1.add(btnNewButton_5);
				
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(424, 121, 90, 22);
		panel_1.add(comboBox_1);
		Connection conexionInscritos;
		try {
			conexionInscritos = DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234");
			Statement ajena=conexionInscritos.createStatement();
			
			ResultSet consultaAjena = ajena.executeQuery("select dni from inscritos" ); 
			
			while(consultaAjena.next()) {
			comboBox_1.addItem(consultaAjena.getString("dni"));
		 
			}
			
			} catch (SQLException e1) {
			
			e1.printStackTrace();
		} 
		JLabel lblNewLabel_3 = new JLabel("Codigo: ");
		lblNewLabel_3.setBounds(30, 155, 58, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("DNI: ");
		lblNewLabel_4.setBounds(30, 180, 46, 14);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Codigo Deporte: ");
		lblNewLabel_5.setBounds(30, 205, 139, 14);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_9 = new JLabel("Curso:");
		lblNewLabel_9.setBounds(30, 230, 46, 14);
		panel_1.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Cuota:");
		lblNewLabel_10.setBounds(30, 255, 79, 14);
		panel_1.add(lblNewLabel_10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(96, 153, 86, 20);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(86, 177, 86, 20);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(173, 203, 86, 20);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		textField_12 = new JTextField();
		textField_12.setBounds(137, 228, 86, 20);
		panel_1.add(textField_12);
		textField_12.setColumns(10);
		
		textField_13 = new JTextField();
		textField_13.setBounds(137, 253, 86, 20);
		panel_1.add(textField_13);
		textField_13.setColumns(10);
		
		
		JButton btnNewButton_8 = new JButton("Buscar");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conexion;
				try {
					conexion = DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234");
					Statement comando=conexion.createStatement();
		 		
				ResultSet registro = comando.executeQuery("select codigo,dni,codigodeporte,curso,cuota from inscritos where codigo="+textField_7.getText() ); 
					
					
		 		ActualizarTabla_1(table_1, registro );
		 		
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				} 
			
			}
		});
		btnNewButton_8.setBounds(271, 184, 89, 23);
		panel_1.add(btnNewButton_8);
		
		textField_7 = new JTextField();
		textField_7.setBounds(417, 185, 86, 20);
		panel_1.add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnNewButton_9 = new JButton("Borrar");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 int filaseleccionada;
			 try{

				 filaseleccionada = table_1.getSelectedRow();
				 if (filaseleccionada == -1){
					 JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
				 		} else {
				 			int codigo = (int)table_1.getValueAt(filaseleccionada, 0);

				 			textField_7.setText(""+codigo);
			 

				 			Connection borrando=DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234"); 
							Statement borrar=borrando.createStatement();
				 			int cantidad = borrar.executeUpdate("delete from inscritos where codigo="+textField_7.getText()); 
							
								
				 			ResultSet registro = borrar.executeQuery("select codigo,dni,codigodeporte,curso,cuota from inscritos" ); 
				 			
				 			ActualizarTabla_1(table_1, registro );
									 borrando.close(); 
				 		}		
				 		}catch (HeadlessException ex){
			 								JOptionPane.showMessageDialog(null, "Error: "+ex+"\nInténtelo nuevamente", " .::Error En la Operacion::." ,JOptionPane.ERROR_MESSAGE);
			 						}
			 						catch(SQLException ex){

			 							setTitle(ex.toString()); 
			 						}
									}
		});
		btnNewButton_9.setBounds(414, 314, 89, 23);
		panel_1.add(btnNewButton_9);
		
		JButton btnNewButton_16 = new JButton("Alta");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				try { 
					Connection conexion=DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234"); 
					Statement comando=conexion.createStatement(); 

					ResultSet altaInscritos = comando.executeQuery("insert into inscritos(codigo,dni,codigodeporte,curso,cuota) values ("+textField_4.getText()+",'"+textField_5.getText()+"',"+textField_6.getText()+",'"+textField_12.getText()+"','"+textField_13.getText()+"')"); 
					ResultSet registro = comando.executeQuery("select codigo,dni,codigodeporte,curso,cuota from inscritos" ); 
					conexion.close(); 
		
					textField_4.setText(""); 
					textField_5.setText(""); 
					textField_6.setText(""); 
					textField_12.setText(""); 
					textField_13.setText(""); 
					ActualizarTabla_1(table_1, registro );
					
					} catch(SQLException ex){ 
						setTitle(ex.toString()); 
		}	
			}
		});
		btnNewButton_16.setBounds(20, 280, 89, 23);
		panel_1.add(btnNewButton_16);
		
		
		JButton btnNewButton_6 = new JButton("Seleccionar: ");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String ele = comboBox_1.getSelectedItem().toString();

		 		 for (int i = 0; i < table_1.getRowCount(); i++) {
				 if (table_1.getValueAt(i, 1).equals(ele)) {
				 table_1.changeSelection(i, 1, false, false);
				 break; 
		}		
		 		 }}
		});
		btnNewButton_6.setBounds(272, 121, 140, 23);
		panel_1.add(btnNewButton_6);
		
		JLabel lblResultado = new JLabel("Resultado");
		lblResultado.setBounds(306, 255, 197, 15);
		panel_1.add(lblResultado);
		
		
		JButton btnNewButton_18 = new JButton("Antes de modificar");
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int filaseleccionada;
				 try{

				filaseleccionada = table_1.getSelectedRow();
				if (filaseleccionada == -1){
				JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
				 } else {
				 
				int codigo = (int)table_1.getValueAt(filaseleccionada, 0);
				 String dni = (String)table_1.getValueAt(filaseleccionada, 1);
				 String codigodeporte = (String)table_1.getValueAt(filaseleccionada, 2);
				 String curso = (String)table_1.getValueAt(filaseleccionada, 3);
				 String cuota = (String)table_1.getValueAt(filaseleccionada, 4);
				

				 textField_4.setText(""+codigo); 
				 textField_5.setText(dni); 
				 textField_6.setText(codigodeporte); 
				 textField_12.setText(curso); 
				 textField_13.setText(cuota); 
				 
				}
	}catch (HeadlessException ex){
				 JOptionPane.showMessageDialog(null, "Error: "+ex+"\nInténtelo nuevamente", " .::Error En la Operacion::." ,JOptionPane.ERROR_MESSAGE);
				 } 
			
			}
		});
		btnNewButton_18.setBounds(20, 314, 174, 23);
		panel_1.add(btnNewButton_18);
		
		
		JButton btnNewButton_7 = new JButton("Modificar");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				
			Connection modificando=DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234"); 
			Statement modificar=modificando.createStatement(); 
										
			ResultSet cantidad = modificar.executeQuery("update inscritos set codigo='" + textField_4.getText() + "', dni='" + textField_5.getText() +"', codigodeporte=" + textField_6.getText() +", curso='" + textField_12.getText() +"', cuota='" + textField_13.getText() + "' where codigo="+textField_4.getText()); 
			ResultSet registro = modificar.executeQuery("select codigo,dni,codigodeporte,curso,cuota from inscritos" ); 
				textField_4.setText(""); 
				textField_5.setText(""); 
				textField_6.setText(""); 
			    textField_12.setText(""); 
				textField_13.setText(""); 
				ActualizarTabla_1(table_1, registro );					
					
					 modificar.close(); 
						 
					}catch (HeadlessException ex){
						 JOptionPane.showMessageDialog(null, "Error: "+ex+"\nInténtelo nuevamente", " .::Error En la Operacion::." ,JOptionPane.ERROR_MESSAGE);
						 }
					catch(SQLException ex){

									setTitle(ex.toString()); 
						}						
			}
		});
		btnNewButton_7.setBounds(204, 314, 89, 23);
		panel_1.add(btnNewButton_7);
		
	
		
		
		//tercer panel SOCIOS
		
		
		
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Socios", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 530, 98);
		panel_2.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_2.setViewportView(table_2);
		
		JButton btnNewButton_10 = new JButton("Actualizar tabla");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conexion;
				try {
					conexion = DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234");
					Statement actualizarSocios=conexion.createStatement();
		 		
				ResultSet actualizandoSocios = actualizarSocios.executeQuery("select dni,nombre,email from socios" ); 
					
					
		 		ActualizarTabla_2(table_2, actualizandoSocios );
		 		
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				} 
			
			}
		});
		btnNewButton_10.setBounds(10, 130, 130, 23);
		panel_2.add(btnNewButton_10);
		
				
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(407, 130, 115, 22);
		panel_2.add(comboBox_2);
		Connection conexionSocios;
		try {
			conexionSocios = DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234");
			Statement ajena=conexionSocios.createStatement();
			
			ResultSet consultaAjena = ajena.executeQuery("select nombre from socios" ); 
			
			while(consultaAjena.next()) {
		    comboBox_2.addItem(consultaAjena.getString("nombre"));
		 
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		JLabel lblNewLabel_6 = new JLabel("DNI:");
		lblNewLabel_6.setBounds(10, 174, 46, 14);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Nombre:");
		lblNewLabel_7.setBounds(10, 207, 70, 14);
		panel_2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Email:");
		lblNewLabel_8.setBounds(10, 247, 46, 14);
		panel_2.add(lblNewLabel_8);
		
		textField_8 = new JTextField();
		textField_8.setBounds(64, 171, 86, 20);
		panel_2.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(90, 204, 86, 20);
		panel_2.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(64, 244, 86, 20);
		panel_2.add(textField_10);
		textField_10.setColumns(10);
		
		JButton btnNewButton_12 = new JButton("Buscar");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conexion;
				try {
					conexion = DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234");
					Statement comando=conexion.createStatement();
		 		
				ResultSet registro = comando.executeQuery("select dni,nombre,email from socios where dni="+textField_11.getText() ); 
					
				textField_11.setText("");
					
		 		ActualizarTabla_2(table_2, registro );
		 		
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 	
			}
		});
		btnNewButton_12.setBounds(275, 203, 89, 23);
		panel_2.add(btnNewButton_12);
		
		textField_11 = new JTextField();
		textField_11.setBounds(407, 204, 86, 20);
		panel_2.add(textField_11);
		textField_11.setColumns(10);
		
				
		JButton btnNewButton_14 = new JButton("Borrar");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 int filaseleccionada;
			try{
				
			 filaseleccionada = table_2.getSelectedRow();
			 if (filaseleccionada == -1){
			 JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
			} else {
	
			String nombre = (String)table_2.getValueAt(filaseleccionada, 1);
			
			 

			textField_11.setText(nombre);
			  

			Connection borrando=DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234"); 
							Statement borrar=borrando.createStatement();
				 			int cantidad = borrar.executeUpdate("delete from socios where nombre='"+textField_11.getText()+"'"); 
				 			ResultSet actualizandoSocios = borrar.executeQuery("select dni,nombre,email from socios" ); 
				 			ActualizarTabla_2(table_2, actualizandoSocios );
								textField_11.setText(""); 
						
								
								
							borrando.close(); 
			 }}catch (HeadlessException ex){
			 JOptionPane.showMessageDialog(null, "Error: "+ex+"\nInténtelo nuevamente", " .::Error En la Operacion::." ,JOptionPane.ERROR_MESSAGE);
			 }
			catch(SQLException ex){

						setTitle(ex.toString()); 
			}		
			}
		});
		btnNewButton_14.setBounds(404, 303, 89, 23);
		panel_2.add(btnNewButton_14);
		
		JButton btnNewButton_17 = new JButton("Alta");
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try { 
					Connection conexion=DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234"); 
					Statement comando=conexion.createStatement(); 
					comando.executeUpdate("insert into socios(dni,nombre, email) values ('"+textField_8.getText()+"','"+textField_9.getText()+"','"+textField_10.getText()+"')"); 
					ResultSet actualizandoSocios = comando.executeQuery("select dni,nombre,email from socios" ); 
		 			ActualizarTabla_2(table_2, actualizandoSocios );
					conexion.close(); 
		
					textField_8.setText(""); 
					textField_9.setText(""); 
					textField_10.setText(""); 
					} catch(SQLException ex){ 
						setTitle(ex.toString()); 
		}
			
			}
		});
		btnNewButton_17.setBounds(51, 272, 89, 23);
		panel_2.add(btnNewButton_17);
		
		JButton btnNewButton_11 = new JButton("Seleccionar");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ele = comboBox_2.getSelectedItem().toString();

		 		 for (int i = 0; i < table_2.getRowCount(); i++) {
				 if (table_2.getValueAt(i, 1).equals(ele)) {
				 table_2.changeSelection(i, 1, false, false);
		 break; 
		}
			
			}}
		});
		btnNewButton_11.setBounds(275, 130, 120, 23);
		panel_2.add(btnNewButton_11);
		
		JLabel lblNewLabel_11 = new JLabel("Resultado");
		lblNewLabel_11.setBounds(286, 257, 207, 14);
		panel_2.add(lblNewLabel_11);
		
		JButton btnNewButton_19 = new JButton("Antes de modificar");
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaseleccionada;
				 try{
	
	filaseleccionada = table_2.getSelectedRow();
				if (filaseleccionada == -1){
				JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
				 } else {
				 
				String dni = (String)table_2.getValueAt(filaseleccionada, 0);
				 String nombre = (String)table_2.getValueAt(filaseleccionada, 1);
				 String email = (String)table_2.getValueAt(filaseleccionada, 2);
				 
				

				 textField_8.setText(dni); 
					textField_9.setText(nombre); 
					textField_10.setText(email); 
		
				}
	}catch (HeadlessException ex){
				 JOptionPane.showMessageDialog(null, "Error: "+ex+"\nInténtelo nuevamente", " .::Error En la Operacion::." ,JOptionPane.ERROR_MESSAGE);
				 } 
			
			}
		});
		btnNewButton_19.setBounds(33, 303, 162, 23);
		panel_2.add(btnNewButton_19);
		
		JButton btnNewButton_13 = new JButton("Modificar");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					 

					Connection modificando=DriverManager.getConnection("jdbc:mariadb://guillermopg.com.es:3306/DEPORTES","guipagar","alumno1234"); 
					Statement modificar=modificando.createStatement(); 
												
					int cantidad = modificar.executeUpdate("update socios set dni='" + textField_8.getText() + "', nombre='" + textField_9.getText() +"', email='" + textField_10.getText() + "' where dni='"+textField_8.getText()+"'"); 
					
					ResultSet actualizandoSocios = modificar.executeQuery("select dni,nombre,email from socios" ); 
		 			ActualizarTabla_2(table_2, actualizandoSocios );
					
					
					
						textField_8.setText(""); 
						textField_9.setText(""); 
						textField_10.setText(""); 
					  
											
							
							 modificar.close(); 
								 
					}catch (HeadlessException ex){
								 JOptionPane.showMessageDialog(null, "Error: "+ex+"\nInténtelo nuevamente", " .::Error En la Operacion::." ,JOptionPane.ERROR_MESSAGE);
								 }
					catch(SQLException ex){

											setTitle(ex.toString()); 
								}						
				
			}
		});
		btnNewButton_13.setBounds(224, 303, 89, 23);
		panel_2.add(btnNewButton_13);
		
	}
	
	public void ActualizarTabla(JTable jtabla, ResultSet rs){ 
		//Creo un modelo de datos para un jtable 
		DefaultTableModel modelo = new DefaultTableModel();
		//le asigno a la tabla el modelo de   datos 
		jtabla.setModel(modelo); 
				try { 
			//creo 3 columnas con sus etiquetas    
			//estas son las columnas del JTable  
			modelo.addColumn("CODIGO"); 
			modelo.addColumn("NOMBRE"); 
			modelo.addColumn("DIRECCION");
			//Recorro el ResultSet que contiene los resultados.  
			while(rs.next()){ 
				//Crea un vector      
				Object []ob=new Object[4];
				//para almacenar los valores del ResultSet  
				ob[0]=(rs.getInt(1)); 
				ob[1]=(rs.getString(2)); 
				ob[2]=(rs.getString(3)); 
				//añado la fila a la tabla      
				modelo.addRow(ob); 
				//limpia los datos del vector de la memoria  
				ob=null; 
				}
			// Cierra el ResultSet     
			rs.close(); 
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			}
	public void ActualizarTabla_1(JTable jtabla, ResultSet rs){ 
		//Creo un modelo de datos para un jtable 
		DefaultTableModel modelo = new DefaultTableModel();
		//le asigno a la tabla el modelo de   datos 
		jtabla.setModel(modelo); 
				try { 
			//creo 3 columnas con sus etiquetas    
			//estas son las columnas del JTable  
			modelo.addColumn("CODIGO"); 
			modelo.addColumn("DNI"); 
			modelo.addColumn("CODIGODEPORTE");
			modelo.addColumn("CURSO");
			modelo.addColumn("CUOTA");
			//Recorro el ResultSet que contiene los resultados.  
			while(rs.next()){ 
				//Crea un vector      
				Object []ob=new Object[5];
				//para almacenar los valores del ResultSet  
				ob[0]=(rs.getInt(1)); 
				ob[1]=(rs.getString(2)); 
				ob[2]=(rs.getString(3)); 
				ob[3]=(rs.getString(4)); 
				ob[4]=(rs.getString(5)); 
				//añado la fila a la tabla      
				modelo.addRow(ob); 
				//limpia los datos del vector de la memoria  
				ob=null; 
				}
			// Cierra el ResultSet     
			rs.close(); 
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			}
	public void ActualizarTabla_2(JTable jtabla, ResultSet rs){ 
		//Creo un modelo de datos para un jtable 
		DefaultTableModel modelo = new DefaultTableModel();
		//le asigno a la tabla el modelo de   datos 
		jtabla.setModel(modelo); 
				try { 
			//creo 3 columnas con sus etiquetas    
			//estas son las columnas del JTable  
			modelo.addColumn("DNI"); 
			modelo.addColumn("NOMBRE"); 
			modelo.addColumn("EMAIL");
			//Recorro el ResultSet que contiene los resultados.  
			while(rs.next()){ 
				//Crea un vector      
				Object []ob=new Object[4];
				//para almacenar los valores del ResultSet  
				ob[0]=(rs.getString(1)); 
				ob[1]=(rs.getString(2)); 
				ob[2]=(rs.getString(3)); 
				//añado la fila a la tabla      
				modelo.addRow(ob); 
				//limpia los datos del vector de la memoria  
				ob=null; 
				}
			// Cierra el ResultSet     
			rs.close(); 
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			}
}