package cn.com.sparknet.sjMessage.common.util;

import cn.com.sparknet.sjMessage.app.service.RUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Autowired
    private RUserService ruserService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String attachment_maxsize = ruserService.findProperty("attachment_maxsize");
        FileUploadInterceptor fileUploadInterceptor = new FileUploadInterceptor();
        fileUploadInterceptor.setMaxSize(Long.parseLong(attachment_maxsize));
        System.out.println(Long.parseLong(attachment_maxsize));
        registry.addInterceptor(fileUploadInterceptor).addPathPatterns("/attachmentUpload/fileUpload");
    }
}
