package api.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.List;

@Entity(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    @Null
    private int sort;

    @Column
    private boolean isActive;

    @Column
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "categories_works",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "work_id")
    )
    private List<Work> works;

    @Column
    private int createdAt;

    @Column
    private int updatedAt;
}
