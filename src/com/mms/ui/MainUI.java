package com.mms.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.mms.ui.common.CopyRight;
import com.mms.ui.pane.LeftPanesBox;
import com.mms.ui.tab.TabsPanel;

import com.mms.service.UserService;

public class MainUI extends JFrame {

	public MainUI() {
		super("Market Management System 2.0");
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new UserService().updateSession(UserService.getLoggedInUser().getIdutilisateur());
			}
		});
	}

	public void init() {
		JPanel copyRightPanel = new JPanel();
		copyRightPanel.setLayout(new GridLayout(1, 1));
		copyRightPanel.add(new CopyRight());

		JPanel leftPanesBoxPanel = new JPanel();
		leftPanesBoxPanel.add(LeftPanesBox.create());

		JPanel connectedUserPanel = new JPanel();
		connectedUserPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		connectedUserPanel.add(new ConnectedUserPanel());

		add(new TabsPanel(), BorderLayout.CENTER);
		add(connectedUserPanel, BorderLayout.NORTH);
		add(copyRightPanel, BorderLayout.SOUTH);
		add(new JScrollPane(leftPanesBoxPanel), BorderLayout.WEST);

		setJMenuBar(new MenuBar());

		pack();
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new MainUI().setVisible(true);
	}

}
