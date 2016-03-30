package programbasedonjsoup;

import org.jsoup.Jsoup;
//import org.jsoup.helper.Validate; 
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
//import org.jsoup.nodes.Element; 
import org.jsoup.select.Elements;

import java.io.IOException;

public class test {
	public static void main(String[] args) throws IOException {
		String url = "http://www.banki.ru/products/currency/cash/Nizhniy_novgorod/";
		Document doc = Jsoup.connect(url).get();
		String title = doc.title();
		System.out.println(title);

		Elements links = doc.select("tbody"); // находим все что под тегом tbody
		// Elements tr = links.select("tr"); // находим все что лежит в tr (tr
		// подмножество tbody)
		Elements td = links.select("td");
		Elements a = td.select("a");
		// System.out.println(tr);
		// System.out.println(td.text());
		// String text = td.text();
		for (Element link : a) {
			System.out.println(link.getElementsByAttribute("class").text());
		}
	}

}