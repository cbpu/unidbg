package com.github.unidbg;

import com.github.unidbg.arm.backend.Backend;
import com.github.unidbg.arm.context.RegisterContext;
import com.github.unidbg.debugger.Debugger;
import com.github.unidbg.debugger.DebuggerType;
import com.github.unidbg.file.FileSystem;
import com.github.unidbg.file.NewFileIO;
import com.github.unidbg.listener.TraceCodeListener;
import com.github.unidbg.listener.TraceReadListener;
import com.github.unidbg.listener.TraceSystemMemoryWriteListener;
import com.github.unidbg.listener.TraceWriteListener;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.memory.SvcMemory;
import com.github.unidbg.serialize.Serializable;
import com.github.unidbg.spi.*;
import com.github.unidbg.thread.ThreadDispatcher;
import com.github.unidbg.unwind.Unwinder;

import java.io.Closeable;
import java.io.File;
import java.net.URL;

/**
 * cpu emulator
 * Created by zhkl0228 on 2017/5/2.
 *
 * <p>模拟器接口</p>
 *
 * @param <T>
 * @author pu_chaobo@163.com
 * @date 2021-12-30 12:24:06
 */
public interface Emulator<T extends NewFileIO> extends Closeable, ArmDisassembler, ValuePair, Serializable {
    /**
     * <p>获取指针大小，单位字节</p>
     *
     * @return {@code int} 字节数
     * @author pu_chaobo@163.com
     * @date 2021-12-30 18:02:03
     */
    int getPointerSize();

    /**
     * <p>是否是64位ARM模拟器</p>
     *
     * @return {@code ture} 64位ARM
     * @author pu_chaobo@163.com
     * @date 2021-12-30 18:03:09
     */
    boolean is64Bit();

    /**
     * <p>是否是32位ARM模拟器</p>
     *
     * @return {@code ture} 32位ARM
     * @author pu_chaobo@163.com
     * @date 2021-12-30 18:03:09
     */
    boolean is32Bit();

    /**
     * <p>获取内存页面对齐大小，单位字节</p>
     *
     * @return {@code int} 字节数
     * @author pu_chaobo@163.com
     * @date 2021-12-30 18:07:32
     */
    int getPageAlign();

    /**
     * <p>跟踪内存读取操作</p>
     *
     * @return {@link TraceMemoryHook} 内存追踪hook实例
     * @author pu_chaobo@163.com
     * @date 2021-12-30 18:10:08
     */
    TraceHook traceRead();

    /**
     * <p>跟踪内存读取操作</p>
     *
     * @param begin 起始内存地址
     * @param end   内存结束地址
     * @return {@link TraceMemoryHook} 内存追踪hook实例
     * @author pu_chaobo@163.com
     * @date 2021-12-30 18:10:08
     */
    TraceHook traceRead(long begin, long end);

    /**
     * <p>跟踪内存读取操作</p>
     *
     * @param begin    起始内存地址
     * @param end      内存结束地址
     * @param listener 内存读取监听器
     * @return {@link TraceMemoryHook} 内存追踪hook实例
     * @author pu_chaobo@163.com
     * @date 2021-12-30 18:10:08
     */
    TraceHook traceRead(long begin, long end, TraceReadListener listener);

    /**
     * <p>跟踪内存写入操作</p>
     *
     * @return {@link TraceMemoryHook} 内存追踪hook实例
     * @author pu_chaobo@163.com
     * @date 2021-12-30 18:10:08
     */
    TraceHook traceWrite();

    /**
     * <p>跟踪内存写入操作</p>
     *
     * @param begin 起始内存地址
     * @param end   内存结束地址
     * @return {@link TraceMemoryHook} 内存追踪hook实例
     * @author pu_chaobo@163.com
     * @date 2021-12-30 18:10:08
     */
    TraceHook traceWrite(long begin, long end);

    /**
     * <p>跟踪内存写入操作</p>
     *
     * @param begin    起始内存地址
     * @param end      内存结束地址
     * @param listener 内存写入监听器
     * @return {@link TraceMemoryHook} 内存追踪hook实例
     * @author pu_chaobo@163.com
     * @date 2021-12-30 18:10:08
     */
    TraceHook traceWrite(long begin, long end, TraceWriteListener listener);

    /**
     * <p>设置系统内存写入跟踪</p>
     *
     * @param begin    起始内存地址
     * @param end      内存结束地址
     * @param listener 系统内存写入监听器
     * @author pu_chaobo@163.com
     * @date 2022-01-04 14:38:15
     */
    void setTraceSystemMemoryWrite(long begin, long end, TraceSystemMemoryWriteListener listener);

    /**
     * <p>跟踪汇编指令</p>
     *
     * @return {@link AssemblyCodeDumper} 汇编指令Dump
     * @author pu_chaobo@163.com
     * @date 2022-01-05 10:49:08
     */
    TraceHook traceCode();

    /**
     * <p>跟踪汇编指令</p>
     *
     * @param begin 起始内存地址
     * @param end   内存结束地址
     * @return {@link AssemblyCodeDumper} 汇编指令Dump
     * @author pu_chaobo@163.com
     * @date 2022-01-05 10:49:08
     */
    TraceHook traceCode(long begin, long end);

    /**
     * <p>跟踪汇编指令</p>
     *
     * @param begin    起始内存地址
     * @param end      内存结束地址
     * @param listener 系统内存写入监听器
     * @return {@link AssemblyCodeDumper} 汇编指令Dump
     * @author pu_chaobo@163.com
     * @date 2022-01-05 10:49:08
     */
    TraceHook traceCode(long begin, long end, TraceCodeListener listener);

    Number eFunc(long begin, Number... arguments);

    Number eEntry(long begin, long sp);

    /**
     * emulate signal handler
     *
     * @param sig signal number
     * @return <code>true</code> means called handler function.
     */
    boolean emulateSignal(int sig);

    /**
     * 是否正在运行
     */
    boolean isRunning();

    /**
     * show all registers
     */
    void showRegs();

    /**
     * show registers
     */
    void showRegs(int... regs);

    Module loadLibrary(File libraryFile);

    Module loadLibrary(File libraryFile, boolean forceCallInit);

    Memory getMemory();

    Backend getBackend();

    int getPid();

    String getProcessName();

    Debugger attach();

    Debugger attach(DebuggerType type);

    FileSystem<T> getFileSystem();

    SvcMemory getSvcMemory();

    SyscallHandler<T> getSyscallHandler();

    Family getFamily();

    LibraryFile createURLibraryFile(URL url, String libName);

    Dlfcn getDlfcn();

    /**
     * @param timeout Duration to emulate the code (in microseconds). When this value is 0, we will emulate the code in infinite time, until the code is finished.
     */
    void setTimeout(long timeout);

    <V extends RegisterContext> V getContext();

    Unwinder getUnwinder();

    void pushContext(int off);

    int popContext();

    ThreadDispatcher getThreadDispatcher();

    long getReturnAddress();

}
