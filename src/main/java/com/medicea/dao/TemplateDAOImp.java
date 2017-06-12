package com.medicea.dao;

import com.medicea.businessObjects.ClientTemplate;
import com.medicea.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Arjun on 6/11/2017.
 */
public class TemplateDAOImp implements TemplateDAO{

    TemplateRepository templateRepository;

    @Autowired
    TemplateDAOImp(TemplateRepository templateRepository){
        this.templateRepository = templateRepository;
    }
    @Override
    public ClientTemplate findById(String id) {
        return templateRepository.findOne(id);
    }
}
