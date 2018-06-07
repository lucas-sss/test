package com.example.demo.core.coder;

import com.example.demo.common.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/6/5
 */
public class MsgJsonEncoder extends MessageToMessageEncoder<Object> {

    private static final int BF_SIZE = 1024;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
        ByteBuf buf = ctx.alloc().buffer(BF_SIZE);
        buf.writeByte(Constants.MSG_START_FLAG);

        String msgencode = objectMapper.writeValueAsString(msg);

        buf.writeBytes(msgencode.getBytes());
        buf.writeByte(Constants.MSG_END_FLAG);
        out.add(buf);


    }

}
