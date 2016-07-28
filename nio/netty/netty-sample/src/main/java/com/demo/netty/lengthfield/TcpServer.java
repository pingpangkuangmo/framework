package com.demo.netty.lengthfield;

import java.nio.charset.Charset;

import io.netty.bootstrap.ServerBootstrap;
<<<<<<< HEAD
import io.netty.buffer.ByteBuf;
=======
>>>>>>> f17dce85fee1b98e972e66a75d1621fdb483c1f7
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TcpServer {
	
	NioEventLoop aas;
	ByteBuf byteBuf;
	ByteBufAllocator byteBufAllocator;

	public static void main(String[] args){
		EventLoopGroup bossGroup=new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap=new ServerBootstrap();
			serverBootstrap.group(bossGroup,workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(80,0,4,0,4));
						ch.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
						ch.pipeline().addLast(new TcpServerHandler());
					}
				});
			ChannelFuture f=serverBootstrap.bind(8080).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {  
            workerGroup.shutdownGracefully();  
            bossGroup.shutdownGracefully();  
        }  
	}
}
