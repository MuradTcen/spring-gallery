package api.repository;

import api.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {
    @Query("select a from api.entity.Author a where a.name = :name")
    Author findByName(@Param("name") String name);

    @Query("delete from api.entity.Author a where a.id = :id")
    void delete(long id);

    Author getAuthorById(long id);

    boolean existsAuthorById(long id);

    Page<Author> findAll(Pageable pageable);
}
