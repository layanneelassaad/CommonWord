
import java.util.*;
import java.util.Iterator;
import java.util.Map.Entry;

import java.io.*;
import java.util.*;

public class CommonWordFinder {

    static class MapEntry<K, V> implements Entry<K, V> {
        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return null;
        }
    }

    // MyMap interface represents the map data structure
    interface MyMap<K, V> {
        void put(K key, V value);
        V get(K key);
        int size();
        Set<K> keySet();
    }

    // Implementation of MyMap interface using HashMap
    static class MyHashMap<K, V> implements MyMap<K, V> {
        private HashMap<K, V> hashMap;

        public MyHashMap() {
            hashMap = new HashMap<>();
        }

        @Override
        public void put(K key, V value) {
            hashMap.put(key, value);
        }

        @Override
        public V get(K key) {
            return hashMap.get(key);
        }

        @Override
        public int size() {
            return hashMap.size();
        }

        @Override
        public Set<K> keySet() {
            return hashMap.keySet();
        }
    }

    public static void main(String[] args) throws Exception {
        // ... (Main method content remains the same)
    }



    public static void outputDisplay(MyMap<String, Integer> map, int limit) {
        System.out.println("Total unique words: " + map.size());

        // Convert the map to an array of Entry objects
        Entry<String, Integer>[] mapToArray = new Entry[map.size()];
        int index = 0;
        for (String key : map.keySet()) {
            mapToArray[index++] = new MapEntry<>(key, map.get(key));
        }

        insertionSort(mapToArray);
        printOutput(mapToArray, limit);
    }

    public static void insertionSort(Entry<String, Integer>[] array) {
        for (int i = 1; i < array.length; ++i) {
            int k;
            Entry<String, Integer> current = array[i];
            for (k = i - 1; k >= 0 && compareEntries(array[k], current) == -1; --k) {
                array[k + 1] = array[k];
            }
            array[k + 1] = current;
        }
    }

    private static int compareEntries(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
        if (!entry1.getValue().equals(entry2.getValue())) {
            return entry1.getValue().compareTo(entry2.getValue()) * -1; // Reverse the comparison for descending order
        } else { // if the values are the same, we compare the keys using compareTo
            return entry1.getKey().compareTo(entry2.getKey());
        }
    }

    private static int stringLength(Entry<String, Integer>[] array) {
        int length = 0;
        for (Entry<String, Integer> entry : array) {
            if (entry.getKey().length() > length) {
                length = entry.getKey().length();
            }
        }
        return length;
    }



    private static void printOutput(Entry<String, Integer>[] array, int limit) {
        int outputLength = stringLength(array);

        for (int i = 0; i < Math.min(array.length, limit); i++) {
            int lengthofI = String.valueOf(i + 1).length(); // Add 1 to the index to start from 1 instead of 0
            int lengthofCount = String.valueOf(array.length).length();
            int numberOfSpaces = outputLength - array[i].getKey().length(); // Calculate the number of spaces needed

            for (int x = lengthofI; x < lengthofCount; x++) {
                System.out.print(" ");
            }
            System.out.print(i + 1 + ". " + array[i].getKey());
            for (int j = 0; j < numberOfSpaces; j++) {
                System.out.print(' ');
            }
            System.out.println(array[i].getValue());
        }
    }