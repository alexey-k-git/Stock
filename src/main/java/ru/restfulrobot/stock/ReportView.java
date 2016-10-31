package ru.restfulrobot.stock;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import ru.restfulrobot.stock.structure.Category;
import ru.restfulrobot.stock.structure.Goods;
import ru.restfulrobot.stock.structure.Stock;
import ru.restfulrobot.stock.structure.SubCategory;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ReportView extends JFrame {

	private JPanel contentPane;
	private StockMainView mainWindow;

	public ReportView(final StockMainView mainWindow, Stock stock) {
		this.mainWindow = mainWindow;
		mainWindow.setEnabled(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				mainWindow.setEnabled(true);
			}
		});
		setResizable(false);
		setTitle("\u041E\u0442\u0447\u0435\u0442");
		setBounds(100, 100, 760, 168);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 2, 5, 5));
		JLabel lblNewLabel = new JLabel(
				"\u041E\u0431\u0449\u0435\u0435 \u043A\u043E\u043B-\u0432\u043E \u043D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0439:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		JLabel lblCount = new JLabel("0");
		lblCount.setFont(new Font("Tahoma", Font.ITALIC, 14));
		contentPane.add(lblCount);
		JLabel lblNewLabel_2 = new JLabel(
				"\u0421\u0443\u043C\u043C\u0430\u0440\u043D\u044B\u0439 \u0432\u0435\u0441 \u0432\u0441\u0435\u0445 \u0442\u043E\u0432\u0430\u0440\u043E\u0432:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_2);
		JLabel lblWeight = new JLabel("0");
		lblWeight.setFont(new Font("Tahoma", Font.ITALIC, 14));
		contentPane.add(lblWeight);
		JLabel lblNewLabel_4 = new JLabel(
				"\u0421\u0443\u043C\u043C\u0430\u0440\u043D\u044B\u0439 \u043E\u0431\u044A\u0451\u043C \u0437\u0430\u043D\u0438\u043C\u0430\u0435\u043C\u044B\u0439  \u0432\u0441\u0435\u043C\u0438 \u0442\u043E\u0432\u0430\u0440\u0430\u043C\u0438:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_4);
		JLabel lblSize = new JLabel("0");
		lblSize.setFont(new Font("Tahoma", Font.ITALIC, 14));
		contentPane.add(lblSize);
		calculate(lblCount, lblWeight, lblSize, stock);

	}

	private void calculate(JLabel lblCount, JLabel lblWeight, JLabel lblSize,
			Stock stock) {
		int count = 0;
		float weight = 0;
		float size = 0;

		for (Category category : stock.getCategoriesArray()) {

			for (SubCategory subCategory : category.getSubCategoriesArray()) {
				for (Goods goods : subCategory.getGoodsArray()) {
					count++;
					weight += goods.getWeight();
					size += goods.getSize();
				}
			}
		}

		lblCount.setText(" " + count + " шт ");
		lblWeight.setText(" " + String.format("%.2f%n", weight) + " кг ");
		lblSize.setText(" " + String.format("%.2f%n", size) + " м\u00B3 ");
	}

}
