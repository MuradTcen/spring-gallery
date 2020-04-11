package api.service.impl;

import api.dto.WorkDTO;
import api.entity.Work;
import api.repository.AuthorRepository;
import api.repository.WorkRepository;
import api.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class WorkServiceImpl implements WorkService {
    private final WorkRepository workRepository;

    private final AuthorRepository authorRepository;

    @Override
    public Page<Work> getAll(Pageable pageable) {
        return workRepository.findAll(pageable);
    }

    @Override
    public Work saveFromDTO(WorkDTO work) {
        Work newWork = new Work();

        newWork.setAuthor(authorRepository
                .findById(work.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Author Not Found")));
        newWork.setName(work.getName());
        newWork.setDescription(work.getDescription());

        return workRepository.save(newWork);
    }
}
