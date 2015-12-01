package jsonapi.controller;

import jsonapi.entity.Request;
import jsonapi.entity.User;
import jsonapi.model.ErrorResponse;
import jsonapi.model.Statistic;
import jsonapi.model.StatusResponse;
import jsonapi.model.UIDResponse;
import jsonapi.service.RequestService;
import jsonapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/jsonApi", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        return "main";
    }

    @RequestMapping(value = "/get_user", method = RequestMethod.GET)
    @ResponseBody
    public User getUserById(@RequestParam Integer id) {
        User user = userService.findById(id);
        return user;
    }

    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    @ResponseBody
    public Object changeStatus(@RequestParam Integer id, @RequestParam(required = false) boolean isOnline) throws InterruptedException {
        StatusResponse statusResponse = new StatusResponse();
        User user = userService.findById(id);
        Thread.currentThread().sleep(5000);
        if (user != null) {
            statusResponse.setBeforeIsOnline(user.isOnline());
            user.setOnline(isOnline);
            user.setLastChange(new Date());
            userService.save(user);
            statusResponse.setAfterIsOnline(isOnline);
            statusResponse.setId(id);
            return statusResponse;
        } else {
            ErrorResponse error = new ErrorResponse();
            error.setError("user not found");
            return error;
        }
    }

}