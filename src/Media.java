// Mirza
class Media {
    private String id; // The unique identifier of the media
    private String title; // The title of the media
    private String type; // The type of the media)

    /**
     * Constructor for creating a new Media object.
     * @param id The unique identifier of the media.
     * @param title The title of the media.
     * @param type The type of the media.
     */
    public Media(String id, String title, String type) {
        this.id = id;
        this.title = title;
        this.type = type;
    }

    // Getters and setters

    /**
     * Gets the unique identifier of the media.
     * @return The unique identifier of the media.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the media.
     * @param id The unique identifier of the media.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the title of the media.
     * @return The title of the media.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the media.
     * @param title The title of the media.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the type of the media.
     * @return The type of the media.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the media.
     * @param type The type of the media.
     */
    public void setType(String type) {
        this.type = type;
    }
}
