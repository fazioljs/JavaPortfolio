import java.util.*;
import java.io.*;
/*
* A class that is essentially an ArrayList of places, along with some methods that can
* process the data in that ArrayList.  Each place is a zipcode, city, state, latitude, and longitude.
*/
public class Places {
	
	// Do all your testing in this main method.
	public static void main(String[] args) throws FileNotFoundException {
	    System.out.println("STARTING");
	   
       Places places = new Places("ZipCodes.txt");
       System.out.println("Number of states = " + places.allStateNames().size());
       System.out.println("Number of cities = " + places.allCityNames().size());
       System.out.println("States that contain city named OXFORD = " + places.getStatesThatContainThisCity("OXFORD"));
       System.out.println("Number of cities names in OH = " + places.allCityNames("OH").size());
       TreeSet<String> states = new TreeSet<String>();
       states.add("OH");
       states.add("KY");
       System.out.println("Number of unique cities names in OH & KY = " + places.allCityNames(states).size());
       TreeSet<String> cities = new TreeSet<String>();
       cities.add("OXFORD");
       cities.add("TRENTON");
       System.out.println("The states that contains city named OXFORD or TRENTON= " + places.getStatesThatContainAnyOfTheseCities(cities));
       System.out.println("Number of common city names in OH & KY: " + places.getCommonCityNames("OH", "KY").size());
       System.out.println("Cities in zip code 45040: " + places.getCityNameFromZipCode(45056));
       System.out.println("Zip codes in OXFORD OH: " + places.getZipCodes("OXFORD", "OH"));
       System.out.println("Number of Zip codes in CINNCINATI OH: " + places.getZipCodes("CINCINNATI", "OH").size());
       System.out.println("Test getZipCount: ");
       System.out.println("Zip count of 501= " + places.getZipCount().get(501));
       System.out.println("Zip count of 45040= " + places.getZipCount().get(45040));
       System.out.println("Zip count of 10001= " + places.getZipCount().get(10001));
       System.out.println("Test getStateCount: ");
       System.out.println("OH appears " + places.getStateCount().get("OH") + " in the list");
       System.out.println("KY appears " + places.getStateCount().get("KY") + " in the list");
       System.out.println("Test getNumberOfZipCodesForEachState: ");
       System.out.println("Number of Zip codes in OH = " + places.getNumberOfZipCodesForEachState().get("OH"));
       System.out.println("Number of Zip codes in KY = " + places.getNumberOfZipCodesForEachState().get("KY"));
       System.out.println("Test getCityNames: ");
       System.out.println("The number of city names in OH = " + places.getCityNames().get("OH").size());
       System.out.println("Test getZipCodesInStates: ");
       System.out.println("The number of zip codes in OH = " + places.getZipCodesInStates().get("OH").size());
       System.out.println("The zip codes that are within a 10 from 45050= " + places.getZipCodesCloseTo(45050, 10));
       System.out.println("Ranked list of states by the number of zip codes= " + places.mostZipCodes());
       System.out.println("The city name(s) in most states = " + places.cityNameInMostStates());
       System.out.println("DONE");
	}
	private ArrayList<Location> list;
	// Authors: Tyler
    /*
     * Takes a file full of locations formatted in a particular way, and builds an
     * ArrayList of all those locations, storing each one in a Location object. Once
     * this method is done, the places ArrayList will have all the data you need in
     * order to solve these problems. If you are curious, you can look at
     * ZipCodes.txt to see where this data is coming from. The data in that file is
     * "tab separated".
     * DON'T CHANGE THIS.
     */
	public Places(String fname) throws FileNotFoundException {
		list = new ArrayList<>();
		Scanner input = new Scanner(new File(fname));
		input.nextLine(); // skip first line of file because it is header data
		// Process each line of the file
		while (input.hasNextLine()) {
			// The data in each line is tab-separated. That's what \t is. So, it splits
			// the data into a String array based on the locations of the tabs.
			String[] toks = input.nextLine().split("\t", -1);
			Location loc = new Location(toks);
			list.add(loc);
		}
		input.close();
	}
	// Authors: Mirza
    /**
     * Returns all state names in the entire database
     *
     * @return set of state names
     */
	public Set<String> allStateNames() {
		Set<String> set = new TreeSet<>();
		
		for(Location loc : list) {
		    set.add(loc.state);
		}
		
		return set;
	}
	// Authors: Joseph
    /**
     * Returns all city names in the entire database
     *
     * @return set of city names
     */
	public Set<String> allCityNames() {
	    Set<String> set = new TreeSet<>();
      
       for(Location loc : list) {
           set.add(loc.city);
       }
      
       return set;
	}
	// Authors: Tyler
    /**
     * Returns all the states that contain a particular city name. The empty set is
     * returned if the city name is not in any state.
     *
     * @param cityName target city name
     * @return set of states that contain the target city.
     */
	public Set<String> getStatesThatContainThisCity(String cityName) {
		Set<String> set = new TreeSet<>();
		
		for(Location loc : list) {
		    if(loc.city.equals(cityName)) {
		        set.add(loc.state);
		    }
		}
		
		return set;
	}
	// Authors: Mirza
    /**
     * Returns all city names that reside within a particular state.
     *
     * @param state target state
     * @return set of city names
     */
	public Set<String> allCityNames(String state) {
	    Set<String> set = new TreeSet<>();
      
       for(Location loc : list) {
           if(loc.state.equals(state)) {
               set.add(loc.city);
           }
       }
      
       return set;
	}
	 // Authors: Joseph
    /**
     * Returns all city names that reside within any of the given states.
     *
     * @param states target states
     * @return set of city names
     */
	public Set<String> allCityNames(Set<String> states) {
	    Set<String> set = new TreeSet<>();
      
       for(Location loc : list) {
           if(states.contains(loc.state)) {
               set.add(loc.city);
           }
       }
      
       return set;
	}
	// Authors: Tyler
    /**
     * Returns the states that contain any of the target cities. The empty set is
     * returned if none of the cities are in any state.
     *
     * @param cityNames target cities
     * @return set of states that contain any of the target cities.
     */
	public Set<String> getStatesThatContainAnyOfTheseCities(Set<String> cityNames) {
	    Set<String> set = new TreeSet<>();
	   
	    for(Location loc : list) {
	        if(cityNames.contains(loc.city)) {
	            set.add(loc.state);
	        }
	    }
	   
	    return set;
	}
	// Authors: Mirza
    /**
     * Returns the city names that appear in both of the given states
     *
     * @param state1 first target state
     * @param state2 second target state
     * @return set of city names
     */
	public Set<String> getCommonCityNames(String state1, String state2) {
		Set<String> setStr1 = allCityNames(state1);
		Set<String> setStr2 = allCityNames(state2);
		setStr1.retainAll(setStr2);
		return setStr1;
	}
	// Authors: Joseph
    /**
     * Returns all the city names that correspond to a particular zipcode. The empty
     * set is returned if the zipcode is not valid.
     *
     * @param zipCode target zip code
     * @return set with all city names with the target zip code.
     */
	public Set<String> getCityNameFromZipCode(int zipCode) {
	    Set<String> set = new TreeSet<>();
	   
	    for(Location loc : list) {
	        if(loc.zipCode == zipCode) {
	            set.add(loc.city);
	        }
	    }
	   
	    return set;
	}
	// Authors: Tyler
    /**
     * Returns all the zipcodes that are contained in a particular city-state. An
     * empty set (not null) is returned if the city-state pair is illegal.
     *
     * @param cityName target city name
     * @param state    target state
     * @return set with all relevant zipcodes.
     */
	public Set<Integer> getZipCodes(String cityName, String state) {
	    Set<Integer> set = new TreeSet<>();
	   
	    for(Location loc : list) {
	        if(loc.city.equals(cityName) && loc.state.equals(state)) {
	            set.add(loc.zipCode);
	        }
	    }
	   
	    return set;
	}
	// Authors: Mirza
    /**
     * Some zipcodes appear only once, while others appear multiple times.
     * This method returns a map keyed to a zipcode.  The value for each zipcode
     * is the number of times it appears in the list.
     * @return
     */
	public Map<Integer, Integer> getZipCount() {
		Map<Integer, Integer> map = new TreeMap<>();
		
		for(Location loc : list) {
		    if(!map.containsKey(loc.zipCode)) {
		        map.put(loc.zipCode, 0);
		    }
		    map.put(loc.zipCode, map.get(loc.zipCode) + 1);
		}
		
		return map;
	}
	
