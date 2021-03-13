package com.ai.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;

/**
 * {@link org.springframework.core.io.FileSystemResourceLoader}
 */
public class EncodedFileSystemResourceLoaderDemo {
    public static void main(String[] args) throws IOException {
        String currentJavaFilePath = "D:\\coder\\workSpaceForSpring\\deep-in-springframework\\thinking-in-spring\\resource\\src\\main\\java\\com\\ai\\thinking\\in\\spring\\resource\\EncodedFileSystemResourceLoaderDemo.java";
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource resource = resourceLoader.getResource(currentJavaFilePath);
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        System.out.println(IOUtils.toString(encodedResource.getReader()));
    }
}
