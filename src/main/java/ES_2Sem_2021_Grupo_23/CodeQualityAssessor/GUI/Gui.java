package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.GUI;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Gui extends JFrame implements ActionListener {
	private static final long serialVersionUID = 7448241899663378705L;
	private JButton open;
	private JLabel directory;

	public Gui() {
		open = new JButton("Select Directory");
		open.addActionListener(this);
		getContentPane().add(open, BorderLayout.SOUTH);
		directory = new JLabel("");
		directory.setVerticalAlignment(SwingConstants.CENTER);
		directory.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(directory, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == open) {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = fc.showOpenDialog(this);
			if (option == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				directory.setText("Directory: " + file.getPath());
			} else {
				directory.setText("Open command canceled");
			}
		}
	}

	public static void main(String[] args) {
		Gui gui = new Gui();
		gui.setSize(500, 200);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - gui.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - gui.getHeight()) / 2);
	    gui.setLocation(x, y);
	    gui.setVisible(true);
	    gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}