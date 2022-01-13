package com.github.unidbg.thread;

import com.github.unidbg.Emulator;

/**
 * <p>可执行任务</p>
 *
 * @author pu_chaobo@163.com
 * @date 2022-01-05 18:37:09
 */
public interface RunnableTask {
    /**
     * <p>是否能被调度</p>
     *
     * @return 能被调度true，不能被调度false
     * @author pu_chaobo@163.com
     * @date 2022-01-05 18:37:32
     */
    boolean canDispatch();

    /**
     * <p>保存任务上下文</p>
     *
     * @param emulator 模拟器
     * @author pu_chaobo@163.com
     * @date 2022-01-05 18:38:56
     */
    void saveContext(Emulator<?> emulator);

    /**
     * <p>任务上下文是否被保存</p>
     *
     * @return 已保存true，未保存false
     * @author pu_chaobo@163.com
     * @date 2022-01-05 18:41:44
     */
    boolean isContextSaved();

    /**
     * <p>恢复任务上下文</p>
     *
     * @param emulator 模拟器
     * @author pu_chaobo@163.com
     * @date 2022-01-05 18:38:56
     */
    void restoreContext(Emulator<?> emulator);

    /**
     * <p>销毁任务</p>
     *
     * @param emulator 模拟器
     * @author pu_chaobo@163.com
     * @date 2022-01-05 18:38:56
     */
    void destroy(Emulator<?> emulator);

    /**
     * <p>description</p>
     *
     * @author pu_chaobo@163.com
     * @date 2022-01-05 18:43:23
     */
    void setWaiter(Waiter waiter);

    Waiter getWaiter();

    void setResult(Emulator<?> emulator, Number ret);

    void setDestroyListener(DestroyListener listener);

    void popContext(Emulator<?> emulator);
}
