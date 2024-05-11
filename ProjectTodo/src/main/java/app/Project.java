package app;

	public class Project {
	    private int id;
	    private String title;
	    private String createdDate;

	    // Constructor
	    public Project(int id, String title, String createdDate) {
	        this.id = id;
	        this.title = title;
	        this.createdDate = createdDate;
	    }

	    //Getters and setters
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getCreatedDate() {
	        return createdDate;
	    }

	    public void setCreatedDate(String createdDate) {
	        this.createdDate = createdDate;
	    }
	}

