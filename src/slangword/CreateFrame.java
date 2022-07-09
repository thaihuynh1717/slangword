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

public class CreateFrame extends JFrame implements ActionListener {
	SlangWord slangWord;
	JButton btnBack, btnAdd;
	JTextField textFieldDefinition, textFieldSlang;

	CreateFrame() {
		// Get container & slang word
		slangWord = SlangWord.getInstance();
		Container con = this.getContentPane();
		JLabel titleLabel = new JLabel();
		titleLabel.setText("CREATE SLANG WORD");
		titleLabel.setForeground(Color.orange);
		titleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 24));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setPreferredSize(new Dimension(300, 100));

		// Form
		JPanel form = new JPanel();

		JPanel slangwordPanel = new JPanel();
		form.setBackground(Color.black);
		SpringLayout layout = new SpringLayout();
		slangwordPanel.setLayout(layout);
		JLabel labelForSlang = new JLabel("Slang word: ");
		textFieldSlang = new JTextField("", 20);
		labelForSlang.setPreferredSize(new Dimension(100, 20));
		slangwordPanel.add(labelForSlang);
		slangwordPanel.add(textFieldSlang);
		layout.putConstraint(SpringLayout.WEST, labelForSlang, 6, SpringLayout.WEST, slangwordPanel);
		layout.putConstraint(SpringLayout.NORTH, labelForSlang, 6, SpringLayout.NORTH, slangwordPanel);
		layout.putConstraint(SpringLayout.WEST, textFieldSlang, 6, SpringLayout.EAST, labelForSlang);
		layout.putConstraint(SpringLayout.NORTH, textFieldSlang, 6, SpringLayout.NORTH, slangwordPanel);
		layout.putConstraint(SpringLayout.EAST, slangwordPanel, 6, SpringLayout.EAST, textFieldSlang);
		layout.putConstraint(SpringLayout.SOUTH, slangwordPanel, 6, SpringLayout.SOUTH, textFieldSlang);

		JPanel definitionPanel = new JPanel();
		SpringLayout layout1 = new SpringLayout();
		definitionPanel.setLayout(layout1);
		JLabel labelForDefinition = new JLabel("Definition: ");
		labelForDefinition.setPreferredSize(new Dimension(100, 20));
		textFieldDefinition = new JTextField("", 20);
		definitionPanel.add(labelForDefinition);
		definitionPanel.add(textFieldDefinition);
		layout1.putConstraint(SpringLayout.WEST, labelForDefinition, 6, SpringLayout.WEST, definitionPanel);
		layout1.putConstraint(SpringLayout.NORTH, labelForDefinition, 6, SpringLayout.NORTH, definitionPanel);
		layout1.putConstraint(SpringLayout.WEST, textFieldDefinition, 6, SpringLayout.EAST, labelForDefinition);
		layout1.putConstraint(SpringLayout.NORTH, textFieldDefinition, 6, SpringLayout.NORTH, definitionPanel);
		layout1.putConstraint(SpringLayout.EAST, definitionPanel, 6, SpringLayout.EAST, textFieldDefinition);
		layout1.putConstraint(SpringLayout.SOUTH, definitionPanel, 6, SpringLayout.SOUTH, textFieldDefinition);

		form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		form.add(slangwordPanel);
		con.add(Box.createRigidArea(new Dimension(0, 10)));
		form.add(definitionPanel);

		// Button Back and button Add
		JPanel bottomPanel = new JPanel();
		btnBack = new JButton("Back ");
		btnBack.setFocusable(false);
		btnBack.addActionListener(this);
		btnBack.setAlignmentX(CENTER_ALIGNMENT);
		btnAdd = new JButton("Create");
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
		this.setTitle("Create Slang word");
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
			String slangword = textFieldSlang.getText();
			String definition = textFieldDefinition.getText();
			if (slangword.isEmpty() || definition.isEmpty()) {
				// custom title, error icon
				JOptionPane.showMessageDialog(this, "SlangWord and Definition maybe empty", "Inane error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			System.out.println(slangword + " = " + definition);

			if (slangWord.checkSlang(slangword)) {
				// Duplicate or Overwrite
				Object[] options = { "Overwrite", "Duplicate" };
				int n = JOptionPane.showOptionDialog(this,
						"Slang `" + slangword + "` have already exist on  SlangWord  List", "A Silly Question",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
				if (n == 0) {
					// Overwrite
					slangWord.addOverwrite(slangword, definition);
					JOptionPane.showMessageDialog(this, "Overwrite Slang Word Success.");
				} else if (n == 1) {
					// Duplicate
					slangWord.addDuplicate(slangword, definition);
					JOptionPane.showMessageDialog(this, "Dupilicate Slang Word Success.");
				}
			} else {
				// Add new slangword
				slangWord.addNew(slangword, definition);
				JOptionPane.showMessageDialog(this, "Adding new Slang Word Success.");
			}
			textFieldSlang.setText("");
			textFieldDefinition.setText("");
		}
	}

}
