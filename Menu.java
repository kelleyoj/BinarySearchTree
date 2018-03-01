import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;

public class Menu extends JFrame {

	private JPanel contentPane;

	public Menu(BinarySearchTree tree)
	{
		//editing the jpanel
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new CompoundBorder());
		setContentPane(contentPane);	
		contentPane.setLayout(null);
		
		//creating the buttons
		JButton buttonInsert = new JButton("Add");
		JButton buttonDelete = new JButton("Delete Node");
		JButton buttonSearch = new JButton("Delete Tree");
		JButton buttonDisplay = new JButton("Display");
		JButton buttonExit = new JButton("Exit"); 
		JRadioButton radioInorder = new JRadioButton("In-Fix");
		JRadioButton radioPostOrder = new JRadioButton("Post-Fix"); 
		JRadioButton radioPreOrder = new JRadioButton("Pre-Fix");
		JLabel labelBinarysearchtree = new JLabel("BinarySearchTree");
		JButton buttonDraw = new JButton("Draw");
		
		//adding buttons to the jpanel
		contentPane.add(buttonInsert);
		contentPane.add(buttonDelete);
		contentPane.add(buttonSearch);
		contentPane.add(buttonDisplay);
		contentPane.add(buttonExit);
		contentPane.add(radioInorder);
		contentPane.add(radioPostOrder);
		contentPane.add(radioPreOrder);
		contentPane.add(labelBinarysearchtree);
		contentPane.add(buttonDraw);
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(radioInorder);
		radioGroup.add(radioPreOrder);
		radioGroup.add(radioPostOrder);
		labelBinarysearchtree.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
		
		//setting location of the buttons on the jpanel
		buttonInsert.setBounds(6, 243, 97, 29);
		buttonDelete.setBounds(115, 202, 109, 29);
        buttonDelete.setBounds(115, 202, 109, 29);
		buttonSearch.setBounds(224, 202, 97, 29);
		buttonDisplay.setBounds(115, 243, 103, 29);
		buttonDisplay.setEnabled(false);	
		buttonExit.setBounds(333, 243, 97, 29);
		radioInorder.setBounds(42, 6, 77, 23);
		radioPostOrder.setBounds(144, 6, 109, 23);
		radioPreOrder.setBounds(283, 6, 97, 23);
		radioPreOrder.setBounds(283, 6, 97, 23);
		radioPreOrder.setBounds(283, 6, 97, 23);
        labelBinarysearchtree.setBounds(144, 41, 189, 29);
		buttonDraw.setBounds(224, 243, 109, 29);

		/*
		 * Adding action listeners to the buttons
		 */
		
		buttonInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int stayOn = 0;
				while(stayOn == 0)
				{
					String toInsert = JOptionPane.showInputDialog(null, "Enter a string");
					if(toInsert.length() < 6)
					{
						tree.insert(toInsert);
						stayOn = 1;
					}
						
					else
					{
						JOptionPane.showMessageDialog(null, "Error string longer than 6 characters. Try again");
						
					}
				}
				
			}
		});
		
		
		buttonDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				String toDelete = JOptionPane.showInputDialog(null, "Enter a Node to be deleted");
				JOptionPane.showMessageDialog(null, tree.delete(toDelete));
				
			}
		});

		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to Destroy the tree!", "Delete Tree",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				if(input==0)
					JOptionPane.showMessageDialog(null, tree.destroy());
				else
				{
					JOptionPane.showMessageDialog(null, "Phew!! You did the right thing." + " \u270C");
				}
			}
		});
		
		
		buttonDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				JFrame displayFrame = new JFrame("Tree contents");
				JButton done = new JButton("Done");
				JTextArea textArea = new JTextArea();
				JPanel buttonPanel = new JPanel();
				JLabel theLabel = new JLabel("Tree Contents", JLabel.CENTER);
				JScrollPane scrollPane = new JScrollPane(textArea);
				
				buttonPanel.add(done);
				
				if(radioInorder.isSelected())
				{
					textArea.setText(tree.inOrder());
				}
				else if(radioPostOrder.isSelected())
				{
					textArea.setText(tree.postOrder());
				}
				else if(radioPreOrder.isSelected())
				{
					textArea.setText(tree.preOrder());
				}
				else
					buttonDisplay.setEnabled(false);
				
				done.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						displayFrame.dispose();
					}
				});
				
				displayFrame.add(theLabel, BorderLayout.PAGE_START);
				displayFrame.add(scrollPane, BorderLayout.CENTER);
				displayFrame.add(buttonPanel, BorderLayout.PAGE_END);
				displayFrame.setSize(300, 500);
				displayFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				displayFrame.setVisible(true);
					
			}
		});
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		
		radioInorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(radioInorder.isSelected())
					buttonDisplay.setEnabled(true);
			}
		});
		
		radioPostOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(radioPostOrder.isSelected())
					buttonDisplay.setEnabled(true);
			}
		});
		
		radioPreOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(radioPreOrder.isSelected())
					buttonDisplay.setEnabled(true);
			}
		});
	}
}