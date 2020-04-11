package api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Entity(name = "works")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank(message = "Work's name must not be blank!")
    private String name;

    @Column
    @NotBlank(message = "Work's description must not be blank!")
    private String description;

    @Column
    private String imagePath;

    @ManyToOne
    @JoinColumn (name = "author_id", nullable = false)
    @JsonIgnore
    private Author author;

    @ManyToMany
    @JoinTable(
            name = "file_work",
            joinColumns = @JoinColumn(name = "work_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id"))
    Set<File> files;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column
    @LastModifiedDate
    private Date updatedAt;

}
