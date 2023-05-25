package cn.tanglaoer.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/5/14
 */
public class HttpFileServer {
    private static final String DEFAULT_URL = "/src/cn/tanglaoer/netty/";

    public void run(final int port, final String url) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                            ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            ch.pipeline().addLast("fileServerHandler", new MyHttpFileServerHandler(url));
                        }
                    });
            ChannelFuture future = b.bind("127.0.0.1", port).sync();
            System.out.println("Http 文件目录服务器启动，网址是:" + "http://127.0.0.1:" + port + url);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            }catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        String url = DEFAULT_URL;
        if (args.length > 1) {
            url = args[1];
        }
        new HttpFileServer().run(port, url);
    }
}

class MyHttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    public final String url;

    public MyHttpFileServerHandler(String url) {
        this.url = url;
    }



    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {

    }

}