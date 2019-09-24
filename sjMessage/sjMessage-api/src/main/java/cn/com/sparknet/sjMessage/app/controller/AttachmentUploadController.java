package cn.com.sparknet.sjMessage.app.controller;

import cn.com.sparknet.sjMessage.app.entity.MsgFile;
import cn.com.sparknet.sjMessage.app.service.MsgFileService;
import cn.com.sparknet.sjMessage.common.util.JsonResult;
import cn.com.sparknet.sjMessage.fdfs.fastdfs.FastDFSClient;
import cn.com.sparknet.sjMessage.fdfs.fastdfs.FastDFSFile;
import com.github.pagehelper.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/attachmentUpload", produces = "text/plain; charset=UTF-8")
@Api(value = "附件上传操作接口", tags = "附件上传操作接口")
public class AttachmentUploadController {

    private static final Logger logger = LoggerFactory.getLogger(AttachmentUploadController.class);

    @Autowired
    private MsgFileService msgFileService;

    /*
     * 通用附件上传，并将上传的附件返回至前台
     */
    @Transactional
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ApiOperation(value = "通用附件上传，并将上传的附件返回至前台", notes = "通用附件上传")
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        MsgFile file = null;
        JsonResult jsonResult;
        String path = "";
        int count = 0;
        String fileName = "";
//        long fileSizeMax = 10 * 1024 * 1024;
        try{
            if (multipartFile.isEmpty()) {
                jsonResult = new JsonResult(null,"404","上传附件为空");
            }else {
//                if(multipartFile.getSize() > fileSizeMax){
//                    jsonResult = new JsonResult(null,"500","文件不能超过10MB");
//                }else {
                    path = saveFile(multipartFile);
                    fileName = multipartFile.getOriginalFilename();
                    //去掉ie中获取的文件名包含本地路径
                    if(fileName.contains("\\")){
                        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                    }
                    if(StringUtil.isEmpty(path)) {
                        jsonResult = new JsonResult(null,"400","上传文件服务器失败");
                    }else {
                        file = msgFileService.fileUpload(path,fileName);
                        jsonResult = new JsonResult(file,"200","成功");
                    }
//                }
            }
        }catch(Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            jsonResult = new JsonResult(null,"500", "失败");
            logger.error("upload file failed",e);
        }
        JSONObject jsonObject = JSONObject.fromObject(jsonResult);
        return jsonObject.toString();
    }

    /**
     * @Description: saveFile
     * @Param: [multipartFile]
     * @return: java.lang.String
     * @Author: Leo Lee
     * @Date: 19.4.11
     *  从 MultipartFile 中读取文件信息，
     * 然后使用 FastDFSClient 将文件上传到 FastDFS 集群中，
     * 封装一个 saveFile() 方法用来调用上面封装的 FastDFS 工具类，
     * 将 MultipartFile 文件上传到 FastDFS 中，并返回上传后文件的地址信息。
     */
    public String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath={};
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        String path = "";
        if (null != inputStream) {
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile fastDFSFile = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(fastDFSFile);
//            path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
            path = fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
        }catch (Exception e){
            logger.error("upload file Exception!",e);
        }

        if (null == fileAbsolutePath) {
            logger.error("upload file failed,please upload again!");
        }
        return path;
    }
}
