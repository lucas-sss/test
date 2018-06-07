package com.example.demo.core.coder;

import com.example.demo.common.Constants;
import com.example.demo.msg.BusinessMsg;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/6/5
 */
public class MsgJsonDecoder extends ByteToMessageDecoder {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {

        byte b = byteBuf.getByte(0);
        if (b == Constants.MSG_START_FLAG) {
            int bytes = byteBuf.bytesBefore(Constants.MSG_END_FLAG);
            if (bytes != -1) {
                byteBuf.readByte();
                byte[] bs = new byte[bytes - 1];
                byteBuf.readBytes(bs, 0, bytes - 1);
                String s = new String(bs, "UTF-8");
//                System.out.println("解码后字符串：" + s);
                BusinessMsg businessMsg = objectMapper.readValue(s, BusinessMsg.class);

                out.add(businessMsg);
                byteBuf.readByte();
            }
        }
    }
}
