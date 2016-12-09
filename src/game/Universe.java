package game;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Universe {
	static Set<Space> spaces = new HashSet<Space>();

	static void addSpace(Space space) {
		spaces.add(space);
	}

	static Iterator<Space> spaceIterator() {
		return spaces.iterator();
	}
}
