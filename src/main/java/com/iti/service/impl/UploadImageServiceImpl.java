package com.iti.service.impl;

import com.iti.model.cfg.HibernateUtils;
import com.iti.model.cfg.transaction.TransactionManager;
import com.iti.service.UploadImageService;
import org.apache.commons.fileupload.FileItem;

import java.io.File;

public class UploadImageServiceImpl implements UploadImageService {

    @Override
    public void uploadImage(FileItem item,String directory, String imageName) {
        //validate if directory exist or not
        File fileDirectory=new File(directory);
        if(!fileDirectory.exists()){
            fileDirectory.mkdir();
        }
        //save image to directory
        File file = new File(directory,imageName); // Define destination file.
        try {
            item.write(file); // Write to destination file.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
