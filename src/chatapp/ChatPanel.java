package chatapp;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ChatPanel extends JPanel {
	Socket socket = null;
	BufferedReader bf =null;
	DataOutputStream os = null;
	OutputThread t = null;
	String sender;
	String receiver;
	JTextArea txtMessages;

	/**
	 * Create the panel.
	 */
	public ChatPanel(Socket s, String sender, String receiver) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JTextArea txtMessage = new JTextArea();
		txtMessage.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane.setViewportView(txtMessage);
		
		JButton btnSend = new JButton("SEND");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtMessage.getText().trim().length()==0) return;
				try {
					os.writeBytes(txtMessage.getText());
					os.write(13); os.write(10);
					os.flush();
					txtMessages.append("\n"+sender+":"+txtMessage.getText());
					txtMessage.setText("");
					System.out.println(sender);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btnSend.setFont(new Font("Times New Roman", Font.BOLD, 22));
		panel.add(btnSend);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, BorderLayout.CENTER);
		
		txtMessages = new JTextArea();
		txtMessages.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane_1.setViewportView(txtMessages);
		
		socket = s;
		this.sender = sender;
		this.receiver = receiver;
		try {
			bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new DataOutputStream(socket.getOutputStream());
			t = new OutputThread(s, txtMessages, sender, receiver);
			
			JMenuBar menuBar = new JMenuBar();
			scrollPane_1.setColumnHeaderView(menuBar);
			
			JMenu mnNewMenu = new JMenu("FILE");
			menuBar.add(mnNewMenu);
			
			JMenuItem mnSave = new JMenuItem("Save");
			mnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser jfc = new JFileChooser();
			        FileNameExtensionFilter text = new FileNameExtensionFilter("text", "all");
			        jfc.setFileFilter(text);
			        jfc.setMultiSelectionEnabled(false);
			        int x = jfc.showDialog(mnSave, "Save");
			        if(x == JFileChooser.APPROVE_OPTION){
			            Modify.saveAs(jfc.getSelectedFile().getPath(), txtMessages.getText());
			        }
				}
			});
			mnNewMenu.add(mnSave);
			
			JMenuItem mnOpen = new JMenuItem("Open");
			mnOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser jfc = new JFileChooser();
			        FileNameExtensionFilter text = new FileNameExtensionFilter("text","txt","text");
			        jfc.setFileFilter(text);
			        jfc.setMultiSelectionEnabled(false);
			       
			        int x = jfc.showDialog(mnOpen,"Open");
			        if(x == JFileChooser.APPROVE_OPTION){
			            txtMessages.setText(Modify.openFile(jfc.getSelectedFile().getPath()));
			        }
				}
			});
			mnNewMenu.add(mnOpen);
			t.start();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public JTextArea getTxtMessages() {
		return this.txtMessages;
	}
}
