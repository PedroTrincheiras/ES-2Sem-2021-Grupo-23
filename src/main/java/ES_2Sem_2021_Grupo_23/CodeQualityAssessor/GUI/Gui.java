package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.io.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;

public class Gui extends JFrame implements ActionListener {
	private static final long serialVersionUID = 7448241899663378705L;
	private JButton choose;
	private JTextField cDirectory;
	private JButton export;
	private JTextField eDirectory;
	private JButton exportButton;

	public Gui() {
		setTitle("Metric Exporter");
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 255, 255));
		choose = new JButton("Select Directory");
		choose.setForeground(new Color(255, 255, 255));
		choose.setBackground(new Color(52, 73, 94));
		choose.setBounds(324, 64, 150, 35);
		choose.addActionListener(this);
		choose.setBorder(null);
		getContentPane().add(choose);
		
		cDirectory = new JTextField();
		cDirectory.setBackground(new Color(255, 255, 255));
		cDirectory.setBounds(10, 64, 304,35);
		cDirectory.setBorder(new LineBorder(new Color(189, 195, 199)));
		getContentPane().add(cDirectory);
		
		export = new JButton("Export Directory");
		export.setForeground(new Color(255, 255, 255));
		export.setBackground(new Color(52, 73, 94));
		export.setBounds(324, 110, 150, 35);
		export.addActionListener(this);
		export.setBorder(null);
		getContentPane().add(export);
		
		eDirectory = new JTextField();
		eDirectory.setBackground(new Color(255, 255, 255));
		eDirectory.setBounds(10, 110, 304, 35);
		eDirectory.setBorder(new LineBorder(new Color(189, 195, 199)));
		getContentPane().add(eDirectory);
		
		exportButton = new JButton("Export");
		exportButton.setForeground(new Color(255, 255, 255));
		exportButton.setBackground(new Color(52, 73, 94));
		exportButton.setBounds(10, 195, 464, 35);
		exportButton.setBorder(null);
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
		gui.setSize(500, 280);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - gui.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - gui.getHeight()) / 2);
	    gui.setLocation(x, y);
	    gui.setVisible(true);
	    gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}