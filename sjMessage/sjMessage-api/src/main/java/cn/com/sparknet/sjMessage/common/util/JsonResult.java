package cn.com.sparknet.sjMessage.common.util;

public class JsonResult<T> {
    
    /**
     * 数据
     * */
    protected T data;
    
    /**
     * 状态码
    */
    protected String code;

    /**
     * 信息
     */
    protected String msg;

    /**
     * 总数
     */
    protected int count;

    /**
    * @Description: JsonResult 若没有数据返回，默认状态码为0，提示信息为：操作成功！
    * @Param: []
    * @return:
    * @Author: Leo Lee
    * @Date: 18.9.24
    */
    public JsonResult() {
        this.code = "200";
        this.msg = "操作成功";
    }

    /**
    * @Description: JsonResult 若没有数据返回，可以人为指定状态码和提示信息
    * @Param: [code, msg]
    * @return:
    * @Author: Leo Lee
    * @Date: 18.9.24
    */
    public JsonResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /** 
    * @Description: JsonResult 有数据返回，状态码为200，默认提示信息为：操作成功
    * @Param: [data] 
    * @return:  
    * @Author: Leo Lee
    * @Date: 18.9.24 
    */ 
    public JsonResult(T data){
        this.data = data;
        this.code = "200";
        this.msg = "操作成功";
    }

    /**
    * @Description: JsonResult 有数据返回，状态码为200，人为指定提示信息
    * @Param: [data, msg]
    * @return:
    * @Author: Leo Lee
    * @Date: 18.9.24
    */
    public JsonResult(T data, String msg){
        this.data = data;
        this.code = "200";
        this.msg = msg;
    }
    
    /**
     * @Description: JsonResult 有数据返回，人为指定提示信息、状态码
     * @Param: [data, code, msg]
     * @return:
     * @Author: Leo Lee
     * @Date: 18.9.24
     */
    public JsonResult(T data,String code, String msg){
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    /**
     * @Description: JsonResult 有数据返回，人为指定提示信息、状态码
     * @Param: [data, code, msg, count]
     * @return:
     * @Author: Leo Lee
     * @Date: 18.9.24
     */
    public JsonResult(T data,String code, String msg,int count){
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.count = count;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
