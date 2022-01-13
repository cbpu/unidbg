package com.github.unidbg.thread;

import com.github.unidbg.signal.SignalOps;
import com.github.unidbg.signal.SignalTask;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>description</p>
 *
 * @author pu_chaobo@163.com
 * @date 2022-01-05 11:30:20
 */
public interface ThreadDispatcher extends SignalOps {

    void addThread(ThreadTask task);

    List<Task> getTaskList();

    Number runMainForResult(MainTask main);

    void runThreads(long timeout, TimeUnit unit);

    int getTaskCount();

    boolean sendSignal(int tid, SignalTask signalTask);

    RunnableTask getRunningTask();

}
