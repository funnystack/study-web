package com.funny.study.netty.example6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author funnystack
 * @date 2021-08-08 14:41
 */
public class TestProtoServerHandler extends SimpleChannelInboundHandler<BaseMessageProto.Student> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseMessageProto.Student student) throws Exception {
        ctx.writeAndFlush(student);
    }
}
