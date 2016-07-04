package com.yhl.socket.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-4-19
 * Time: 下午2:42
 * To change this template use File | Settings | File Templates.
 */
public class FileChannelTest {

    public static void test(){
        try {
            String path = ClassLoader.getSystemResource("log4j.properties").getFile();
            path = path.substring(1);

            RandomAccessFile aFile = new RandomAccessFile(path, "rw");
            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {

                System.out.println("Read " + bytesRead);
                buf.flip();

                while(buf.hasRemaining()){
                    System.out.print((char) buf.get());
                }

                buf.clear();
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void test1(){

        try {
            String path = ClassLoader.getSystemResource(".").getFile();
            path = path.substring(1)+"channelWriteTest";
            File f = new File(path);
//            if(!f.exists()){
//                f.createNewFile();
//            }

            RandomAccessFile aFile = new RandomAccessFile(path, "rw");
            FileChannel inChannel = aFile.getChannel();


            String newData = "New String to write to file..." + System.currentTimeMillis();

            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());

            buf.flip();

            while(buf.hasRemaining()) {
                inChannel.write(buf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        test1();
    }
}