	// Authors: Joseph
    /**
     * Returns a map keyed to a state's name. The value for each state is the
     * number of times it appears in the list.
     * @return
     */
	public Map<String, Integer> getStateCount() {
	    Map<String, Integer> map = new TreeMap<>();
	   
	    for(Location loc : list) {
	        if(!map.containsKey(loc.state)) {
	            map.put(loc.state, 0);
	        }
	        map.put(loc.state, map.get(loc.state) + 1);
	    }
	   
	    return map;
	}
	
	
	// Authors: Tyler
    /**
     * Returns a map that is keyed to a state's name. The value for each state is the
     * number of unique zip codes for that state.
     * @return map in which the key is a state, and the value is the count of how many zip codes the state has
     */
	public Map<String, Integer> getNumberOfZipCodesForEachState() {
	    Map<String, Set<Integer>> map = getZipCodesInStates();
	    Map<String, Integer> result = new TreeMap<>();
	   
	    for(Map.Entry<String, Set<Integer>> entry : map.entrySet()) {
	        result.put(entry.getKey(), entry.getValue().size());
	    }
	   
	    return result;
	}
	
	// Authors: Mirza
    /**
     * Returns a map that is keyed to state name. The values in the map are the set
     * of city names that reside in that particular state. The map looks like: "AL"
     * --> { "MONTGOMERY", "MOBILE", ... } "AK" --> { "ANCHORAGE", "BARROW", ...}
     * ...
     *
     * @return mapping from states to set of city names.
     */
	public Map<String, Set<String>> getCityNames() {
	    Map<String, Set<String>> map = new TreeMap<>();
	   
	    for(Location loc : list) {
	        if(!map.containsKey(loc.state)) {
	            map.put(loc.state, new TreeSet<>());
	        }
	        map.get(loc.state).add(loc.city);
	    }
	   
	    return map;
	}
	// Authors: Joseph
    /**
     * Returns a map that is keyed to state name. The values in the map is a set of
     * zip codes that reside in that particular state. The map looks like: "AL" -->
     * { 36863, 35755, ... } "AK" --> { 44256, 44257, ...} ...
     *
     * @return mapping from states to set of zipcodes.
     */
	public Map<String, Set<Integer>> getZipCodesInStates() {
		Map<String, Set<Integer>> map = new TreeMap<>();
		
	    for(Location loc : list) {
	        if(!map.containsKey(loc.state)) {
	            map.put(loc.state, new TreeSet<>());
	        }
	        map.get(loc.state).add(loc.zipCode);
	    }
	   
	    return map;
	}
	// Authors: Tyler
    /**
     * Returns all zipcodes that are within a specified distance from a particular
     * zipcode.
     *
     * @param zipCode  target zipcode
     * @param distance maximum distance from target zipcode
     * @return all zipcodes that are within "distance" from the target zipcode
     */
	public Set<Integer> getZipCodesCloseTo(int zipCode, double distance) {
	    Set<Integer> set = new TreeSet<>();
	    Location target = null;
	   
	    for(Location loc : list) {
	        if(loc.zipCode == zipCode) {
	            target = loc;
	            break;
	        }
	    }
	    if(target == null) {
	        return set; // Return empty set if target zipcode is not found
	    }
	    for(Location loc : list) {
	        if(haversine(target.latitude, target.longitude, loc.latitude, loc.longitude) <= distance) {
	            set.add(loc.zipCode);
	        }
	    }
	   
	    return set;
	}
	
