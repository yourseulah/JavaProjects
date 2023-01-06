import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SecGUI extends JFrame implements ActionListener {
	JPanel atm;
	JTextField tf;
	JButton[] bt;
	JButton clear;
	JButton submit;
	static String cardNum;
	static String passWord;
	
	BankCustomer b2 = new BankCustomer();
	
	public SecGUI(String cnum) {
		this.cardNum = cnum;
		Initiate();
	}
	
	public void Initiate() {
		this.setTitle("ATM");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setFocusable(true);
		
		JLabel lblmsg = new JLabel("Enter Password");
		lblmsg.setFont(new Font("Arial", Font.BOLD, 16));
		lblmsg.setBounds(230, 40, 200, 30);
		this.getContentPane().add(lblmsg);
		
		tf = new JTextField();
		tf.setBounds(150, 90, 300, 30);
		tf.setColumns(10);
		tf.setEditable(false);
		this.getContentPane().add(tf);
		
		atm = new JPanel();
		atm.setBounds(150, 150, 300, 320);
		atm.setBackground(Color.LIGHT_GRAY);
		atm.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		bt = new JButton[10];
		for(int i = 0; i<10; i++) {
			bt[i] = new JButton(String.valueOf(i));
			bt[i].setPreferredSize(new Dimension(80,60));
			bt[i].addActionListener(this);
		}
		
		clear = new JButton("Clear");
		clear.setPreferredSize(new Dimension(100,60));
		clear.addActionListener(this);
		
		submit = new JButton("Submit");
		submit.setPreferredSize(new Dimension(100,60));
		submit.addActionListener(this);
		
		atm.add(bt[6]);
		atm.add(bt[8]);
		atm.add(bt[3]);
		atm.add(bt[4]);
		atm.add(bt[5]);
		atm.add(bt[2]);
		atm.add(bt[1]);
		atm.add(bt[7]);
		atm.add(bt[0]);
		atm.add(bt[9]);
		atm.add(clear);
		atm.add(bt[0]);
		atm.add(submit);
	
		this.getContentPane().add(atm);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean value = false;
		for (int i = 0; i < 10; i++) {
			if (e.getSource() == bt[i])
				tf.setText(tf.getText().concat(String.valueOf(i)));
		}
		if (e.getSource() == clear)
			tf.setText(" ");
		if (e.getSource() == submit) {
			passWord = tf.getText();
			if (b2.Login(cardNum, passWord)) {
				this.dispose();
			}
		}
	}
}
