package com.xiao5.twmall.apiadmin.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadTest {
    public static ExecutorService exectorService = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture future01 = CompletableFuture.supplyAsync(()->{
//            System.out.println("线程1启动");
//            Integer num = 10 /0;
//            return num;
//        }, exectorService).handle((res01, exception01)->{
//            if(exception01 != null){
//                return "error";
//            }else{
//                return res01;
//            }
//        });
//
//        System.out.println(future01.get());

//        CompletableFuture future02 = CompletableFuture.supplyAsync(()->{
//            System.out.println("线程2启动");
//            return "线程2返回的结果";
//        }, exectorService);
//
//        CompletableFuture future03 = CompletableFuture.supplyAsync(()->{
//            System.out.println("线程3启动");
//            return "线程3返回的结果";
//        }, exectorService);
//
//        CompletableFuture finished = CompletableFuture.allOf(future01, future02, future03);
//        finished.get();
//
//        System.out.println(future01.get());
//        System.out.println(future02.get());
//        System.out.println(future03.get());

//        CompletableFuture future02 = future01.thenApplyAsync((res01)->{
//            System.out.println("线程2拿到线程1的结果："+res01);
//            return "线程2返回的结果";
//        }, exectorService);
//
//        System.out.println(future02.get());

//        CompletableFuture future02 = CompletableFuture.supplyAsync(()->{
//            System.out.println("线程2启动");
//            return "线程2返回的结果";
//        },exectorService);
//
//        CompletableFuture future03 = future01.thenCombineAsync(future02,(res01, res02)->{
//            System.out.println("线程3启动");
//            System.out.println("线程3拿到线程1、2的结果："+res01+"----"+res02);
//            return "线程3返回的结果";
//        },exectorService);
//        System.out.println(future03.get());
    }
}
