package com.ai.thinking.in.spring;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * 动态更新资源
 * 定位资源位置（Properties文件）
 * 初始化Properties对象
 * 实现AbstractMessageSource#resolveCode方法
 * 监听资源文件（JavaNIO2 WatchService)
 * 使用线程池处理文件变化
 */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {
    private static final String resourceFileName = "msg.properties";
    private static final String resourcePath = "/META-INF/"+resourceFileName;
    private final Properties messageProperties;
    private static final String ENCODING = "UTF-8";
    private final Resource messagePropertiesResource;

    private ResourceLoader resourceLoader;

    private final ExecutorService executorService;
    public DynamicResourceMessageSource(){
        this.messagePropertiesResource = getMessageProperteisResource();
        this.messageProperties = loadMessageProperties();
        this.executorService = Executors.newSingleThreadExecutor();
        //监听资源文件(JavaNIO2 WatchService)
        onMessagePropertiesChanged();
    }

    private void onMessagePropertiesChanged() {
        if(this.messagePropertiesResource.isFile()){ //判断是否是个文件
            try {
                File messagePropertiesResourceFile = this.messagePropertiesResource.getFile();
                Path messagePropertieFilePath = messagePropertiesResourceFile.toPath();
                //获取当前OS文件系统
                FileSystem fileSystem = FileSystems.getDefault();
                //注册WatchService
                WatchService watchService = fileSystem.newWatchService();
                //获取资源文件所在的目录
                Path dirPath = messagePropertieFilePath.getParent();
                //注册WatchService到MessageProperteisFilePath,并关心修改时间
                dirPath.register(watchService,ENTRY_MODIFY,ENTRY_DELETE);
                processMessagePropertiesChanged(watchService);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 处理资源文件变化
     * @param watchService
     */
    private void processMessagePropertiesChanged(WatchService watchService){
        executorService.submit(() ->{
           while (true){
               WatchKey watchKey = watchService.take();
               try {
                   if(watchKey.isValid()){
                       for(WatchEvent event:watchKey.pollEvents()){
                           Watchable watchable = watchKey.watchable();
                           //目录路径（监听的注册目录）
                           Path dirPath = (Path)watchable;
                           //事件所关关联的对象即注册目录的子文件（或子文件夹）
                           //时间发生源是相对路径
                           Path fileRelativePath = (Path) event.context();
                           if(resourceFileName.equals(fileRelativePath.getFileName().toString())){
                               //处理为绝对路径
                               Path filePath = dirPath.resolve(fileRelativePath);
                               File file = filePath.toFile();
                               Properties properties = loadMessageProperties(new FileReader(file));
                               synchronized (messageProperties){
                                   messageProperties.clear();
                                   messageProperties.putAll(properties);
                               }
                               System.out.println("修改的文件："+filePath);
                           }

                       }
                   }
               }finally {
                   if(watchKey!=null){
                       watchKey.reset();
                   }

               }

           }
        });
    }

    private Properties loadMessageProperties() {
        EncodedResource encodedResource = new EncodedResource(this.messagePropertiesResource,ENCODING);
        try {
            return loadMessageProperties(encodedResource.getReader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Properties loadMessageProperties(Reader reader) {
        Properties properties = new Properties();
        try{
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    private Resource getMessageProperteisResource(){
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(resourcePath);
        return resource;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String messageFormatPattern  = messageProperties.getProperty(code);
        if(StringUtils.hasText(messageFormatPattern)){
            return new MessageFormat(messageFormatPattern,locale);
        }
        return null;
    }
    public ResourceLoader getResourceLoader(){
        return this.resourceLoader !=null?this.resourceLoader:new DefaultResourceLoader();
    }
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public static void main(String[] args) throws InterruptedException {
        DynamicResourceMessageSource messageSource = new DynamicResourceMessageSource();
        for(int i=0;i<10000;i++){
            String message = messageSource.getMessage("name", new Object[]{}, Locale.getDefault());
            System.out.println(message);
            Thread.sleep(1000);
        }

    }
}
