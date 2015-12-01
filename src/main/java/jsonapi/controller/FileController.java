package jsonapi.controller;

import jsonapi.entity.User;
import jsonapi.model.UIDResponse;
import jsonapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

@Controller
public class FileController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/file_upload", method = RequestMethod.POST)
    @ResponseBody
    public UIDResponse uploadFile(Model model, @RequestParam MultipartFile file) {
        UIDResponse UIDResponse = new UIDResponse();
        if (!file.isEmpty()) {
            String path = System.getProperty("catalina.home");
            File dir = new File(path + File.separator + "json_api");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
            try {
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                UIDResponse.setError("Cannot handle file " + file.getOriginalFilename());
            }
            UIDResponse.setUid(serverFile.getAbsolutePath());
        } else {
            UIDResponse.setError("File not found");
        }
        return UIDResponse;
    }

    @RequestMapping(value = "/saving", method = RequestMethod.POST)
    @ResponseBody
    public UIDResponse saveUser(Model model, @RequestParam String name, @RequestParam String email, @RequestParam String uid) {
        UIDResponse uidResponse = new UIDResponse();
        File avatarFile = new File(uid);
        if (!avatarFile.exists()) {
            uidResponse.setError("photo didn't found");
            return uidResponse;
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setUriAvatar(uid);
        user.setLastChange(new Date());
        user = userService.save(user);
        uidResponse.setUid(String.valueOf(user.getId()));
        return uidResponse;
    }

}
