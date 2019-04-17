package com.iti.service;

import org.apache.commons.fileupload.FileItem;

import java.io.File;

public interface UploadImageService {
    void uploadImage(FileItem item,String directory, String imageName);
}
