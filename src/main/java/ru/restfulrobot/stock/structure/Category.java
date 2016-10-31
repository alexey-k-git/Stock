package ru.restfulrobot.stock.structure;

import java.util.ArrayList;

public class Category {

	private String name;
	private String description;
	private ArrayList<SubCategory> subcategories = new ArrayList<SubCategory>();
	private Stock stock;

	public Stock getStock() {
		return stock;
	}

	public void addSubCategory(SubCategory subCategory) {
		subcategories.add(subCategory);
	}

	public void deleteSubCategory(SubCategory subCategory) {
		subcategories.remove(subCategory);
	}

	public ArrayList<SubCategory> getSubCategoriesArray() {
		return subcategories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category(Stock stock, String name, String description) {
		this.stock=stock;
		this.name = name;
		this.description = description;

	}

	@Override
	public String toString() {
		return getName();
	}

}
