package com.medicea.dao;

import com.medicea.businessObjects.ClientTemplate;

/**
 * Created by Arjun on 6/11/2017.
 */
public interface TemplateDAO {
    ClientTemplate findById(String id);
}
