package pmazzoncini.revise.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordCoupleRepository extends CrudRepository<WordCoupleModel, Long> {

}
