package ru.restfulrobot.stock.structure;

import java.util.ArrayList;

public class SubCategory {

	private String name;
	private String description;
	private Category category;
	ArrayList<Goods> goodsInSubCategory = new ArrayList<Goods>();

	public ArrayList<Goods> getGoodsArray() {
		return goodsInSubCategory;
	}

	public Category getCategory() {
		return category;
	}

	public void addGoods(Goods goods) {
		goodsInSubCategory.add(goods);
	}

	public void deleteGoods(Goods goods) {
		goodsInSubCategory.remove(goods);
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

	public SubCategory(Category category, String name, String description) {
		this.category = category;
		this.name = name;
		this.description = description;

	}

	@Override
	public String toString() {
		return getName();
	}

}