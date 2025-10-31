import java.time.LocalDateTime;

public class Task {
    private static int count = 0;
    private int id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Task(String description, String status) {
        this.id = ++count;
        this.description = description;
        this.status = status;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
