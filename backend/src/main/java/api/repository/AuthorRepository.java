package api.repository;

import api.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("select a from api.entity.Author a where a.name = :name")
    Author findByName(@Param("name") String name);
}
