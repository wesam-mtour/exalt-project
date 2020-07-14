package com.exalt.sparepartsmanagement.service;

import com.exalt.sparepartsmanagement.error.FileNotFoundExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);


    @Override
    public String update(String data, String fileName) {
        String filePath = Paths.get("").toAbsolutePath().toString() + "\\" + fileName;
        logger.info(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundExceptions("File Not found");
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath, true);
            byte[] strToBytes = data.getBytes();
            outputStream.write(strToBytes);
            outputStream.close();
            return "writing done successfully ";
        } catch (Exception e) {
            throw new FileNotFoundExceptions(e.getMessage());
        }
    }

    @Override
    public String get(String fileName) {
        String filePath = Paths.get("").toAbsolutePath().toString() + "\\" + fileName;
        logger.info(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundExceptions("File Not found");
        }
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fileInputStream.read(data);
            fileInputStream.close();

            try {
                String str = new String(data, "UTF-8");
                return str;
            } catch (UnsupportedEncodingException e) {
                throw new FileNotFoundExceptions("Unsupported Encoding");
            }

        } catch (IOException e) {
            throw new FileNotFoundExceptions(e.getMessage());

        }
    }

}
