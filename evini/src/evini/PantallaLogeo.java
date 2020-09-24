package evini;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.sun.jdi.connect.spi.Connection;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class PantallaLogeo extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;
	
	
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PantallaLogeo frame = new PantallaLogeo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	
	public PantallaLogeo() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);

		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(245, 82, 104, 49);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(87, 228, 104, 49);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contrase\u00F1a");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(87, 307, 162, 49);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(280, 228, 198, 42);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(280, 319, 198, 42);
		panel.add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnNewButton.setBounds(245, 428, 104, 84);
		btnNewButton.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            String userName = textField.getText();
            String password = passwordField.getText();
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/evinidb?serverTimezone=UTC",
                    "root", "1234" );
                
                
                
                

                PreparedStatement st = (PreparedStatement) ((java.sql.Connection) connection)
                    .prepareStatement("Select name, password from usuario where name=? and password=?");

                st.setString(1, userName);
                st.setString(2, password);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    dispose();
                    UserHome ah = new UserHome(userName);
                    ah.setTitle("Bienvenido");
                    ah.setVisible(true);
                    JOptionPane.showMessageDialog(btnNewButton, "Muy bien");
                } else {
                    JOptionPane.showMessageDialog(btnNewButton, "Ha habido un error");
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
		});
		
		panel.add(btnNewButton);
	}
}
