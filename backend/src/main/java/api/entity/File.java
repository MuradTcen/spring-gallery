package api.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Entity(name = "files")
@Data
@EntityListeners(AuditingEntityListener.class)
public class File extends BaseEntity {

    @Column
    private String path;

}
