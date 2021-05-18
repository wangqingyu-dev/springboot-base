package com.rt.base.framework.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author dlm
 */
@Configuration
@PropertySource({
        "classpath:config-${spring.profiles.active}.properties"}
        )
@Data
public class ConfigProperties {

    private String baseUrlPath;

    private FileConfig fileConfig = new FileConfig();

    private ImGroup imGroup = new ImGroup();

    @Data
    public class FileConfig{

        private String basePath;

        private String imageReadPath;

        private String imageUnReadPath;

        private String voiceReadPath;

        private String voiceUnReadPath;

    }

    @Data
    public class ImGroup{
        private String createUrl;

        private String delGroupMember;

        private String removeGroup;
    }
}
