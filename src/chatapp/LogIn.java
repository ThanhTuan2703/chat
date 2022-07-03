package chatapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import database.UserDAO;

public class LogIn extends JFrame {

	private JPanel contentPane;
	private JTextField jtfUser;
	private JPasswordField jtfPW;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogIn() {
		setTitle("SignIn Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("SIGNIN ACCOUNT");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(106, 39, 240, 24);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		contentPane.add(lblNewLabel);

		jtfUser = new JTextField();
		jtfUser.setBounds(197, 104, 144, 20);
		contentPane.add(jtfUser);
		jtfUser.setColumns(10);

		jtfPW = new JPasswordField();
		jtfPW.setBounds(197, 159, 144, 20);
		contentPane.add(jtfPW);

		JLabel jlbUser = new JLabel("User Name ");
		jlbUser.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		jlbUser.setBounds(106, 106, 81, 14);
		contentPane.add(jlbUser);

		JLabel jlbPW = new JLabel("Password ");
		jlbPW.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		jlbPW.setBounds(106, 162, 81, 14);
		contentPane.add(jlbPW);

		JButton jbtSignin = new JButton("SignIn");
		jbtSignin.addActionListener(new ActionListener() {
			UserDAO uDao = new UserDAO();
			public void actionPerformed(ActionEvent e) {
				String username = jtfUser.getText();
				String password = new String(jtfPW.getPassword());
				boolean check = uDao.login(username,password) != null;
				System.out.println(check);
				if (check) {
					ClientChatter cl = new ClientChatter(username);
					cl.setVisible(true);
					contentPane.setVisible(false);
					exit();
				}else {
					JOptionPane.showConfirmDialog(null, "Account is incorrect. Please sign up again or contact to owner");
					
				}
			}
		});
		jbtSignin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		jbtSignin.setBounds(123, 206, 89, 23);
		contentPane.add(jbtSignin);

		JButton jbtSignup = new JButton("SignUp");
		jbtSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogUp sg = new LogUp();
				sg.setVisible(true);
				exit();
			}
		});
		jbtSignup.setFont(new Font("Times New Roman", Font.BOLD, 16));
		jbtSignup.setBounds(257, 206, 89, 23);
		contentPane.add(jbtSignup);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		UserDAO uDao = new UserDAO();
		if(e.getActionCommand().equals("SignIn")) {
			String username = jtfUser.getText();
			String password = new String(jtfPW.getPassword());
			if (uDao.login(username,password)!= null) {
				JOptionPane.showConfirmDialog(null, "ok");
				ClientChatter cl = new ClientChatter(username);
				cl.setVisible(true);
				contentPane.setVisible(false);
				exit();
			}else {
				JOptionPane.showConfirmDialog(null, "Account is incorrect. Please sign up again or contact to owner");
				
			}
		}
		if(e.getActionCommand().equals("SignUp")) { 
			String username = jtfUser.getText();
			String password = new String(jtfPW.getPassword());
			if (username.trim().equals("")&& password.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Please sign up to start program."
						+ "If you don't have account ,"
						+ " You should contact with owner to sign up ");
		        
			}
		}
	}
	private void exit() {
		this.setVisible(false);
	}
}
