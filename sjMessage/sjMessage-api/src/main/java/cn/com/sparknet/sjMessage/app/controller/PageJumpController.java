package cn.com.sparknet.sjMessage.app.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pageJump")
public class PageJumpController {
    /**
     * 列表页面
     * @return
     */
    @RequestMapping("/toListPage")
    public String toListPage(){
        return "system/frame";
    }

    /**
     * 写消息页面
     */
    @RequestMapping("/toWriteMessagePage")
    public String toWriteMessagePage(){
        return "system/writeMessage";
    }

    /**
     * 发件箱列表页面
     * @return
     */
    @RequestMapping("/toOutboxPage")
    public String toOutboxPage(){
        return "outbox/outbox";
    }

    /**
     * 发件箱详情页面
     * @return
     */
    @RequestMapping("/toOutboxDetailPage")
    public String toOutboxDetailPage(Model model){
//        model.addAttribute("dataId", "123456789");
        return "outbox/outboxDetail";
    }

    /**
     * 发件箱转发页面
     * @return
     */
    @RequestMapping("/toOutboxForwardPage")
    public String toOutboxForwardPage(){
        return "outbox/outboxForward";
    }

    /**
     * 草稿箱列表页面
     * @return
     */
    @RequestMapping("/toDraftboxPage")
    public String toDraftboxPage(){
        return "draftbox/draftbox";
    }

    /**
     * 草稿箱详情页面
     * @return
     */
    @RequestMapping("/toDraftboxDetailPage")
    public String toDraftboxDetailPage(){
        return "draftbox/draftboxDetail";
    }

    /**
     * 草稿箱转发页面
     * @return
     */
    @RequestMapping("/toDraftboxForwardPage")
    public String toDraftboxForwardPage(){
        return "draftbox/draftboxForward";
    }

    /**
     * 垃圾箱列表页面
     * @return
     */
    @RequestMapping("/toDustbinPage")
    public String toDustbinPage(){
        return "dustbin/dustbin";
    }

    /**
     * 垃圾箱详情页面
     * @return
     */
    @RequestMapping("/toDustbinDetailPage")
    public String toDustbinDetailPage(){
        return "dustbin/dustbinDetail";
    }

    /**
     * 垃圾箱转发页面
     * @return
     */
    @RequestMapping("/toDustbinForwardPage")
    public String toDustbinForwardPage(){
        return "dustbin/dustbinForward";
    }

    /**
     *  收件箱列表
     * @return
     */
    @RequestMapping("/receiveMsg")
    public String  receiveMsg(){
        return "receiveMsg/receiveMsgIndex";
    }

    /**
     *  收件箱详情页
     * @return
     */
    @RequestMapping("/msgDetail")
    public String  forwordMsg(){
        return "receiveMsg/receiveMsgDetail";
    }

    /**
     *  收件箱转发页
     * @return
     */
    @RequestMapping("/msgForward")
    public String  msgForward(){
        return "receiveMsg/receiveMsgForward";
    }

    /**
     *  收件箱回复页
     * @return
     */
    @RequestMapping("/msgReply")
    public String  msgReply(){
        return "receiveMsg/receiveMsgReply";
    }

    /**
     *  通讯录
     * @return
     */
    @RequestMapping("/toAddressBookPage")
    public String  deptTree(){
        return "addressBook/addressBook";
    }
}
