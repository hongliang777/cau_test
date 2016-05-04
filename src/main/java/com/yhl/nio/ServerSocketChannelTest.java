package com.yhl.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-4-19
 * Time: 下午5:47
 写就绪相对有一点特殊，一般来说，你不应该注册写事件。写操作的就绪条件为底层缓冲区有空闲空间，而写缓冲区绝大部分时间都是有空闲空间的，所以当你注册写事件后，写操作一直是就绪的，选择处理线程全占用整个CPU资源。所以，只有当你确实有数据要写时再注册写操作，并在写完以后马上取消注册。

 当有数据在写时，将数据写到缓冲区中，并注册写事件。
 */
public class ServerSocketChannelTest {

    static AtomicInteger count = new AtomicInteger(0);

    static void test(){
        try {
            // server
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(9991));

            // selector
            Selector selector = Selector.open();
            serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

            while(true){

                selector.select();
                Iterator keyIterator = selector.selectedKeys().iterator();
                while(keyIterator.hasNext()) {
                    SelectionKey key = (SelectionKey)keyIterator.next();
                    keyIterator.remove();
                    if(key.isAcceptable()) {
                        System.out.println("is acceptable :"+key);
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel channel = server.accept();
                        if(channel != null){
                            channel.configureBlocking(false);
                            channel.write(ByteBuffer.wrap(new String("连接成功").getBytes()));
                            channel.register(selector, SelectionKey.OP_READ);
                        }
                    }  else if (key.isReadable()) {
                        System.out.println("is readable :"+key);
                        String newData = "New String to write to client..." + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\r\n";
                        System.out.println("server : "+newData);

                        SocketChannel c = (SocketChannel)key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        c.read(buffer);
                        byte[] data = buffer.array();
                        String msg = new String(data).trim();
                        System.out.println("服务端收到信息："+msg);
                        c.write(ByteBuffer.wrap(newData.getBytes()));
                    } else if(key.isWritable()){
                        System.out.println("is readable :"+key);
                        String newData = "New String to write to client..." + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\r\n";
                        System.out.println("server : "+newData);

                        SocketChannel c = (SocketChannel)key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        c.read(buffer);
                        byte[] data = buffer.array();
                        String msg = new String(data).trim();
                        System.out.println("服务端收到信息："+msg);
                        c.write(ByteBuffer.wrap(newData.getBytes()));
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test();
    }
}
