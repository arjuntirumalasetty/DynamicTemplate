package com.medicea.controller;

import com.amazonaws.services.s3.model.*;
import com.medicea.util.AmazonS3Template;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Arjun on 6/11/2017.
 */
@RestController
@PropertySource("classpath:application.properties")
@RequestMapping("/template")
public class TemplateController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TemplateController.class);
    private AmazonS3Template amazonS3Template;
    private String bucketName;
    @Autowired
    public TemplateController(AmazonS3Template amazonS3Template, @Value("${amazon.s3.default-bucket}") String bucketName) {
        this.amazonS3Template = amazonS3Template;
        this.bucketName = bucketName;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<S3ObjectSummary>> getBucketResources() {

        ObjectListing objectListing = amazonS3Template.getAmazonS3Client()
                .listObjects(new ListObjectsRequest()
                        .withBucketName(bucketName));

        return objectListing.getObjectSummaries()
                .stream()
                .map(a -> new Resource<>(a,
                        new Link(String.format("https://s3.amazonaws.com/%s/%s",
                                a.getBucketName(), a.getKey())).withRel("url")))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/downloadTemplate", method = RequestMethod.GET)
    public void readTemplate() throws IOException {

        ObjectListing objectListing1 = amazonS3Template.getAmazonS3Client()
                .listObjects(new ListObjectsRequest()
                        .withBucketName("dynamic-template"));
        List<S3ObjectSummary> s3ObjectSummaryList = objectListing1.getObjectSummaries();
        for(S3ObjectSummary s3ObjectSummary :s3ObjectSummaryList){
            logger.info(s3ObjectSummary.getBucketName());
            logger.info(s3ObjectSummary.getKey());
        }

       S3Object objectListing =  amazonS3Template.getAmazonS3Client().getObject(new GetObjectRequest("dynamic-template","clienttest/style.css"));

       String bucketName2 = objectListing.getBucketName();
        logger.info("bucket Name "+bucketName2);
       String keyName = objectListing.getKey();
        logger.info("keyName "+keyName);
        InputStream reader = new BufferedInputStream(objectListing.getObjectContent());
        File file = new File("C:\\Studies\\style.css");
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        int read = -1;
        while((read = reader.read()) != -1){
            outputStream.write(read);
        }
        outputStream.flush();
        outputStream.close();
        reader.close();
    }
}
