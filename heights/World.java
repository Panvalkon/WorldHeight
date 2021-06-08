package heights;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.TreeMap;

public class World {
	private List<Country> countries;

	private World(List<Country> list) {
		countries = new ArrayList<Country>(list);
	}

	public static World createFromFile(String file) throws FileNotFoundException {
		List<Country> list = new ArrayList<>();
		try (Scanner sc = new Scanner(new File(file))) {
			processFile(list, sc);
		}
		return new World(list);
	}

	private static void processFile(List<Country> list, Scanner sc) {
		while (sc.hasNextLine()) {
			String line = new String(sc.nextLine());
			processLine(list, line);
		}

	}

	private static void processLine(List<Country> list, String line) {
		try (Scanner sc = new Scanner(line)) {
			sc.useDelimiter(",");
			try {
				Country c = new Country(sc.next(), sc.next(), sc.nextDouble());
				list.add(c);
			} catch (NoSuchElementException | NumberFormatException e) {
				// we just ignore it
			}
		}
	}

	public List<Country> getCountries() {
		return countries;
	}

	public static <K, V> void print(Map<K, V> map) {
		// TODO FinishIt

		String s = new String();
		for (K key : map.keySet()) {
			StringJoiner sj = new StringJoiner(", ", "[", "]");
			sj.add(key.toString()).add("\t").add(map.get(key).toString());
		}
		System.out.println(s);
		// TODO as on following example
		/*
		 * Paises por altura 1.5 [Pais(Indonesia, Asia, 1.58)] 1.6 [Pais(Bahrain, Asia,
		 * 1.651), Pais(Bolivia, South America, 1.6),* ..., Pais(Venezuela, South
		 * America, 1.69), Pais(Vietnam, Asia, 1.621)] 1.7 [Pais(Albania, Europe, 1.74),
		 * Pais(Algeria, Africa, 1.722), ..., Pais(Uruguay, South America, 1.7),
		 * Pais(Uzbekistan, Asia, 1.754)]
		 */
	}

	public Map<String, Integer> numberOfCountriesPerContinent() {
		Map<String, Integer> map = new TreeMap<>();
		for (Country c : countries) {
			int numPerContinent = map.getOrDefault(c.getContinent(), 0);
			map.put(c.getContinent(), numPerContinent++);
		}
		return map;
	}

}
