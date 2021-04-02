package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.GUI;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class Gui extends JFrame implements ActionListener {
	private static final long serialVersionUID = 7448241899663378705L;
	private JButton choose;
	private JTextField cDirectory;
	private JButton export;
	private JTextField eDirectory;
	private JButton exportButton;

	public Gui() {
		getContentPane().setLayout(null);
		
		choose = new JButton("Select Directory");
		choose.setBounds(324, 76, 150, 23);
		choose.addActionListener(this);
		getContentPane().add(choose);
		
		cDirectory = new JTextField();
		cDirectory.setBackground(SystemColor.controlHighlight);
		cDirectory.setBounds(10, 76, 304, 23);
		getContentPane().add(cDirectory);
		
		export = new JButton("Export Directory");
		export.setBounds(324, 127, 150, 23);
		export.addActionListener(this);
		getContentPane().add(export);
		
		eDirectory = new JTextField();
		eDirectory.setBackground(SystemColor.controlHighlight);
		eDirectory.setBounds(10, 127, 304, 23);
		getContentPane().add(eDirectory);
		
		exportButton = new JButton("Export");
		exportButton.setBounds(10, 206, 464, 23);
		getContentPane().add(exportButton);
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (e.getSource() == choose) {
			int option = fc.showOpenDialog(this);
			if (option == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				cDirectory.setText(file.getPath());
				eDirectory.setText(file.getPath());
			}
		}
		if (e.getSource() == export) {
			int option = fc.showOpenDialog(this);
			if (option == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				eDirectory.setText(file.getPath());
			} else {
				eDirectory.setText("Open command canceled");
			}
		}
		if (e.getSource() == export) {
			String fromDirectory = cDirectory.getText();
			String saveDirectory = eDirectory.getText();
			
		// TODO implement function XLSX that receive fromDirectory and saveDirectory as param
			
		}
	}
	
	public static void main(String[] args) {
		Gui gui = new Gui();
		gui.setSize(500, 300);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - gui.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - gui.getHeight()) / 2);
	    gui.setLocation(x, y);
	    gui.setVisible(true);
	    gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}