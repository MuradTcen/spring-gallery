package api.controller;

import api.dto.WorkDTO;
import api.entity.Work;
import api.repository.WorkRepository;
import api.service.WorkService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Log
public class WorkController {
    private final WorkService workService;
    private final WorkRepository workRepository;
    private static final Logger logger = LoggerFactory.getLogger(WorkController.class);

    @GetMapping(value = "/work")
    public Page<Work> read(Pageable pageRequest) {
        return workService.getAll(pageRequest);
    }

    @GetMapping(value = "/work/{id}")
    public Work one(@PathVariable Long id, HttpServletResponse response) {
        return workRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Work Not Found"));
    }

    @PutMapping(value = "/work/{id}")
    Work update(@RequestBody Work newWork, @PathVariable Long id) {

        return workRepository.findById(id)
                .map(x -> {
                    if (newWork.getName() != null) {
                        x.setName(newWork.getName());
                    }
                    if (newWork.getDescription() != null) {
                        x.setDescription(newWork.getDescription());
                    }
                    return workRepository.save(x);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Work Not Found"));
    }

    @PostMapping("/work")
    Work newWork(@Valid @RequestBody WorkDTO newWork) {
        return workService.saveFromDTO(newWork);
    }

    @DeleteMapping("/api/work/{id}")
    void deleteWork(@PathVariable Long id) {
        workRepository.deleteById(id);
    }

}