	public double haversine(double lat1, double lon1, double lat2, double lon2) {
	    int r = 6371;
	    lat1 = Math.toRadians(lat1);
       lat2 = Math.toRadians(lat2);
      
       double dLat = lat2 - lat1;
       double dLon = Math.toRadians(lon2 - lon1);
       double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
       double c = 2 * Math.asin(Math.sqrt(a));
      
       return r * c;
	}
	// Authors: Mirza
    /**
     * Ranked list of states, where the ranking is ascending order of number of
     * zipcodes.
     *
     * @return
     */
	public ArrayList<String> mostZipCodes() {
	    Map<String, Integer> map = getNumberOfZipCodesForEachState();
	    List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
	   
	    list.sort(Map.Entry.<String, Integer>comparingByValue().reversed());
	    ArrayList<String> result = new ArrayList<>();
	   
	    for(Map.Entry<String, Integer> entry : list) {
	        result.add(entry.getKey());
	    }
	   
	    return result;
	}
	// Authors: Joseph
    /**
     * The city name(s) that appears in the most states. Note that some
     * larger cities may appear more than once in a state, but should only be counted at most
     * once for that state.  For example, "COLUMBUS" is listed multiple times for OH, but should
     * only be counted one time for OH. That is, the large number of "COLUMBUS" zip codes in OH
     * does not make it a more common name.
     *
     * @return set of city names
     */
	public Set<String> cityNameInMostStates() {
	    Map<String, Set<String>> cityToStates = new HashMap<>();
	   
	    for(Location loc : list) {
	        if(!cityToStates.containsKey(loc.city)) {
	            cityToStates.put(loc.city, new HashSet<>());
	        }
	        cityToStates.get(loc.city).add(loc.state);
	    }
	    int maxStates = 0;
	    Set<String> cities = new HashSet<>();
	   
	    for(Map.Entry<String, Set<String>> entry : cityToStates.entrySet()) {
	        int numStates = entry.getValue().size();
	        if(numStates > maxStates) {
	            maxStates = numStates;
	            cities.clear();
	            cities.add(entry.getKey());
	        } else if(numStates == maxStates) {
	            cities.add(entry.getKey());
	        }
	    }
	    return cities;
	}
	/*
	 * Writes a set to a specified file.
	 * DON'T CHANGE THIS.
	 */
	public static <T> void display(PrintWriter output, Set<T> items) {
		if (items == null) {
			output.println("null");
			return;
		}
		int LEN = 80;
		String line = "[";
		for (T item : items) {
			line += item.toString() + ",";
			if (line.length() > LEN) {
				output.println(line);
				line = "";
			}
		}
		output.println(line + "]");
	}
	/*
	 * Writes a map to a specified file
	 * DON'T CHANGE THIS.
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> void display(PrintWriter output, Map<K, V> items) {
		if (items == null) {
			output.println("null");
			return;
		}
		for (K key : items.keySet()) {
			output.print(key + "---------->");
			Object o = items.get(key);
			if (o instanceof Collection) {
				output.println();
				display(output, (Set<Object>) items.get(key));
			} else {
				output.println(items.get(key));
			}
		}
	}
	/*
	 * A private inner class to group together data about a location: city, state,
	 * zip, latitude,and longitude
	 * DON'T CHANGE THIS INNER CLASS
	 */
	private class Location {
		private int zipCode;
		private String city;
		private String state;
		private double latitude, longitude;
		// Java already has a parseDouble method, but it will not handle empty
		// strings for us. This version converts empty strings to the value -999.0.
		private double parseDouble(String str) {
			double value = -999.0;
			if (!str.isEmpty()) {
				value = Double.parseDouble(str);
			}
			return value;
		}
		// This works because we already know from the file which fields contain
		// the data we want. Fields 1, 3, 4, 6, and 7 are the 5 pieces of information we
		// are interested in.
		// the rest will be ignored.
		private Location(String[] toks) {
			zipCode = (int) parseDouble(toks[1]);
			city = toks[3];
			state = toks[4];
			latitude = parseDouble(toks[6]);
			longitude = parseDouble(toks[7]);
		}
		@Override
		public String toString() {
			return "Location [zipCode=" + zipCode + ", cityName=" + city + ", state=" + state + ", latitude=" + latitude
					+ ", longitude=" + longitude + "]";
		}
	}
}
