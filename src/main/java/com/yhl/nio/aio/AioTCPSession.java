//package com.yhl.nio.aio;
//
//public class AioTCPSession {
//    protected void start0() {
//        pendingRead();
//    }
//
//    protected final void pendingRead() {
//        if (!isClosed() && this.asynchronousSocketChannel.isOpen()) {
//            if (!this.readBuffer.hasRemaining()) {
//                this.readBuffer = ByteBufferUtils
//                        .increaseBufferCapatity(this.readBuffer);
//            }
//            this.readFuture = this.asynchronousSocketChannel.read(
//                    this.readBuffer, this, this.readCompletionHandler);
//        } else {
//            throw new IllegalStateException(
//                    "Session Or Channel has been closed");
//        }
//    }
//
//}