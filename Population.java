import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *	Population - implement different sor0ng methods and organize a large set of data.
 *  Create a database from the list of USA cities and sort the data.
 *  Cities will sort the data for population and city name using Selection Sort, Insertion Sort, and Merge Sort.
 *	Requires FileUtils and Prompt classes.
 *
 * @author  Aarya Kulkarni
 * @since   January 09, 2023
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
    // object having apis to sort list of cities object
	private final SortMethods sm = new SortMethods();
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
    /**
     *  Reads usPopData2017.txt into List
     */
	public void readData() {
	    Scanner scanner = FileUtils.openToRead(DATA_FILE, "[\\t\\n]");
	    cities = new ArrayList<City>();
	    while(scanner.hasNext())
        {
	        cities.add(new City(scanner.next(), scanner.next(), scanner.next(), Integer.parseInt(scanner.next())));
        }
	    System.out.println(cities.size() + " cities in database");
	    int val = 0;
	    
	    while (val != 9)
	    {
	        System.out.println();
	        printMenu();
	        do {
	            val = Prompt.getInt("Enter selection (1, 6) OR 9");
	        } while (val < 1 || (val > 9 || val == 7 || val == 8));
	        switch (val)
	        {
	            case 1: fiftyLeastPopulousCities(); break;
                case 2: fiftyMostPopulousCities(cities, "Fifty most populous cities"); break;
                case 3: fiftyCitiesSortedByName(true); break;
                case 4: fiftyCitiesSortedByName(false); break;
                case 5: fiftyMostPopulousCitiesInState(); break;
                case 6: AllNamedCities(); break;
                default: break;
	        }
	    }
	    System.out.println("\nThanks for using Population!");
	}
	
    // Fifty most populous cities in any given state
	private void fiftyMostPopulousCitiesInState() {
	    System.out.println();
	    String header = "Fifty most populous cities in %s";
	    List<City> matched = new ArrayList<City>();
	    String state = "";
	    while (matched.isEmpty())
	    {
	        // Prompt for State Name
    	    state = Prompt.getString("Enter state name (ie. Alabama)");
    	    for (City city : cities) {
                if (city.getState().equals(state))
                    matched.add(city);
            }
    	    if (matched.isEmpty()) {
    	        System.out.println("ERROR: " + state +" is not valid");
    	    }
	    }
	    fiftyMostPopulousCities(matched, String.format(header, state));
    }

    // Lists all cities with the user provided city name sorted by its population
    // Uses bubble sort
    private void AllNamedCities() {
        System.out.println();
        String header = "City %s by population";
        List<City> matched = new ArrayList<City>();
        String name = "";
        while (matched.isEmpty())
        {
            // Prompt for City Name
            name = Prompt.getString("Enter city name");
            for (City city : cities) {
                if (city.getName().equals(name))
                    matched.add(city);
            }
            if (matched.isEmpty()) {
                System.out.println("ERROR: " + name +" is not valid");
            }
        }
        System.out.println();
        long startMillisec = System.currentTimeMillis();
        sm.bubbleSort(matched, null);
        long endMillisec = System.currentTimeMillis();
        printCities(String.format(header, name), matched, matched.size() - 1, 0);
        System.out.println("Elapsed Time " + (endMillisec - startMillisec) + " milliseconds");

    }

    /**
     * Fifty cities sorted by name either in ascending order or descending order
     * 
     * @param ascending true if cities are sorted in ascending order, descending otherwise
     */
    private void fiftyCitiesSortedByName(boolean ascending) {
        String header;
        long startMillisec;
        long endMillisec;
        if (ascending)
        {
            header = "First fifty cities sorted by name";
            startMillisec = System.currentTimeMillis();
            sm.insertionSort(cities, new CityNameComparator(ascending));
            endMillisec = System.currentTimeMillis();
        }
        else
        {
            header = "Fifty cities sorted by name descending";
            startMillisec = System.currentTimeMillis();
            sm.mergeSort(cities, new CityNameComparator(ascending));
            endMillisec = System.currentTimeMillis();
        }
        printCities(header, cities, 0, 50);
        System.out.println("Elapsed Time " + (endMillisec - startMillisec) + " milliseconds");
    }

    // fifty most populous cities
    private void fiftyMostPopulousCities(List<City> list, String header) {
        long startMillisec = System.currentTimeMillis();
        sm.mergeSort(list, null);
        long endMillisec = System.currentTimeMillis();
        printCities(header, list, list.size() - 1, list.size() - 50);
        System.out.println("Elapsed Time " + (endMillisec - startMillisec) + " milliseconds");
    }

    // fifty least populous cities
    private void fiftyLeastPopulousCities() {
        String header = "Fifty least populous cities";
        long startMillisec = System.currentTimeMillis();
        sm.selectionSort(cities, null);
        long endMillisec = System.currentTimeMillis();
        printCities(header, cities, 0, 50);
        System.out.println("Elapsed Time " + (endMillisec - startMillisec) + " milliseconds");
    }
    
    // method to print cities from start to end within the list of cities
    private void printCities(String header, List<City> list, int start, int end)
    {
        System.out.println();
        System.out.println(header);
        System.out.println(String.format("     %-22s %-22s %-12s %12s", "State", "City", "Type", "Population"));
        if (start < end) {
            int ctr = 1;
            for (int i = start; i < end; i++) {
                System.out.print(String.format(" %2d: ", ctr));
                System.out.println(list.get(i));
                ctr ++;
            }
        } else {
            int ctr = 1;
            for (int i = start; i >= end; i--) {
                System.out.print(String.format(" %2d: ", ctr));
                System.out.println(list.get(i));
                ctr ++;
            }
        }
        System.out.println();
    }

    /**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	public static void main(String[] args) {
	    Population program = new Population();
	    program.printIntroduction();
	    program.readData();
    }
	
}
