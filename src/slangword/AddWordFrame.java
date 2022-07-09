package slangword;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class AddWordFrame extends JFrame implements ActionListener {
	SlangWord slangWord;
	JButton btnBack, btnAdd;
	JTextField textFieldMeaning, textFieldSlang;

	AddWordFrame() {
		// Get container & slang word
		slangWord = SlangWord.getInstance();
		Container con = this.getContentPane();
		JLabel titleLabel = new JLabel();
		titleLabel.setText("Add Slang Words");
		titleLabel.setForeground(Color.black);
		titleLabel.setFont(new Font("Sans-serif", Font.PLAIN, 35));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setPreferredSize(new Dimension(300, 100));

		// Form
		JPanel form = new JPanel();

		JPanel slagPanel = new JPanel();
		form.setBackground(Color.CYAN);
		SpringLayout layout = new SpringLayout();
		slagPanel.setLayout(layout);
		JLabel labelForSlang = new JLabel("Slang word: ");
		textFieldSlang = new JTextField("", 20);
		labelForSlang.setPreferredSize(new Dimension(100, 20));
		slagPanel.add(labelForSlang);
		slagPanel.add(textFieldSlang);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 6, SpringLayout.WEST, slagPanel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 6, SpringLayout.NORTH, slagPanel);
		layout.putConstraint(SpringLayout.WEST, textFieldSlang, 6, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, textFieldSlang, 6, SpringLayout.NORTH, slagPanel);
		layout.putConstraint(SpringLayout.EAST, slagPanel, 6, SpringLayout.EAST, textFieldSlang);
		layout.putConstraint(SpringLayout.SOUTH, slagPanel, 6, SpringLayout.SOUTH, textFieldSlang);

		JPanel meaningPanel = new JPanel();
		SpringLayout layout1 = new SpringLayout();
		meaningPanel.setLayout(layout1);
		JLabel labelForMeaning = new JLabel("Meaning: ");
		labelForMeaning.setPreferredSize(new Dimension(100, 20));
		textFieldMeaning = new JTextField("", 20);
		meaningPanel.add(labelForMeaning);
		meaningPanel.add(textFieldMeaning);
		layout1.putConstraint(SpringLayout.WEST, labelForMeaning, 6, SpringLayout.WEST, meaningPanel);
		layout1.putConstraint(SpringLayout.NORTH, labelForMeaning, 6, SpringLayout.NORTH, meaningPanel);
		layout1.putConstraint(SpringLayout.WEST, textFieldMeaning, 6, SpringLayout.EAST, labelForMeaning);
		layout1.putConstraint(SpringLayout.NORTH, textFieldMeaning, 6, SpringLayout.NORTH, meaningPanel);
		layout1.putConstraint(SpringLayout.EAST, meaningPanel, 6, SpringLayout.EAST, textFieldMeaning);
		layout1.putConstraint(SpringLayout.SOUTH, meaningPanel, 6, SpringLayout.SOUTH, textFieldMeaning);

		form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		form.add(slagPanel);
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		form.add(meaningPanel);
		// Button Back and button Add
		JPanel bottomPanel = new JPanel();
		btnBack = new JButton("Back ");
		// btnBack.addActionListener(this);
		btnBack.setFocusable(false);
		btnBack.addActionListener(this);
		btnBack.setAlignmentX(CENTER_ALIGNMENT);
		btnAdd = new JButton("Add");
		// btnBack.addActionListener(this);
		btnAdd.setFocusable(false);
		btnAdd.addActionListener(this);
		btnAdd.setAlignmentX(CENTER_ALIGNMENT);
		bottomPanel.add(btnBack);
		bottomPanel.add(btnAdd);

		// Setting content
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		con.add(titleLabel);
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		con.add(form);
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		con.add(bottomPanel);
		// Setting Frame
		this.setTitle("Add Slang word");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnBack) {
			this.dispose();
			new MenuFrame();
		} else if (e.getSource() == btnAdd) {
			String slag = textFieldSlang.getText();
			String meaning = textFieldMeaning.getText();
			if (slag.isEmpty() || meaning.isEmpty()) {
				// custom title, error icon
				JOptionPane.showMessageDialog(this, "Slag and Meaning maybe empty", "Inane error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			// System.out.println(slag + " = " + meaning);

			if (slangWord.checkSlang(slag)) {
				// Duplicate or Overwrite
				Object[] options = { "Overwrite", "Duplicate" };
				int n = JOptionPane.showOptionDialog(this,
						"Slang `" + slag + "` have already exist on  SlangWord  List", "A Silly Question",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
				if (n == 0) {
					// Overwrite
					slangWord.addOverwrite(slag, meaning);
					JOptionPane.showMessageDialog(this, "Overwrite Slang Word Success.");
				} else if (n == 1) {
					// Duplicate
					slangWord.addDuplicate(slag, meaning);
					JOptionPane.showMessageDialog(this, "Dupilicate Slang Word Success.");
				}
			} else {
				// Add new slag
				slangWord.addNew(slag, meaning);
				JOptionPane.showMessageDialog(this, "Adding new Slang Word Success.");
			}
			textFieldSlang.setText("");
			textFieldMeaning.setText("");
		}
	}

}
