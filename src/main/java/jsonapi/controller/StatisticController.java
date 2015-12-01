package jsonapi.controller;

import jsonapi.entity.Request;
import jsonapi.entity.User;
import jsonapi.model.ErrorResponse;
import jsonapi.model.Statistic;
import jsonapi.service.RequestService;
import jsonapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class StatisticController {

    Logger logger = LoggerFactory.getLogger(StatisticController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @RequestMapping(value = "/staistic", method = RequestMethod.GET)
    @ResponseBody
    public Object statistic(@RequestParam(required = false) Integer requestId, @RequestParam(required = false) boolean isOnline) throws InterruptedException {
        logger.info("id =" +requestId);
        logger.info("isOnline =" +isOnline);
        Statistic statistic;
        Request request = new Request();
        request.setDate(new Date());
        request = requestService.save(request);
        if(requestId == null){
            List<User> users = userService.findByStatus(isOnline);
            statistic= new Statistic();
            statistic.setRequestId(request.getId());
            statistic.setUserList(users);
            return statistic;
        }
        Request lastRequest = requestService.findById(requestId);
        if(lastRequest == null){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setError("Cannot find id request");
            return errorResponse;
        }
        List<User> users = userService.findUserByStatusAndDate(isOnline, lastRequest.getDate());
        statistic = new Statistic();
        statistic.setRequestId(lastRequest.getId());
        statistic.setUserList(users);
        return statistic;
    }

}
