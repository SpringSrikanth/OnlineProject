package com.koseksi.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.koseksi.app.modals.LocalFileDetails;
import com.koseksi.app.models.FileStorageService;
import com.koseksi.app.models.UploadFileResponce;
import com.koseksi.app.repository.LocalFileRepository;

@RestController
@RequestMapping("/files/local")
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;
    
    @Autowired
    private LocalFileRepository localFileRepository;
    
    @Autowired
    private Environment environment;
    
    @PostMapping("/uploadFile")
    public UploadFileResponce uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("userId") int userId) {
    	LocalFileDetails localFileDetails = new LocalFileDetails();
    	localFileDetails.setFileName(file.getOriginalFilename());
    	localFileDetails.setFileType(file.getContentType());
    	localFileDetails.setUserId(userId);
    	localFileDetails.setSize(file.getSize());
    	localFileRepository.save(localFileDetails);
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/local/downloadFile/")
                .path(fileName)
                .toUriString();
        return new UploadFileResponce(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    
    @PostMapping("/uploadFile/update")
    public ResponseEntity<?> updateUploadFile(@RequestParam("file") MultipartFile file,
    		@RequestParam("fileId") String fileId) {
		try {
			Optional<LocalFileDetails> fileData=localFileRepository.findById(fileId);
			LocalFileDetails localFileDetails=null;
			if (fileData.isPresent()) {
				localFileDetails=fileData.get();
				Files.deleteIfExists(Paths.get(environment.getProperty("fileLocal.upload-dir")+localFileDetails.getFileName()));
				localFileDetails.setFileName(file.getOriginalFilename());
				
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			String fileName = fileStorageService.storeFile(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/files/local/downloadFile/")
					.path(fileName)
					.toUriString();
			localFileRepository.save(localFileDetails);
			return new ResponseEntity<>(new UploadFileResponce(fileName, fileDownloadUri,
					file.getContentType(), file.getSize()),HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @PostMapping("/uploadFile/updateFileName")
    public ResponseEntity<?> updateUploadFile(@RequestParam("fileId") String fileId,@RequestParam("updatedFileName") String updatedFileName) {
		try {
			Optional<LocalFileDetails> fileData=localFileRepository.findById(fileId);
			LocalFileDetails localFileDetails=null;
			if (fileData.isPresent()) {
				localFileDetails=fileData.get();
				if(!updatedFileName.equals(localFileDetails.getFileName().split("\\.")[0])){
					Path source = Paths.get(environment.getProperty("fileLocal.upload-dir")+localFileDetails.getFileName());
					Files.move(source, source.resolveSibling(updatedFileName+"."+localFileDetails.getFileName().split("\\.")[1]));
					localFileDetails.setFileName(updatedFileName+"."+localFileDetails.getFileName().split("\\.")[1]);
					localFileRepository.save(localFileDetails);
				}
				else {
					return new ResponseEntity<>("fileName Already Exists",HttpStatus.BAD_GATEWAY);
				}
				
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/files/local/downloadFile/")
					.path(localFileDetails.getFileName())
					.toUriString();
			return new ResponseEntity<>(new UploadFileResponce(localFileDetails.getFileName(), fileDownloadUri,
					localFileDetails.getFileType(), localFileDetails.getSize()),HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
	@DeleteMapping("/deleteFile")
	public ResponseEntity<?> deleteFile(@RequestParam("fileId") String fileId) {
		try {
			Optional<LocalFileDetails> fileData=localFileRepository.findById(fileId);
			LocalFileDetails localFileDetails=null;
			if (fileData.isPresent()) {
				localFileDetails=fileData.get();
				Files.deleteIfExists(Paths.get(environment.getProperty("fileLocal.upload-dir")+localFileDetails.getFileName()));
				localFileRepository.delete(localFileDetails);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>("File deleted successfully with id::: "+fileId,HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllFiles")
    public List<LocalFileDetails> getAllFiles() {
        return localFileRepository.findAll();
    }
    

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponce> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,@RequestParam("userId") int userId) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file,userId))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    
    
}
