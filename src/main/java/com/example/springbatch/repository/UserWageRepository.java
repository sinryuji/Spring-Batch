package com.example.springbatch.repository;

import com.example.springbatch.entity.UserWage;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserWageRepository extends MongoRepository<UserWage, ObjectId> {

}
