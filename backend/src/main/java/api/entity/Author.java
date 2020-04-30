package api.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity(name = "authors")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Author extends BaseEntity{

    @Column
    @NotBlank(message = "Author article must not be blank!")
    private String article;

    @Column
    @NotBlank(message = "Author name must not be blank!")
    private String name;

    @Column
    private String photoPath;

    @ManyToMany
    @JoinTable(
            name = "file_author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id"))
    Set<File> files;

}
