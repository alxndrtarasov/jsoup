package programbasedonjsoup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Alexander
 */
public class ParseForPictures implements ParseMethod, Serializable {
	public String parse(String url) throws IOException {
		StringBuilder result = new StringBuilder();
		Document doc = Jsoup.connect(url).get();
		Elements media = doc.select("img");
		result.append("\nMedia: (" + media.size() + ")\n");
		for (Element src : media) {
			if (src.tagName().equals("img"))
				result.append(" * " + src.tagName() + ": <" + src.attr("abs:src") + "> " + src.attr("width") + "x"
						+ src.attr("height") + " (" + src.text() + ")\n");
			else
				result.append(" * " + src.tagName() + ": <" + src.attr("abs:src") + ">\n");
		}
		return result.toString();
	}
	public void serialize(String path) throws IOException{
		FileOutputStream fos = new FileOutputStream(path);
		  ObjectOutputStream oos = new ObjectOutputStream(fos);
		  oos.writeObject(this);
		  oos.flush();
		  oos.close();
	}
	public String toString(){
		return "Pictures' Parser";
		
	}
}
