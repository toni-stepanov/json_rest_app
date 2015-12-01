package jsonapi.service;

import jsonapi.entity.Request;
import jsonapi.entity.User;
import jsonapi.repository.RequestRepository;
import jsonapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    public Request findById(Integer id){
        return requestRepository.findById(id);
    }

    public Request save(Request request){
        return requestRepository.save(request);
    }


}
