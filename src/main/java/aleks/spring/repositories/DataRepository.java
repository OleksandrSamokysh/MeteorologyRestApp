package aleks.spring.repositories;

import aleks.spring.models.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer> {
    List<Data> findByRaining(Boolean raining);


}
