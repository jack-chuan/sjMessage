package cn.com.sparknet.sjMessage.fdfs.fastdfs;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: sjMessage-spsvn
 * @description: FastDFS Client
 * @author: Leo Lee
 * @create: 2019-04-11 20:25
 **/
public class FastDFSClient {
    private static Logger logger = LoggerFactory.getLogger(FastDFSClient.class);

    /**
     * 在类加载的时候读取配置信息，并进行初始化。
    * @Description: static initializer
    * @Author: Leo Lee
    * @Date: 19.4.11
    */
    static {
        try {
            String filePath = new ClassPathResource("fdfs/fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
        } catch (IOException e) {
            logger.error("FastDFS Client Init Failed! (IO Exception)", e);
        } catch (MyException e) {
            logger.error("FastDFS Client Init Failed! (Non IO Exception(My Exception))", e);
        }
    }

    /**
     * 使用 TrackerServer 构建出每次操作的客户端实例 StorageClient。
    * @Description: getTrackerClient
    * @return: org.csource.fastdfs.StorageClient
    * @Author: Leo Lee
    * @Date: 19.4.11
    */
    private static StorageClient getTrackerClient() throws IOException {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return  storageClient;
    }

    /**
     * 获取 TrackerServer 信息
    * @Description: getTrackerServer
    * @return: org.csource.fastdfs.TrackerServer
    * @Author: Leo Lee
    * @Date: 19.4.11
    */
    private static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return  trackerServer;
    }

    /**
     * ① NameValuePair，主要存储文件的一些基础属性，如作者信息、创建时间等；
     * ② getStorageClient()，封装了获取客户端的方法。
    * @Description: upload
    * @Param: [file]
    * @return: java.lang.String[]
    * @Author: Leo Lee
    * @Date: 19.4.11
    */
    public static String[] upload(FastDFSFile file){
        logger.info("File Name: " + file.getName() + ", File Length: " + file.getContent().length);

        // 文件属性信息
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", file.getAuthor());
        long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        StorageClient storageClient = null;
        try {
            // 获取
            storageClient = getTrackerClient();
            // 上次
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        } catch (IOException e) {
            logger.error("IO Exception when uploadind the file:" + file.getName(), e);
        } catch (MyException e) {
            logger.error("Non IO Exception(My Exception) when uploadind the file:" + file.getName(), e);
        }
        logger.info("upload_file time used:" + (System.currentTimeMillis() - startTime) + " ms");

        //验证上传结果
        if (uploadResults == null && storageClient != null){
            logger.error("upload file fail, error code:" + storageClient.getErrorCode());
        }
        String groupName = uploadResults[0];
        String remoteFileName = uploadResults[1];

        //上传文件成功会返回 groupName。
        logger.info("upload file successfully!!!" + "group_name:" + groupName + ", remoteFileName:" + " " + remoteFileName);
        return uploadResults;
    }

    /**
     * 根据 groupName 和文件名获取文件信息。
     * ① group 也可称为卷，同组内服务器上的文件是完全相同的，
     * ② 同一组内的 storage server 之间是对等的，
     * ③ 文件上传、删除等操作可以在任意一台 storage server 上进行。
    * @Description: getFile 
    * @Param: [groupName, remoteFileName] 
    * @return: org.csource.fastdfs.FileInfo 
    * @Author: Leo Lee
    * @Date: 19.4.11 
    */ 
    public static FileInfo getFile(String groupName, String remoteFileName){
        try {
            StorageClient storageClient = getTrackerClient();
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (MyException e) {
            logger.error("Non IO Exception(My Exception): Get File from Fast DFS failed", e);
        }
        return null;
    }

    /**
     * 根据 storageClient 的 API 获取文件的字节流并返回
    * @Description: downFile 
    * @Param: [groupName, remoteFileName]
    * @return: java.io.InputStream 
    * @Author: Leo Lee
    * @Date: 19.4.11
    */ 
    public static InputStream downFile(String groupName, String remoteFileName){
        try {
            StorageClient storageClient = getTrackerClient();
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            InputStream ins = new ByteArrayInputStream(fileByte);
            return ins;
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (MyException e) {
            logger.error("Non IO Exception(My Exception): Get File from Fast DFS failed", e);
        }
        return null;
    }

    /**
     * 根据文件名和组删除对应的文件。
    * @Description: deleteFile
    * @Param: [groupName, remoteFileName]
    * @return: void
    * @Author: Leo Lee
    * @Date: 19.4.11
    */
    public static void deleteFile(String groupName, String remoteFileName) throws Exception{
        StorageClient storageClient = getTrackerClient();
        int i = storageClient.delete_file(groupName, remoteFileName);
        logger.info("delete file successfully!!!" + i);
    }

    public static StorageServer[] getStoreStorages(String groupName) throws IOException{
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorages(trackerServer, groupName);
    }

    public static ServerInfo[] getFetchStorages(String groupName, String remoteFileName) throws IOException{
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }

    /**
     * 获取文件服务器URL
    * @Description: getTrackerUrl
    * @return: java.lang.String
    * @Author: Leo Lee
    * @Date: 19.4.11
    */
    public static String getTrackerUrl() throws IOException{
        return "http://" + getTrackerServer().getInetSocketAddress().getHostString() + ":" + ClientGlobal.getG_tracker_http_port() + "/";
    }

}
