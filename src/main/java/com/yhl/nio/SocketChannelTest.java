package com.yhl.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-4-19
 * Time: 下午5:47
 * To change this template use File | Settings | File Templates.
 */
public class SocketChannelTest {

    static void test(){
        try {
            Selector selector = Selector.open();
            SocketChannel channel = SocketChannel.open();
            channel.configureBlocking(false);
            channel.connect(new InetSocketAddress("localhost", 9991));

            channel.register(selector,SelectionKey.OP_CONNECT);


            while (true){
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if(key.isAcceptable()) {
                        System.out.println("is acceptable :"+key);
                    } else if (key.isConnectable()) {
                        System.out.println("is connectable :"+key);
                        // 如果正在连接，则完成连接
                        SocketChannel c = (SocketChannel)key.channel();
                        if(c.isConnectionPending()){
                            c.finishConnect();
                        }
                        // 设置成非阻塞
                        c.configureBlocking(false);
                        //在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
                        channel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        System.out.println("is readable :"+key);
                        SocketChannel c = (SocketChannel)key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        channel.read(buffer);
                        byte[] data = buffer.array();
                        String msg = new String(data).trim();
                        System.out.println(msg);
                        c.write(ByteBuffer.wrap("dodo".getBytes()));
                    } else if (key.isWritable()) {
                        System.out.println("is writable :"+key);
                        System.out.println("is readable :"+key);
                        SocketChannel c = (SocketChannel)key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        channel.read(buffer);
                        byte[] data = buffer.array();
                        String msg = new String(data).trim();
                        System.out.println(msg);
                        c.write(ByteBuffer.wrap("dodo".getBytes()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        test();
    }
}
