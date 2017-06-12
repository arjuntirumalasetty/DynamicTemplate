package com.medicea.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Arjun on 6/11/2017.
 */
@RestController
@RequestMapping("/template")
public class TemplateController {

    @RequestMapping(value = "/downloadTemplate", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    void readTemplate() {

    }
}
