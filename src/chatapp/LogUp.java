package chatapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import database.UserDAO;
import chatapp.User;

public class LogUp extends JFrame {

	private JPanel contentPane;
	private JTextField jtfUser;
	private JTextField jtfEmail;
	private JPasswordField jtfPW;
	private JTextField jtfTel;
	private UserDAO uDao = new UserDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogUp frame = new LogUp();
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
	public LogUp() {
		setTitle("SignUp Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 381);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("SIGNUP ACCOUNT");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(93, 26, 256, 32);
		contentPane.add(lblNewLabel);

		jtfUser = new JTextField();
		jtfUser.setBounds(178, 87, 171, 20);
		contentPane.add(jtfUser);
		jtfUser.setColumns(10);

		jtfEmail = new JTextField();
		jtfEmail.setBounds(178, 222, 171, 20);
		contentPane.add(jtfEmail);
		jtfEmail.setColumns(10);

		jtfPW = new JPasswordField();
		jtfPW.setBounds(178, 135, 171, 20);
		contentPane.add(jtfPW);

		jtfTel = new JTextField();
		jtfTel.setBounds(178, 178, 171, 20);
		contentPane.add(jtfTel);
		jtfTel.setColumns(10);

		JButton jbtSignup = new JButton("SignUp");
		jbtSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jtfUser.getText() != null) {
					String userName = jtfUser.getText();
					String pass = new String(jtfPW.getPassword());
					String tel = jtfTel.getText();
					String email = jtfEmail.getText();

					String validate = "null";

					if (userName.equals("")) {
						validate = "Username cannot empty!";
					} else if (pass.equals("")) {
						validate = "Password cannot empty!";
					} else if (tel.equals("")) {
						validate = "Telephone cannot empty!";
					} else if (email.equals("")) {
						validate = "Telephone cannot empty!";
					}
					if (!validate.equals("null")) {
						JOptionPane.showMessageDialog(null, validate);
					} else {
						User user = new User(userName, pass, tel, email);
						if (uDao.getUser(userName) == null) {
							try {
								if (uDao.addUser(user)) {
									JOptionPane.showMessageDialog(null, "Sign up successfull!");

									LogIn log = new LogIn();
									log.setVisible(true);
									exit();
								} else {
									JOptionPane.showMessageDialog(null, "Error!");
								}
							} catch (HeadlessException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Username available!");
						}
					}

				} else {
					JOptionPane.showMessageDialog(null, "Please enter UserName");
				}
			}
		});
		jbtSignup.setFont(new Font("Times New Roman", Font.BOLD, 15));
		jbtSignup.setBounds(168, 282, 89, 23);
		contentPane.add(jbtSignup);

		JLabel jlbUser = new JLabel("User Name ");
		jlbUser.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		jlbUser.setBounds(84, 90, 86, 14);
		contentPane.add(jlbUser);

		JLabel jlbPW = new JLabel("Password ");
		jlbPW.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		jlbPW.setBounds(84, 138, 86, 14);
		contentPane.add(jlbPW);

		JLabel jlbEmail = new JLabel("Email ");
		jlbEmail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		jlbEmail.setBounds(84, 224, 74, 14);
		contentPane.add(jlbEmail);

		JLabel jlbTel = new JLabel("Phone number ");
		jlbTel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		jlbTel.setBounds(84, 177, 94, 20);
		contentPane.add(jlbTel);
	}

	private void exit() {
		this.setVisible(false);
	}

}
