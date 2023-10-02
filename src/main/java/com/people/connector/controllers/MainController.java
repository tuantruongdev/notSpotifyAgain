package com.people.connector.controllers;

import com.people.connector.NetResponse;
import com.people.connector.models.User;
import com.people.connector.repositories.song.SongRepository;
import com.people.connector.repositories.user.UserRepository;
import com.people.connector.storageServices.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SongRepository songRepository;
//    private StorageService storageService;
//    @Autowired
//    public void FileUploadController(StorageService storageService) {
//        this.storageService = storageService;
//    }

    @GetMapping("/stream-mp3")
    public ResponseEntity<InputStreamResource> streamMp3() throws IOException {
        // Load the MP3 file from the classpath. You can replace "your-music.mp3" with the actual file path.
        ClassPathResource mp3File = new ClassPathResource("music/nguoi_em_co_do.mp3");

        // Set up HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
        headers.setContentLength(mp3File.contentLength());
        headers.setContentDispositionFormData("attachment", "nguoi_em_co_do.mp3");

        // Create InputStreamResource from the MP3 file
        InputStreamResource mp3InputStreamResource = new InputStreamResource(mp3File.getInputStream());

        // Return the response entity
        return new ResponseEntity<>(mp3InputStreamResource, headers, org.springframework.http.HttpStatus.OK);
    }

    @PostMapping(value = "song/upload")
    @ResponseBody
    NetResponse uploadSong(@RequestParam("file")MultipartFile file){
        if (file == null|| file.isEmpty() ){
            return new NetResponse("error",1,null);
        }
      //  storageService.store(file);
       // songRepository.save(song);
        return new NetResponse("ok",0,null);
    }



    @PostMapping("user")
    String user(@RequestParam(value = "userName",defaultValue = "usrName")String name, @RequestParam(value = "realName",defaultValue = "realName")String realName){
        User user = new User(name,realName,new Date().getTime());
        userRepository.save(user);
        return "ok";
    }
    @GetMapping("user/find")
    @ResponseBody
    List<User> findUser(@RequestParam(value = "name",defaultValue = "realName")String name){
        List<User> users =  userRepository.findUsersWithName(name);
        return users;
    }
    @PostMapping(value = "user/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    NetResponse user2(@RequestBody User user){
        //User user = new User(name,realName,new Date().getTime());
        if (user == null || user.getUserName() == null || user.getRealName() == null){
           return new NetResponse("error",1,null);
        }
        userRepository.save(user);
        return new NetResponse("ok",0,user);
    }
}
