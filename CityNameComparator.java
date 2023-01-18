import java.util.Comparator;

/**
 * Implementation of the Comparator for the City object
 * that compares the name of the city in ascending or descending order
 * 
 * @author  Aarya Kulkarni
 * @since   January 09, 2023
 */
public class CityNameComparator implements Comparator<City> {

    private final boolean ascending;
    /**
     * constructs the object of CityNameComparator
     * @param ascending boolean to decide if the comparision should be ascending or descending
     */
    public CityNameComparator(boolean ascending)
    {
        this.ascending = ascending;
    }
    /**
     * @param a object of City
     * @param b object of City
     * 
     * @return comparitive value of names of the cities a and b
     * 
     */
    @Override
    public int compare(City a, City b) {
        if (ascending)
            return a.getName().compareTo(b.getName());
        return b.getName().compareTo(a.getName());
    }

}
