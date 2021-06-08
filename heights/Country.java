package heights;

import java.util.StringJoiner;

public class Country implements Comparable<Country> {
	private String name;
	private String continent;
	private double height;

	public Country(String name, String continent, double height) {
		this.name = name;
		this.continent = continent;
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public String getContinent() {
		return continent;
	}

	public double getHeight() {
		return height;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Country && ((Country) o).getName().equalsIgnoreCase(this.name);
	}

	@Override
	public int hashCode() {
		return this.name.toUpperCase().hashCode();
	}

	@Override
	public int compareTo(Country o) {
		return this.name.compareToIgnoreCase(o.getName());
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(",", "Country(", ")");
		sj.add(name).add(continent).add(String.valueOf(height));
		return null;
	}
}
