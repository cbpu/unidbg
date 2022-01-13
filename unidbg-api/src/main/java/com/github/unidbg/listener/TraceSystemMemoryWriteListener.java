package com.github.unidbg.listener;

import com.github.unidbg.Emulator;

/**
 * <p>系统内存写入监听器</p>
 *
 * @author pu_chaobo@163.com
 * @date 2022-01-04 14:39:59
 */
public interface TraceSystemMemoryWriteListener {

    void onWrite(Emulator<?> emulator, long address, byte[] buf);

}
