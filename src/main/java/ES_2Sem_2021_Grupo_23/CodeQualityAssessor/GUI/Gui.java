package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.InputMismatchException;

import javax.script.ScriptException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.github.javaparser.utils.Pair;

import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Calculate_Resume_Metrics.Calculate_Resume_Metrics;
import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Calculate_Indicators.CalculateIndicators;
import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.CodeSmell_Editor.CodeSmell_Editor;
import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Generate_XLSX_With_Metrics.Generate_XLSX_With_Metrics;
import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Rules.Rules_Storage;
import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Utils.ArrayListAnySize;

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
	private JTable results;
	private List<String> columnNames;
	private List<List<String>> rows;
	private JButton QualityPageButton;
	private JComboBox<String> Qrule_list;
	private JButton Qchoose_import;
	private JButton QimportButton;
	private JTextField Qimport_directory;
	private JScrollPane scrollPane;
	private JButton removeButton;

	private JPanel Quality;
	private JLabel QL1;
	private JLabel QL2;
	private JLabel QL3;
	private JLabel QL4;
	private JLabel QL5;
	private JLabel QL6;
	private JLabel QAlabel;
	private JLabel QPlabel;
	private JLabel QVPlabel;
	private JLabel QVNlabel;
	private JLabel QFNlabel;
	private JLabel QFPlabel;
	private JLabel QVPtext;
	private JLabel QVNtext;
	private JLabel QFPtext;
	private JLabel QFNtext;
	private JLabel Qwarning_import;
	private JLabel lblRuleNameMust;

	public Gui() {

		rules = new Rules_Storage();

		l1 = new DefaultListModel<>();

		columnNames = new ArrayList<String>();
		rows = new ArrayList<List<String>>();

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

		QualityPageButton = new JButton("Quality");
		QualityPageButton.setForeground(Color.WHITE);
		QualityPageButton.setBorder(null);
		QualityPageButton.addActionListener(this);
		QualityPageButton.setBackground(new Color(52, 73, 94));
		QualityPageButton.setBounds(310, 0, 65, 36);
		Menu.add(QualityPageButton);

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
		showResults.addActionListener(this);
		showResults.setBackground(new Color(52, 73, 94));
		showResults.setBounds(10, 385, 234, 35);
		CSResults.add(showResults);

		JLabel lblNewLabel = new JLabel("Select Code Smells");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(45, 168, 150, 24);
		CSResults.add(lblNewLabel);

		JScrollPane csListScroll = new JScrollPane(results, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		codeSmellsList = new JList<>(l1);
		csListScroll.setBounds(26, 213, 122, 142);
		CSResults.add(csListScroll);
		csListScroll.setViewportView(codeSmellsList);

		Rules = new JPanel();
		Rules.setBackground(Color.WHITE);
		Rules.setBounds(0, 0, 484, 461);
		getContentPane().add(Rules);
		Rules.setLayout(null);
		Rules.setVisible(false);

		rule_input = new JTextField();
		rule_input.setBorder(new LineBorder(new Color(189, 195, 199)));
		rule_input.setBackground(Color.WHITE);
		rule_input.setBounds(10, 291, 464, 35);
		Rules.add(rule_input);

		operatorsLabel = new JLabel("Use &&(and) ||(or)");
		operatorsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		operatorsLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		operatorsLabel.setBounds(10, 106, 464, 24);
		Rules.add(operatorsLabel);

		setRuleButton = new JButton("Set Rule");
		setRuleButton.setForeground(Color.WHITE);
		setRuleButton.setBorder(null);
		setRuleButton.setBackground(new Color(52, 73, 94));
		setRuleButton.setBounds(140, 385, 186, 35);
		setRuleButton.addActionListener(this);
		Rules.add(setRuleButton);

		rule_name = new JTextField();
		rule_name.setToolTipText("");
		rule_name.setBorder(new LineBorder(new Color(189, 195, 199)));
		rule_name.setBackground(Color.WHITE);
		rule_name.setBounds(10, 223, 464, 35);
		Rules.add(rule_name);

		exampleRuleLabel = new JLabel("Rule Example : loc_method>5 && loc_class>10");
		exampleRuleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		exampleRuleLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		exampleRuleLabel.setBounds(10, 81, 464, 24);
		Rules.add(exampleRuleLabel);

		ruleNameLabel = new JLabel("Rule Name");
		ruleNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ruleNameLabel.setBounds(10, 199, 64, 24);
		Rules.add(ruleNameLabel);

		ruleLabel = new JLabel("Rule");
		ruleLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ruleLabel.setBounds(10, 266, 64, 24);
		Rules.add(ruleLabel);

		rule_list = new JComboBox<String>();
		rule_list.setBounds(10, 166, 141, 22);
		Rules.add(rule_list);

		changeRuleLabel = new JLabel("Change Rule");
		changeRuleLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		changeRuleLabel.setBounds(10, 141, 64, 24);
		Rules.add(changeRuleLabel);

		ruleStatusLabel = new JLabel("");
		ruleStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ruleStatusLabel.setBounds(140, 337, 186, 24);
		Rules.add(ruleStatusLabel);

		removeButton = new JButton("Remove Selected Rule");
		removeButton.setForeground(Color.WHITE);
		removeButton.setBorder(null);
		removeButton.setBackground(new Color(52, 73, 94));
		removeButton.addActionListener(this);
		removeButton.setBounds(268, 166, 163, 22);
		Rules.add(removeButton);

		lblRuleNameMust = new JLabel("Rule name must have \"class\" or \"method\"");
		lblRuleNameMust.setHorizontalAlignment(SwingConstants.CENTER);
		lblRuleNameMust.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRuleNameMust.setBounds(10, 54, 464, 24);
		Rules.add(lblRuleNameMust);

		Quality = new JPanel();
		Quality.setBackground(Color.WHITE);
		Quality.setBounds(0, 0, 484, 461);
		getContentPane().add(Quality);
		Quality.setLayout(null);
		Quality.setVisible(false);

		Qrule_list = new JComboBox<String>();
		Qrule_list.setBounds(10, 140, 141, 22);
		Quality.add(Qrule_list);

		Qchoose_import = new JButton("Select Directory");
		Qchoose_import.setForeground(Color.WHITE);
		Qchoose_import.setBorder(null);
		Qchoose_import.addActionListener(this);
		Qchoose_import.setBackground(new Color(52, 73, 94));
		Qchoose_import.setBounds(324, 92, 150, 35);
		Quality.add(Qchoose_import);

		QimportButton = new JButton("Import");
		QimportButton.setForeground(Color.WHITE);
		QimportButton.setBorder(null);
		QimportButton.addActionListener(this);
		QimportButton.setBackground(new Color(52, 73, 94));
		QimportButton.setBounds(10, 176, 464, 35);
		Quality.add(QimportButton);

		Qimport_directory = new JTextField();
		Qimport_directory.setBorder(new LineBorder(new Color(189, 195, 199)));
		Qimport_directory.setBackground(Color.WHITE);
		Qimport_directory.setBounds(10, 92, 304, 35);
		Quality.add(Qimport_directory);

		QL1 = new JLabel("Indicators");
		QL1.setHorizontalAlignment(SwingConstants.CENTER);
		QL1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		QL1.setBounds(10, 252, 464, 26);
		Quality.add(QL1);

		QL2 = new JLabel("CodeSmells Quality");
		QL2.setHorizontalAlignment(SwingConstants.CENTER);
		QL2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		QL2.setBounds(10, 51, 464, 26);
		Quality.add(QL2);

		QL3 = new JLabel("True");
		QL3.setHorizontalAlignment(SwingConstants.CENTER);
		QL3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		QL3.setBounds(51, 369, 74, 23);
		Quality.add(QL3);

		QL4 = new JLabel("False");
		QL4.setHorizontalAlignment(SwingConstants.CENTER);
		QL4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		QL4.setBounds(51, 403, 74, 23);
		Quality.add(QL4);

		QL5 = new JLabel("True");
		QL5.setHorizontalAlignment(SwingConstants.CENTER);
		QL5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		QL5.setBounds(128, 331, 74, 23);
		Quality.add(QL5);

		QL6 = new JLabel("False");
		QL6.setHorizontalAlignment(SwingConstants.CENTER);
		QL6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		QL6.setBounds(212, 331, 74, 23);
		Quality.add(QL6);

		QAlabel = new JLabel("Actual");
		QAlabel.setBounds(10, 389, 46, 14);
		Quality.add(QAlabel);

		QPlabel = new JLabel("Predicted");
		QPlabel.setBounds(180, 306, 61, 14);
		Quality.add(QPlabel);

		QVPlabel = new JLabel("x");
		QVPlabel.setHorizontalAlignment(SwingConstants.CENTER);
		QVPlabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		QVPlabel.setBounds(128, 369, 74, 23);
		Quality.add(QVPlabel);

		QFPlabel = new JLabel("x");
		QFPlabel.setHorizontalAlignment(SwingConstants.CENTER);
		QFPlabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		QFPlabel.setBounds(128, 403, 74, 23);
		Quality.add(QFPlabel);

		QFNlabel = new JLabel("x");
		QFNlabel.setHorizontalAlignment(SwingConstants.CENTER);
		QFNlabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		QFNlabel.setBounds(212, 369, 74, 23);
		Quality.add(QFNlabel);

		QVNlabel = new JLabel("x");
		QVNlabel.setHorizontalAlignment(SwingConstants.CENTER);
		QVNlabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		QVNlabel.setBounds(212, 403, 74, 23);
		Quality.add(QVNlabel);

		Qwarning_import = new JLabel("");
		Qwarning_import.setHorizontalAlignment(SwingConstants.CENTER);
		Qwarning_import.setBounds(10, 222, 464, 26);
		Quality.add(Qwarning_import);

		QVPtext = new JLabel("VP = ?");
		QVPtext.setFont(new Font("Tahoma", Font.PLAIN, 13));
		QVPtext.setBounds(343, 336, 88, 22);
		Quality.add(QVPtext);

		QVNtext = new JLabel("VN = ?");
		QVNtext.setFont(new Font("Tahoma", Font.PLAIN, 13));
		QVNtext.setBounds(343, 361, 88, 22);
		Quality.add(QVNtext);

		QFPtext = new JLabel("FP = ?");
		QFPtext.setFont(new Font("Tahoma", Font.PLAIN, 13));
		QFPtext.setBounds(343, 383, 88, 22);
		Quality.add(QFPtext);

		QFNtext = new JLabel("FN = ?");
		QFNtext.setFont(new Font("Tahoma", Font.PLAIN, 13));
		QFNtext.setBounds(343, 404, 88, 22);
		Quality.add(QFNtext);

		setTitle("Metric Exporter");
		getContentPane().setLayout(null);

		Import = new JPanel();
		Import.setLayout(null);
		Import.setBackground(Color.WHITE);
		Import.setBounds(0, 0, 484, 461);
		Import.setVisible(false);

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

		String[][] finalData = rows.stream().map(arr -> arr.toArray(String[]::new)).toArray(String[][]::new);
		results = new JTable(finalData, columnNames.toArray());

//		scrollPane = new JScrollPane(results, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		results.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); scrollPane.setBounds(274,168, 200, 270); CSResults.add(scrollPane);
//		scrollPane.setViewportView(results);

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
			getCodeSmells();
		}
		
		if (e.getSource() == removeButton) {
			rules.removeRule(rule_list.getSelectedItem().toString());
			refreshRuleList(rule_list);
			rule_name.setText("");
			rule_input.setText("");
			try {
				rules.saveCurrentDatabase();
				ruleStatusLabel.setText("Successfully Removed");
				ruleStatusLabel.setForeground(Color.green);
			} catch (Exception e1) {
				ruleStatusLabel.setText("Removal Error");
				ruleStatusLabel.setForeground(Color.red);
			}
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

		if (e.getSource() == Qrule_list) {
			if (Qrule_list.getSelectedItem() != null) {
				crname = Qrule_list.getSelectedItem().toString();
				crule = rules.getRule(crname);
				rule_name.setText(crname);
				rule_input.setText(crule);
				change_rule = true;
			}
		}

		if (e.getSource() == Qchoose_import) {
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setMultiSelectionEnabled(false);
			fc.setFileFilter(new FileNameExtensionFilter("Files ending in .xlsx", "xlsx"));
			int option = fc.showOpenDialog(this);
			if (option == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				Qimport_directory.setText(file.getPath());
			} else {
				Qimport_directory.setText("Open command canceled");
			}
		}

		if (e.getSource() == QimportButton) {
			if (Qrule_list.getSelectedItem() != null) {
				String rname = Qrule_list.getSelectedItem().toString();
				String rule = rules.getRule(rname);

				String fromFile = Qimport_directory.getText();
				try {
					List<Pair<String, Integer>> indicators = CalculateIndicators
							.countIndicators(CalculateIndicators.getIndicators(fromFile, rule, rname));
					QVPlabel.setText(indicators.get(0).b.toString());
					QVPtext.setText("VP = " + indicators.get(0).b.toString());
					QFPlabel.setText(indicators.get(1).b.toString());
					QFPtext.setText("FP = " + indicators.get(1).b.toString());
					QVNlabel.setText(indicators.get(2).b.toString());
					QVNtext.setText("VN = " + indicators.get(2).b.toString());
					QFNlabel.setText(indicators.get(3).b.toString());
					QFNtext.setText("FN = " + indicators.get(3).b.toString());

					Qwarning_import.setForeground(Color.GREEN);
					Qwarning_import.setText("Sucessful");
				} catch (InputMismatchException e1) {
					Qwarning_import.setForeground(Color.RED);
					Qwarning_import.setText("Rule must have class or method on its name");
				} catch (IOException e2) {
					Qwarning_import.setForeground(Color.RED);
					Qwarning_import.setText("Error Reading the File");
				} catch(IllegalArgumentException e3) {
					Qwarning_import.setForeground(Color.RED);
					Qwarning_import.setText("Rule Not Found on File");
				}
			}
		}

		if (e.getSource() == setRuleButton) {
			String rname = rule_name.getText();
			String rule = rule_input.getText();
			if (!rname.equals("") && !rule.equals("")) {
				if (change_rule) {
					if (!rname.equals(crname)) {
						rules.changeRuleName(crname, rname);
					}
					if (!rule.equals(crule)) {
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
				refreshRuleList(rule_list);
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
			Quality.setVisible(false);
			ExportPageButton.setBackground(new Color(100, 120, 140));
			ImportPageButton.setBackground(new Color(52, 73, 94));
			RulesPageButton.setBackground(new Color(52, 73, 94));
			CSResultsPageButton.setBackground(new Color(52, 73, 94));
			QualityPageButton.setBackground(new Color(52, 73, 94));
		}
		if (e.getSource() == ImportPageButton) {
			Export.setVisible(false);
			Import.setVisible(true);
			Rules.setVisible(false);
			CSResults.setVisible(false);
			Quality.setVisible(false);
			ImportPageButton.setBackground(new Color(100, 120, 140));
			ExportPageButton.setBackground(new Color(52, 73, 94));
			RulesPageButton.setBackground(new Color(52, 73, 94));
			CSResultsPageButton.setBackground(new Color(52, 73, 94));
			QualityPageButton.setBackground(new Color(52, 73, 94));
		}

		if (e.getSource() == RulesPageButton) {
			Export.setVisible(false);
			Import.setVisible(false);
			Rules.setVisible(true);
			CSResults.setVisible(false);
			Quality.setVisible(false);
			ImportPageButton.setBackground(new Color(52, 73, 94));
			ExportPageButton.setBackground(new Color(52, 73, 94));
			RulesPageButton.setBackground(new Color(100, 120, 140));
			CSResultsPageButton.setBackground(new Color(52, 73, 94));
			QualityPageButton.setBackground(new Color(52, 73, 94));
			ruleStatusLabel.setText("");
			refreshRuleList(rule_list);
		}
		if (e.getSource() == CSResultsPageButton) {
			Export.setVisible(false);
			Import.setVisible(false);
			Rules.setVisible(false);
			CSResults.setVisible(true);
			Quality.setVisible(false);
			ImportPageButton.setBackground(new Color(52, 73, 94));
			ExportPageButton.setBackground(new Color(52, 73, 94));
			RulesPageButton.setBackground(new Color(52, 73, 94));
			CSResultsPageButton.setBackground(new Color(100, 120, 140));
			QualityPageButton.setBackground(new Color(52, 73, 94));
			refreshCodeSmellsList();
		}
		if (e.getSource() == QualityPageButton) {
			Export.setVisible(false);
			Import.setVisible(false);
			Rules.setVisible(false);
			CSResults.setVisible(false);
			Quality.setVisible(true);
			ImportPageButton.setBackground(new Color(52, 73, 94));
			ExportPageButton.setBackground(new Color(52, 73, 94));
			RulesPageButton.setBackground(new Color(52, 73, 94));
			CSResultsPageButton.setBackground(new Color(52, 73, 94));
			QualityPageButton.setBackground(new Color(100, 120, 140));
			refreshRuleList(Qrule_list);
		}
	}

	private void getCodeSmells() {
		columnNames.clear();
		rows.clear();
		columnNames.add("Identification");
		List<String> csNames = codeSmellsList.getSelectedValuesList();
		columnNames.addAll(csNames);
		Map<String, List<String>> map = new TreeMap<>();
		try {
			for (int i = 0; i < csNames.size(); i++) {
				List<Pair<String, Boolean>> cs = CodeSmell_Editor.getCodeSmellsResults(rules.getRule(csNames.get(i)),
						csNames.get(i), fileDirectory.getText());

				for (Pair<String, Boolean> x : cs) {
					if (map.get(x.a) != null) {
						List<String> l = map.get(x.a);
						l.add(i, x.b.toString());
						map.put(x.a, l);
					} else {
						List<String> l = new ArrayListAnySize<>();
						l.add(i, x.b.toString());
						map.put(x.a, l);
					}
				}
			}
			for (Map.Entry<String, List<String>> me : map.entrySet()) {
				List<String> na = new ArrayListAnySize<>();
				na = me.getValue();
				if (na.size() < csNames.size() + 1) {
					na.add((csNames.size() + 1), " ");
				}
				na.add(0, me.getKey());
				rows.add(na);
			}
			String[][] finalData = rows.stream().map(arr -> arr.toArray(String[]::new)).toArray(String[][]::new);
			results = new JTable(finalData, columnNames.toArray());
			results.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			scrollPane.setViewportView(results);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void refreshCodeSmellsList() {
		l1.removeAllElements();
		rules.getRulesNames().forEach(x -> l1.addElement(x));
	}

	private void refreshRuleList(JComboBox<String> rlist) {
		rlist.removeActionListener(this);
		rlist.removeAllItems();
		for (String rule : rules.getRulesNames()) {
			rlist.addItem(rule);
		}
		rlist.addActionListener(this);
		rlist.setSelectedIndex(-1);
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