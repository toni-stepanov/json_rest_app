package jsonapi.repository;

import jsonapi.entity.Request;
import jsonapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer>{

    Request findById(Integer id);

}
