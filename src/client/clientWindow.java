package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;




public class clientWindow {

	private JFrame frame;
	private JTextField messagefield;
	private static JTextArea textarea =new JTextArea();

	private client client;	

	public static void main(String[]args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//make the window
					clientWindow window=new clientWindow();
					window.frame.setVisible(true);

				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}

		});
	}
	public clientWindow() {
		initialize();

		String name=JOptionPane.showInputDialog("ENTER NAME");
		client=new client(name,"localhost", 52864);
	}

	private void initialize() {
		frame=new JFrame();
		frame.setBounds(100, 200, 550, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		textarea.setEditable(false);

		JScrollPane scrollpane= new JScrollPane(textarea);
		frame.getContentPane().add(scrollpane);

		JPanel panel=new JPanel();
		frame.getContentPane().add(panel,BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));

		messagefield=new JTextField();
		panel.add(messagefield);
		messagefield.setColumns(50);

		JButton btnsend=new JButton("send");
		btnsend.addActionListener(e->{
			if(!messagefield.getText().equals("")) {
				client.send(messagefield.getText());
				messagefield.setText("");
			}
		});


		panel.add(btnsend);

		frame.setLocationRelativeTo(null);
	}

	public static void printToConsole(String message) {
		textarea.setText(textarea.getText()+message+"\n");

	}


}

