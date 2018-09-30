package com.asiabill.thread;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);

    private static String ID = "10000000000001";
    private static String IP = "192.168.80.123";

    public static void main(String[] args) {
        AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("application04.xml");
        TreadContext context = appContext.getBean(TreadContext.class);
        MDC.put(Constant.TRANC_ID, ID);
        MDC.put(Constant.LOCAL_IP, "192.168.80.123");
        AbstractRunnable thread1 = new MyThread(ID, IP);
        AbstractRunnable thread2 = new MyThread(ID, IP);
        List<AbstractRunnable> threads = new ArrayList<AbstractRunnable>();
        threads.add(thread1);
        threads.add(thread2);
        context.source(threads).open();
        logger.info("thread name:{}  ,trace_id:{}  ,local_ip:{}",new String[] {Thread.currentThread().getName(), MDC.get(Constant.TRANC_ID), MDC.get(Constant.LOCAL_IP)});
        appContext.registerShutdownHook();
    }
}
