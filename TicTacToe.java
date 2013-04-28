import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.text.*;

public class TicTacToe implements ActionListener
{
	private int[][] winCombinations = new int[][] {
	{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, 
	{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, 
	{0, 4, 8}, {2, 4, 6} 
	};
	private JFrame window = new JFrame("Tic-Tac-Toe game by Moonis Javed");
	private JButton buttons[] = new JButton[9];
	private int count = 0;
	private String letter = "";
	private boolean win = false;
	private static int startCount =0;
	JMenuBar menu = new JMenuBar();
	JMenuItem newGame = new JMenuItem("New Game"),
	instr = new JMenuItem("Instructions"),
	exit = new JMenuItem("Exit"),
	name = new JMenuItem("Change Name");
	static String x = "X";
	static String y = "Y";

	public TicTacToe()
	{
		//Create Window
		window.setSize(300,300);
		window.setLocationRelativeTo(null);
		window.setUndecorated(true);
		window.setExtendedState(Frame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new GridLayout(3,3));
		String a = "white";
		ImageIcon white = new ImageIcon(a+".png");
		Image img = white.getImage();  
		Image newimg = img.getScaledInstance(300,180,java.awt.Image.SCALE_SMOOTH);  
		white = new ImageIcon(newimg);
		//Add Buttons To The Window
		for(int i=0; i<=8; i++)
		{
			//window.setSize(buttons[i].getSize());
			buttons[i] = new JButton(white);
			//a.setSize(buttons[i].getHeight(),buttons[i].getWidth());
			window.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		menu.add(newGame);
		menu.add(name);
		menu.add(instr);
		menu.add(exit);
		name.addActionListener(this);
		newGame.addActionListener(this);
		exit.addActionListener(this);
		instr.addActionListener(this);
		window.setJMenuBar(menu);
		//Make The Window Visible
		window.setVisible(true);
	}

	public void setName()
	{
		x = JOptionPane.showInputDialog(null, "Enter Name of player X: ", "", 1);
		y = JOptionPane.showInputDialog(null, "Enter Name of player O: ", "", 1);
		if(x == null)
		{
			x = "X";
		}
		if(y == null)
		{
			y = "O";
		}
		if(x.length() == 0)
		{
			x = "X";
		}
		if(y.length() == 0)
		{
			y = "O";
		}
		JOptionPane.showMessageDialog(null, "Your names have been set\nTo change your names click on the Change name button in the menu bar","Name Changed!!!",JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	When an object is clicked, perform an action.
	@param a action event object
	*/
	public void actionPerformed(ActionEvent a)
	{
		Object source = a.getSource();
		//System.out.println(str1+"\t"+str2);
		if(source == newGame)
		{
			int answer = JOptionPane.showConfirmDialog(null, "Your current game will not be saved...\nContinue Anyways??", "Do you want to start a new game?", JOptionPane.YES_NO_OPTION);

			if (answer == JOptionPane.YES_OPTION)
			{
				this.clearIt();
			}
		}
		else if(source == name)
		{
			this.setName();
		}
		else if(source == instr)
		{
			JOptionPane.showMessageDialog(null, "Your goal is to be the first player to get 3 X's or O's in a row. (horizontally, diagonally, or vertically)","Instructions",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(source == exit)
		{
			int answer = JOptionPane.showConfirmDialog(null, "EXIT", "Are You sure you want to exit??", JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION)
			{
				JOptionPane.showMessageDialog(null, "Thank you " + x+ " and " + y + " for playing");
				System.exit(0);
			}
		}
		else
		{
			count++;
			ImageIcon icon;
			/*Calculate whose turn it is*/
			if(count % 2 == 0)
			{
				letter = "O";
				icon = new ImageIcon("knot.png");
			}
			else
			{
				letter = "X";
				icon = new ImageIcon("cross.png");
			}
			Image img = icon.getImage();  
			Image newimg = img.getScaledInstance(300,180,java.awt.Image.SCALE_SMOOTH);  
			icon = new ImageIcon(newimg);
			/*Write the letter to the button and deactivate it*/
			JButton pressedButton = (JButton)source;
			pressedButton.setText(letter);
			pressedButton.setDisabledIcon(icon);
			pressedButton.setEnabled(false);

			/*Determine who won*/
			for(int i=0; i<=7; i++)
			{
				if( buttons[winCombinations[i][0]].getText().equals(buttons[winCombinations[i][1]].getText()) &&
				buttons[winCombinations[i][1]].getText().equals(buttons[winCombinations[i][2]].getText()) &&
				buttons[winCombinations[i][0]].getText() != "")
				{
					win = true;
				}
			}

			/*Show a dialog when game is over*/
			if(win == true)
			{
				if(letter.equals("X"))
				{
					letter = x;
				}
				else
				{
					letter = y;
				}
				JOptionPane.showMessageDialog(null, letter + " wins the game!");
				int answer = JOptionPane.showConfirmDialog(null, "Start", "Do you want to start a new game", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION)
				{
					this.clearIt();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Thank you " + x+ " and " + y + " for playing");
				System.exit(0);
				}
			}
			else if(count == 9 && win == false)
			{
				JOptionPane.showMessageDialog(null, "The game was tie!");
				int answer = JOptionPane.showConfirmDialog(null, "Start", "Do you want to start a new game", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION)
				{
					this.clearIt();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Thank you " + x+ " and " + y + " for playing");
					System.exit(0);
				}
			}
		}
	}

	public void clearIt()
	{

		window.setVisible(false);
		//this.window = null;
		this.startIt();
	}

	public void startIt()
	{
		new TicTacToe();
	}

	public static void main(String[] args)
	{

		TicTacToe starter = new TicTacToe();
		starter.setName();
	}
}