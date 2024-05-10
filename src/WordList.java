import java.util.Arrays;

public class WordList {
    private String[] words;
    private int size;
    private final static int DEFAULT_CAPACITY = 10;

    public WordList(int capacity) {
        words = new String[capacity];
        size = 0;
    }

    public WordList() {
        this(DEFAULT_CAPACITY);
    }

    public int size() {
        return size;
    }

    public boolean add(String s) {
        if (size == words.length) {
            throw new WordListFullException();
        }
        words[size++] = s;
        return true;
    }

    public boolean add(int index, String s) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == words.length) {
            throw new WordListFullException();
        }
        for (int i = size; i > index; i--) {
            words[i] = words[i - 1];
        }
        words[index] = s;
        size++;
        return true;
    }

    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        String removedWord = words[index];
        for (int i = index; i < size - 1; i++) {
            words[i] = words[i + 1];
        }
        words[--size] = null;
        return removedWord;
    }

    public void clear() {
        Arrays.fill(words, null);
        size = 0;
    }

    public boolean contains(String s) {
        for (int i = 0; i < size; i++) {
            if (words[i].equals(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(String s) {
        for (int i = 0; i < size; i++) {
            if (words[i].equals(s)) {
                for (int j = i; j < size - 1; j++) {
                    words[j] = words[j + 1];
                }
                words[--size] = null;
                return true;
            }
        }
        return false;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return words[index];
    }

    public String set(int index, String s) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        String oldWord = words[index];
        words[index] = s;
        return oldWord;
    }

    public int getLocation(String s) {
        for (int i = 0; i < size; i++) {
            if (words[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    public String[] toArray() {
        return Arrays.copyOf(words, size);
    }
}

class WordListFullException extends RuntimeException {
    public WordListFullException() {
        super("WordList is full");
    }
}
