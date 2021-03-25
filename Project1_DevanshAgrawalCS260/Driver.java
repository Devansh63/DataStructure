package Project1_DevanshAgrawalCS260;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class Driver extends JFrame {
	public static void main(String[] args) throws ClassNotFoundException, IOException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		// UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		Driver gui = new Driver();
		gui.setVisible(true);

	}

	private JTextArea displayPoint;
	private JButton ChechiN;
	private JButton CheckouT;
	private JScrollPane s;
	private JTextArea entryPoint;
	private JMenuItem clear;
	private GarageSet garageObject;
	private JPanel first;
	private JPanel second;
	private JPanel third;
	private JMenuBar menuOptions;
	private JMenu filesOptions;
	private JMenuItem exittingbutton;

	public Driver() throws ClassNotFoundException, IOException {
		setSize(750, 700);
		setTitle("Garage GUI");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		getContentPane().setLayout(new GridLayout(3, 1));
		menuOptions = new JMenuBar();
		setJMenuBar(menuOptions);
		filesOptions = new JMenu("File");
		menuOptions.add(filesOptions);
		exittingbutton = new JMenuItem("Exit");
		clear = new JMenuItem("Clear");
		filesOptions.add(clear);
		filesOptions.add(exittingbutton);
		first = new JPanel();
		first.setSize(750, 500);
		getContentPane().add(first);
		entryPoint = new JTextArea();
		entryPoint.setColumns(70);
		entryPoint.setRows(10);
		entryPoint.setBorder(BorderFactory.createTitledBorder("Please Enter Your Number"));
		first.add(entryPoint);
		second = new JPanel();
		getContentPane().add(second);
		ChechiN = new JButton("Enter GArage");
		second.add(ChechiN);
		CheckouT = new JButton("Exit Garage");
		second.add(CheckouT);
		third = new JPanel();
		getContentPane().add(third);
		if (garageObject == null) {
			garageObject = new GarageSet();
		}
		displayPoint = new JTextArea();
		s = new JScrollPane(displayPoint, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		third.add(s);
		displayPoint.setColumns(70);
		displayPoint.setBorder(BorderFactory.createTitledBorder("Garage Inventory"));
		displayPoint.setRows(10);
		ButtonListener buttonListenerl = new ButtonListener();
		ChechiN.addActionListener(buttonListenerl);
		CheckouT.addActionListener(buttonListenerl);
		JMenuItemListener JMenuItemlistenerb = new JMenuItemListener();
		clear.addActionListener(JMenuItemlistenerb);
		exittingbutton.addActionListener(JMenuItemlistenerb);
	}

	private class JMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (exittingbutton == e.getSource()) {
				try {
					GarageSet.saveGsData(garageObject);
					GarageExitBag.dumpOutPutFile(garageObject.getGarageExitbag());
				} catch (IOException exception1) {
					exception1.printStackTrace();
				}
				System.exit(0);
			} else if (clear == e.getSource()) {

				displayPoint.setText("");
			}
		}
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (ChechiN == e.getSource()) {
				String data = entryPoint.getText();
				if (!entryPoint.equals("")) {
					entryPoint.setText("");
					if (!garageObject.chechkin(data)) {
						JOptionPane.showMessageDialog(null, "This car has already entered the Garage. ");
					} else {
						displayPoint.setText(garageObject.toString());
					}
				}
			} else if (CheckouT == e.getSource()) {
				String data = entryPoint.getText();
				displayPoint.setText("");
				if (!data.equals("")) {
					if (!garageObject.checkOut(data)) {
						JOptionPane.showMessageDialog(null, "This Car is not within the Garage");
					} else {
						displayPoint.setText(garageObject.toString());
					}
				}
			}
		}
	}

}
