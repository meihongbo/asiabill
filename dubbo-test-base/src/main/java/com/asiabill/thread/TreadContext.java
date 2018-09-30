package com.asiabill.thread;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class TreadContext {

	@Autowired
    private ThreadPoolTaskExecutor executor;

    private static TreadContext context;

    private List<AbstractRunnable> threads = new ArrayList<AbstractRunnable>();

    @PostConstruct
    private void init() {
        context = this;
    }

    /**
     * 关闭线程池
     */
    public void close() {
        executor.shutdown();
    }

    /**
     * 执行所有线程池
     */
    public void open() {
        for (Runnable thread : this.threads) {
            executor.execute(thread);
        }
    }

    /**
     * 添加多个线程任务
     *
     * @param threads
     * @return
     */
    public TreadContext source(List<AbstractRunnable> threads) {
        this.threads.addAll(threads);
        return context;
    }

    /**
     * 添加单个线程任务
     *
     * @param thread
     */
    public void source(AbstractRunnable thread) {
        this.threads.add(thread);
    }

    /**
     * 获取线程池管理对象
     *
     * @return
     */
    public static TreadContext getContext() {
        return context;
    }
}
