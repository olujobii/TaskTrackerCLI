package app;

import java.time.LocalDateTime;

class Task {
    private static int count = 0;
    private int id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected Task(int id, String description, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        ++count;
    }

    protected Task(String description, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = ++count;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected int getId() {
        return id;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected String getDescription() {
        return description;
    }

    protected void setStatus(String status) {
        this.status = status;
    }

    protected String getStatus() {
        return status;
    }

    protected LocalDateTime getCreatedAt() {
        return createdAt;
    }

    protected void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    protected LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
