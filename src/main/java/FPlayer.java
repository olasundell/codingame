import java.util.*;
import java.io.*;
import java.math.*;

/**
 * CodinGame planet is being attacked by slimy insectoid aliens.
 **/
class FPlayer {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        SortedMap<Integer, Enemy> enemies = new TreeMap<>();
        
        // game loop
        while (true) {
            enemies.clear();

            System.err.println("--- NEW TURN ---");

            for (int i = 0 ; i < 2 ; i++) {
                Enemy e = new Enemy();
                e.name = in.next();
                e.distance = in.nextInt();

                System.err.println("Putting enemy " + e.name + " with distance " + e.distance);
                enemies.put(e.distance, e);
            }
            
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            if (!enemies.isEmpty()) {
                System.err.println("Targeting " + enemies.get(enemies.firstKey()).name);
                System.out.println(enemies.get(enemies.firstKey()).name);
            } else {
                System.err.println("Map is empty");
                System.out.println("Nobody");
            }
        }
    }
    
    static class Enemy {
        String name;
        int distance;
    }
}