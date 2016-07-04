package com.yhl.socket.nio;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-4-20
 * Time: 下午7:06
 * To change this template use File | Settings | File Templates.
 */
public class Info {
    /**
     * 写就绪相对有一点特殊，一般来说，你不应该注册写事件。写操作的就绪条件为底层缓冲区有空闲空间，而写缓冲区绝大部分时间都是有空闲空间的，所以当你注册写事件后，写操作一直是就绪的，选择处理线程全占用整个CPU资源。所以，只有当你确实有数据要写时再注册写操作，并在写完以后马上取消注册。

     当有数据在写时，将数据写到缓冲区中，并注册写事件。
     public void write(byte[] data) throws IOException {
     writeBuffer.put(data);
     key.interestOps(SelectionKey.OP_WRITE);
     }

     注册写事件后，写操作就绪，这时将之前写入缓冲区的数据写入通道，并取消注册。
     channel.write(writeBuffer);
     key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);

     */
}
