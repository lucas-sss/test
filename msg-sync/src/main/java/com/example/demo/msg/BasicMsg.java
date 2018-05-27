package com.example.demo.msg;

import com.example.demo.utils.MsgUtil;

import java.util.Date;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/27 0027
 */
public class BasicMsg {

    private String msgId = MsgUtil.getUuid();

    private Date createDate = new Date();

    private String msgBody;

    public BasicMsg() {
    }

    public BasicMsg(String msgBody) {
        this.msgId = MsgUtil.getUuid();
    }

    public String getMsgId() {
        return msgId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

}
