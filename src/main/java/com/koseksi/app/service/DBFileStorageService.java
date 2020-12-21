package com.koseksi.app.service;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.koseksi.app.modals.FileDetails;
import com.koseksi.app.repository.FileRepository;

@Service
public class DBFileStorageService {

    @Autowired
    private FileRepository dbFileRepository;

    public FileDetails storeFile(MultipartFile file,int userId) throws Exception {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
            }

            FileDetails dbFile = new FileDetails(fileName, file.getContentType(), file.getBytes(),userId);

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public FileDetails getFile(String fileId) throws Exception {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new Exception("File not found with id " + fileId));
    }
}