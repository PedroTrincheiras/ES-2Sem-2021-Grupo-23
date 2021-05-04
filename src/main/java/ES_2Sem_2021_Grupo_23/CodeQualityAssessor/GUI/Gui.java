package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.github.javaparser.utils.Pair;

import ES_2Sem_2021_Grupo23.CodeQualityAssessor.Calculate_Resume_Metrics.Calculate_Resume_Metrics;
import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Generate_XLSX_With_Metrics.Generate_XLSX_With_Metrics;
import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Rules.Rules_Storage;

import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

/**
 * 
 * @author Pedro Trincheiras and Bernardo Várzea and Filipe Barroso
 *
 */

public class Gui extends JFrame implements ActionListener {
	private static final long serialVersionUID = 7448241899663378705L;
	private JButton choose;
	private JTextField cDirectory;
	private JButton export;
	private JTextField eDirectory;
	private JButton exportButton;
	private JLabel warning_export;
	private JPanel Export;

	private JPanel Import;
	private JButton choose_import;
	private JTextField toReadImport;
	private JButton importButton;
	private JLabel warning_import;

	private JPanel Rules;
	private JTextField rule_input;
	private JButton setRuleButton;
	private JTextField rule_name;
	private JLabel operatorsLabel;
	private JLabel exampleRuleLabel;
	private JLabel ruleNameLabel;
	private JLabel ruleLabel;
	private JComboBox<String> rule_list;
	private JLabel changeRuleLabel;
	private boolean change_rule;
	private JLabel ruleStatusLabel;
	private JLabel CSRestultsLabel;
	
	private JPanel CSResults;

	private JButton ExportPageButton;
	private JButton ImportPageButton;
	private JButton RulesPageButton;
	private JButton CSResultsPageButton;
	
	private Rules_Storage rules;
	private String crname;
	private String crule;
	private JTextField fileDirectory;
	private JButton directoryButton;
	private DefaultListModel<String> l1; 
    private JList<String> codeSmellsList;
    private JButton showResults;
    private JTextPane results;

