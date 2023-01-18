/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 * @author	Aarya Kulkarni
 * @since	January 09, 2023
 */
public class City implements Comparable<City> {
	
	// fields
    private String name;
    private String state;
    private String designation;
    private int population;
	
	/**
	 * Constructs the object of city
	 * @param state state of the city
	 * @param name name of the city
	 * @param designation type of the city
	 * @param population population of the city
	 */
	public City(String state, String name, String designation, int population) {
	    this.state = state;
	    this.name = name;
	    this.designation = designation;
	    this.population = population;
	}

	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
    @Override
    public int compareTo(City other) {
        if (this.population != other.population) {
            return this.population - other.population;
        } else if (!this.state.equals(other.state)) {
            return this.state.compareTo(other.state);
        }
        return this.name.compareTo(other.name);
    }

	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
	    City other = (City)obj;
	    return this.name.equals(other.name) && this.state.equals(other.state);
	}

	/**	Accessor methods */
	/**
	 * @return name of the city 
	 */
    public String getName() {
        return name;
    }
	/**
	 * @return state of the city 
	 */
    public String getState() {
        return state;
    }
	/**
	 * @return type of the city
	 */
    public String getDesignation() {
        return designation;
    }
	/**
	 * @return population of the city 
	 */

    public int getPopulation() {
        return population;
    }
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}
