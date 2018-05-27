/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

import java.net.URI;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 *
 * @author Administrator
 */
public class WebsocketClient {
    
    private static final String CID_FORMAT = "0000000";   
    private static final String CID_PREFIX = "1R";
  
    public static String getCidNumber(int index){
        DecimalFormat df = new DecimalFormat(CID_FORMAT);
        String cidSuffix = df.format(index);
        return cidSuffix;
    }

    public static void main(String[] args) throws Exception {
       // String url = "wss://s9998.kugou.hstx.17kxgame.com";
       // String url = "wss://game.hiwan360.com";
//        String url = "ws://172.16.39.11:7010";
//        String url = "ws://localhost:7010";
//        ArrayList<String> msgs = new ArrayList<>();
//
//        msgs.add(paramsWaibao.enterMessage("888888888","Robot","888888888888888889"));
//        msgs.add(paramsWaibao.gobangMessage("READY",-1,-1));
//        msgs.add(paramsWaibao.gobangMessage("STEP",4,7));
//        msgs.add(paramsWaibao.gobangMessage("STEP",5,7));
//        msgs.add(paramsWaibao.gobangMessage("STEP",6,7));
//        msgs.add(paramsWaibao.gobangMessage("STEP",7,7));
//        msgs.add(paramsWaibao.gobangMessage("STEP",8,7));
//
//        ArrayList<String> msgs2 = new ArrayList<>();
//        msgs2.add(paramsWaibao.enterMessage("888888889","888888888","888888888888888889"));
//        msgs2.add(paramsWaibao.gobangMessage("READY",-1,-1));
//        msgs2.add(paramsWaibao.gobangMessage("STEP",4,8));
//        msgs2.add(paramsWaibao.gobangMessage("STEP",5,8));
//        msgs2.add(paramsWaibao.gobangMessage("STEP",6,8));
//        msgs2.add(paramsWaibao.gobangMessage("STEP",7,8));
//        msgs2.add(paramsWaibao.gobangMessage("STEP",8,8));

        //System.out.println(msg);
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String url = "ws://172.16.39.11:7010";
                String url = "ws://localhost:7010";
                ArrayList<String> msgs = new ArrayList<>();
                try {
                    msgs.add(paramsWaibao.enterMessage("wanjia", "Robot", "888888888888888889"));
                    msgs.add(paramsWaibao.gobangMessage("READY", -1, -1));
                    msgs.add(paramsWaibao.gobangMessage("STEP", 4, 7));
                    msgs.add(paramsWaibao.gobangMessage("STEP", 5, 7));
                    msgs.add(paramsWaibao.gobangMessage("STEP", 6, 7));
                    msgs.add(paramsWaibao.gobangMessage("STEP", 7, 7));
                    msgs.add(paramsWaibao.gobangMessage("STEP", 8, 7));

                    ArrayList<String> msgs2 = new ArrayList<>();
                    msgs2.add(paramsWaibao.enterMessage("Robot", "wanjia", "888888888888888889"));
                    msgs2.add(paramsWaibao.gobangMessage("READY", -1, -1));
                    msgs2.add(paramsWaibao.gobangMessage("STEP", 4, 8));
                    msgs2.add(paramsWaibao.gobangMessage("STEP", 5, 8));
                    msgs2.add(paramsWaibao.gobangMessage("STEP", 6, 8));
                    msgs2.add(paramsWaibao.gobangMessage("STEP", 7, 8));
                    msgs2.add(paramsWaibao.gobangMessage("STEP", 8, 8));
                    processRequest(url,msgs,msgs2);

                }catch (Exception e){

                }
            }
        }, "ttttttttttttttttttttttttttt1").start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String url = "ws://172.16.39.11:7010";
