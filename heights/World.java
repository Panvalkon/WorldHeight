package heights;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class World {
	private List<Country> countries;

	private World(List<Country> list) {
		this.countries = list;
	}

	public static World createFromFile(String file) throws FileNotFoundException {
		List<Country> list = new ArrayList<>();
		try (Scanner sc = new Scanner(new File(file))) {
			// processFile(list, sc);
			while (sc.hasNextLine()) {
				String line = new String(sc.nextLine());
				try (Scanner scc = new Scanner(line)) {
					scc.useDelimiter(",");
					scc.useLocale(Locale.ENGLISH);
					try {
						Country c = new Country(scc.next(), scc.next(), scc.nextDouble());
						list.add((Country) c);
					} catch (NoSuchElementException | NumberFormatException e) {
						// we just ignore it
					}
				}
			}
		}
		World w = new World(list);
		return w;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public static <K, V> void print(Map<K, V> map) {
		for (K key : map.keySet()) {
			System.out.println(key.toString() + "\t" + map.get(key).toString());
		}

	}

	public Map<String, Integer> numberOfCountriesPerContinent() {
		Map<String, Integer> map = new TreeMap<>();
		for (Country c : countries) {
			int numPerContinent = map.getOrDefault(c.getContinent(), 0);
			map.put(c.getContinent(), numPerContinent + 1);
		}
		return map;
	}

	public Map<Double, List<Country>> countriesPerHeight() {
		Map<Double, List<Country>> map = new TreeMap<>();
		for (Country c : countries) {
			Double height = c.getHeight();
			height = ((int) (height * 10)) / 10.0;
			map.putIfAbsent(height, new ArrayList<Country>());
			map.get(height).add(c);
		}
		return map;
	}

	public Map<String, Set<Country>> countriesPerContinent(){
		Map<String, Set<Country>> map = new TreeMap<>();
		for (Country c : countries) {
			map.putIfAbsent(c.getContinent(), new TreeSet<Country>());
			map.get(c.getContinent()).add(c);
		}
		return map;
	}
	
	public Set<Country> countriesByHeight(){
		Set<Country> set = new TreeSet<>(new CompHeight());
		for(Country c : countries) {
			set.add(c);
		}
		return set;
	}
}
