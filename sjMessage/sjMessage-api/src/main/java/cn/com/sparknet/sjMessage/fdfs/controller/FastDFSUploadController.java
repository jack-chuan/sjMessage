package cn.com.sparknet.sjMessage.fdfs.controller;

import cn.com.sparknet.sjMessage.fdfs.fastdfs.FastDFSClient;
import cn.com.sparknet.sjMessage.fdfs.fastdfs.FastDFSFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: sjMessage-spsvn
 * @description: FastDFS Upload Controller
 * @author: Leo Lee
 * @create: 2019-04-11 20:25
 **/
@Controller(value = "/fdfs")
public class FastDFSUploadController {
    private static Logger logger = LoggerFactory.getLogger(FastDFSUploadController.class);

    @GetMapping("/")
    public String index(){
        return "fdfs/upload";
    }

    /**
    * @Description: singleFileUpload
    * @Param: [multipartFile, redirectAttributes]
    * @return: java.lang.String
    * @Author: Leo Lee
    * @Date: 19.4.11
    */
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile multipartFile,
                                   RedirectAttributes redirectAttributes){
        if (multipartFile.isEmpty()) {
            redirectAttributes.addFlashAttribute("message",  "Please select a file to upload");
            return "redirect: uploadStatus";
        }
        try {
            // Get the file and save it somewhere
            String path = saveFile(multipartFile);
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '"+ multipartFile.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("path", "File path url '" + path + "'");
        }catch (Exception e){
            logger.error("upload file failed",e);
        }
        return "redirect:/uploadStatus";
    }

    @GetMapping("uploadStatus")
    public String uploadStatus(){
        return "fdfs/uploadStatus";
    }
    
    /**
     * 从 MultipartFile 中读取文件信息，
     * 然后使用 FastDFSClient 将文件上传到 FastDFS 集群中，
     * 封装一个 saveFile() 方法用来调用上面封装的 FastDFS 工具类，
     * 将 MultipartFile 文件上传到 FastDFS 中，并返回上传后文件的地址信息。
    * @Description: saveFile 
    * @Param: [multipartFile] 
    * @return: java.lang.String 
    * @Author: Leo Lee
    * @Date: 19.4.11 
    */ 
    public String saveFile(MultipartFile multipartFile) throws IOException{
        String[] fileAbsolutePath={};
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        if (null != inputStream) {
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile fastDFSFile = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(fastDFSFile);
        }catch (Exception e){
            logger.error("upload file Exception!",e);
        }

        if (null == fileAbsolutePath) {
            logger.error("upload file failed,please upload again!");
        }
        String path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
        return path;
    }

}
