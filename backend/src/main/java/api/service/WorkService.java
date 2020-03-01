package api.service;

import api.dto.WorkDTO;
import api.entity.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkService {
    Page<Work> getAll(Pageable pageable);

    Work saveFromDTO(WorkDTO work);
}
