package ru.restfulrobot.stock.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.restfulrobot.stock.structure.Category;
import ru.restfulrobot.stock.structure.Goods;
import ru.restfulrobot.stock.structure.Stock;
import ru.restfulrobot.stock.structure.SubCategory;

public class FileCreater {

	static public Stock loadDBfromXML() {

		DocumentBuilderFactory factory;
		DocumentBuilder builder;
		Document document;
		Stock stock;
		File file = new File("database.xml");
		System.out.print(file.getAbsoluteFile().toString());
		factory = DocumentBuilderFactory.newInstance();

		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(file);
			document.normalize();
			NodeList categories = document.getFirstChild().getChildNodes();
			stock = new Stock();
			for (int s = 0; s < categories.getLength(); s++) {
				Node catNode = categories.item(s);
				if (!catNode.getNodeName().equals("#text")) {
					addCategory(stock, catNode);
				}
			}
		} catch (Exception e) {
			Object[] options = { "Ок" };
			JOptionPane
					.showOptionDialog(
							null,
							"Не удалось загрузить файл \"database.xml\" в корневой папке программы!Приложение не будет запущено.",
							"Ошибка", JOptionPane.OK_OPTION,
							JOptionPane.ERROR_MESSAGE, null, options,
							options[0]);
			stock = null;
		}
		return stock;
	}

	private static void addCategory(Stock stock, Node catNode) {
		NamedNodeMap attributes = catNode.getAttributes();
		String name = attributes.getNamedItem("description").getTextContent();
		String description = attributes.getNamedItem("name").getTextContent();
		Category category = new Category(stock, name, description);
		stock.addCategory(category);
		NodeList subCategories = catNode.getChildNodes();
		for (int s = 0; s < subCategories.getLength(); s++) {
			Node subCat = subCategories.item(s);
			if (!subCat.getNodeName().equals("#text")) {
				addSubCategory(category, subCat);
			}
		}
	}

	private static void addSubCategory(Category category, Node subCatNode) {
		NamedNodeMap attributes = subCatNode.getAttributes();
		String description = attributes.getNamedItem("description").getTextContent();
		String name = attributes.getNamedItem("name").getTextContent();
		SubCategory subCategory = new SubCategory(category, name, description);
		category.addSubCategory(subCategory);
		NodeList subCategories = subCatNode.getChildNodes();
		for (int s = 0; s < subCategories.getLength(); s++) {
			Node subGoods = subCategories.item(s);
			if (!subGoods.getNodeName().equals("#text")) {
				addGoods(subCategory, subGoods);
			}
		}

	}

	private static void addGoods(SubCategory subCategory, Node goodsNode) {
		NamedNodeMap attributes = goodsNode.getAttributes();
		String description = attributes.getNamedItem("description").getTextContent();
		String name = attributes.getNamedItem("name").getTextContent();
		int id = 0;
		int count = 0;
		float weight = 0;
		float size = 0;
		try {
			id = Integer.parseInt(attributes.getNamedItem("id")
					.getTextContent());
			count = Integer.parseInt(attributes.getNamedItem("count")
					.getTextContent());
			weight = Float.parseFloat(attributes.getNamedItem("weight")
					.getTextContent());
			size = Float.parseFloat(attributes.getNamedItem("size")
					.getTextContent());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		Date date = new Date(Long.parseLong(attributes.getNamedItem(
				"arrivaldate").getTextContent()));
		Goods goods = new Goods(subCategory, id, name, description, weight,
				size, count, date);
		subCategory.addGoods(goods);
	}

	public static void saveDBtoXML(Stock stock) {
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transFactory.newTransformer();
		} catch (TransformerConfigurationException e1) {
			e1.printStackTrace();
		}
		DocumentBuilderFactory factory;
		DocumentBuilder builder = null;
		Document document;
		Element mainNode;
		factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		document = builder.newDocument();
		document.setXmlStandalone(true);
		mainNode = document.createElement("Stock");
		mainNode.setAttribute("version", "1.0");
		fillXmlDocument(document, mainNode, stock);
		document.appendChild(mainNode);
		DOMSource source = new DOMSource(document);
		File fileFromDialog = new File("./database.xml");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileFromDialog);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StreamResult result = new StreamResult(fos);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	static private Element fillXmlDocument(Document document, Element mainNode,
			Stock stock) {
		Element categoryElement;
		Element subCategoryElement;
		Element goodsElement;

		for (Category category : stock.getCategoriesArray()) {

			categoryElement = document.createElement("category");
			categoryElement.setAttribute("name", category.getName());
			categoryElement.setAttribute("description",
					category.getDescription());

			for (SubCategory subCategory : category.getSubCategoriesArray()) {
				subCategoryElement = document.createElement("subcategory");
				subCategoryElement.setAttribute("description",
						subCategory.getDescription());
				subCategoryElement.setAttribute("name", subCategory.getName());

				for (Goods goods : subCategory.getGoodsArray()) {
					goodsElement = document.createElement("goods");
					goodsElement.setAttribute("id",
							String.valueOf(goods.getId()));
					goodsElement.setAttribute("name", goods.getName());
					goodsElement.setAttribute("count",
							String.valueOf(goods.getCount()));
					goodsElement.setAttribute("description",
							goods.getDescription());
					goodsElement.setAttribute("weight",
							String.valueOf(goods.getWeight()));
					goodsElement.setAttribute("size",
							String.valueOf(goods.getSize()));
					goodsElement.setAttribute("arrivaldate",
							String.valueOf(goods.getArrivaldate().getTime()));
					subCategoryElement.appendChild(goodsElement);
				}
				categoryElement.appendChild(subCategoryElement);
			}

			mainNode.appendChild(categoryElement);
		}

		return mainNode;
	}

}
