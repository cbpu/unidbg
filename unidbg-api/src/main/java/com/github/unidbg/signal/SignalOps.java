package com.github.unidbg.signal;

/**
 * <p>description</p>
 *
 * @author pu_chaobo@163.com
 * @date 2022-01-05 11:30:51
 */
public interface SignalOps {

    SigSet getSigMaskSet();

    void setSigMaskSet(SigSet sigMaskSet);

    SigSet getSigPendingSet();

    void setSigPendingSet(SigSet sigPendingSet);

}
