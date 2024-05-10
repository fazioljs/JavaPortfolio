// Tyler
class Video extends Media {
    private String director; // The director of the video
    private String format; // The format of the video (e.g., DVD, Blu-ray)

    /**
     * Constructor for creating a new Video object.
     * @param id The unique identifier of the video.
     * @param title The title of the video.
     * @param director The director of the video.
     * @param format The format of the video.
     */
    public Video(String id, String title, String director, String format) {
        super(id, title, "video");
        this.director = director;
        this.format = format;
    }

    // Getters and setters

    /**
     * Gets the director of the video.
     * @return The director of the video.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Sets the director of the video.
     * @param director The director of the video.
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Gets the format of the video.
     * @return The format of the video.
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the format of the video.
     * @param format The format of the video.
     */
    public void setFormat(String format) {
        this.format = format;
    }
}
