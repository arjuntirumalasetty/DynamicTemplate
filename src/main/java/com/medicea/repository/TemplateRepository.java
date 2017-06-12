package com.medicea.repository;

import com.medicea.businessObjects.ClientTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Arjun on 6/11/2017.
 */
public interface TemplateRepository extends MongoRepository<ClientTemplate,String> {

}
