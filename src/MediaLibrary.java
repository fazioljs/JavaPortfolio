// Mirza
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class MediaLibrary {
    private ArrayList<Media> mediaList; // List to store media items

    public MediaLibrary() {
        this.mediaList = new ArrayList<>(); // Initialize mediaList
    }

    // Add a media item to the library
    public void addMedia(Media media) {
        mediaList.add(media);
    }

    // Remove a media item from the library
    public void removeMedia(Media media) {
        mediaList.remove(media);
    }

    // Display all media items in the library
    public void displayMedia() {
        for (Media media : mediaList) {
            System.out.println(media.getTitle() + " (" + media.getType() + ")");
            if (media instanceof Book) {
                Book book = (Book) media;
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Publisher: " + book.getPublisher());
            } else if (media instanceof Video) {
                Video video = (Video) media;
                System.out.println("Director: " + video.getDirector());
                System.out.println("Format: " + video.getFormat());
            }
            System.out.println();
        }
    }

    // Read media data from a file and add to the library
    public void readFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String id = parts[0].trim();
                    String title = parts[1].trim();
                    String type = parts[2].trim();
                    if (type.equalsIgnoreCase("book") && parts.length >= 5) {
                        String author = parts[3].trim();
                        String publisher = parts[4].trim();
                        addMedia(new Book(id, title, author, publisher));
                    } else if (type.equalsIgnoreCase("video") && parts.length >= 5) {
                        String director = parts[3].trim();
                        String format = parts[4].trim();
                        addMedia(new Video(id, title, director, format));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Write media data from the library to a file
    public void writeToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Media media : mediaList) {
                writer.println(media.getId() + "," + media.getTitle() + "," + media.getType() + ",");
                if (media instanceof Book) {
                    Book book = (Book) media;
                    writer.println(book.getAuthor() + "," + book.getPublisher());
                } else if (media instanceof Video) {
                    Video video = (Video) media;
                    writer.println(video.getDirector() + "," + video.getFormat());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getter for the mediaList
    public ArrayList<Media> getMediaList() {
        return mediaList;
    }
}
