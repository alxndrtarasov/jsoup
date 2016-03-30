package programbasedonjsoup;

import java.io.IOException;
import java.io.Serializable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseForLinks implements ParseMethod, Serializable {
	public String parse(String url) throws IOException {
		StringBuilder result = new StringBuilder();
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.select("a[href]");

		result.append("\nLinks: (" + links.size() + ")\n");
		for (Element link : links) {
			result.append(" * a: <" + link.attr("abs:href") + ">  (" + link.text() + ")\n");
		}
		System.out.println(doc.text());
		return result.toString();
	}

	public String toString() {
		return "Links' Parser";

	}
}
