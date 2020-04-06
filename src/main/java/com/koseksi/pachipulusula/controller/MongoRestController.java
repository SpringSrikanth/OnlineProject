package com.koseksi.pachipulusula.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.jwtProject.modals.BlogDetails;
import com.jwt.jwtProject.modals.BlogsMongoRepository;
import com.koseksi.pachipulusula.constants.CommonConstants;
import com.koseksi.pachipulusula.util.UtilService;

@RestController
public class MongoRestController {

	@Autowired
	private BlogsMongoRepository blogsMongoRepository;

	@Autowired
	private UtilService utilService;

	@PostMapping(path = "/blogs/save")
	public ResponseEntity<BlogDetails> saveBlogDetails(@RequestBody BlogDetails bDetails) {
		try {
			System.out.println(utilService.getNextSequenceId(CommonConstants.BLOG_SEQUENCE_NAME));
			bDetails.setBlogId(Integer.parseInt(utilService.getNextSequenceId(CommonConstants.BLOG_SEQUENCE_NAME)));
			bDetails.setCreatedDate(new Date());
			bDetails.setUpdatedDate(new Date());
			BlogDetails blogDetails=blogsMongoRepository.save(bDetails);   
			return new ResponseEntity<>(blogDetails, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}	
	}

	@PostMapping(path = "/blogs/update/{id}")
	public ResponseEntity<BlogDetails> updateBlogDetails(@PathVariable(name = "id") int blogId,@RequestBody BlogDetails bDetails) {
		try {

			bDetails.setUpdatedDate(new Date());
			Optional<BlogDetails> blogDetailsData = blogsMongoRepository.findById(blogId);
			if (blogDetailsData.isPresent()) {
				BlogDetails blogDetails = blogDetailsData.get();
				blogDetails.setBlogTitle(bDetails.getBlogTitle());
				blogDetails.setBlogDescription(bDetails.getBlogDescription());
				blogDetails.setBlogContent(bDetails.getBlogContent());
				blogDetails.setMainImage(bDetails.getMainImage());
				blogDetails.setBlogImageUrls(bDetails.getBlogImageUrls());
				blogDetails.setPublished(bDetails.isPublished());
				blogDetails.setEmailNotified(bDetails.isEmailNotified());
				blogDetails.setUpdatedDate(new Date());
				return new ResponseEntity<>(blogsMongoRepository.save(blogDetails), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(path = "/blogs/blog/{id}")
	public ResponseEntity<BlogDetails> getBlogDetails(@PathVariable(name = "id") int blogid){
		try {
			Optional<BlogDetails> blogDetailsData=blogsMongoRepository.findById(blogid);
			if(blogDetailsData.isPresent())
				return new ResponseEntity<>(blogDetailsData.get(),HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(path ="blogs/all")
	public ResponseEntity<List<BlogDetails>> getAllBlogs(){
		try {
			List<BlogDetails> allBlogDetails=blogsMongoRepository.findAll();
			return new ResponseEntity<>(allBlogDetails,HttpStatus.FOUND); 
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND); 
		}
	}
	
	
	@DeleteMapping(path = "/blogs/delete/{id}")
	public ResponseEntity<HttpStatus> deleteBlogById(@PathVariable(name = "id") int blogId){
		try {
			blogsMongoRepository.deleteById(blogId);
			return new ResponseEntity<>(HttpStatus.FOUND);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
}
