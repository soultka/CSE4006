package faceduck.skeleton.util;

import faceduck.custom.util.Pair;

/**
 * @Custom Improve
 *
 * Represents the four directions in the world.
 *
 * Now it also represents the stop state.
 * And represents pair as x, y position for easy use in add to location.
 */
public enum Direction {
    STOP(0, 0), NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0);

    Location position;

    Direction(int x, int y) {
        position = new Location(x, y);
    }

    public Location getValue() {
        return position;
    }
}
