package com.koseksi.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.koseksi.app.modals.Blog;

public interface BlogsMongoRepository extends MongoRepository<Blog, Integer>{

}
