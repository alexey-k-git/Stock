package ru.restfulrobot.stock;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import ru.restfulrobot.stock.StockMainView.StatusDB;
import ru.restfulrobot.stock.structure.*;

import java.awt.Dialog.ModalExclusionType;
import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;
import java.util.Date;
import java.util.Calendar;

public class GoodsView extends JFrame {
	Goods goods;
	JTextPane txtName;
	JTextPane txtDescription;
	JSpinner spinnerDate;
	JSpinner spinnerSize;
	JSpinner spinnerWeight;
	JSpinner spinnerCount;
	JButton btnAdd;
	JButton btnSave;
	StockMainView mainWindow;
	SubCategory subcat;

	public GoodsView(final StockMainView mainWindow, final SubCategory subcat,
			final Goods goods, Boolean edit) {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setAlwaysOnTop(true);
		setTitle("\u0420\u0435\u0434\u0430\u043A\u0442\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u0435 \u0442\u043E\u0432\u0430\u0440\u0430");
		setResizable(false);
		this.goods = goods;
		this.subcat = subcat;
		this.mainWindow = mainWindow;
		mainWindow.setEnabled(false);
		setBounds(100, 100, 493, 338);
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("\u0418\u043C\u044F");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 29, 146, 20);
		getContentPane().add(lblNewLabel);
		Border border = BorderFactory.createLineBorder(Color.black);
		txtName = new JTextPane();
		txtName.setBounds(241, 29, 135, 20);
		txtName.setBorder(border);
		getContentPane().add(txtName);
		JLabel label = new JLabel(
				"\u041E\u043F\u0438\u0441\u0430\u043D\u0438\u0435");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 60, 146, 20);
		getContentPane().add(label);
		JLabel label_1 = new JLabel(
				"\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 91, 146, 20);
		getContentPane().add(label_1);
		JLabel label_2 = new JLabel("\u0412\u0435\u0441, \u043A\u0433");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(10, 122, 146, 20);
		getContentPane().add(label_2);
		JLabel lblub = new JLabel("\u041E\u0431\u044A\u0435\u043C, м\u00B3");
		lblub.setHorizontalAlignment(SwingConstants.CENTER);
		lblub.setBounds(10, 153, 146, 20);
		getContentPane().add(lblub);
		txtDescription = new JTextPane();
		txtDescription.setBounds(163, 60, 314, 20);
		txtDescription.setBorder(border);
		getContentPane().add(txtDescription);
		spinnerCount = new JSpinner();
		spinnerCount.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
		spinnerCount.setBounds(275, 91, 59, 20);
		getContentPane().add(spinnerCount);
		spinnerWeight = new JSpinner();
		spinnerWeight.setModel(new SpinnerNumberModel(new Float(0),
				new Float(0), new Float(6000), new Float(1)));
		spinnerWeight.setBounds(275, 122, 59, 20);
		getContentPane().add(spinnerWeight);
		spinnerSize = new JSpinner();
		spinnerSize.setModel(new SpinnerNumberModel(new Float(0), new Float(0),
				new Float(5), new Float(0.2)));
		spinnerSize.setBounds(275, 153, 59, 20);
		getContentPane().add(spinnerSize);
		JLabel label_3 = new JLabel(
				"\u0414\u0430\u0442\u0430 \u043F\u043E\u0441\u0442\u0443\u043F\u043B\u0435\u043D\u0438\u044F");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(10, 184, 146, 20);
		getContentPane().add(label_3);
		spinnerDate = new JSpinner();
		spinnerDate.setModel(new SpinnerDateModel(new Date(1416945600000L),
				new Date(-10800000L), new Date(1543089600000L),
				Calendar.DAY_OF_YEAR));
		spinnerDate.setBounds(241, 184, 135, 20);
		getContentPane().add(spinnerDate);
		btnAdd = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		btnAdd.setBounds(54, 247, 117, 23);
		getContentPane().add(btnAdd);
		btnSave = new JButton(
				"\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		btnSave.setBounds(301, 247, 133, 23);
		getContentPane().add(btnSave);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mainWindow.setEnabled(true);
			}
		});

		if (edit) {
			fillFields(goods);
			btnAdd.setEnabled(false);
		} else {
			fillFieldsForNewGoods();
			btnSave.setEnabled(false);
		}
		btnAdd.addActionListener(new ButtonAddGoodsListener());
		btnSave.addActionListener(new ButtonChangeGoodsListener());

	}

	private void updateDataAndCloseThisForm() {
		mainWindow.updateGoods();
		mainWindow.setEnabled(true);
		mainWindow.setDBStatus(StatusDB.CHANGE_DB);
		GoodsView.this.dispose();
	}

	private void fillFieldsForNewGoods() {
		txtName.setText("Новый товар");
		txtDescription.setText("Описание нового товара");
		spinnerDate.setValue(new Date());
		spinnerSize.setValue(new Float(0));
		spinnerWeight.setValue(new Float(0));
		spinnerCount.setValue(0);
	}

	private void fillFields(Goods goods) {
		txtName.setText(goods.getName());
		txtDescription.setText(goods.getDescription());
		spinnerDate.setValue(goods.getArrivaldate());
		spinnerSize.setValue(goods.getSize());
		spinnerWeight.setValue(goods.getWeight());
		spinnerCount.setValue(goods.getCount());
	}

	private boolean fieldsAreEmpty() {
		if (txtName.getText().equals("") || txtDescription.getText().equals("")
				|| (float) spinnerSize.getValue() == 0
				|| (float) spinnerWeight.getValue() == 0
				|| (int) spinnerCount.getValue() == 0) {
			Object[] options = { "Ок" };
			JOptionPane.showOptionDialog(GoodsView.this,
					"Введите корректные данные!", "Ошибка",
					JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null,
					options, options[0]);
			return true;
		}
		return false;
	}

	private int generateId() {
		Stock stock = subcat.getCategory().getStock();
		int maxId = 0;
		for (Category category : stock.getCategoriesArray()) {
			for (SubCategory subCatgeory : category.getSubCategoriesArray()) {
				for (Goods goods : subCatgeory.getGoodsArray()) {
					if (maxId < goods.getId()) {
						maxId = goods.getId();
					}
				}
			}
		}
		maxId++;
		return maxId;
	}

	protected class ButtonAddGoodsListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			if (fieldsAreEmpty()) {
				return;
			}
			Goods newGoods = new Goods(subcat, generateId(), txtName.getText(),
					txtDescription.getText(), (float) spinnerWeight.getValue(),
					(float) spinnerSize.getValue(),
					(int) spinnerCount.getValue(),
					(Date) spinnerDate.getValue());
			subcat.addGoods(newGoods);
			updateDataAndCloseThisForm();
		}
	}

	protected class ButtonChangeGoodsListener extends AbstractAction {
		public void actionPerformed(ActionEvent arg0) {
			if (fieldsAreEmpty()) {
				return;
			}
			goods.setName(txtName.getText());
			goods.setDescription(txtDescription.getText());
			goods.setCount((int) spinnerCount.getValue());
			goods.setSize((float) spinnerSize.getValue());
			goods.setWeight((float) spinnerWeight.getValue());
			goods.setArrivaldate((Date) spinnerDate.getValue());
			updateDataAndCloseThisForm();
		}
	}

}