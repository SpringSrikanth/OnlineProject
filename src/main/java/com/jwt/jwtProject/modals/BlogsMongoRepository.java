package com.jwt.jwtProject.modals;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogsMongoRepository extends MongoRepository<BlogDetails, Integer>{

}
