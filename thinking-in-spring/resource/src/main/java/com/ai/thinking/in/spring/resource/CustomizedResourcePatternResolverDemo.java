package com.ai.thinking.in.spring.resource;

import com.ai.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * {@link ResourcePatternResolver}
 */
public class CustomizedResourcePatternResolverDemo {
    public static void main(String[] args) throws IOException {
        String currentPackagePath = System.getProperty("user.dir")+"\\thinking-in-spring\\resource\\src\\main\\java\\com\\ai\\thinking\\in\\spring\\resource\\";
        String locationPattern = currentPackagePath+"*.java";
        System.out.println(locationPattern);

        PathMatchingResourcePatternResolver resourcePatternResolver
                = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());
        //resourcePatternResolver.setPathMatcher(new JavaFilePathMatcher());
        Resource[] resources = resourcePatternResolver.getResources(locationPattern);
        System.out.println(resources.length);
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
    }
    static class JavaFilePathMatcher implements PathMatcher{

        @Override
        public boolean isPattern(String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean match(String pattern, String path) {
            return pattern.endsWith(".java");
        }

        @Override
        public boolean matchStart(String pattern, String path) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String pattern, String path) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String path) {
            return null;
        }

        @Override
        public String combine(String pattern1, String pattern2) {
            return null;
        }
    }
}
