package ru.restfulrobot.stock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import ru.restfulrobot.stock.StockMainView.StatusDB;
import ru.restfulrobot.stock.structure.Category;
import ru.restfulrobot.stock.structure.Stock;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CategoriesView extends JFrame {

	private JPanel contentPane;
	private JTextField txtNewCatName;
	private JTextField txtNewCatDesc;
	private JButton btnDeleteCategory;
	private JButton btnAddNewCategory;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JList list;
	private Stock stock;
	private JLabel lblNewLabel_3;
	private JPanel panel;
	private StockMainView mainWindow;

	public CategoriesView(final StockMainView mainWindow, final Stock stock) {
		this.mainWindow = mainWindow;
		mainWindow.setEnabled(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				mainWindow.setEnabled(true);
				mainWindow.updateDataCategory();
			}
		});
		setResizable(false);
		setTitle("\u041A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438");
		setBounds(100, 100, 307, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Border border = BorderFactory.createLineBorder(Color.black);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		lblNewLabel_3 = new JLabel(
				"\u0421\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u044E\u0449\u0438\u0435 \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438 \u0442\u043E\u0432\u0430\u0440\u043E\u0432:");
		lblNewLabel_3.setAlignmentX(CENTER_ALIGNMENT);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_3);
		contentPane.add(Box.createRigidArea(new Dimension(5, 8)));
		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new CategoriesListModel());
		list.setBorder(border);
		list.setVisibleRowCount(8);
		list.setAlignmentX(CENTER_ALIGNMENT);
		list.setAlignmentY(CENTER_ALIGNMENT);
		JScrollPane scrollPane = new JScrollPane(list);
		panel.add(scrollPane);
		contentPane.add(Box.createRigidArea(new Dimension(5, 8)));
		btnDeleteCategory = new JButton(
				"\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		btnDeleteCategory.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(btnDeleteCategory);
		JLabel label = new JLabel("_____________________________");
		label.setAlignmentY(Component.TOP_ALIGNMENT);
		label.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(label);
		contentPane.add(Box.createRigidArea(new Dimension(5, 8)));
		lblNewLabel = new JLabel(
				"\u041D\u043E\u0432\u0430\u044F \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044F:");
		lblNewLabel.setAlignmentX(CENTER_ALIGNMENT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel);
		contentPane.add(Box.createRigidArea(new Dimension(5, 8)));
		lblNewLabel_1 = new JLabel(
				"\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435");
		lblNewLabel_1.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(lblNewLabel_1);
		contentPane.add(Box.createRigidArea(new Dimension(5, 8)));
		txtNewCatName = new JTextField("-");
		txtNewCatName.setHorizontalAlignment(SwingConstants.CENTER);
		txtNewCatName.setAlignmentX(CENTER_ALIGNMENT);
		txtNewCatName.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				txtNewCatName.getPreferredSize().height));
		contentPane.add(txtNewCatName);
		contentPane.add(Box.createRigidArea(new Dimension(5, 8)));
		lblNewLabel_2 = new JLabel(
				"\u041A\u0440\u0430\u0442\u043A\u043E\u0435 \u043E\u043F\u0438\u0441\u0430\u043D\u0438\u0435");
		lblNewLabel_2.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(lblNewLabel_2);
		contentPane.add(Box.createRigidArea(new Dimension(5, 8)));
		txtNewCatDesc = new JTextField("-");
		txtNewCatDesc.setHorizontalAlignment(SwingConstants.CENTER);
		txtNewCatDesc.setAlignmentX(CENTER_ALIGNMENT);
		txtNewCatDesc.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				txtNewCatDesc.getPreferredSize().height));
		contentPane.add(txtNewCatDesc);
		contentPane.add(Box.createRigidArea(new Dimension(5, 8)));
		btnAddNewCategory = new JButton(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		btnAddNewCategory.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(btnAddNewCategory);
		btnDeleteCategory.addActionListener(new ButtonDeleteCategoryListener());
		btnAddNewCategory.addActionListener(new ButtonAddCategoryListener());

		this.stock = stock;
		updateList();
	}

	private void updateList() {
		((CategoriesListModel) list.getModel()).fillList(stock);
		list.updateUI();

	}

	private boolean checkEmptyFileds() {
		if (txtNewCatName.getText().isEmpty()
				|| txtNewCatDesc.getText().isEmpty()) {
			Object[] options = { "��" };
			JOptionPane.showOptionDialog(CategoriesView.this,
					"Такое имя категории товара уже существует!", "Ошибка", JOptionPane.OK_OPTION,
					JOptionPane.ERROR_MESSAGE, null, options, options[0]);

			return true;
		}
		return false;
	}

	private boolean checkUniqueName() {

		String newName = txtNewCatName.getText();
		for (Category category : stock.getCategoriesArray()) {

			if (newName.equals(category.getName())) {

				Object[] options = { "Ок" };
				JOptionPane.showOptionDialog(CategoriesView.this,
						"Такое имя категории товара уже существует!", "Ошибка",
						JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null,
						options, options[0]);

				return true;
			}

		}

		return false;
	}

	class CategoriesListModel extends AbstractListModel {
		ArrayList<Category> array = new ArrayList<Category>();

		public int getSize() {
			return array.size();
		}

		public void fillList(Stock stock) {
			array.clear();
			for (Category category : stock.getCategoriesArray()) {
				array.add(category);
			}

		}

		public Object getElementAt(int index) {
			return array.get(index).toString();
		}

		public Category getCategoryAt(int index) {
			return array.get(index);
		}

	}
	
	protected class ButtonAddCategoryListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			if (checkUniqueName() || checkEmptyFileds()) {
				return;
			}
			stock.addCategory(new Category(stock, txtNewCatName.getText(),
					txtNewCatDesc.getText()));
			updateList();
			mainWindow.setDBStatus(StatusDB.CHANGE_DB);
		}
	}



	protected class ButtonDeleteCategoryListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			int index = list.getSelectedIndex();
			if (index != -1) {
				list.clearSelection();
				stock.deleteCategory((Category) ((CategoriesListModel) list
						.getModel()).getCategoryAt(index));
				updateList();
				mainWindow.setDBStatus(StatusDB.CHANGE_DB);
			}
	
		}
	}

}
