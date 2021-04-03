package collections;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.opencsv.CSVWriter;

public class Main {

	static TreeSet<Integer> treeSet = new TreeSet<Integer>();
	static HashSet<Integer> hashSet = new HashSet<Integer>();
	static LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<Integer>();
	static Vector<Integer> vector = new Vector<Integer>();
	static PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
	static ArrayList<Integer> arrayList = new ArrayList<Integer>();
	static LinkedList<Integer> linkedList = new LinkedList<Integer>();
	static LinkedList<Collection<Integer>> collections = new LinkedList<Collection<Integer>>();
	static CSVWriter writer;
	static List<String> csvLine = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		writer = new CSVWriter(new FileWriter("retrievingTime.csv"));
		String titles[] = { "Size", "TreeSet", "HashSet", "LinkedHashSet", "Vector", "PriorityQueue", "ArrayList",
				"LinkedList" };
		writer.writeNext(titles);
		writer.flush();
		int sizes[] = { 10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000, 500000, 1000000 };

		for (int size : sizes) {
			int random = (int) (Math.random() * size);
			csvLine.add(size + "");
			fillCollections();
			for (Collection<Integer> collection : collections) {
				initializingData(collection, size);
				retrievingTime(collection, size, random);
			}
			writeToCSV();
		}
		writer = new CSVWriter(new FileWriter("deletionTime.csv"));
		writer.writeNext(titles);
		writer.flush();
		for (int size : sizes) {
			int random = (int) (Math.random() * size);
			csvLine.add(size + "");
			fillCollections();
			for (Collection<Integer> collection : collections) {
				initializingData(collection, size);
				deletionTime(collection, size, random);
			}
			writeToCSV();
		}
		writer = new CSVWriter(new FileWriter("traversingTime.csv"));
		writer.writeNext(titles);
		writer.flush();
		for (int size : sizes) {
			csvLine.add(size + "");
			fillCollections();
			for (Collection<Integer> collection : collections) {
				initializingData(collection, size);
				traversingTime(collection, size);
			}
			writeToCSV();
		}
		System.out.println("Done!");
	}

	private static void writeToCSV() throws IOException {
		writer.writeNext(csvLine.toArray(new String[0]));
		writer.flush();
		csvLine.removeAll(csvLine);
	}

	private static void fillCollections() {
		collections.removeAll(collections);
		collections.add(treeSet);
		collections.add(hashSet);
		collections.add(linkedHashSet);
		collections.add(vector);
		collections.add(priorityQueue);
		collections.add(arrayList);
		collections.add(linkedList);
	}

	private static void traversingTime(Collection<Integer> collection, int size) throws IOException {
		Iterator<Integer> iterator = collection.iterator();
		long startTime = System.nanoTime();
		while (iterator.hasNext()) {
			iterator.next();
		}
		long totalTime = System.nanoTime() - startTime;
		csvLine.add(totalTime + "");
	}

	private static void deletionTime(Collection<Integer> collection, int size, int random) throws IOException {
		long startTime = System.nanoTime();
		collection.remove(random);
		long totalTime = System.nanoTime() - startTime;
		csvLine.add(totalTime + "");
	}

	private static void retrievingTime(Collection<Integer> collection, int size, int random) throws IOException {
		long startTime = System.nanoTime();
		collection.contains(random);
		long totalTime = System.nanoTime() - startTime;
		csvLine.add(totalTime + "");
	}

	private static void initializingData(Collection<Integer> collection, int size) {
		for (int i = 0; i < size; i++) {
			collection.add(i);
		}
	}
}