import java.util.Hashtable;
import java.util.*;

class Main {
    public static void main(String[] args) {

        int size = 16;
        Hashtable<Integer, Integer> hashtable = new Hashtable<>(size);
        int[] keys = {10, 24, 36, 15, 42, 19, 33, 27, 65, 78, 91, 53, 12, 30, 47, 58};

        doubleHashInsert(hashtable, keys, size);
    }

    private static void doubleHashInsert(Hashtable<Integer, Integer> hashtable, int[] keys, int size) {

        for (int key : keys) {
            int i = 0;
            int H1 = hash1(key, size);
            int H2 = hash2(key);

            while (i < size) {
                int slot = (H1 + (i * H2)) % size;
                System.out.println("\ni: " + i);

                if (hashtable.get(slot) == null) {
                    hashtable.put(slot, key);
                    System.out.println("Key " + key + " inserted at slot " + (slot + 1));
                    break;
                } else {
                    System.out.println("Collision detected at slot " + (slot + 1));
                    System.out.println("Key " + key + " collided with key " + hashtable.get(slot));
                    System.out.println("Testing new slot");
                    i++;
                }
            }

            if (i == size) {
                System.out.println("Table is full, could not insert key " + key);
            }

            System.out.println("\nCurrent hashtable: ");
            for (int j = 0; j < size; j++) {
                System.out.println(String.format("Slot %2d: %2d", (j + 1), hashtable.get(j)));
            }
        }
    }

    private static int hash1(int key, int size) {
        return key % size;
    }

    private static int hash2(int key) {
        return 2 * (key % 4) + 1;
    }
}