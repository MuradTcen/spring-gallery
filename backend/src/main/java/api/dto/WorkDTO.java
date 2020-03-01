package api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkDTO {

    private String name;
    private String description;
    private long authorId;
}
