package com.example.demo.msg;

import com.example.demo.utils.MsgUtil;

import java.io.Serializable;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/27 0027
 */
public abstract class BaseMsg implements Serializable {

    private String msgId = MsgUtil.getUuid();

    private Long createDate = System.currentTimeMillis();

    private Object msgBody;

    public BaseMsg() {
    }

    public BaseMsg(String msgBody) {
        this.msgId = MsgUtil.getUuid();
    }

    public String getMsgId() {
        return msgId;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public Object getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(Object msgBody) {
        this.msgBody = msgBody;
    }
}
