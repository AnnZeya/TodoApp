package app;

import java.util.Date;

public class todo {
    private int todoId;
    private int projectId;
    private String description;
    private String status;
    private Date createdDate;
    private Date updatedDate;
    private Date dueDate;

    // Constructors
    public todo() {
    }

    public todo(int projectId, String description, Date dueDate) {
        this.projectId = projectId;
        this.description = description;
        this.dueDate = dueDate;
        this.status = "pending"; // Default status
        this.createdDate = new Date(); // Current date
        this.updatedDate = new Date(); // Current date
    }

    // Getters and setters
    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    // Additional methods if needed
}
