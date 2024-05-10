import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();

        MediaLibrary library = new MediaLibrary();
        library.readFromFile(fileName);

        int choice;
        do {
            // Display the main menu
            System.out.println("1. View Media");
            System.out.println("2. Add Media");
            System.out.println("3. Remove Media");
            System.out.println("4. Save to file");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    // Display all media items in the library
                    library.displayMedia();
                    break;
                case 2:
                    // Add a new media item to the library
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter type (book/video): ");
                    String type = scanner.nextLine();
                    if (type.equalsIgnoreCase("book")) {
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter publisher: ");
                        String publisher = scanner.nextLine();
                        library.addMedia(new Book(Integer.toString(library.getMediaList().size() + 1), title, author, publisher));
                    } else if (type.equalsIgnoreCase("video")) {
                        System.out.print("Enter director: ");
                        String director = scanner.nextLine();
                        System.out.print("Enter format (Digital/DVD/Blu-Ray): ");
                        String format = scanner.nextLine();
                        library.addMedia(new Video(Integer.toString(library.getMediaList().size() + 1), title, director, format));
                    }
                    break;
                case 3:
                    // Remove a media item from the library
                    System.out.print("Enter title of media to remove: ");
                    String titleToRemove = scanner.nextLine();
                    for (Media media : library.getMediaList()) {
                        if (media.getTitle().equalsIgnoreCase(titleToRemove)) {
                            library.removeMedia(media);
                            break;
                        }
                    }
                    break;
                case 4:
                    // Save the library to the specified file
                    library.writeToFile(fileName);
                    break;
                case 5:
                    // Exit the program
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
