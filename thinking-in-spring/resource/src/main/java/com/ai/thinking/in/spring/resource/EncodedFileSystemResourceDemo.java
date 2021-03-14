package com.ai.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.Reader;

/**
 * {@link org.springframework.core.io.FileSystemResource}
 */
public class EncodedFileSystemResourceDemo {
    public static void main(String[] args) throws  Exception{
        String currentJavaFilePath = System.getProperty("user.dir")+"\\thinking-in-spring\\resource\\src\\main\\java\\com\\ai\\thinking\\in\\spring\\resource\\EncodedFileSystemResourceDemo.java";
        File currentJavaFile = new File(currentJavaFilePath);
        FileSystemResource fileSystemResource = new FileSystemResource(currentJavaFilePath);
        EncodedResource encodedResource = new EncodedResource(fileSystemResource,"UTF-8");
        //字符输入流
        Reader reader = encodedResource.getReader();
        System.out.println(IOUtils.toString(reader));
    }
}
