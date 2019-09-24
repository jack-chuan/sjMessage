package cn.com.sparknet.sjMessage.common.util;


/**
 * @program: ZTreeResult
 * @description: zTree result
 * @author: Leo Lee
 * @create: 2019-03-29 17:00
 **/
public class ZTreeResult {

    private String id;
    private String pId;
    private String name;
    private String isParent;
    private String open;
    private String uri;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
