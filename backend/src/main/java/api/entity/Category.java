package api.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotBlank;

@Entity(name = "categories")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Category extends BaseEntity {

    @Column
    @NotBlank(message = "Category name must not be blank!")
    private String name;

    @Column
    private int sort;

    @Column(name = "active")
    private boolean isActive;

}
