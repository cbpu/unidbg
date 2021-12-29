package com.github.unidbg.spi;

import capstone.api.Instruction;
import com.github.unidbg.arm.InstructionVisitor;

import java.io.PrintStream;

/**
 * disassembler
 * Created by zhkl0228 on 2017/5/9.
 *
 * <p>ARM反汇编器</p>
 *
 * @author pu_chaobo@163.com
 * @date 2021-12-29 16:29:30
 */
public interface ArmDisassembler {

    /**
     * <p>打印汇编信息</p>
     *
     * @param out     打印流
     * @param address 要读取的内存区域的起始地址(Capstone反汇编器要反汇编的第一个机器码字节的地址)
     * @param size    CPU模拟器Unicorn或Unicorn2，虚拟化引擎KVM，Hypervisor要检索内存的字节数
     * @param visitor Capstone反汇编器的成功反汇编指令{@link Instruction}信息组装器
     * @return Capstone反汇编器成功反汇编指令的数组，如果没有可以反汇编的指令，则为空
     * @author pu_chaobo@163.com
     * @date 2021-12-29 17:36:19
     */
    Instruction[] printAssemble(PrintStream out, long address, int size, InstructionVisitor visitor);

    /**
     * <p>从假设位于@address的@code反汇编最多@count条指令，遇到第一个中断指令时停止</p>
     *
     * @param address 要读取的内存区域的起始地址(Capstone反汇编器要反汇编的第一个机器码字节的地址)
     * @param size    CPU模拟器Unicorn或Unicorn2，虚拟化引擎KVM，Hypervisor要检索内存的字节数
     * @param count   要反汇编的最大指令数，0 表示没有最大值
     * @return Capstone反汇编器成功反汇编指令的数组，如果没有可以反汇编的指令，则为空
     * @author pu_chaobo@163.com
     * @date 2021-12-29 17:46:31
     */
    Instruction[] disassemble(long address, int size, long count);

    /**
     * <p>从假设位于@address的@code反汇编最多@count条指令，遇到第一个中断指令时停止</p>
     *
     * @param address 要读取的内存区域的起始地址(Capstone反汇编器要反汇编的第一个机器码字节的地址)
     * @param code    未反编译的机器码字节数组
     * @param thumb   是否使用THUMB模式
     * @param count   要反汇编的最大指令数，0 表示没有最大值
     * @return Capstone反汇编器成功反汇编指令的数组，如果没有可以反汇编的指令，则为空
     * @author pu_chaobo@163.com
     * @date 2021-12-29 17:46:31
     */
    Instruction[] disassemble(long address, byte[] code, boolean thumb, long count);
}
