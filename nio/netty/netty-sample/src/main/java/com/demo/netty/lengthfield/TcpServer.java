package com.demo.netty.lengthfield;

import java.nio.channels.SelectionKey;
import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;

public class TcpServer {
	
	EventLoopGroup eventLoopGroup;
	EventLoop eventLoop;
	NioEventLoop aas;
	//EpollEventLoop epollEventLoop;
	EpollEventLoopGroup epollEventLoopGroup;
	ByteBuf byteBuf;
	ByteBufAllocator byteBufAllocator;
	//sun.nio.ch.EPollSelectorImpl ePollSelectorImpl;
	EventLoopGroup eventLoopGroup;
	Lock lock;
	SelectionKey selectionKey;
	MessageToMessageDecoder<?> messageDecoder;
	StringDecoder stringDecoder;
	HttpRequestDecoder httpRequestDecoder;
	HttpResponseEncoder httpResponseEncoder;
	java.nio.channels.spi.SelectorProvider selectorProvider;
	ConcurrentHashMap<String, String> con;

	public static void main(String[] args){
		EventLoopGroup bossGroup=new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new EpollEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap=new ServerBootstrap();
			serverBootstrap.group(bossGroup,workerGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 200)
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
