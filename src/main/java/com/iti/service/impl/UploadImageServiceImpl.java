package com.iti.service.impl;

import com.iti.service.UploadImageService;
import org.apache.commons.fileupload.FileItem;

import java.io.File;

public class UploadImageServiceImpl implements UploadImageService {

    @Override
    public void uploadImage(FileItem item,String directory, String imageName) {
        File file = new File(directory,imageName); // Define destination file.
        try {
            item.write(file); // Write to destination file.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
