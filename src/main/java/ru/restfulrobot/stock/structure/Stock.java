package ru.restfulrobot.stock.structure;

import java.util.ArrayList;

public class Stock {

	private ArrayList<Category> categories = new ArrayList<Category>();

	public void addCategory(Category category) {
		categories.add(category);
	}

	public void deleteCategory(Category category) {
		categories.remove(category);
	}

	public ArrayList<Category> getCategoriesArray() {
		return categories;
	}

}