//                ArrayList<String> msgs = new ArrayList<>();
//                try {
//                    msgs.add(paramsWaibao.enterMessage("888888888", "888888889", "888888888888888889"));
//                    msgs.add(paramsWaibao.gobangMessage("READY", -1, -1));
//                    msgs.add(paramsWaibao.gobangMessage("STEP", 4, 7));
//                    msgs.add(paramsWaibao.gobangMessage("STEP", 5, 7));
//                    msgs.add(paramsWaibao.gobangMessage("STEP", 6, 7));
//                    msgs.add(paramsWaibao.gobangMessage("STEP", 7, 7));
//                    msgs.add(paramsWaibao.gobangMessage("STEP", 8, 7));
//
//                    ArrayList<String> msgs2 = new ArrayList<>();
//                    msgs2.add(paramsWaibao.enterMessage("888888889", "888888888", "888888888888888889"));
//                    msgs2.add(paramsWaibao.gobangMessage("READY", -1, -1));
//                    msgs2.add(paramsWaibao.gobangMessage("STEP", 4, 8));
//                    msgs2.add(paramsWaibao.gobangMessage("STEP", 5, 8));
//                    msgs2.add(paramsWaibao.gobangMessage("STEP", 6, 8));
//                    msgs2.add(paramsWaibao.gobangMessage("STEP", 7, 8));
//                    msgs2.add(paramsWaibao.gobangMessage("STEP", 8, 8));
//                    processRequest(url,msgs,msgs2);
//
//                }catch (Exception e){
//
//                }
//            }
//        }, "tttttttttttttttttttttt2").start();
//       processRequest(url,msgs,msgs2);
//        processRequest(url,msgs,msgs2);

    }
    
    public static void processRequest(String url, ArrayList<String> msgs,ArrayList<String> msgs2) throws Exception {
        URI uri = new URI(url);
        EventLoopGroup group = new NioEventLoopGroup();
        EventLoopGroup group2 = new NioEventLoopGroup();
        try {
            final WebSocketClientHandler handler =
                    new WebSocketClientHandler(
                            WebSocketClientHandshakerFactory.newHandshaker(
                                    uri, WebSocketVersion.V13, null, false, new DefaultHttpHeaders()));

            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .handler(new ChannelInitializer<Channel>() {
                 @Override
                 protected void initChannel(Channel ch) {
                     ChannelPipeline p = ch.pipeline();
                     p.addLast(
                             new HttpClientCodec(),
                             new HttpObjectAggregator(8192),
                             handler);
                 }

             }
             );
            final WebSocketClientHandler handler2 =
                    new WebSocketClientHandler(
                            WebSocketClientHandshakerFactory.newHandshaker(
                                    uri, WebSocketVersion.V13, null, false, new DefaultHttpHeaders()));
            Bootstrap c = new Bootstrap();
            c.group(group2)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<Channel>() {
                                 @Override
                                 protected void initChannel(Channel ch) {
                                     ChannelPipeline p = ch.pipeline();
                                     p.addLast(
                                             new HttpClientCodec(),
                                             new HttpObjectAggregator(8192),
                                             handler2);
                                 }

                             }


                    );
            ChannelFuture channelFuture = b.connect(uri.getHost(), uri.getPort()).sync();
            Channel ch = channelFuture.channel();

            //ch.closeFuture();
            // 异步创建连接

            System.out.println("client ip, port: " + uri.getHost()+" "+uri.getPort());
            handler.handshakeFuture().sync();
            ChannelFuture channelFuture2 = c.connect(uri.getHost(), uri.getPort()).sync();
            Channel ch2= channelFuture2.channel();
            System.out.println("client2 ip, port: " + uri.getHost()+" "+uri.getPort());
            handler2.handshakeFuture().sync();
            //ch2.closeFuture();

            String msg="";
            String msg2 = "";
            // 如果是单个客户端多次请求，就用while 循环.
            for(int i = 0;i<msgs.size();i++){
                msg = msgs.get(i);
                msg2 = msgs2.get(i);
                WebSocketFrame frame = new TextWebSocketFrame(msg);
                ch.writeAndFlush(frame);
                System.out.println("request message: " + msg);
                Thread.sleep(1000);
                WebSocketFrame frame2 = new TextWebSocketFrame(msg2);
                ch2.writeAndFlush(frame2);
                System.out.println("request message2: " + msg2);
                Thread.sleep(1000);
            }

            /*WebSocketFrame frame = new TextWebSocketFrame(msg);
            ch.writeAndFlush(frame);*/
            //sleep(50);


        } finally {
            group.shutdownGracefully().sync();
            //group2.shutdownGracefully().sync();


        }
    }    
    
}
