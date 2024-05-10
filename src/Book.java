// Tyler
class Book extends Media {
    private String author; // The author of the book
    private String publisher; // The publisher of the book

    /**
     * Constructor for creating a new Book object.
     * @param id The unique identifier of the book.
     * @param title The title of the book.
     * @param author The author of the book.
     * @param publisher The publisher of the book.
     */
    public Book(String id, String title, String author, String publisher) {
        super(id, title, "book");
        this.author = author;
        this.publisher = publisher;
    }

    // Getters and setters

    /**
     * Gets the author of the book.
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     * @param author The author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the publisher of the book.
     * @return The publisher of the book.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the publisher of the book.
     * @param publisher The publisher of the book.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
