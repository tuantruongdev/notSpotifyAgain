package com.people.connector.controllers;

import com.google.gson.Gson;
import com.people.connector.NetResponse;
import com.people.connector.models.FileStorage;
import com.people.connector.models.Song;
import com.people.connector.models.User;
import com.people.connector.repositories.file.FileRepository;
import com.people.connector.repositories.song.SongRepository;
import com.people.connector.repositories.user.UserRepository;
import com.people.connector.storageServices.StorageService;
import com.people.connector.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/")
public class MainController {
    Gson gson = new Gson();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private FileRepository fileRepository;
    private StorageService storageService;

    @Autowired
    public void FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/stream-mp3")
    public ResponseEntity<InputStreamResource> streamMp3() throws IOException {
        // Load the MP3 file from the classpath. You can replace "your-music.mp3" with the actual file path.
        String path = new File(".").getCanonicalPath() + "/music/nguoi_em_co_do_2.mp3";
        InputStream mp3File = new FileInputStream(path);
        // Set up HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
        headers.setContentLength(Files.size(Paths.get(path)));
        headers.setContentDispositionFormData("attachment", "nguoi_em_co_do_2.mp3");

        // Create InputStreamResource from the MP3 file
        InputStreamResource mp3InputStreamResource = new InputStreamResource(mp3File);

        // Return the response entity
        return new ResponseEntity<>(mp3InputStreamResource, headers, org.springframework.http.HttpStatus.OK);
    }

    @PostMapping(value = "song/file")
    @ResponseBody
    NetResponse uploadSong(@RequestParam(value = "file",required = false) MultipartFile file, @RequestParam(value = "indirectLink",required = false) String additionalInfo) {
        if (file != null && !file.isEmpty()) {
            if (!Utils.isMusicFile(file)){
                return new NetResponse("file is not music file", 1, 70, null);
            }
            String storedFileName = Utils.generateTimeStampName(file);
            storageService.store(file,storedFileName);
            FileStorage fileStorage = new FileStorage();
            fileStorage.setFileName(storedFileName);
            fileStorage.setUploader(1);
            fileRepository.save(fileStorage);
            return new NetResponse("ok", 0,fileStorage.getId());
        } else if (additionalInfo.length() > 0 && Utils.isValidURL(additionalInfo)) {

        } else {
            return new NetResponse("upload not valid, please provide valid file", 1, 69, null);
        }
        return null;
    }

    @PostMapping(value = "song")
    @ResponseBody
    NetResponse postSong(@RequestBody Song songInfo){
        FileStorage musicFile = fileRepository.getFileById(songInfo.getFileId());
        Song song = new Song();
        song.setName(songInfo.getName());
        song.setFile(musicFile);
        song.setAuthorId(1);
        songRepository.save(song);
        return new NetResponse("ok", 0, song);
    }

    @GetMapping(value = "song/{songId}")
    @ResponseBody
    NetResponse getSong(@PathVariable int songId){
        Optional<Song> querySong = songRepository.findById(songId);
        Song requestSong = querySong.get();
        return new NetResponse("ok", 0, requestSong);
    }
    @GetMapping(value = "song/{songId}/stream")
    @ResponseBody
    ResponseEntity<InputStreamResource> streamSong(@PathVariable int songId) throws IOException {
        Optional<Song> querySong = songRepository.findById(songId);
        Song requestSong = querySong.get();
        System.out.println("stream hit");
        return  streamMusic(requestSong.getFile().getFileName());
    }




    @PostMapping("user")
    String user(@RequestParam(value = "userName", defaultValue = "usrName") String name, @RequestParam(value = "realName", defaultValue = "realName") String realName) {
        User user = new User(name, realName, new Date().getTime());
        userRepository.save(user);
        return "ok";
    }

    @GetMapping("user/find")
    @ResponseBody
    List<User> findUser(@RequestParam(value = "name", defaultValue = "realName") String name) {
        List<User> users = userRepository.findUsersWithName(name);
        return users;
    }

    @PostMapping(value = "user/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    NetResponse user2(@RequestBody User user) {
        //User user = new User(name,realName,new Date().getTime());
        if (user == null || user.getUserName() == null || user.getRealName() == null) {
            return new NetResponse("error", 1, null);
        }
        userRepository.save(user);
        return new NetResponse("ok", 0, user);
    }

    public ResponseEntity<InputStreamResource> streamMusic(String storageName) throws IOException {
        // Load the MP3 file from the classpath. You can replace "your-music.mp3" with the actual file path.
        String path = new File(".").getCanonicalPath() + "/music/"+storageName;
        InputStream mp3File = new FileInputStream(path);
        // Set up HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
        headers.setContentLength(Files.size(Paths.get(path)));
        headers.setContentDispositionFormData("attachment", storageName);
        // Create InputStreamResource from the MP3 file
        InputStreamResource mp3InputStreamResource = new InputStreamResource(mp3File);
        // Return the response entity
        return new ResponseEntity<>(mp3InputStreamResource, headers, org.springframework.http.HttpStatus.OK);
    }

}
