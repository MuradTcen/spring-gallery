package api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

    private String name;
    private int sort;
    private boolean isActive;

}
