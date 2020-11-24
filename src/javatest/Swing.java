package javatest;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class Swing extends JFrame implements ActionListener{
	
	private Container c;
	private GridLayout grid;
	private JLabel laPhone, laMsg;
	private JTextField tfPhone, tfMsg;
	private JButton btn1,btn2;
	
	public void initObject() {
		c = getContentPane();
		grid = new GridLayout(3,2);
		laPhone = new JLabel("전화번호");
		laMsg = new JLabel("메시지");
		tfPhone = new JTextField();
		tfMsg = new JTextField();
		btn1 = new JButton("전송");
		btn2 = new JButton("초기화");

		
	}
	
	public void initSetting() {
		setTitle("");
		setSize(550, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initBatch() {
		c.setLayout(grid);
		c.add(laPhone);
		c.add(tfPhone);
		c.add(laMsg);
		c.add(tfMsg);
		c.add(btn1);
		c.add(btn2);
	}
	
	public Swing() {
		initObject();
		initSetting();
		initBatch();
		
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new Swing();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		
		if(btn.getText().equals("전송")) {
			String api_key = "NCS7ETPB5OGUDFFG";
		    String api_secret = "ZJJNACVGHWXT2CIDBRHELXGGNDNR2KUC";
		    Message coolsms = new Message(api_key, api_secret);

		    // 4 params(to, from, type, text) are mandatory. must be filled
		    HashMap<String, String> params = new HashMap<String, String>();
		    params.put("to", tfPhone.getText());
		    params.put("from", "01067506785");
		    params.put("type", "SMS");
		    params.put("text", tfMsg.getText());
		    params.put("app_version", "test app 1.2"); // application name and version

		    try {
		      JSONObject obj = (JSONObject) coolsms.send(params);
		      System.out.println(obj.toString());
		    } catch (CoolsmsException e1) {
		      System.out.println(e1.getMessage());
		      System.out.println(e1.getCode());
		    }
		}
		if(btn.getText().equals("초기화")) {
			tfPhone.setText("");
			tfMsg.setText("");
		}
		
	}
	
}
