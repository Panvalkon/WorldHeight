package heights;

import java.util.Comparator;

public class CompHeight implements Comparator<Country> {

	@Override
	public int compare(Country o1, Country o2) {
		int res = 0;
		res = Double.compare(o1.getHeight(), o2.getHeight());
		if (res == 0) {
			res = o1.compareTo(o2);
		}
		return res;
	}

}

