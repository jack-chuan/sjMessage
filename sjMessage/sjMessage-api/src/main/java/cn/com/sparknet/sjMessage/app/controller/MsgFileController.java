package cn.com.sparknet.sjMessage.app.controller;

import cn.com.sparknet.sjMessage.app.entity.MsgFile;
import cn.com.sparknet.sjMessage.app.service.MsgFileService;
import cn.com.sparknet.sjMessage.common.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping(value = "/msgFile", produces = "application/json; charset=UTF-8")
@Api(value = "mybatis 实体类映射及相关信息查询", tags = "附件操作接口")
public class MsgFileController {

	private static final Logger logger = LoggerFactory.getLogger(MsgFileController.class);
	
	@Autowired
    private MsgFileService msgFileService;
	
	/*
	 * 转发消息附件操作：将转发消息附件重新保存并传至前台
	 */
    @Transactional
	@RequestMapping(value = "/forwardFileOpt/{msgId}", method = RequestMethod.POST)
    @ApiOperation(value = "转发消息附件操作：将转发消息附件重新保存并传至前台", notes = "转发消息带出新附件列表")
	public JsonResult forwardFileOpt(@PathVariable("msgId") @ApiParam("转发消息时原消息主键msgId") String msgId) {
    	List<MsgFile> fileList = new ArrayList<MsgFile>();
    	JsonResult jsonResult;
		int count = 0;
		try{
			fileList = msgFileService.forwardFileOpt(msgId);
			jsonResult = new JsonResult(fileList,"200","成功");
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			jsonResult = new JsonResult(fileList,"500", "失败");
		}
		return jsonResult;
	}

    /*
	 * 通用附件删除（物理删除）
	 */
    @Transactional
	@RequestMapping(value = "/fileDetele/{fileId}", method = RequestMethod.POST)
    @ApiOperation(value = "通用附件删除（物理删除）", notes = "通用附件删除（物理删除）")
	public JsonResult fileDetele(@PathVariable("fileId") @ApiParam("附件表主键fileId") String fileId) {
    	JsonResult jsonResult;
		int count = 0;
		try{
			count = msgFileService.fileDetele(fileId);
			if(count > 0) {
				jsonResult = new JsonResult("200","成功");
			}else {
				jsonResult = new JsonResult("500", "失败");
			}
			
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			jsonResult = new JsonResult("500", "失败");
		}
		return jsonResult;
	}
    
    /*
	 * 通用附件删除（逻辑删除）
	 */
    @Transactional
	@RequestMapping(value = "/updateFileStateByFileId/{fileId}", method = RequestMethod.POST)
    @ApiOperation(value = "通用附件删除（逻辑删除）", notes = "通用附件删除（逻辑删除）")
	public JsonResult updateFileStateByFileId(@PathVariable("fileId") @ApiParam("附件表主键fileId") String fileId) {
    	JsonResult jsonResult;
		int count = 0;
		try{
			count = msgFileService.updateFileStateByFileId(fileId);
			if(count > 0) {
				jsonResult = new JsonResult("200","成功");
			}else {
				jsonResult = new JsonResult("500", "失败");
			}
			
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			jsonResult = new JsonResult("500", "失败");
		}
		return jsonResult;
	}

    /*
     * 通用附件下载
     */
    /*@RequestMapping(value = "/fileDown", method = RequestMethod.GET)
    @ApiOperation(value = "通用附件下载", notes = "通用附件下载")
    public byte[] fileDown(*//*@RequestParam("groupName") @ApiParam("附件分组名") String groupName,*//*
                             *//*@RequestParam("remoteFileName") @ApiParam("附件远程路径") String remoteFileName,*//*
                             *//*@RequestParam("fileName") @ApiParam("附件名称") String fileName,*//*
            @RequestParam("fileId") @ApiParam("附件ID") String fileId,
                             HttpServletRequest request, HttpServletResponse response) {
        byte[] result = null;
        InputStream ins = null;
        OutputStream output = null;
        try {
            request.setCharacterEncoding("UTF-8");
            response.reset();
            response.addHeader("content-type", "application/x-msdownload;");
            MsgFile msgFile = msgFileService.selectByPrimaryKey(fileId);
            String fileName = msgFile.getFileTitle();
            String fileAddress = msgFile.getFileFdfsAddress();
            String groupName = fileAddress.substring(0,fileAddress.indexOf("/") - 1);
            String remoteFileName = fileAddress.substring(fileAddress.indexOf("/") + 1);
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859_1");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            output = response.getOutputStream();
            ins = FastDFSClient.downFile(groupName, remoteFileName);
            result = changeToByte(ins, output);
            ins.close();
        } catch (Exception e) {
            logger.error("upload file failed", e);
        }
        return result;
    }*/

    /*public byte[] changeToByte(InputStream ins, OutputStream out) {
        try {
            byte[] by = new byte[4096];
            out = new ByteArrayOutputStream();
            int len = 0;
            while ((len = ins.read(by)) != -1) {
                out.write(by, 0, len);
            }
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return ((ByteArrayOutputStream) out).toByteArray();
    }*/

    @RequestMapping(value = "/downloadIpAddress", method = RequestMethod.POST)
    @ApiOperation(value = "获取附件下载服务器地址", notes = "获取附件下载服务器地址")
    public JsonResult downloadIpAddress(HttpServletRequest request, HttpServletResponse response){
        String downloadServerIp = "";
        JsonResult jsonResult;

        InputStream in = this.getClass().getResourceAsStream("/fdfs/fdfs.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
            if(properties.getProperty("download_server") != null){
                downloadServerIp = properties.getProperty("download_server");
                jsonResult = new JsonResult(downloadServerIp,"200","成功");
            } else {
                logger.error("未配置下载服务地址");
                jsonResult = new JsonResult(downloadServerIp,"500","失败");
            }
        } catch (IOException e) {
            jsonResult = new JsonResult("500","失败");
            e.printStackTrace();
        }
        return jsonResult;
    }
}
