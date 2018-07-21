package com.example.demo.msg;

import java.io.File;

/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/6/20
 */
public class FileMsg extends BaseMsg implements MsgInvoker {

    private File file;

    private int length;

    private byte[] bytes;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public void invoke() {

    }
}
