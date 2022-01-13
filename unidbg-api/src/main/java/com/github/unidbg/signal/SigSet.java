package com.github.unidbg.signal;

/**
 * <p><a href="https://www.gnu.org/software/libc/manual/html_node/Signal-Sets.html">信号集</a></p>
 *
 * @author pu_chaobo@163.com
 * @date 2022-01-05 12:18:46
 */
public interface SigSet extends Iterable<Integer> {
    /**
     * <p>获取进程信号掩码（当前阻塞的信号集合）</p>
     *
     * @return <a href="https://www.gnu.org/software/libc/manual/html_node/Process-Signal-Mask.html">进程信号掩码</a>
     * @author pu_chaobo@163.com
     * @date 2022-01-05 12:20:10
     */
    long getMask();

    /**
     * <p>设置进程信号掩码（当前阻塞的信号集合），该进程新的信号掩码将被输入的信号集代替</p>
     *
     * @param mask 进程信号掩码 <a href="https://www.gnu.org/software/libc/manual/html_node/Process-Signal-Mask.html">进程信号掩码</a>
     * @author pu_chaobo@163.com
     * @date 2022-01-05 12:24:31
     */
    void setMask(long mask);

    /**
     * <p>信号阻塞，将其添加到现有掩码中。新掩码是现有掩码和输入掩码的或</p>
     *
     * @param mask 进程信号掩码 <a href="https://www.gnu.org/software/libc/manual/html_node/Process-Signal-Mask.html">进程信号掩码</a>
     * @author pu_chaobo@163.com
     * @date 2022-01-05 12:27:30
     */
    void blockSigSet(long mask);

    /**
     * <p>取消信号阻塞，从现有掩码中删除。新掩码是现有掩码和输入掩码取反的与</p>
     *
     * @param mask 进程信号掩码 <a href="https://www.gnu.org/software/libc/manual/html_node/Process-Signal-Mask.html">进程信号掩码</a>
     * @author pu_chaobo@163.com
     * @date 2022-01-05 12:28:27
     */
    void unblockSigSet(long mask);

    /**
     * <p>测试信号signum是否是信号集合的成员</p>
     *
     * @param signum 要操作的signal信号
     * @return 如果信号位于集合中，则返回true，如果不是，则为false
     * @author pu_chaobo@163.com
     * @date 2022-01-05 14:34:48
     */
    boolean containsSigNumber(int signum);

    /**
     * <p>从阻塞信号集合中删除信号</p>
     *
     * @param signum 要操作的signal信号
     * @author pu_chaobo@163.com
     * @date 2022-01-05 14:40:47
     */
    void removeSigNumber(int signum);

    /**
     * <p>将信号signum添加到阻塞信号集合</p>
     *
     * @param signum 要操作的signal信号
     * @author pu_chaobo@163.com
     * @date 2022-01-05 14:42:07
     */
    void addSigNumber(int signum);
}
