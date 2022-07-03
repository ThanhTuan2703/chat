package chatapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

import database.DatabaseManager;

public class OutputThread extends Thread{
	Socket socket;
	JTextArea txt;
	BufferedReader bf;
	String sender;
	String receiver;
	
	public OutputThread(Socket socket, JTextArea txt, String sender, String receiver) {
		super();
		this.socket = socket;
		this.txt = txt;
		this.sender = sender;
		this.receiver = receiver;
		try {
			bf=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void run() {
		while(true) {
			try {
				if(socket!=null) {
					String msg = " ";
					if((msg=bf.readLine())!=null &&msg.length()>0) {
						txt.append("\n"+receiver+":"+msg);
						//DatabaseManager.saveMessage(receiver, msg);
					}
				}
				sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
