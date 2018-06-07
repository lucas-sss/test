package com.example.demo.msg;

/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/6/5
 */
public class BusinessMsg extends BaseMsg implements MsgInvoker {

    private String msgType;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * 消息的回调方法，业务实现在具体实现类中
     *
     */
    @Override
    public void invoke(){

    }
}
