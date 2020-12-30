package de.linus.launcher;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.linus.advancedGui.AdvFrame;

@SuppressWarnings("deprecation")
public class StartWindow extends AdvFrame{
	private static final long serialVersionUID = 2584510148484047916L;
	
	private JLabel background;
	private JTextField email;
	private JPasswordField password;
	
	@Override
	protected void init() {
		this.setIconImage(new ImageIcon(StartWindow.class.getResource("/soapICON.png")).getImage());
		this.add(background = new JLabel(new ImageIcon(StartWindow.class.getResource("/soap.png"))));
		this.setTransparent(true);
		
		Font font = new Font("Futura", Font.BOLD,12);
		email = new JTextField();
		email.setOpaque(false);
		email.setBorder(null);
		email.setText("E-Mail");
		email.setHorizontalAlignment(JTextField.CENTER);
		email.setFont(font);
		email.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(email.getText().trim().equals("E-Mail"))
					email.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(email.getText().trim().equals(""))
					email.setText("E-Mail");
			}
		});
		email.setBounds(300, 270, 220, 34);
		background.add(email);
		
		password= new JPasswordField();
		password.setOpaque(false);
		password.setBorder(null);
		password.setText("Password");
		password.setHorizontalAlignment(JTextField.CENTER);
		password.setFont(font);
		password.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(password.getText().trim().equals("Password"))
					password.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(password.getText().trim().equals(""))
					password.setText("Password");
			}
		});
		password.setBounds(300, 330, 220, 34);
		background.add(password);
		
		ImageIcon image = new ImageIcon(StartWindow.class.getResource("/onHover.png"));
		JLabel onHover = new JLabel(image);
		onHover.setVisible(false);
		onHover.setBounds(150, 68, 500, 500);
		background.add(onHover);
		JButton button = new JButton();
		button.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	onHover.setVisible(true);
		    }

		    public void mouseExited(MouseEvent evt) {
		    	onHover.setVisible(false);
		    }
		});
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CleanLauncher.getCleanLauncher().openMC();
			}
		});
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBounds(270, 360, 290, 140);
		background.add(button);
		
		ImageIcon imageCross = new ImageIcon(StartWindow.class.getResource("/cross.png"));
		JLabel onHoverCross = new JLabel(imageCross);
		onHoverCross.setVisible(false);
		onHoverCross.setBounds(148, 71, 500, 500);
		background.add(onHoverCross);
		JButton buttonCross = new JButton();
		buttonCross.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	onHoverCross.setVisible(true);
		    }

		    public void mouseExited(MouseEvent evt) {
		    	onHoverCross.setVisible(false);
		    }
		});
		buttonCross.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		buttonCross.setOpaque(false);
		buttonCross.setContentAreaFilled(false);
		buttonCross.setBorderPainted(false);
		buttonCross.setBounds(570, 187, 50, 50);
		background.add(buttonCross);
	}
	
	public String getEmail() {
		return email.getText();
	}
	
	public String getPassword() {
		return password.getText();
	}
}
