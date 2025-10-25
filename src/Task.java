import java.time.LocalDateTime;

public class Task {
    public static int count = 0;
    private int id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Task(String description, String status){
        this.description = description;
        this.status = status;
        this.id = ++count;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
