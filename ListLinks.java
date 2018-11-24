package jsoup;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Example program to list links from a URL.
 * @author Siddhesh Rajiv Karekar
 */
public class ListLinks {

	private static Map<String, String> urlFileMap = new HashMap<>();
	private static Map<String, String> fileUrlMap = new HashMap<>();

	public static void main(String[] args) throws IOException {

		File outputFile = new File("edgeList.txt");
		PrintWriter printWriter = new PrintWriter(outputFile);

		// Read CSV file
		File csvFile = new File("/Users/mahima/Desktop/IRAssignment4/URLtoHTML_latimes.csv");
		Scanner csvIn = new Scanner(csvFile);

		while (csvIn.hasNextLine()) {

			String currentLine = csvIn.nextLine();
			String[] parts = currentLine.split(",");
			String fileName = parts[0];
			String url = parts[1];

			urlFileMap.put(url, fileName);
			fileUrlMap.put(fileName, url);
		}
		csvIn.close();
		
		System.out.println("Map created!\n");

		File dir = new File("/Users/mahima/Desktop/IRAssignment4/latimes");

		Set<String> edges = new HashSet<>();
		for (File file : dir.listFiles()) {

			Document doc = Jsoup.parse(file, "UTF-8", fileUrlMap.get(file.getName()));
			Elements links = doc.select("a[href]");
			
			// Elements pngs = doc.select("[src]");

			for (Element link : links) {
				String url = link.attr("abs:href").trim();
				if (urlFileMap.containsKey(url)) {
					String edge = file.getName() + " " + urlFileMap.get(url);
					edges.add(edge);
					// System.out.println(edge);
				}
			}

		}
		System.out.println("Edges created!\n");

		for (String s : edges) {
			printWriter.println(s);
			System.out.println(s);
		}

		printWriter.flush();
		printWriter.close();

		System.out.println("\nFinished!");
	}

}