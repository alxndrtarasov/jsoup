package programbasedonjsoup;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.imageio.ImageIO;
import javax.swing.*;

public class SoupGUI extends JFrame {
	private JTextField linkInput = new JTextField();
	private JComboBox<ParseMethod> methodChooser = new JComboBox<ParseMethod>();
	private JButton goBut = new JButton("GO");
	private JTextArea output = new JTextArea();
	private JButton explorer = new JButton("Add your own parser");
	JScrollPane scroller = new JScrollPane(output);
	private JPanel mainPanel = new JPanel(new GridLayout(4, 1));

	private void guiSettings() {
		setBounds(300, 300, 500, 200);
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Link Parser");
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		add(scroller, BorderLayout.CENTER);
		mainPanel.add(linkInput);
		mainPanel.add(explorer);
		mainPanel.add(methodChooser);
		mainPanel.add(goBut);
		add(mainPanel, BorderLayout.NORTH);
	}

	SoupGUI() throws IOException, ClassNotFoundException {
		guiSettings();
		FileInputStream fis = new FileInputStream("picparser");
		ObjectInputStream oin = new ObjectInputStream(fis);
		methodChooser.addItem(new ParseForPictures());
		methodChooser.addItem(new ParseForLinks());
		goBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					output.setText(
							methodChooser.getItemAt(methodChooser.getSelectedIndex()).parse(linkInput.getText()));
				} catch (IOException e1) {
					output.setText("Incorrect link");
				}
			}
		});
		explorer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create window for choosing an image
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new java.io.File("."));
				fileChooser.setDialogTitle("Select method");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.setAcceptAllFileFilterUsed(false);
				if (fileChooser.showOpenDialog(explorer) == JFileChooser.APPROVE_OPTION) {
					FileInputStream fis = null;
					Object deserializedObj = new Object();
					try {
						fis = new FileInputStream(fileChooser.getSelectedFile().toString());
						ObjectInputStream oin = new ObjectInputStream(fis);
						deserializedObj = oin.readObject();
						if (deserializedObj instanceof ParseMethod) 
							methodChooser.addItem((ParseMethod) deserializedObj);
					} catch (ClassNotFoundException | IOException e1) {
						JOptionPane.showMessageDialog(null, "File error!");
					}
					
				}
			}
		});
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		new SoupGUI().setVisible(true);
	}
}
