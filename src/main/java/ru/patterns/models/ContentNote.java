package ru.patterns.models;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "content_notes")
public class ContentNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Type cannot be blank")
    private String type; // Тип заметки (text, image, checklist)

    private String content;

    private String imagePath;

    @ElementCollection
    @CollectionTable(name = "note_tasks", joinColumns = @JoinColumn(name = "note_id"))
    @Column(name = "task")
    private List<String> tasks;

    public ContentNote() {
    }

    public ContentNote(String type, String content, String imagePath, List<String> tasks) {
        this.type = type;
        this.content = content;
        this.imagePath = imagePath;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }
}
