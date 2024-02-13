package gr.codehub.j101.p05network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WebTemperatures {
	private static final Logger logger = LoggerFactory.getLogger(WebTemperatures.class);

	public static void main(String[] args) throws Exception {
		showMeteoData();
		showK24Data();
	}

	//	Repetition in regex by default is greedy: they try to match as many reps as possible,
	//	and when this doesn't work
	//	and they have to backtrack, they try to match one fewer rep at a time, until a
	//	match of the whole pattern is found. As a result, when a match finally happens, a
	//	greedy repetition would match as many reps as possible.
	//
	//	The ? as a repetition quantifier changes this behavior into non-greedy, also called
	//	reluctant (in e.g. Java) (and
	//	sometimes "lazy"). In contrast, this repetition will first try to match as few
	//	reps as possible, and when this doesn't work and they have to backtrack, they
	//	start matching one more rept a time. As a result, when a match finally happens,
	//	a reluctant repetition would match as few reps as possible.
	//
	// <.*?> will match "<h1>" in "<h1>Hello</h1>" instead of the whole line between the first < and last >

	private static void showK24Data() throws Exception {
		URL site = new URL("https://gr.k24.net/en/thessaloniki/weather-thessaloniki-19");
		logger.info("===================================================");
		logger.info("Processing site {}", site.toString());
		try (BufferedReader in = new BufferedReader(new InputStreamReader(site.openStream()));) {
			List<String> lines = new ArrayList<>();
			lines.add("ΘΕΡΜΟΚΡΑΣΙΕΣ");
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.contains("class=\"date\"") || inputLine.contains(":00")
						|| inputLine.contains("\u00b0C")) { // this is °C
					inputLine = inputLine.replaceAll("<.*?>", " ").replaceAll("\\s+", " ").strip();
					lines.add(inputLine);
				}
			}
			lines.stream().forEach(s -> logger.info("K24: {}", s));
		} catch (IOException e) {
			logger.info("Cannot process site {}", site.toString());
			e.printStackTrace();
		}
	}

	private static void showMeteoData() throws Exception {
		URL site = new URL("https://www.meteo.gr/cf.cfm?city_id=52");
		logger.info("===================================================");
		logger.info("Processing site {}", site.toString());
		try (BufferedReader in = new BufferedReader(new InputStreamReader(site.openStream()));) {
			List<String> lines = new ArrayList<>();
			lines.add("ΘΕΡΜΟΚΡΑΣΙΕΣ");
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.contains("forecastDate") || inputLine.contains(":00</td>") || inputLine.contains(
						"&deg;C")) {
					inputLine = inputLine.replaceAll("<.*?>", " ").replaceAll("&deg;", "°").replaceAll("\\s+", " ")
										 .strip();
					lines.add(inputLine);
				}
			}
			lines.stream().forEach(s -> logger.info("Meteo: {}", s));
		} catch (IOException e) {
			logger.info("Cannot process site {}", site.toString());
			e.printStackTrace();
		}
	}
}

