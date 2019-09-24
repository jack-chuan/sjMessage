package cn.com.sparknet.sjMessage.common.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

//import javax.xml.soap.Node;


/**
 * 基础公共类
 * @author sunjiangtao
 *
 */
public class WaterUtil {
	
	//日期格式
	public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_YYYYMMDD_HHMM = "yyyy-MM-dd HH:mm";
	public static final int pageSize = 20;
	public static final String defaultAlgorithm = "MD5";
	/**
	 * 数据库别名．
	 */
//	public static final String dbAlias = "agricMach";
	
	
	/**
	 * 日志名.
	 */
//	public static final String logName = "agricMach";
	
	
	/**
	 * 判断字符串是否为空串(null或只有空格).是则返回true，否则返回false.
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		return str == null || str.trim().equals(""); 
	}
	
	
	/**
	 * 如果字符串为空,转化为指定内容.
	 * @param str				给定字符串
	 * @param replaceContent	替换内容
	 * @return					替换内容
	 */
	public static String NVLToStr(Object obj,String replaceContent){
		if(obj == null){
			return replaceContent;
		}
		String str = obj.toString().trim();
		return str.length() == 0 ? replaceContent : str;
	}
	
/**
 * 验证时间格式对不对：正确格式如（2008-1-11 12:20:20 || 2008-01-11）
 * @param checkValue  给定字符串
 * @return 如果正确返回true,不正确返回false
 * @author wuxj
 */
	public static boolean checkDateFormatAndValite(String checkValue){
  	   String checkTime=checkValue.trim();
 	  DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");  
	         if(checkTime != null && !checkTime.equals(""))  
	         {  
	             if(checkTime.split("/").length > 1)  
	             {  
	                 dateFormat = new SimpleDateFormat("yyyy/MM/dd");  
	             }  
	             if (checkTime.split("-").length > 1)  
	             {  
	                 dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	             }  
	         }else  
	         {  
	             return true;  
	         }  
	         try  
	         {  
	        	 Date d = dateFormat.parse(checkTime); //把字符串转化为时间格式 ,通过异常来确定格式
	         }  
	         catch(Exception e)  
	         {  
	             return false;  
	         }  
	         String eL= "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";  
	         Pattern p = Pattern.compile(eL);  //用正则表达式验证 
	         Matcher m = p.matcher(checkTime);   
	         boolean b = m.matches();  
	         if(b)  
	         {  
	             return true;
	         }  
	         else  
	         {  
	             return false;
	         }  
   }
	
	
	/**
	 * 如果字符串为空,转化为指定内容.
	 * @param str				给定字符串
	 * @param replaceContent	替换内容
	 * @return					替换内容
	 */	
	public static int NVLToInt(Object obj,int replaceContent){
		if(obj == null){
			return replaceContent;
		}
		String str = obj.toString().trim();
		return str.length() == 0 ? replaceContent : Integer.parseInt(str); 
	}
	
	
	/**
	 * 如果字符串为空,转化为指定内容.
	 * @param obj				给定字符串
	 * @param replaceContent	替换内容
	 * @return					替换内容
	 */	
	public static long NVLToLong(Object obj,long replaceContent){
		if(obj == null){
			return replaceContent;
		}
		String str = obj.toString().trim();
		return str.length() == 0 ? replaceContent : Long.parseLong(str); 
	}
	
	
	/**
	 * 如果字符串为空,转化为指定内容.
	 * @param obj				给定字符串
	 * @param replaceContent	替换内容
	 * @return					替换内容
	 */
	public static float NVLToFloat(Object obj,float replaceContent){
		if(obj == null){
			return replaceContent;
		}
		String str = obj.toString().trim();
		return str.length() == 0 ? replaceContent : Float.parseFloat(str); 
	}
	
	
	/**
	 * 如果字符串为空,转化为指定内容.
	 * @param obj				给定字符串
	 * @param replaceContent	替换内容
	 * @return					替换内容
	 */
	public static double NVLToDouble(Object obj,double replaceContent){
		if(obj == null){
			return replaceContent;
		}
		String str = obj.toString().trim();
		return str.length() == 0 ? replaceContent : Double.parseDouble(str); 
	}
	
	
	/**
	 * 规范sql字符串（如果为空，转为空格；否则将单引号替换为2个单引号），预防sql注入．
	 * @param str				给定字符串
	 * @param replaceContent	替换内容
	 * @return					替换内容
	 */
	public static String transDBStr(String str){
		return str == null ? " " : str.trim().replaceAll("'", "''");
	}
	
	
	/**
	 * 将字符串中"<","&"替换为html转义字符．
	 * <br>(html展示).<br>
	 * @param str	源字符串
	 * @return		转换后的字符串
	 */
	public static String formatHtmlESC(String str){
		return str == null ? "" : str.trim().replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll("\r\n|\r|\n","<br>");
	}
	
	
	/**
	 * 将textarea中的换行转换为可用的换行符。
	 * @param str
	 * @return
	 */
	public static String formatTextAreaESC(String str){
		return str == null ? "" : str.trim().replaceAll("\r\n|\r|\n","\\\\n");
	}
	
	
	/**
	 * 将制定的字符串sourceStr中的内容replaceStr1替换为内容replaceStr2
	 * @param sourceStr
	 * @param replaceStr
	 * @return
	 */
	public static String formatStr(String sourceStr,String replaceStr1,String replaceStr2){
		return sourceStr == null || replaceStr1 ==null || replaceStr2 == null ? ""  : sourceStr.trim().replaceAll(replaceStr1,replaceStr2);
	}
	
	
	/**
	 * 将制定的字符串sourceStr中的内容replaceStr1替换为内容replaceStr2
	 * @param sourceStr
	 * @param replaceStr
	 * @return
	 */
	public static String formatFilePath(String filePath){
		return filePath == null ? ""  : filePath.trim().replaceAll(":\\\\\\\\",":/").replaceAll(":\\\\",":/").replaceAll("\\\\","/");
	}
	
	
	/**
	 * 将字符串中"<","&"的html转义字符转换成"<","&"．
	 *  <br>(js中赋值).<br>
	 * @param str	源字符串
	 * @return		转换后的字符串
	 */
	public static String conFormatHtmlESC(String str){
		return str == null ? "" : str.trim().replaceAll("<br>","\r\n|\r|\n").replaceAll("&lt;", "<").replaceAll("&amp;", "&");
	}

	
	/**
	 * 将字符串中单/双引号转化为字符单/双引号．
	 * @param str	源字符串
	 * @return		转换后的字符串
	 */
	public static String formatJsESC(String str){
		return str == null ? "" : str.trim().replaceAll("'", "\\\\'").replaceAll("\"", "\\\\\"");
	}

	
	/**
	 * 将字符串数组中的各值，用拼接符拼接
	 * @param strArray	源字符数组
	 * @param joinSign	拼接符，为空默认为","
	 * @return		转换后的字符串
	 */
	public static String joinStr(Object[] strArray,String joinSign){
		StringBuffer buffer = new StringBuffer();
		joinSign = WaterUtil.NVLToStr(joinSign, ",");
		for(int i=0; i<strArray.length; i++){
			buffer.append(WaterUtil.NVLToStr(strArray[i], "")).append(joinSign);
		}
		if(buffer.length() > 0)
			buffer.deleteCharAt(buffer.length() - 1);
		return buffer.toString();
	}

	
	/**
	 * 将字符串数组中的各值，用","拼接
	 * @param strArray	源字符数组
	 * @return		转换后的字符串
	 */
	public static String joinStr(Object[] strArray){
		return joinStr(strArray,"");
	}
	
	
	/**
	 * 加入一个元素到数组最后．
	 * @param 	addValue  		要增加的值
	 * @param 	addArray		要增加的数组
	 * @return
	 */
	public static long[] insertBottomIntoArray(long addValue, long[] addArray){
		int oldLength = addArray.length;
		long[] newArray = new long[oldLength+1];
		for(int i=0; i<oldLength; i++){
			newArray[i] = addArray[i];
		}
		newArray[oldLength] = addValue;
		return newArray;
	}
	
	
	/**
	 * 从数组中删除一个元素．
	 * @param 	addValue  		要删除的值
	 * @param 	addArray		要删除的数组
	 * @return
	 */
	public static long[] deleteValuesFromArray(long delValue, long[] delArray){
		int oldLength = delArray.length;
		int num=-1;
		for(int i=0; i<oldLength; i++){
			if(delArray[i] == delValue){
				num = i;
				break;
			}
		}
		if(num==-1)
			return delArray;
		long[] newArray = new long[oldLength-1];
		int j = 0;
		for(int i=0; i<oldLength; i++){
			if(i!=num){
				newArray[j] = delArray[i];
				j++;
			}
		}
		return newArray;
	} 
	
	
	/**
	 * 加入一个元素到数组最后．
	 * @param 	addValue  		要增加的值
	 * @param 	addArray		要增加的数组
	 * @return
	 */
	public static int[] insertBottomIntoArray(int addValue, int[] addArray){
		int oldLength = addArray.length;
		int[] newArray = new int[oldLength+1];
		for(int i=0; i<oldLength; i++){
			newArray[i] = addArray[i];
		}
		newArray[oldLength] = addValue;
		return newArray;
	}
	
	
	/**
	 * 从数组中删除一个元素．
	 * @param 	addValue  		要删除的值
	 * @param 	addArray		要删除的数组
	 * @return
	 */
	public static int[] deleteValuesFromArray(int delValue, int[] delArray){
		int oldLength = delArray.length;
		int num=-1;
		for(int i=0; i<oldLength; i++){
			if(delArray[i] == delValue){
				num = i;
				break;
			}
		}
		if(num==-1)
			return delArray;
		int[] newArray = new int[oldLength-1];
		int j = 0;
		for(int i=0; i<oldLength; i++){
			if(i!=num){
				newArray[j] = delArray[i];
				j++;
			}
		}
		return newArray;
	}
	
	
	/**
	 * 加入一个元素到数组最后．
	 * @param 	addValue  		要增加的值
	 * @param 	addArray		要增加的数组
	 * @return
	 */
	public static String[] insertBottomIntoArray(String addValue, String[] addArray){
		int oldLength = addArray.length;
		String[] newArray = new String[oldLength+1];
		for(int i=0; i<oldLength; i++){
			newArray[i] = addArray[i];
		}
		newArray[oldLength] = addValue;
		return newArray;
	}
	
	
	/**
	 * 从数组中删除一个元素．
	 * @param 	addValue  		要删除的值
	 * @param 	addArray		要删除的数组
	 * @return
	 */
	public static String[] deleteValuesFromArray(String delValue, String[] delArray){
		int oldLength = delArray.length;
		int num=-1;
		for(int i=0; i<oldLength; i++){
			if(delArray[i] == delValue){
				num = i;
				break;
			}
		}
		if(num==-1)
			return delArray;
		String[] newArray = new String[oldLength-1];
		int j = 0;
		for(int i=0; i<oldLength; i++){
			if(i!=num){
				newArray[j] = delArray[i];
				j++;
			}
		}
		return newArray;
	}
	
	
	/**
	 * 创建zip压缩文件。
	 * @param inputFilePath			需要压缩的文件路径(如果是文件夹，则压缩其下所有文件).
	 * @param outFilePath			压缩文件保存路径。
	 * @throws Exception
	 */
	public static void createZip(String[] inputFilePath, String outFilePath) throws Exception {
		// 压缩baseDir下所有文件，包括子目录
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outFilePath));
		ZipEntry ze = null;
		byte[] buf = new byte[1024];
		int readLen = 0;
		for(int i=0;i<inputFilePath.length;i++){
			String baseDir = inputFilePath[i];
			List fileList = getSubFiles(new File(baseDir));

			// 压缩文件名
		
			for (int j = 0; j < fileList.size(); j++) {
				File f = (File) fileList.get(j);
				// 创建一个ZipEntry，并设置Name和其它的一些属性
				ze = new ZipEntry(getAbsFileName(baseDir, f));
				ze.setSize(f.length());
				ze.setTime(f.lastModified());
				// 将ZipEntry加到zos中，再写入实际的文件内容
				zos.putNextEntry(ze);
				InputStream is = new BufferedInputStream(new FileInputStream(f));
				while ((readLen = is.read(buf, 0, 1024)) != -1) {
					zos.write(buf, 0, readLen);
				}
				is.close();
			}
		}
		zos.finish();
		zos.close();
	}
	
	
	/**
	 * 根据给定读入流，向目标文件位置写文件
	 * @param targetFile
	 * @param in
	 * @throws IOException 
	 */
	public static void saveFile(String targetFile, InputStream in) throws IOException{
		FileOutputStream fOut = null;
		BufferedOutputStream buffOut = null;
		BufferedInputStream buffIn = null;
		try {
			if(isNull(targetFile)){
				return;
			}
			targetFile = targetFile.replaceAll("\\\\", "/");
			String targetPath = targetFile.substring(0,targetFile.lastIndexOf("/")+1);
			String targetFileName = targetFile.substring(targetFile.lastIndexOf("/")+1);
			ensureDirectory(targetPath);
			targetFile = targetPath + targetFileName;
			fOut = new FileOutputStream(targetFile);
			buffOut = new BufferedOutputStream(fOut);
			buffIn = new BufferedInputStream(in);
			byte[] b = new byte[4096];
			int readLen = 0;
			while((readLen = in.read(b)) != -1){
				buffOut.write(b, 0, readLen);
			}
			buffOut.flush();
			buffOut.close();
		} finally{
			try {
				buffOut.close();
				fOut.close();
				buffIn.close();
			} catch (IOException e) {
			}
		}
	}
	
	
	/**
	 * 复制文件.(inputFile不能为空，targetFile无值时，按同目录复制处理)
	 * @param inputFile		源文件
	 * @param targetFile	目标文件
	 */
	public static void copyFile(String inputFile, String targetFile){
		FileOutputStream fOut = null;
		BufferedOutputStream buffOut = null;
		InputStream in = null;
		BufferedInputStream buffIn = null;
		try {
			if(isNull(inputFile)){
				return;
			}
			File file = new File(inputFile);
			if(!file.isFile()){
				return;
			}
			if(isNull(targetFile)){
				targetFile = inputFile;
			}
			inputFile = inputFile.replaceAll("\\\\", "/");
			targetFile = targetFile.replaceAll("\\\\", "/");
			String targetPath = targetFile.substring(0,targetFile.lastIndexOf("/")+1);
			String targetFileName = targetFile.substring(targetFile.lastIndexOf("/")+1);
			ensureDirectory(targetPath);
			String resultFileName = "";
			if(inputFile.equals(targetFile)){
				resultFileName = "复件 "+targetFileName;
				int i=2;
				while(true){
					File tempFile = new File(targetPath+resultFileName);
					if(tempFile.exists()){
						resultFileName = "复件" + " ("+i+") " + targetFileName;
						i++;
					}else{
						break;
					}
				}
				targetFileName = resultFileName;
			}
			targetFile = targetPath + targetFileName;
			fOut = new FileOutputStream(targetFile);
			buffOut = new BufferedOutputStream(fOut);
			in = new FileInputStream(inputFile);
			buffIn = new BufferedInputStream(in);
			byte[] b = new byte[4096];
			int readLen = 0;
			while((readLen = in.read(b)) != -1){
				buffOut.write(b, 0, readLen);
			}
			buffOut.flush();
			buffOut.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				buffOut.close();
				fOut.close();
				buffIn.close();
				in.close();
			} catch (IOException e) {
			}
		}
	}
	
	
	public static void cutFile(String inputFile, String targetFile){
		if(isNull(inputFile)){
			return;
		}
		if(!inputFile.equals(targetFile)){
			copyFile(inputFile, targetFile);
			File file = new File(inputFile);
			if(file.isFile())
				file.delete();
		}
		
	}
	
	
	public static void corpDirectory(String inputPath, String targetPath){
		if(isNull(inputPath)){
			return;
		}
		File dir = new File(inputPath);
		if(!dir.isDirectory()){
			return;
		}
		if(isNull(targetPath)){
			targetPath = inputPath;
		}
		inputPath = inputPath.replaceAll("\\\\", "/");
		targetPath = targetPath.replaceAll("\\\\", "/");
		inputPath = inputPath.endsWith("/") ? inputPath : inputPath + "/";
		targetPath = targetPath.endsWith("/") ? targetPath : targetPath + "/";
		File targetDir = new File(targetPath);
		if(!targetDir.isDirectory()){
			ensureDirectory(targetPath);
		}
		File[] file = dir.listFiles();
		for(int i=0; i<file.length; i++){
			if(file[i].isDirectory()){
				corpDirectory(file[i].getAbsolutePath(), targetPath+file[i].getName()+"/");
			}
			if(file[i].isFile()){
				copyFile(file[i].getAbsolutePath(), targetPath+file[i].getName());
			}
		}
	}
	
	
	
	/**
	 * 得到路径下所有我文件。
	 * @param baseDir		基本路径。
	 * @return				路径下文件列表。
	 */
	private static List getSubFiles(File baseDir) {
		List ret = new ArrayList();
		// File base=new File(baseDir);
		if(baseDir.isFile()){
			ret.add(baseDir);
			return ret;
		}
		File[] tmp = baseDir.listFiles();
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i].isFile()) {
				ret.add(tmp[i]);
			}
			if (tmp[i].isDirectory()) {
				ret.addAll(getSubFiles(tmp[i]));
			}
		}
		return ret;
	}
		

	/**
	 * 得到文件路径名。
	 * @param baseDir			url。
	 * @param realFileName		文件名。
	 * @return					绝对路径。
	 */
	private static String getAbsFileName(String baseDir, File realFileName) {
		File real = realFileName;
		File base = new File(baseDir);
		String ret = real.getName();
		if(real.equals(base))
			return ret;
		while (true) {
			real = real.getParentFile();
			if (real == null)
				break;
			if (real.equals(base))
				break;
			else {
				ret = real.getName() + "/" + ret;
			}
		}
		return ret;
	}
	
	
	/**
	 * 将毫秒数转化为日期。格式为'YYYY-mm-dd HH24:mi:ss'
	 * @param millis
	 * @return
	 */
	public static String formatTime(long millis){
		Date date = new Date(millis);
		return date.toLocaleString();
	}
	
	
	/**
	 * 获得当前时间的毫秒数。
	 * @return
	 */
	public static long getNowTimeMillis(){
		return System.currentTimeMillis();
	}
	
	
	/**
	 * 获得当前日期(格式为：yyyy-MM-dd).
	 * @return
	 */
	public static String getNowDate(){
		return getDate(new Date(),"yyyy-MM-dd");
	}
	
	
	/**
	 * 使用指定格式返回当前日期。
	 * @param pattern
	 * @return
	 */
	public static String getNowDate(String pattern){
		return getDate(new Date(),pattern);
	}
	
	
	/**
	 * 使用指定格式返回指定日期.
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getDate(Date date,String pattern){
		if(date == null)
			return "";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	
	/**
	 * 使用指定格式返回指定日期.
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getDate(String date,String pattern){
		if(date == null)
			return "";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(java.sql.Date.valueOf(date));
	}
	
	
	/**
	 * 使用指定格式返回日期
	 * @param dateStr		日期格式字符串
	 * @param patten
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String dateStr, String patten) throws ParseException{
		DateFormat format = new SimpleDateFormat(patten);
		return format.parse(dateStr);
	}
	
	
	/**
	 * 将毫秒数转化为日期。格式为'YYYY-mm-dd HH24:mi:ss'
	 * @param millis
	 * @return
	 */
	public static String getTime(long millis){
		return getDate(new Date(millis),"yyyy-MM-dd HH:mm:ss");
	}
	
	
	/**
	 * 将毫秒数按指定格式转化为日期。
	 * @param millis
	 * @return
	 */
	public static String getTime(long millis, String pattern){
		return getDate(new Date(millis),pattern);
	}
	
	
	/**
	 * 格式化字符串，不足位数的在前端以填充字符串补齐.
	 * @param sourceStr			待格式化的字符串
	 * @param digitNum			格式化后的位数
	 * @param fillCharacter		填充字符
	 * @return		格式化后的字符串
	 */
	public static String formatStr(String sourceStr,int digitNum,String fillCharacter){
		String result = sourceStr;
		int length = result.length();
		for(int i=0; i < digitNum - length; i++){
			result = fillCharacter+result;
		}
		return result;
	}
	
	
	/**
	 * 格式化数字，不足位数的在前端以填充字符串补齐.
	 * @param sourceStr			待格式化的字符串
	 * @param digitNum			格式化后的位数
	 * @param fillCharacter		填充字符
	 * @return		格式化后的字符串
	 */
	public static String formatStr(int sourceStr,int digitNum,String fillCharacter){
		String result = formatStr(sourceStr+"",digitNum,fillCharacter);
		return result;
	}
	

	/**
	 * 截取字符串，从指定位数之后替换为"..."。
	 * @param inputStr
	 * @param retainNum
	 * @return
	 */
	public static String formatStr(String inputStr,int retainNum){
		String targetStr = inputStr;
		if(inputStr.length() > retainNum+1)
			targetStr = inputStr.substring(0, retainNum-1)+"...";
		return targetStr;
	}
	
	
	/**
	 * 将指定字符串转换为首字母大写的字符串
	 * @param str
	 * @return
	 */
	public static String toFirstCharUpperCase(String str){
		String replaceStr = "";
		if(!WaterUtil.isNull(str)){
			replaceStr = str.substring(0,1).toUpperCase()+str.substring(1,str.length());
		}
		return replaceStr;
	}
	
	
	/**
	 * 将指定字符串转换为首字母大写的字符串
	 * @param str
	 * @return
	 */
	public static String toFirstCharLowerCase(String str){
		String replaceStr = "";
		if(!WaterUtil.isNull(str)){
			replaceStr = str.substring(0,1).toLowerCase()+str.substring(1,str.length());
		}
		return replaceStr;
	}
	
	
	/**
	 * 获取字符串的加密值.
	 * @param srcStr		源字符串
	 * @param algorithm		加密方式
	 * @return				加密后的密文
	 * @throws NoSuchAlgorithmException 
	 */
	public static String getCipherText(String srcStr, String algorithm) throws NoSuchAlgorithmException {
		if (srcStr == null){
			return null;
		}
		algorithm = WaterUtil.NVLToStr(algorithm, defaultAlgorithm);
		MessageDigest digest = null;
		digest = MessageDigest.getInstance(algorithm);
		digest.update(srcStr.getBytes());
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}
	
	
	/**
	 * 获取单个文件的MD5值！
	 * @param file
	 * @return
	 */
	public static String getFileMD5(File file) {
		if (!file.isFile()){
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in=null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}
	  
	/**
	 * 获取文件夹中文件的MD5值
	 * @param file
	 * @param listChild ;true递归子目录中的文件
	 * @return
	 */
	public static Map getDirMD5(File file,boolean listChild) {
		if(!file.isDirectory()){
			return null;
		}
		//<filepath,md5>
		Map map=new HashMap();
		String md5;
		File files[]=file.listFiles();
		for(int i=0;i<files.length;i++){
			File f=files[i];
			if(f.isDirectory()&&listChild){
				map.putAll(getDirMD5(f, listChild));
			} else {
				md5=getFileMD5(f);
				if(md5!=null){
					map.put(f.getPath(), md5);
				}
			}
		}
		return map;
	}
	
	
	/**
	 * 检查给定路径是否是目录，不是则新建.
	 * @param filePath
	 */
	public static void ensureDirectory(String filePath){
		File fileDir = new File(filePath);
		ensureDirectory(fileDir);
	}
	
	
	/**
	 * 检查给定路径是否是目录，不是则新建.
	 * @param filePath
	 */
	public static void ensureDirectory(File fileDir){
		if(!fileDir.isDirectory()){
			fileDir.mkdirs();
		}
	}	
	
	
	/**
	 * 检查指定文件是否存在，如不存在，从库中提取指定数据，生成文件.
	 * @param filePath
	 * @param fileId
	 * @param seqId
	
	public static void ensureFileByDatabase(ResultSet rs, String colName, String filePath){
		filePath = WaterUtil.formatFilePath(filePath);
		String fileDir = filePath.substring(0,filePath.lastIndexOf("/"));
		WaterUtil.ensureDirectory(fileDir);
		File file = new File(filePath);
		if(!file.isFile())
			WaterDataPoolFunc.getFileFromDatabase(rs,colName, filePath);
	}
	 */
	
	/**
	 * 检查指定文件是否存在，如不存在，从库中提取指定数据，生成文件.
	 * @param proxoolName		数据库别名
	 * @param tableName			表名
	 * @param columName			需要写文件filePath的数据库字段名
	 * @param key				查询条件字段数组(只以and组合各字段)
	 * @param id				查询条件字段对应值
	 * @param filePath			文件路径
	
	public static void ensureFileByDatabase(String proxoolName,String tableName,String columName,String[] key,String[] id, String filePath){
		filePath = WaterUtil.formatFilePath(filePath);
		String fileDir = filePath.substring(0,filePath.lastIndexOf("/"));
		WaterUtil.ensureDirectory(fileDir);
		File file = new File(filePath);
		if(!file.isFile())
			WaterDataPoolFunc.getFileFromDatabase(tableName, columName, key, id, filePath);
	}
	 */
	
	/**
	 * 检查指定文件是否存在，如不存在，从库中提取指定数据，生成文件.
	 * @param filePath
	 * @param fileId
	 * @param seqId
	
	public static void ensureFileByDatabase(String tableName,String columName,String[] key,String[] id, String filePath){
		ensureFileByDatabase("", tableName, columName, key, id, filePath);
	}
	 */
	
	/**
	 * 获得xml节点属性值.
	 * @param node
	 * @return
	 */
	public static String getNodeValue(org.w3c.dom.Node node){
		return node.getFirstChild() == null ? "" : node.getFirstChild().getNodeValue();
	}
	
	
	/**
	 * 获得xml节点属性(nodeName:节点名；nodeValue：节点值).
	 * @param node
	 * @return
	 */
	public static Hashtable getNodeAttributes(org.w3c.dom.Node node){
		Hashtable hash = new Hashtable();
		String nodeValue = getNodeValue(node);
		hash.put("nodeName",node.getNodeName());
		hash.put("nodeValue", nodeValue);
		return hash;
	}
	
	
	/**
	 * 获得指定查询的总条数。
	 * @param sql
	 * @param pageNum
	 * @param perPage
	 * @return
	 */
	public static String getTotalNum(String sql,int pageNum, int perPage ){
		StringBuffer buffer = new StringBuffer("SELECT * FROM (");
		buffer.append(" SELECT temp.* ,ROWNUM num FROM (");
		buffer.append(sql);
		buffer.append(") temp where");
		buffer.append(" ROWNUM <= "+((pageNum)*perPage));
		buffer.append(" ) WHERE num > "+((pageNum-1)*perPage));
		return buffer.toString();
	}
	
	/**
	 * 获得分页的总页数。
	 * @param totalNum
	 * @param perPage
	 * @return
	 */
	public static int getTotalPage(String totalNum,int perPage){
		int num = WaterUtil.NVLToInt(totalNum,0);
		int totalPage = (num/perPage)+1;
		if(num!=0 && num%perPage==0)
			totalPage -= 1;
		return totalPage;
	}
	
	
	/**
	 * 检查此 Class 对象所表示的类或接口的指定属性是否存在.
	 * @param clazz			指定类
	 * @param methodName	所需属性的简称.
	 * @return
	 */
	public static boolean isFieldExist(Class clazz, String fieldName){
		//参数校验起
		Field[] fieldArray = null;
		if(clazz == null)
			return false;
		//参数校验止
		
		
		fieldArray = clazz.getDeclaredFields();
		if(fieldArray == null || fieldArray.length <= 0)
			return false;
		for(int fieldItem = 0; fieldItem < fieldArray.length; fieldItem++ ){
			Field field = fieldArray[fieldItem];
			if(field.getName().equals(fieldName)){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 检查此 Class 对象所表示的类或接口的指定公共成员方法是否存在.
	 * @param clazz			指定类
	 * @param methodName	所需方法的简称.
	 * @param methodParameterTypes			按声明顺序标识该方法形式参数类型的 Class 对象的一个数组,null按空数组处理.
	 * @return
	 */
	public static boolean isMethodExist(Class clazz, String methodName, Class[] methodParameterTypes){
		//参数校验起
		Method[] methodArray = null;
		if(clazz == null)
			return false;
		if(methodParameterTypes == null){
			methodParameterTypes = new Class[0];
		}
		//参数校验止
		
		
		methodArray = clazz.getMethods();
		if(methodArray == null || methodArray.length <= 0)
			return false;
		for(int methodItem = 0; methodItem < methodArray.length; methodItem++ ){
			Method method = methodArray[methodItem];
			if(method.getName().equals(methodName)){
				Class[] parameterTypesArray = method.getParameterTypes();
				if(parameterTypesArray.length != methodParameterTypes.length)
					return false;
				boolean paraEqualFlag = true;
				for( int parameterTypesItem = 0; parameterTypesItem < parameterTypesArray.length; parameterTypesItem ++ ){
					String parameterTypeName = parameterTypesArray[parameterTypesItem].getName();
					String methodParameterTypeName = methodParameterTypes[parameterTypesItem].getName();
					if(!parameterTypeName.equals(methodParameterTypeName)){
						paraEqualFlag = false;
						break;
					}
				}
				if(paraEqualFlag == true){
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * 检查指定的类所对应的表，删除操作是否delete操作.
	 * @param clazz
	 * @return
	 */
	public static boolean isRealDel(Class clazz){
		return isMethodExist(clazz, "setState", new Class[]{String.class});
	}
	
	public static void setDataState(Class clazz,Object obj,String value) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		clazz.getMethod("setState",  new Class[]{String.class}).invoke(obj, value);
	}
	
	
	/**
	 * 将文件上传到临时文件夹中
	 * @param fileItems
	 * @param session
	 * @param fileName
	 * @return
	 
	public static String upLoadFile(String sourcePath, String tempPath,List fileItems) {
		WaterParams params = null;
		String fileName = "";
		try {
			params = WaterParams.getInstance();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File fileDir = new File(tempPath);
		if(!fileDir.isDirectory()){
			fileDir.mkdirs();
		}
		try {
			Iterator iter = fileItems.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) {
					long now = WaterUtil.getNowTimeMillis();
					String name = item.getName();			//获取上传文件名,包括路径 
	    			String showName = name;
					name=name.substring(name.lastIndexOf("."));		//从全路径中提取后缀名
	    			name =  now + name;			// 根据系统时间生成上传后保存的文件名
					long size = item.getSize(); 
	    			if((name==null||name.equals("")) && size==0){
	    				continue;
	    			}
	    			if(size > 100*1024){
	    				return "";
	    			}
					File file = new File(tempPath+name);
					item.write(file);
					if(WaterUtil.NVLToInt(WaterParams.getInstance().get("is_md5_check"),0) == 1){
						String[] colName = {"file_name","real_file_name"};
						String md5 = WaterUtil.getFileMD5(file);
						Hashtable hash = WaterDataPoolFunc.checkFileMd5(md5, "file_md5", "attachment", colName);
						fileName = WaterUtil.NVLToStr(hash.get("file_name"), "");
						String realFileName = WaterUtil.NVLToStr(hash.get("real_file_name"), "");
						long millis = WaterUtil.NVLToLong(realFileName.substring(0,realFileName.lastIndexOf(".")), -1);
						sourcePath = sourcePath + WaterUtil.getTime(millis, "yyyyMM")+"/";
						WaterUtil.ensureDirectory(sourcePath);
						fileName = sourcePath+realFileName;
					}
					if(WaterUtil.isNull(fileName)){
						sourcePath = sourcePath + WaterUtil.getNowDate("yyyyMM")+"/";
						WaterUtil.ensureDirectory(sourcePath);
						WaterUtil.copyFile(tempPath+name, sourcePath+name);
						fileName = sourcePath+name;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	*/
	
	
	/**
	 * 根据ip地址，或者mac地址.
	 * @param ip	ip地址
	 * @return
	 * @throws IOException
	 */
	public static String getMACAddress(String ip) throws IOException{
        String str = "";
        String macAddress = "";
        Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
        InputStreamReader ir = new InputStreamReader(p.getInputStream());
        LineNumberReader input = new LineNumberReader(ir);
        for (int i = 1; i < 100; i++) {
            str = input.readLine();
            if (str != null) {
                if (str.indexOf("MAC Address") > 1) {
                    macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
                    break;
                }
            }
        }
        input.close();
        ir.close();
        return macAddress;
    }
	
	
	/**
	 * 生成缩略图.
	 * @param sourcePath		源文件路径
	 * @param thumbnailPath		缩略图路径
	 * @param width				缩略后的宽度(高度自动计算)
	 * @param scale				缩略比例(当width为-1时，按照scale计算)
	 * @throws IOException 
	 */
	public static void createThumbnail(String sourcePath, String thumbnailPath, int width, double scale) throws IOException {
		File fi = new File(sourcePath); // 大图文件
		File fo = new File(thumbnailPath); // 将要转换出的小图文件
		/*
		 * AffineTransform 类表示 2D 仿射变换，它执行从 2D 坐标到其他 2D
		 * 坐标的线性映射，保留了线的“直线性”和“平行性”。可以使用一系 列平移、缩放、翻转、旋转和剪切来构造仿射变换。
		 */
		AffineTransform transform = new AffineTransform();
		BufferedImage bis = ImageIO.read(fi); // 读取图片
		int w = bis.getWidth();
		int h = bis.getHeight();
		// double scale = (double)w/h;
		int nw = width;
		if(width == -1)
			nw = (int)(w*scale);
		int nh = (nw * h) / w;
		double sx = (double) nw / w;
		double sy = (double) nh / h;
		transform.setToScale(sx, sy); // setToScale(double sx, double sy)
										// 将此变换设置为缩放变换。
		/*
		 * AffineTransformOp类使用仿射转换来执行从源图像或 Raster 中 2D 坐标到目标图像或 Raster 中 2D
		 * 坐标的线性映射。所使用的插值类型由构造方法通过 一个 RenderingHints 对象或通过此类中定义的整数插值类型之一来指定。
		 * 如果在构造方法中指定了 RenderingHints 对象，则使用插值提示和呈现
		 * 的质量提示为此操作设置插值类型。要求进行颜色转换时，可以使用颜色 呈现提示和抖动提示。 注意，务必要满足以下约束：源图像与目标图像
		 * 必须不同。 对于 Raster 对象，源图像中的 band 数必须等于目标图像中 的 band 数。
		 */
		AffineTransformOp ato = new AffineTransformOp(transform, null);
		BufferedImage bid = new BufferedImage(nw, nh,
				BufferedImage.TYPE_3BYTE_BGR);
		/*
		 * TYPE_3BYTE_BGR 表示一个具有 8 位 RGB 颜色分量的图像， 对应于 Windows 风格的 BGR
		 * 颜色模型，具有用 3 字节存 储的 Blue、Green 和 Red 三种颜色。
		 */
		ato.filter(bis, bid);
		ImageIO.write(bid, "jpeg", fo);
	}
	
	 // 字符型转成sql.Date
    public static java.sql.Date string_to_sqlDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dDate = null;
        try {
            dDate = sdf.parse(date.trim());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        java.sql.Date sDate = new java.sql.Date(dDate.getYear(), dDate
                .getMonth(), dDate.getDate());
        return sDate;
    }	
    
    /**
     * 过滤文件名特殊字符
     */
    public static String StringFilter(String str) {
    	String regEx="[`~!@#$%^&*()+=|{}':;',//[//]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    	Pattern p = Pattern.compile(regEx);
    	Matcher m = p.matcher(str);
    	return m.replaceAll("").trim();
    }
    
    /**
     * 安全字符过滤
     * @param str
     * @return
     */
    public static String strNewFiltrate(String str) {
        if(str==null || str.equals("")){
            return str;
        }
        
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&#x3C;");
        str = str.replaceAll(">", "&#x3E;");
        str = str.replaceAll("\"", "");
        str = str.replaceAll("'", "");
        str = str.replaceAll("%", "");
        str = str.replaceAll("eval", "");
        str = str.replaceAll("expression", "");
        str = str.replaceAll("unescape", "");
        str = str.replaceAll(",", "，");
        str = str.replaceAll(":", "：");
        
        str = str.replaceAll(";", "");
//        str = str.replaceAll("\\(", "（");
//        str = str.replaceAll("\\)", "）");
        str = str.replaceAll("&", "");
        str = str.replaceAll("\\+", "");
        str = str.replaceAll("=", " ");
        str = str.replaceAll("having", " ");
        str = str.replaceAll("group", " ");
        str = str.replaceAll(".*([';]+|(--)+).*", " ");
        return str;
    }
    
    /**
     * 安全字符过滤
     * @param str
     * @return
     */
    public static String strNewFiltrateForurlParam(String str) {
        if(str==null || str.equals("")){
            return str;
        }
        
        str = str.replaceAll("<", "&#x3C;");
        str = str.replaceAll(">", "&#x3E;");
        str = str.replaceAll("\"", "");
        str = str.replaceAll("'", "");
        str = str.replaceAll("%", "");
        str = str.replaceAll("eval", "");
        str = str.replaceAll("expression", "");
        str = str.replaceAll("unescape", "");
        str = str.replaceAll(",", "，");
        str = str.replaceAll(":", "：");
        
        str = str.replaceAll(";", "");
        str = str.replaceAll("\\+", "");
        str = str.replaceAll("having", " ");
        str = str.replaceAll("group", " ");
        str = str.replaceAll(".*([';]+|(--)+).*", " ");
        return str;
    }
    
    public static String getParameter(String encodedResult, String key) throws Exception {
        int start;
        if (encodedResult.startsWith(key + '=')) {
            start = key.length() + 1;
        } else {
            int i = encodedResult.indexOf('&' + key + '=');
            if (i == -1) {
                return null;
            }
            start = i + key.length() + 2;
        }

        int end = encodedResult.indexOf('&', start);
        String value = (end == -1)
                ? encodedResult.substring(start)
                : encodedResult.substring(start, end);

        return URLDecoder.decode(value, "GBK");
    }
    
}
