package ru.restfulrobot.stock;

import javax.swing.JFrame;

import ru.restfulrobot.stock.database.FileCreater;
import ru.restfulrobot.stock.structure.Stock;

public class StockMainRun {
	public static void main(String args[]) {
		Stock stock = FileCreater.loadDBfromXML();
		JFrame jframe = new StockMainView(stock);
		jframe.setVisible(true);
	}
}
