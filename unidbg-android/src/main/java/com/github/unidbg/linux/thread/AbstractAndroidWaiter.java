package com.github.unidbg.linux.thread;

import com.github.unidbg.Emulator;
import com.github.unidbg.thread.Waiter;

/**
 * <p>Android进程等待器</p>
 *
 * @author pu_chaobo@163.com
 * @date 2022-01-06 10:57:11
 */
public abstract class AbstractAndroidWaiter implements Waiter {

    @Override
    public void onContinueRun(Emulator<?> emulator) {
    }
}
