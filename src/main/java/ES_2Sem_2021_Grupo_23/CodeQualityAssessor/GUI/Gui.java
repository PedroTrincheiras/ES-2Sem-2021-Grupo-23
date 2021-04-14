package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Generate_XLSX_With_Metrics.Generate_XLSX_With_Metrics;

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
	private JLabel warning;
	private JPanel Export;
	
	private JPanel Import;
	private JButton choose_1;
	private JTextField textField;
	private JButton exportButton_1;
	private JLabel warning_1;
	private JButton ExportPageButton;
	private JButton ImportPageButton;

	public Gui() {
		
		JPanel Menu = new JPanel();
		Menu.setBackground(new Color(255, 255, 255));
		Menu.setBounds(0, 0, 484, 36);
		getContentPane().add(Menu);
		Menu.setLayout(null);
		
		ExportPageButton = new JButton("Export");
		ExportPageButton.setForeground(new Color(255, 255, 255));
		ExportPageButton.setBackground(new Color(100, 120, 140));
		ExportPageButton.setBounds(10, 0, 65, 36);
		ExportPageButton.addActionListener(this);
		ExportPageButton.setBorder(null);
		Menu.add(ExportPageButton);
		
		ImportPageButton = new JButton("Import");
		ImportPageButton.setForeground(new Color(255, 255, 255));
		ImportPageButton.setBackground(new Color(52, 73, 94));
		ImportPageButton.setBounds(85, 0, 65, 36);
		ImportPageButton.addActionListener(this);
		ImportPageButton.setBorder(null);
		Menu.add(ImportPageButton);
		
		setTitle("Metric Exporter");
		getContentPane().setLayout(null);
		
		Export = new JPanel();
		Export.setBounds(0, 0, 484, 461);
		Export.setLayout(null);
		Export.setBackground(new Color(255, 255, 255));
		choose = new JButton("Select Directory");
		choose.setForeground(new Color(255, 255, 255));
		choose.setBackground(new Color(52, 73, 94));
		choose.setBounds(324, 148, 150, 35);
		choose.addActionListener(this);
		choose.setBorder(null);
		Export.add(choose);
		
		cDirectory = new JTextField();
		cDirectory.setBackground(new Color(255, 255, 255));
		cDirectory.setBounds(10, 148, 304,35);
		cDirectory.setBorder(new LineBorder(new Color(189, 195, 199)));
		Export.add(cDirectory);
		
		export = new JButton("Export Directory");
		export.setForeground(new Color(255, 255, 255));
		export.setBackground(new Color(52, 73, 94));
		export.setBounds(324, 194, 150, 35);
		export.addActionListener(this);
		export.setBorder(null);
		Export.add(export);
		
		eDirectory = new JTextField();
		eDirectory.setBackground(new Color(255, 255, 255));
		eDirectory.setBounds(10, 194, 304, 35);
		eDirectory.setBorder(new LineBorder(new Color(189, 195, 199)));
		Export.add(eDirectory);
		
		exportButton = new JButton("Export");
		exportButton.setForeground(new Color(255, 255, 255));
		exportButton.setBackground(new Color(52, 73, 94));
		exportButton.setBounds(10, 279, 464, 35);
		exportButton.setBorder(null);
		exportButton.addActionListener(this);
		Export.add(exportButton);
		
		warning = new JLabel("");
		warning.setHorizontalAlignment(SwingConstants.CENTER);
		warning.setBounds(10, 240, 464, 28);
		Export.add(warning);
		
		getContentPane().add(Export);
		
		Import = new JPanel();
		Import.setLayout(null);
		Import.setBackground(Color.WHITE);
		Import.setBounds(0, 0, 484, 461);
		Import.setVisible(false);
		getContentPane().add(Import);
		
		choose_1 = new JButton("Select Directory");
		choose_1.setForeground(Color.WHITE);
		choose_1.setBorder(null);
		choose_1.setBackground(new Color(52, 73, 94));
		choose_1.setBounds(324, 64, 150, 35);
		Import.add(choose_1);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(new Color(189, 195, 199)));
		textField.setBackground(Color.WHITE);
		textField.setBounds(10, 64, 304, 35);
		Import.add(textField);
		
		exportButton_1 = new JButton("Import");
		exportButton_1.setForeground(Color.WHITE);
		exportButton_1.setBorder(null);
		exportButton_1.setBackground(new Color(52, 73, 94));
		exportButton_1.setBounds(10, 110, 464, 35);
		Import.add(exportButton_1);
		
		warning_1 = new JLabel("");
		warning_1.setHorizontalAlignment(SwingConstants.CENTER);
		warning_1.setBounds(10, 156, 464, 28);
		Import.add(warning_1);
		
		
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
		if (e.getSource() == exportButton) {
			String fromDirectory = cDirectory.getText();
			String saveDirectory = eDirectory.getText();
			try {
				Generate_XLSX_With_Metrics.generateXLSX(fromDirectory, saveDirectory);
				warning.setForeground(Color.GREEN);
				warning.setText("Succeful Generated");
			} catch (IOException e1) {
				warning.setForeground(Color.RED);
				warning.setText("Error Generating the File");
			}
			
		}
		
		if (e.getSource() == ExportPageButton) {
			Import.setVisible(false);
			Export.setVisible(true);
			ExportPageButton.setBackground(new Color(100, 120, 140));
			ImportPageButton.setBackground(new Color(52, 73, 94));
		}
		if(e.getSource()==ImportPageButton) {
			Export.setVisible(false);
			Import.setVisible(true);
			ImportPageButton.setBackground(new Color(100, 120, 140));
			ExportPageButton.setBackground(new Color(52, 73, 94));
		}
	}
	
	public static void main(String[] args) {
		Gui gui = new Gui();
		gui.setSize(500, 500);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - gui.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - gui.getHeight()) / 2);
	    gui.setLocation(x, y);
	    gui.setVisible(true);
	    gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}