package lv.helloit.bootcamp.sweeter.sweet;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SweetDao extends CrudRepository<Sweet, String> {
    List<Sweet> findAllByUserId(String userId);
}
