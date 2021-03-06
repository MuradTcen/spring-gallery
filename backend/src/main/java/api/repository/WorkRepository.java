package api.repository;

import api.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
    @Query("select a from api.entity.Work a where a.name = :name")
    Work findByName(@Param("name") String name);
}
