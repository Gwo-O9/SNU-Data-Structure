import java.util.*;
import java.util.ArrayList;

public class LinearHash {

	private Entry[] hashtable;
	private Entry[] hTable2;
	private int size;
	private int xcount = 0;
	private int ocount = 0;
	private int split = 0;

	public LinearHash(int HTinitSize) {
		size = HTinitSize;
		hashtable = new Entry[size * 2];
		for (int i = 0; i < size * 2; i++) {
			hashtable[i] = new Entry();
		}
	}

	class Entry {
		Set<String> tslot = new HashSet<String>();
	}

	public void copytable() {
		split = 0;
		hTable2 = new Entry[size * 2];
		System.arraycopy(hashtable, 0, hTable2, 0, hashtable.length);
		size = size * 2;
		hashtable = new Entry[size * 2];
		System.arraycopy(hTable2, 0, hashtable, 0, hTable2.length);
		for (int i = size; i < size * 2; i++) {
			hashtable[i] = new Entry();
		}
	}

	public int insertUnique(String word) { 
		long hash = MyUtil.ELFhash(word, size);
		int inthash = (int) hash;
		long hash1, hash2;
		int inthash1, inthash2;
		int probesize = size;

		while (probesize <= size * 2) {
			hash2 = MyUtil.ELFhash(word, probesize);
			inthash2 = (int) hash2;
			if (hashtable[inthash2].tslot.contains(word))
				return -1;
			else
				probesize = probesize * 2;
		}

		if (hash >= split) {
			if (!hashtable[inthash].tslot.isEmpty()) {
				hashtable[inthash].tslot.add(word);
				Iterator<String> iter = hashtable[split].tslot.iterator();
				ArrayList<String> arr = new ArrayList<String>();
				while (iter.hasNext()) {
					String str = iter.next();
					hash1 = MyUtil.ELFhash(str, size * 2);
					inthash1 = (int) hash1;
					if (inthash1 != split) {
						hashtable[inthash1].tslot.add(str);
						arr.add(str);
					}
				}
				for (int i = 0; i < arr.size(); i++) {
					hashtable[split].tslot.remove(arr.get(i));
				}
				arr.clear();
				split++;
				if (split == size) {
					copytable();
				}
			} else
				hashtable[inthash].tslot.add(word);
		} 
		else {
			hash1 = MyUtil.ELFhash(word, size * 2);
			inthash1 = (int) hash1;

			if (!hashtable[inthash1].tslot.isEmpty()) {
				hashtable[inthash1].tslot.add(word);
				Iterator<String> iter = hashtable[split].tslot.iterator();
				ArrayList<String> arr = new ArrayList<String>();
				while (iter.hasNext()) {
					String str = iter.next();
					hash = MyUtil.ELFhash(str, size * 2);
					inthash = (int) hash;
					if (split != inthash) {
						hashtable[inthash].tslot.add(str);
						arr.add(str);
					}
				}
				for (int i = 0; i < arr.size(); i++) {
					hashtable[split].tslot.remove(arr.get(i));
				}
				arr.clear();
				split++;
				if (split == size) {
					copytable();
				}
			} 
			else
				hashtable[inthash1].tslot.add(word);

		}
		return 0;
	}

	public int lookup(String word) {
		long hash = 0;
		int inthash = 0;
		long hash1 = 0;
		int inthash1 = 0;
		hash = MyUtil.ELFhash(word, size);
		inthash = (int) hash;
		if (hashtable[inthash].tslot.contains(word))
			return hashtable[inthash].tslot.size();
		else {
			hash1 = MyUtil.ELFhash(word, size * 2);
			inthash1 = (int) hash1;
			if (inthash1 >= size + split)
				return -hashtable[inthash].tslot.size();
			if (hashtable[inthash1].tslot.contains(word))
				return hashtable[inthash1].tslot.size();
		}
		return -hashtable[inthash1].tslot.size();

	}

	public void search() {
		for (int i = 0; i < size + split; i++) {
			if (hashtable[i].tslot.isEmpty()) {
				xcount++;
			} 
			else
				ocount += hashtable[i].tslot.size();
		}
	}

	public int wordCount() {
		if (ocount == 0)
			search();
		return ocount;
	}

	public int emptyCount() {
		return xcount;

	}

	public int size() {
		return size + split;
	}

	public void print() {

		for (int i = 0; i < size + split; i++) {
			if (hashtable[i].tslot.isEmpty()) {
				System.out.println("[" + i + ":]");
			} 
			else {
				System.out.print("[" + i + ":");
				TreeSet<String> treeSet = new TreeSet<String>();
				treeSet.addAll(hashtable[i].tslot);

				for (String result : treeSet)
					System.out.print(" " + result);
				System.out.println("]");
			}
		}
	}
}
