//package com.yhl.nio.aio;
//
//import java.nio.channels.AsynchronousSocketChannel;
//import java.nio.channels.CompletionHandler;
//final class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {
//
//
//
//        @Override
//        public void completed(AsynchronousSocketChannel socketChannel,
//                Object attachment) {
//            try {
//
//                configureChannel(socketChannel);
//                AioSessionConfig sessionConfig = buildSessionConfig(socketChannel);
//                Session session = new AioTCPSession(sessionConfig,
//                        AioTCPController.this.configuration
//                                .getSessionReadBufferSize(),
//                        AioTCPController.this.sessionTimeout);
//                session.start();
//                registerSession(session);
//            } catch (Exception e) {
//                e.printStackTrace();
//                logger.error("Accept error", e);
//                notifyException(e);
//            } finally {
//                pendingAccept();
//            }
//        }
//
//        @Override
//        public void failed(Throwable exc, Object attachment) {
//            try {
//                notifyException(exc);
//            } finally {
//                pendingAccept();
//            }
//        }
//    }