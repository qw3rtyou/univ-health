package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import facade.IDataEngine;
import facade.UIData;
import health.User;

public class TableSelectionDemo extends JPanel implements ListSelectionListener {

	private static final long serialVersionUID = 1L;

	JTable table;

	DefaultTableModel tableModel;

	int selectedIndex = -1;

	String tableTitle = null;

	IDataEngine<?> dataMgr;

	public TableSelectionDemo() {
		super(new BorderLayout());
	}

	void addComponentsToPane(IDataEngine<?> mgr) {
		init(mgr);
		JScrollPane center = new JScrollPane(table);
		add(center, BorderLayout.CENTER);
	}

	@SuppressWarnings("serial")
	void init(IDataEngine<?> mgr) {
		dataMgr = mgr;
		tableModel = new DefaultTableModel(mgr.getColumnNames(), 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		loadData("");

		table = new JTable(tableModel);
		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(this);
		table.setPreferredScrollableViewportSize(new Dimension(500, 220));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.updateUI();
		table.repaint();
	}

	void loadData(String kwd) {
		List<?> result = dataMgr.search(kwd);
		tableModel.setRowCount(0);
		for (Object m : result)
			tableModel.addRow(((UIData) m).getUiTexts());
	}

	void loadData(IDataEngine<?> dataMgr) {
		List<?> result = dataMgr.search("");
		for (Object m : result) {
			tableModel.addRow(((UIData) m).getUiTexts());
		}
	}

	void showDetail() {
		if (selectedIndex < 0)
			return;
		String[] rowTexts = new String[tableModel.getColumnCount()];
		for (int i = 0; i < rowTexts.length; i++)
			rowTexts[i] = (String) tableModel.getValueAt(selectedIndex, i);
		UserDetailDialog dlg = new UserDetailDialog(rowTexts);
		dlg.setup();
		dlg.pack();
		dlg.setVisible(true);
	}

	void addFood() {
		AddFoodDialog afp = new AddFoodDialog();
		afp.setup();
		afp.pack();
		afp.setLocationRelativeTo(null);
		afp.setVisible(true);
	}

	void addExercise() {
		AddExerciseDialog aui = new AddExerciseDialog();
		aui.setup();
		aui.pack();
		aui.setLocationRelativeTo(null);
		aui.setVisible(true);
	}

	void addUser() {
		AddUserDialog aui = new AddUserDialog();
		aui.setup();
		aui.pack();
		aui.setLocationRelativeTo(null);
		aui.setVisible(true);
	}

	void addUserFood(User user) {
		AddUserFoodDialog afp = new AddUserFoodDialog(user);
		afp.setup();
		afp.pack();
		afp.setLocationRelativeTo(null);
		afp.setVisible(true);
	}

	void addUserExercise(User user) {
		AddUserExerciseDialog afp = new AddUserExerciseDialog(user);
		afp.setup();
		afp.pack();
		afp.setLocationRelativeTo(null);
		afp.setVisible(true);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (!lsm.isSelectionEmpty()) {
			selectedIndex = lsm.getMinSelectionIndex();
			String name = (String) tableModel.getValueAt(selectedIndex, 1);
			if (tableTitle.equals("food")) {
				GUIMain.getInstance().foodTop.kwdTextField.setText(name);
			}

			else if (tableTitle.equals("exercise")) {
				GUIMain.getInstance().exerciseBottom.kwdTextField.setText(name);
			}

		}

	}

}
