package ru.restfulrobot.stock;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.GridLayout;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;

import ru.restfulrobot.stock.database.FileCreater;
import ru.restfulrobot.stock.structure.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class StockMainView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBoxCategory;
	private JLabel lblDescriptionCategory;
	private JLabel lblDescriptionSubCategory;
	private JComboBox comboBoxSubCategory;
	private SubCategory currentSubCategory;
	private JLabel lblChangeStatus;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnAddNewGoods;
	private Stock stock;

	public StockMainView(final Stock stock) {
		if (stock == null) {
			System.exit(0);
		}
		setBackground(Color.WHITE);
		setTitle("\u041F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u0430 \"\u0421\u043A\u043B\u0430\u0434\"");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 600);
		// ������������� ����������� ����
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnNewMenu_1 = new JMenu("\u0424\u0430\u0439\u043B");
		menuBar.add(mnNewMenu_1);
		JMenuItem menuItem_3 = new JMenuItem("\u0412\u044B\u0445\u043E\u0434");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnNewMenu_1.add(menuItem_3);
		JMenu menu = new JMenu(
				"\u0411\u0430\u0437\u0430 \u0434\u0430\u043D\u043D\u044B\u0445");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem(
				"\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C \u0438\u0437\u043C\u0435\u043D\u0435\u043D\u0438\u044F");
		menu.add(menuItem);
		JMenuItem mntmNewMenuItem = new JMenuItem(
				"\u041E\u0442\u043C\u0435\u043D\u0438\u0442\u044C \u0432\u0441\u0435 \u0438\u0437\u043C\u0435\u043D\u0435\u043D\u0438\u044F");
		menu.add(mntmNewMenuItem);
		JMenu mnNewMenu = new JMenu(
				"\u0420\u0435\u0434\u0430\u043A\u0442\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u0435 \u0440\u0430\u0437\u0434\u0435\u043B\u043E\u0432");
		menuBar.add(mnNewMenu);
		JMenuItem menuItem_1 = new JMenuItem(
				"\u041A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438");
		mnNewMenu.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem(
				"\u041F\u043E\u0434\u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438");
		mnNewMenu.add(menuItem_2);
		JMenu menu_1 = new JMenu("\u041E\u0442\u0447\u0435\u0442\u044B");
		menuBar.add(menu_1);

		JMenuItem menuItem_4 = new JMenuItem(
				"\u041E\u0431\u0449\u0438\u0439 \u043E\u0442\u0447\u0435\u0442 \u043F\u043E \u0441\u043A\u043B\u0430\u0434\u0443");
		menu_1.add(menuItem_4);
		JMenu mnNewMenu_2 = new JMenu(
				"\u0418\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0438\u044F");
		menuBar.add(mnNewMenu_2);
		JMenuItem mntmNewMenuItem_1 = new JMenuItem(
				"\u041E \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u0435");
		mnNewMenu_2.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(3, 3, 20, 20));
		comboBoxCategory = new JComboBox();
		JLabel lblEmpty = new JLabel("");
		panel.add(lblEmpty);
		JLabel lblName = new JLabel(
				"\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblName);
		JLabel lblNewLabel_1 = new JLabel(
				"\u041A\u0440\u0430\u0442\u043A\u043E\u0435 \u043E\u043F\u0438\u0441\u0430\u043D\u0438\u0435:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		JLabel lblCategory = new JLabel(
				"\u041A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044F:");
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblCategory);
		comboBoxCategory.setModel(new CategoryComboBoxModel());
		panel.add(comboBoxCategory);
		lblDescriptionCategory = new JLabel("Description Category");
		lblDescriptionCategory.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDescriptionCategory);
		comboBoxSubCategory = new JComboBox();
		JLabel lblSubCategory = new JLabel(
				"\u041F\u043E\u0434\u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044F:");
		lblSubCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblSubCategory);
		comboBoxSubCategory.setModel(new SubCategoryComboBoxModel());
		panel.add(comboBoxSubCategory);
		lblDescriptionSubCategory = new JLabel("Description Sub Category");
		lblDescriptionSubCategory.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDescriptionSubCategory);
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setModel(new TableModel());
		table.getColumnModel().getColumn(0).setPreferredWidth(24);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_1.add(scrollPane);
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_2.add(panel_3);
		btnEdit = new JButton(
				"\u0420\u0435\u0434\u0430\u043A\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C");
		panel_3.add(btnEdit);
		btnDelete = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		panel_3.add(btnDelete);
		btnAddNewGoods = new JButton(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043D\u043E\u0432\u044B\u0439 \u0442\u043E\u0432\u0430\u0440");
		panel_3.add(btnAddNewGoods);
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_2.add(panel_4);
		JLabel lblStatus = new JLabel(
				"\u0421\u0442\u0430\u0442\u0443\u0441 \u0411\u0430\u0437\u044B \u0414\u0430\u043D\u043D\u044B\u0445: ");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(lblStatus);
		lblChangeStatus = new JLabel("-");
		lblChangeStatus.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(lblChangeStatus);

		// ���������� �������

		menuItem.addActionListener(new ButtonSaveDBFileListener());
		mntmNewMenuItem.addActionListener(new ButtonLoadDBFromFileListener());
		menuItem_1.addActionListener(new ButtonCategoriesWindowListener());
		menuItem_2.addActionListener(new ButtonSubCategoriesWindowListener());
		menuItem_4.addActionListener(new ButtonReportWindowListener());
		mntmNewMenuItem_1.addActionListener(new ButtonAboutProgramListener());
		comboBoxCategory
				.addActionListener(new ComboBoxCategoryChangeListener());
		comboBoxSubCategory
				.addActionListener(new ComboBoxSubCategoryChangeListener());
		btnAddNewGoods.addActionListener(new ButtonAddGoodsListener());
		btnDelete.addActionListener(new ButtonRemoveGoodsListener());
		btnEdit.addActionListener(new ButtonEditGoodsListener());

		this.stock = stock;
		updateDataCategory();
		setDBStatus(StatusDB.LOAD_DB);

	}

	public void updateDataCategory() {
		((CategoryComboBoxModel) comboBoxCategory.getModel()).fill();

	}

	public void reloadDataBase() {
		stock = FileCreater.loadDBfromXML();
		updateDataCategory();
		setDBStatus(StatusDB.LOAD_DB);

	}

	public void updateGoods() {
		((TableModel) table.getModel()).fillTable(currentSubCategory);
	}

	public void setDBStatus(StatusDB status) {
		switch (status) {
		case CHANGE_DB: {
			lblChangeStatus.setText("Есть несохраненные изменения в БД.");
			lblChangeStatus.setForeground(Color.red);
		}
			;
			break;
		case SAVE_DB: {
			lblChangeStatus.setText("БД успешно сохранена.");
			lblChangeStatus.setForeground(Color.green);
		}
			;
			break;
		case LOAD_DB: {
			lblChangeStatus.setText("БД успешно загружена.");
			lblChangeStatus.setForeground(Color.black);
		}
			;
			break;
		}

	}

	public void changeDBStatus() {

		lblChangeStatus.setText("Есть несохраненные изменения");
		lblChangeStatus.setForeground(Color.red);

	}

	private void saveDataBase() {
		FileCreater.saveDBtoXML(stock);
		setDBStatus(StatusDB.SAVE_DB);
	}

	private void noCategory() {
		comboBoxCategory.setEnabled(false);
		lblDescriptionCategory.setText("");
		((SubCategoryComboBoxModel) comboBoxSubCategory.getModel()).fill(null);

	}

	private void noSubCategory() {
		comboBoxSubCategory.setEnabled(false);
		lblDescriptionSubCategory.setText("");
		((TableModel) table.getModel()).fillTable(null);
	}

	private void setEnableForButtons(boolean b, boolean bb) {
		btnEdit.setEnabled(b);
		btnDelete.setEnabled(b);
		btnAddNewGoods.setEnabled(bb);
	}

	public class CategoryComboBoxModel extends DefaultComboBoxModel {
		public CategoryComboBoxModel() {
			super();
		}

		public void fill() {
			super.removeAllElements();
			if (stock.getCategoriesArray().size() == 0) {
				noCategory();
				return;
			}
			comboBoxCategory.setEnabled(true);
			Iterator<Category> it = stock.getCategoriesArray().iterator();
			while (it.hasNext()) {
				Category cat = ((Category) it.next());
				super.addElement(cat);
			}
		}
	}

	public class SubCategoryComboBoxModel extends DefaultComboBoxModel {
		public SubCategoryComboBoxModel() {
			super();
		}

		public void fill(Category category) {
			super.removeAllElements();
			if (category == null
					|| category.getSubCategoriesArray().size() == 0) {
				noSubCategory();
				return;
			}
			comboBoxSubCategory.setEnabled(true);
			Iterator<SubCategory> it = category.getSubCategoriesArray()
					.iterator();
			while (it.hasNext()) {
				SubCategory subcat = ((SubCategory) it.next());
				super.addElement(subcat);
			}
		}
	}

	class TableModel extends DefaultTableModel {

		Class[] columnTypes = new Class[] { String.class, String.class,
				String.class, String.class, String.class, String.class,
				String.class };

		public TableModel() {
			super(new Object[][] { { "", "", "", "", "", "", "" }, },
					new String[] { "id", "Имя", "Описание", "Кол-во",
							"Вес, кг", "Объём, м\u00B3", "Дата прибытия" });

		}

		void removeAllRows() {
			int rowCount = super.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				super.removeRow(i);
			}

		}

		public void fillTable(SubCategory subcat) {
			removeAllRows();
			setEnableForButtons(true, true);
			if (subcat == null) {
				setEnableForButtons(false, false);
				return;

			} else if (subcat.getGoodsArray().size() == 0) {
				setEnableForButtons(false, true);
				return;
			}
			Iterator<Goods> it = subcat.getGoodsArray().iterator();
			while (it.hasNext()) {
				Goods goods = it.next();
				super.addRow(new Object[] { goods.getId(), goods.getName(),
						goods.getDescription(), goods.getCount(),
						goods.getWeight(), goods.getSize(),
						goods.getArrivaldateInSpecialFormat() });
			}

		}

		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}
	}

	protected class ButtonSaveDBFileListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			Object[] options = { "Да", "Нет" };
			int n;
			n = JOptionPane
					.showOptionDialog(
							StockMainView.this,
							"Вы действительно хотите сохранить все изменения внесенные в Базу Данных?",
							"Предупреждение", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[1]);
			if (n == 0) {
				saveDataBase();
			} else {
				return;
			}
		}
	}

	protected class ButtonLoadDBFromFileListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			Object[] options = { "Да", "Нет" };
			int n;
			n = JOptionPane
					.showOptionDialog(
							StockMainView.this,
							"Вы действительно хотите отменить все изменения внесенные в Базу Данных?",
							"Предупреждение", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[1]);
			if (n == 0) {
				reloadDataBase();
			} else {
				return;
			}
		}
	}

	protected class ButtonEditGoodsListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			if (table.getSelectedRow() != -1) {
				int currentRow = table.getSelectedRow();
				Goods goods = currentSubCategory.getGoodsArray()
						.get(currentRow);
				GoodsView view = new GoodsView(StockMainView.this, null, goods,
						true);
				view.setVisible(true);
			}
		}
	}

	protected class ButtonRemoveGoodsListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			int row = table.getSelectedRow();
			((TableModel) table.getModel()).removeRow(table.getSelectedRow());
			currentSubCategory.getGoodsArray().remove(row);
			setDBStatus(StatusDB.CHANGE_DB);
		}
	}

	protected class ButtonAddGoodsListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			GoodsView view = new GoodsView(StockMainView.this,
					currentSubCategory, null, false);
			view.setVisible(true);
		}
	}

	protected class ButtonReportWindowListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			ReportView view = new ReportView(StockMainView.this, stock);
			view.setVisible(true);
		}
	}

	protected class ButtonCategoriesWindowListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			CategoriesView view = new CategoriesView(StockMainView.this, stock);
			view.setVisible(true);
		}
	}

	protected class ButtonSubCategoriesWindowListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			SubCategoryView view = new SubCategoryView(StockMainView.this,
					stock);
			view.setVisible(true);
		}
	}

	protected class ButtonAboutProgramListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showOptionDialog(StockMainView.this,
					"Программа \"Склад\" v 1.0 \n ", "Информация о программе",
					JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, new Object[] { "Ок" }, "Ок");
		}
	}

	protected class ComboBoxCategoryChangeListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			if (comboBoxCategory.getSelectedItem() != null) {

				Category cat = (Category) comboBoxCategory.getSelectedItem();
				lblDescriptionCategory.setText(cat.getDescription());
				((SubCategoryComboBoxModel) comboBoxSubCategory.getModel())
						.fill(cat);

			}
		}
	}

	protected class ComboBoxSubCategoryChangeListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			if (comboBoxSubCategory.getSelectedItem() != null) {
				currentSubCategory = (SubCategory) comboBoxSubCategory
						.getSelectedItem();
				lblDescriptionSubCategory.setText(currentSubCategory
						.getDescription());
				updateGoods();

			}
		}
	}

	public enum StatusDB {
		SAVE_DB, LOAD_DB, CHANGE_DB
	}

}
