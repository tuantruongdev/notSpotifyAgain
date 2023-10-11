package com.people.connector.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Utils {
    public static boolean isValidURL(String url)  {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException e) {
            return false;
        } catch (URISyntaxException e) {
            return false;
        }
    }
    public static String getFileExtension(MultipartFile m){
        String extension = StringUtils.getFilenameExtension(m.getOriginalFilename());
        return extension;
    }
    public static boolean isMusicFile(MultipartFile m){
        String[] musicExtensions = new String[]{"mp3","m4a","mp4","flac","Wav"};
        String extension = getFileExtension(m);
        for (int i = 0; i < musicExtensions.length; i++) {
            if (musicExtensions[i].equalsIgnoreCase(extension)){
                return true;
            }
        }
        return false;
    }
    public static String generateTimeStampName(MultipartFile m){
        return System.currentTimeMillis()+"_"+m.getOriginalFilename();
    }
}
