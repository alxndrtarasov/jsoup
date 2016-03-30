package programbasedonjsoup;

import java.io.IOException;

import org.jsoup.select.Elements;

public interface ParseMethod {
	public String parse(String link) throws IOException;
}
