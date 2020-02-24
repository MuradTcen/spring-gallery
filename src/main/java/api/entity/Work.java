package api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "works")
@Data
public class Work {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    @Null
    private String description;

    @Column
    private String imagePath;

    @ManyToOne
//    @JoinColumn(referencedColumnName = "id", name = "author_id")
    @JoinColumn (name = "author_id", nullable = false)
    @JsonIgnore
    private Author author;

//    @ManyToMany(mappedBy = "works")
//    private List<Category> categories;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate updatedAt;

}
