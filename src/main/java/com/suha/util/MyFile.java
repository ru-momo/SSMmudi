package com.suha.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class MyFile {

    private static String filePath = "F:\\IDEA_MyBatis_Project\\MySSM\\src\\main\\webapp\\static\\upload\\";


    public static String newFile(String origname, MultipartFile file) throws IOException {
        String extendsName = origname.substring(origname.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString()+extendsName;
        File newFile = new File(filePath + newFilename);

        file.transferTo(newFile);
        return newFilename;
    }

    public static boolean delFile(String filename){
        File file = new File(filePath + filename);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    

    public static void main(String args[]){
        String name="NASDAQ欢迎你";
        String newName=name.substring(4,7);
        System.out.println(newName);
    }

}
