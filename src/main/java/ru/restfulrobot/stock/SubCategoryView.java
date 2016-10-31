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
import java.util.Iterator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ru.restfulrobot.stock.CategoriesView.CategoriesListModel;
import ru.restfulrobot.stock.StockMainView.StatusDB;
import ru.restfulrobot.stock.structure.Category;
import ru.restfulrobot.stock.structure.Stock;
import ru.restfulrobot.stock.structure.SubCategory;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.JComboBox;

public class SubCategoryView extends JFrame {

	private JPanel contentPane;
	private JTextField txtNewSubCatName;
	private JTextField txtNewSubCatDesc;
	private JButton btnDeleteSubCategory;
	private JButton btnAddNewSubCategory;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JList list;
	private Stock stock;
	private Category cat;
	private JLabel lblNewLabel_3;
	private JPanel panel;
	private StockMainView mainWindow;
	private JLabel label_1;
	private Component rigidArea;
	private JComboBox comboBoxCat;

	public SubCategoryView(final StockMainView mainWindow, final Stock stock) {
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
		label_1 = new JLabel(
				"\u0412\u044B\u0431\u043E\u0440 \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438 \u0442\u043E\u0432\u0430\u0440\u043E\u0432:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setAlignmentX(0.5f);
		contentPane.add(label_1);
		rigidArea = Box.createRigidArea(new Dimension(5, 8));
		contentPane.add(rigidArea);
		comboBoxCat = new JComboBox();
		comboBoxCat.setModel(new CategoryComboBoxModel());
		contentPane.add(comboBoxCat);
		lblNewLabel_3 = new JLabel(
				"\u0421\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u044E\u0449\u0438\u0435 \u043F\u043E\u0434\u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438 \u0442\u043E\u0432\u0430\u0440\u043E\u0432:");
		lblNewLabel_3.setAlignmentX(CENTER_ALIGNMENT);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_3);
		contentPane.add(Box.createRigidArea(new Dimension(5, 8)));
		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new SubCategoriesListModel());
		list.setBorder(border);
		list.setVisibleRowCount(8);
		list.setAlignmentX(CENTER_ALIGNMENT);
		list.setAlignmentY(CENTER_ALIGNMENT);
		JScrollPane scrollPane = new JScrollPane(list);
		panel.add(scrollPane);
		contentPane.add(Box.createRigidArea(new Dimension(5, 8)));
		btnDeleteSubCategory = new JButton(
				"\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		btnDeleteSubCategory.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(btnDeleteSubCategory);
		JLabel label = new JLabel("_____________________________");
		label.setAlignmentY(Component.TOP_ALIGNMENT);
		label.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(label);
		contentPane.add(Box.createRigidArea(new Dimension(5, 8)));
		lblNewLabel = new JLabel(
				"\u041D\u043E\u0432\u0430\u044F \u043F\u043E\u0434\u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044F:");
		lblNewLabel.setAlignmentX(CENTER_ALIGNMENT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel);
		contentPane.add(Box.createRigidArea(new Dimension(5, 8)));
		lblNewLabel_1 = new JLabel(
				"\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435");
		lblNewLabel_1.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(lblNewLabel_1);
		contentPane.add(Box.createRigidArea(new Dimension(5, 8)));
		txtNewSubCatName = new JTextField("-");
		txtNewSubCatName.setHorizontalAlignment(SwingConstants.CENTER);
		txtNewSubCatName.setAlignmentX(CENTER_ALIGNMENT);
		txtNewSubCatName.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				txtNewSubCatName.getPreferredSize().height));
		contentPane.add(txtNewSubCatName);
		contentPane.add(Box.createRigidArea(new Dimension(5, 8)));
		lblNewLabel_2 = new JLabel(
				"\u041A\u0440\u0430\u0442\u043A\u043E\u0435 \u043E\u043F\u0438\u0441\u0430\u043D\u0438\u0435");
		lblNewLabel_2.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(lblNewLabel_2);
		contentPane.add(Box.createRigidArea(new Dimension(5, 8)));
		txtNewSubCatDesc = new JTextField("-");
		txtNewSubCatDesc.setHorizontalAlignment(SwingConstants.CENTER);
		txtNewSubCatDesc.setAlignmentX(CENTER_ALIGNMENT);
		txtNewSubCatDesc.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				txtNewSubCatDesc.getPreferredSize().height));
		contentPane.add(txtNewSubCatDesc);
		contentPane.add(Box.createRigidArea(new Dimension(5, 8)));
		btnAddNewSubCategory = new JButton(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		btnAddNewSubCategory.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(btnAddNewSubCategory);
		btnDeleteSubCategory
				.addActionListener(new ButtonDeleteSubCategoryListener());
		btnAddNewSubCategory
				.addActionListener(new ButtonAddSubCategoryListener());
		comboBoxCat.addActionListener(new ComboBoxCategoryChangeListener());
		this.stock = stock;
		((CategoryComboBoxModel) comboBoxCat.getModel()).fill();
	}

	private void updateList(Category category) {
		((SubCategoriesListModel) list.getModel()).fillList(category);
		list.updateUI();

	}

	private boolean checkEmptyFileds() {
		if (txtNewSubCatName.getText().isEmpty()
				|| txtNewSubCatDesc.getText().isEmpty()) {
			Object[] options = { "Ок" };
			JOptionPane.showOptionDialog(SubCategoryView.this,
					"Оставлены пустые поля!", "Ошибка", JOptionPane.OK_OPTION,
					JOptionPane.ERROR_MESSAGE, null, options, options[0]);

			return true;
		}
		return false;
	}

	private boolean checkUniqueName() {

		String newName = txtNewSubCatName.getText();
		for (SubCategory subCategory: cat.getSubCategoriesArray()) {

			if (newName.equals(subCategory.getName())) {

				Object[] options = { "Ок" };
				JOptionPane.showOptionDialog(SubCategoryView.this,
						"Такое имя подкатегории товара уже существует!", "Ошибка",
						JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null,
						options, options[0]);

				return true;
			}
		}
		return false;
	}

	protected class ButtonAddSubCategoryListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			if (checkUniqueName() || checkEmptyFileds()) {
				return;
			}
			cat.addSubCategory(new SubCategory(cat, txtNewSubCatName.getText(),
					txtNewSubCatDesc.getText()));
			updateList(cat);
			mainWindow.setDBStatus(StatusDB.CHANGE_DB);
		}
	}

	protected class ButtonDeleteSubCategoryListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			int index = list.getSelectedIndex();
			if (index != -1) {
				list.clearSelection();
				cat.deleteSubCategory((SubCategory) ((SubCategoriesListModel) list
						.getModel()).getSubCategoryAt(index));
				updateList(cat);
				mainWindow.setDBStatus(StatusDB.CHANGE_DB);
			}
		}
	}

	protected class ComboBoxCategoryChangeListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			if (comboBoxCat.getSelectedItem() != null) {
				cat = (Category) comboBoxCat.getSelectedItem();
				updateList(cat);
			}
		}
	}

	class SubCategoriesListModel extends AbstractListModel {
		ArrayList<SubCategory> array = new ArrayList<SubCategory>();

		public int getSize() {
			return array.size();
		}

		public void fillList(Category category) {
			array.clear();
			for (SubCategory subCategory : category.getSubCategoriesArray()) {
				array.add(subCategory);
			}

		}

		public Object getElementAt(int index) {
			return array.get(index).toString();
		}

		public SubCategory getSubCategoryAt(int index) {
			return array.get(index);
		}

	}

	public class CategoryComboBoxModel extends DefaultComboBoxModel {
		public CategoryComboBoxModel() {
			super();
		}

		public void fill() {
			super.removeAllElements();
			Iterator<Category> it = stock.getCategoriesArray().iterator();
			while (it.hasNext()) {
				Category cat = ((Category) it.next());
				super.addElement(cat);
			}
		}
	}

}
