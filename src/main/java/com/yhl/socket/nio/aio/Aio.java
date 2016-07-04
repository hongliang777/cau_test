//package com.yhl.socket.nio.aio;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.net.SocketOption;
//import java.nio.channels.AsynchronousChannelGroup;
//import java.nio.channels.AsynchronousServerSocketChannel;
//import java.util.concurrent.Executors;
//
///**
// * Created with IntelliJ IDEA.
// * User: Administrator
// * Date: 16-4-21
// * Time: 下午4:28
// * To change this template use File | Settings | File Templates.
// */
//public class Aio {
//
//    AsynchronousChannelGroup asynchronousChannelGroup;
//    static final int initThreadSize = 4;
//
//    AsynchronousServerSocketChannel serverSocketChannel;
//
//    Aio(){
//        try {
//            this.asynchronousChannelGroup = AsynchronousChannelGroup.
//                    withCachedThreadPool(Executors.newCachedThreadPool(), this.initThreadSize);
//
//            this.serverSocketChannel = AsynchronousServerSocketChannel
//                    .open(this.asynchronousChannelGroup);
//
//            this.serverSocketChannel
//                    .bind(new InetSocketAddress("localhost",8080), 100);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void pendingAccept() {
//        if (this.started && this.serverSocketChannel.isOpen()) {
//            this.acceptFuture = this.serverSocketChannel.accept(null,
//                    new AcceptCompletionHandler());
//
//        } else {
//            throw new IllegalStateException("Controller has been closed");
//        }
//    }
//
//}
