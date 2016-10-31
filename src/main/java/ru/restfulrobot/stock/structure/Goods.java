package ru.restfulrobot.stock.structure;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Goods {

	private int id;
	private String name;
	private String description;
	private float weight;
	private float size;
	private int count;
	private Date arrivalDate;
	private SubCategory subCategory;

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getArrivaldate() {
		return arrivalDate;
	}

	public String getArrivaldateInSpecialFormat() {

		return new SimpleDateFormat("dd.MM.yyyy").format(arrivalDate);

	}

	public void setArrivaldate(Date arrivaldate) {
		this.arrivalDate = arrivaldate;
	}

	public Goods(SubCategory subCategory, int id, String name,
			String description, float weight, float size, int count,
			Date arrivalDate) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.weight = weight;
		this.size = size;
		this.count = count;
		this.arrivalDate = arrivalDate;

	}

}
