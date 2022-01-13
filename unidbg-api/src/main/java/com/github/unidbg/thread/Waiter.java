package com.github.unidbg.thread;

import com.github.unidbg.Emulator;

/**
 * <p>进程等待器</p>
 *
 * @author pu_chaobo@163.com
 * @date 2022-01-06 10:41:33
 */
public interface Waiter {
    /**
     * <p>是否能被调度</p>
     *
     * @return 能被调度true，不能被调度false
     * @author pu_chaobo@163.com
     * @date 2022-01-06 10:42:12
     */
    boolean canDispatch();

    /**
     * <p>在继续运行发生时</p>
     *
     * @param emulator 模拟器
     * @author pu_chaobo@163.com
     * @date 2022-01-06 10:42:35
     */
    void onContinueRun(Emulator<?> emulator);
}