	public Gui() {
		
		rules = new Rules_Storage();
		
		CSResults = new JPanel();
		CSResults.setBackground(Color.WHITE);
		CSResults.setBounds(0, 0, 484, 461);
		getContentPane().add(CSResults);
		CSResults.setLayout(null);
		CSResults.setVisible(false);
		
		CSRestultsLabel = new JLabel("Code Smells Results");
		CSRestultsLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CSRestultsLabel.setBounds(171, 60, 142, 24);
		CSResults.add(CSRestultsLabel);
		
		directoryButton = new JButton("Select Directory");
		directoryButton.setForeground(Color.WHITE);
		directoryButton.setBorder(null);
		directoryButton.addActionListener(this);
		directoryButton.setBackground(new Color(52, 73, 94));
		directoryButton.setBounds(324, 110, 150, 35);
		CSResults.add(directoryButton);
		
		fileDirectory = new JTextField();
		fileDirectory.setBorder(new LineBorder(new Color(189, 195, 199)));
		fileDirectory.setBackground(Color.WHITE);
		fileDirectory.setBounds(10, 110, 304, 35);
		CSResults.add(fileDirectory);
		
		showResults = new JButton("Results");
		showResults.setForeground(Color.WHITE);
		showResults.setBorder(null);
		showResults.setBackground(new Color(52, 73, 94));
		showResults.setBounds(10, 385, 234, 35);
		CSResults.add(showResults);
		
		JLabel lblNewLabel = new JLabel("Code Smells");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(45, 168, 106, 24);
		CSResults.add(lblNewLabel);
		
		l1 = new DefaultListModel<>(); 
        codeSmellsList = new JList<>(l1);
		codeSmellsList.setBounds(26, 213, 122, 142);
		CSResults.add(codeSmellsList);
		
		results = new JTextPane();
		results.setBounds(254, 182, 205, 238);
		CSResults.add(results);

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

		RulesPageButton = new JButton("Rules");
		RulesPageButton.setForeground(Color.WHITE);
		RulesPageButton.setBorder(null);
		RulesPageButton.setBackground(new Color(52, 73, 94));
		RulesPageButton.setBounds(160, 0, 65, 36);
		RulesPageButton.addActionListener(this);
		Menu.add(RulesPageButton);
		
		CSResultsPageButton = new JButton("Results");
		CSResultsPageButton.setForeground(Color.WHITE);
		CSResultsPageButton.setBorder(null);
		CSResultsPageButton.setBackground(new Color(52, 73, 94));
		CSResultsPageButton.setBounds(235, 0, 65, 36);
		CSResultsPageButton.addActionListener(this);
		Menu.add(CSResultsPageButton);

		setTitle("Metric Exporter");
		getContentPane().setLayout(null);

		Import = new JPanel();
		Import.setLayout(null);
		Import.setBackground(Color.WHITE);
		Import.setBounds(0, 0, 484, 461);
		Import.setVisible(false);

		Rules = new JPanel();
		Rules.setBackground(Color.WHITE);
		Rules.setBounds(0, 0, 484, 461);
		getContentPane().add(Rules);
		Rules.setLayout(null);
		Rules.setVisible(false);

		rule_input = new JTextField();
		rule_input.setBorder(new LineBorder(new Color(189, 195, 199)));
		rule_input.setBackground(Color.WHITE);
		rule_input.setBounds(10, 258, 464, 35);
		Rules.add(rule_input);

		operatorsLabel = new JLabel("Use &&(and) ||(or)");
		operatorsLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		operatorsLabel.setBounds(154, 55, 130, 24);
		Rules.add(operatorsLabel);

		setRuleButton = new JButton("Set Rule");
		setRuleButton.setForeground(Color.WHITE);
		setRuleButton.setBorder(null);
		setRuleButton.setBackground(new Color(52, 73, 94));
		setRuleButton.setBounds(140, 339, 186, 35);
		setRuleButton.addActionListener(this);
		Rules.add(setRuleButton);

		rule_name = new JTextField();
		rule_name.setToolTipText("");
		rule_name.setBorder(new LineBorder(new Color(189, 195, 199)));
		rule_name.setBackground(Color.WHITE);
		rule_name.setBounds(10, 190, 464, 35);
		Rules.add(rule_name);

		exampleRuleLabel = new JLabel("Example : loc_method>5 && loc_class>10");
		exampleRuleLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		exampleRuleLabel.setBounds(97, 83, 284, 24);
		Rules.add(exampleRuleLabel);

		ruleNameLabel = new JLabel("Rule Name");
		ruleNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ruleNameLabel.setBounds(10, 166, 64, 24);
		Rules.add(ruleNameLabel);

		ruleLabel = new JLabel("Rule");
		ruleLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ruleLabel.setBounds(10, 233, 64, 24);
		Rules.add(ruleLabel);

		rule_list = new JComboBox<String>();
		rule_list.setBounds(10, 133, 141, 22);
		Rules.add(rule_list);

		changeRuleLabel = new JLabel("Change Rule");
		changeRuleLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		changeRuleLabel.setBounds(10, 108, 64, 24);
		Rules.add(changeRuleLabel);
		
		ruleStatusLabel = new JLabel("");
		ruleStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ruleStatusLabel.setBounds(140, 304, 186, 24);
		Rules.add(ruleStatusLabel);

		getContentPane().add(Import);

		choose_import = new JButton("Select Directory");
		choose_import.setForeground(Color.WHITE);
		choose_import.setBorder(null);
		choose_import.addActionListener(this);
		choose_import.setBackground(new Color(52, 73, 94));
		choose_import.setBounds(324, 64, 150, 35);
		Import.add(choose_import);

		toReadImport = new JTextField();
		toReadImport.setBorder(new LineBorder(new Color(189, 195, 199)));
		toReadImport.setBackground(Color.WHITE);
		toReadImport.setBounds(10, 64, 304, 35);
		Import.add(toReadImport);

		importButton = new JButton("Import");
		importButton.setForeground(Color.WHITE);
		importButton.setBorder(null);
		importButton.addActionListener(this);
		importButton.setBackground(new Color(52, 73, 94));
		importButton.setBounds(10, 110, 464, 35);
		Import.add(importButton);

		warning_import = new JLabel("");
		warning_import.setHorizontalAlignment(SwingConstants.CENTER);
		warning_import.setBounds(10, 156, 464, 125);
		Import.add(warning_import);

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
		cDirectory.setBounds(10, 148, 304, 35);
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

		warning_export = new JLabel("");
		warning_export.setHorizontalAlignment(SwingConstants.CENTER);
		warning_export.setBounds(10, 240, 464, 28);
		Export.add(warning_export);

		getContentPane().add(Export);

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

		if (e.getSource() == choose_import) {
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setMultiSelectionEnabled(false);
			fc.setFileFilter(new FileNameExtensionFilter("Files ending in .xlsx", "xlsx"));
			int option = fc.showOpenDialog(this);
			if (option == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				toReadImport.setText(file.getPath());
			} else {
				toReadImport.setText("Open command canceled");
			}
		}
		
		if (e.getSource() == directoryButton) {
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setMultiSelectionEnabled(false);
			fc.setFileFilter(new FileNameExtensionFilter("Files ending in .xlsx", "xlsx"));
			int option = fc.showOpenDialog(this);
			if (option == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				fileDirectory.setText(file.getPath());
			} else {
				fileDirectory.setText("Open command canceled");
			}
		}

		if (e.getSource() == exportButton) {
			String fromDirectory = cDirectory.getText();
			String saveDirectory = eDirectory.getText();
			try {
				Generate_XLSX_With_Metrics.generateXLSX(fromDirectory, saveDirectory);
				warning_export.setForeground(Color.GREEN);
				warning_export.setText("Successful Generated");
			} catch (IOException e1) {
				warning_export.setForeground(Color.RED);
				warning_export.setText("Error Generating the File");
			}

		}
		
		if (e.getSource() == showResults) {
			results.setText("oi");
			
		}

		if (e.getSource() == importButton) {
			String fromFile = toReadImport.getText();
			try {
				List<Pair<String, Integer>> metrics = Calculate_Resume_Metrics.readXLSX(fromFile);
				warning_import.setForeground(new Color(52, 73, 94));
				String info = "<html>";
				for (Pair<String, Integer> p : metrics) {
					info += p.a + " = " + p.b + "<br>";
				}
				info += "</html>";
				warning_import.setText(info);

			} catch (IOException e1) {
				warning_import.setForeground(Color.RED);
				warning_import.setText("Error Reading the File");
			}
		}

		if (e.getSource() == setRuleButton) {
			String rname = rule_name.getText();
			String rule = rule_input.getText();
			if (!rname.equals("") && !rule.equals("")) {
				if (change_rule) {
					if(!rname.equals(crname)) {
						rules.changeRuleName(crname, rname);
					} if(!rule.equals(crule)) {
						rules.changeRule(rname, rule);
					}
					change_rule = false;
					ruleStatusLabel.setText("Successful");
					ruleStatusLabel.setForeground(Color.green);
				} else {
					if (rules.addRule(rname, rule)) {
						ruleStatusLabel.setText("Successful");
						ruleStatusLabel.setForeground(Color.green);
					} else {
						ruleStatusLabel.setText("That rule already exists");
						ruleStatusLabel.setForeground(Color.red);
						return;
					}
				}
				rule_name.setText("");
				rule_input.setText("");
				refreshRuleList();
				try {
					rules.saveCurrentDatabase();
				} catch (Exception e1) {
					ruleStatusLabel.setText("Save Error");
					ruleStatusLabel.setForeground(Color.red);
				}
			}
		}

		if (e.getSource() == rule_list) {
			if (rule_list.getSelectedItem() != null) {
				crname = rule_list.getSelectedItem().toString();
				crule = rules.getRule(crname);
				rule_name.setText(crname);
				rule_input.setText(crule);
				change_rule = true;
			}
		}

		if (e.getSource() == ExportPageButton) {
			Import.setVisible(false);
			Export.setVisible(true);
			Rules.setVisible(false);
			CSResults.setVisible(false);
			ExportPageButton.setBackground(new Color(100, 120, 140));
			ImportPageButton.setBackground(new Color(52, 73, 94));
			RulesPageButton.setBackground(new Color(52, 73, 94));
			CSResultsPageButton.setBackground(new Color(52, 73, 94));
		}
		if (e.getSource() == ImportPageButton) {
			Export.setVisible(false);
			Import.setVisible(true);
			Rules.setVisible(false);
			CSResults.setVisible(false);
			ImportPageButton.setBackground(new Color(100, 120, 140));
			ExportPageButton.setBackground(new Color(52, 73, 94));
			RulesPageButton.setBackground(new Color(52, 73, 94));
			CSResultsPageButton.setBackground(new Color(52, 73, 94));
		}

		if (e.getSource() == RulesPageButton) {
			Export.setVisible(false);
			Import.setVisible(false);
			Rules.setVisible(true);
			CSResults.setVisible(false);
			ImportPageButton.setBackground(new Color(52, 73, 94));
			ExportPageButton.setBackground(new Color(52, 73, 94));
			RulesPageButton.setBackground(new Color(100, 120, 140));
			CSResultsPageButton.setBackground(new Color(52, 73, 94));
			refreshRuleList();
		}
		if (e.getSource() == CSResultsPageButton) {
			Export.setVisible(false);
			Import.setVisible(false);
			Rules.setVisible(false);
			CSResults.setVisible(true);
			ImportPageButton.setBackground(new Color(52, 73, 94));
			ExportPageButton.setBackground(new Color(52, 73, 94));
			RulesPageButton.setBackground(new Color(52, 73, 94));
			CSResultsPageButton.setBackground(new Color(100, 120, 140));
			refreshCodeSmellsList();
		}
	}
	
	private void refreshCodeSmellsList() {
		l1.removeAllElements();
		rules.getRulesNames().forEach(x -> l1.addElement(x));
	}
	
	private void refreshRuleList() {
		rule_list.removeActionListener(this);
		rule_list.removeAllItems();
		for (String rule : rules.getRulesNames()) {
			rule_list.addItem(rule);
		}
		rule_list.addActionListener(this);
		rule_list.setSelectedIndex(-1);
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
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}