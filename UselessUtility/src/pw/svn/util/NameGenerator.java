package pw.svn.util;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Waidmann
 * @version 1.0
 */
public class NameGenerator {
	private static String[] names = { "Spike", "Garble", "Whimsey Weatherbe", "Forget-Me-Not", "Honeysuckle", "Lily", "Morning Glory", "Peach Blossom", "Rosedust", "Backstroke", "Beachcomber", "Ripple", "Sand Dollar", "Sealight", "Sea Mist", "Sea Shimmer", "Seawinkle", "Surf Rider", "Sun Shower", "Water Lily", "Wavedancer", "Whitecap", "Twilight Sparkle", "Spike", "Pinkie Pie", "Applejack", "Rainbow Dash", "Fluttershy", "Rarity", "Princess Celestia", "Apple Bloom", "Scootaloo", "Sweetie Belle", "Babs Seed", "Derpy Hooves", "Big McIntosh", "Granny Smith", "Cheerilee", "Zecora", "Mayor Mare", "Princess Luna", "Trixie", "Discord", "King Sombra"};
	private static ArrayList<String> activeNames = new ArrayList<String>();
	private static Random rand = new Random();
	/**
	 * Get a name from the list of possible names
	 * @return a name
	 */
	public static String getNewName() {
		String name = names[rand.nextInt(names.length)];
		while(isNameActive(name)){
			name = names[rand.nextInt(names.length)];
		}
		activeNames.add(name);
		return name;
	}
	
	/**
	 * A method for checking the availability of a name
	 * @param name
	 * @return whether the name isn't available
	 */
	public static boolean isNameActive(String name) {
		return activeNames.contains(name);
	}
	
}
