package com.exalt.sparepartsmanagement.controller;


import com.exalt.sparepartsmanagement.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileController {
    @Autowired
    FileService fileService;
    private Logger logger = LoggerFactory.getLogger(FileController.class);


    @GetMapping(path = "/api/v1/file/{fileName}")
    public String getFile(@PathVariable String fileName) {
        logger.info("file controller method -getFile");
        return fileService.get(fileName);
    }

    @PutMapping(path = "/api/v1/file/{fileName}")
    public String update(@RequestBody String data, @PathVariable String fileName) {
        logger.info("file controller method -update");
        return fileService.update(data, fileName);
    }


}
