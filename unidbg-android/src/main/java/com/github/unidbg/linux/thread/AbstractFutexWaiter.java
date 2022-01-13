package com.github.unidbg.linux.thread;

import com.github.unidbg.Emulator;
import com.sun.jna.Pointer;
import unicorn.Arm64Const;
import unicorn.ArmConst;

/**
 * <p>Futex进程等待器</p>
 *
 * @author pu_chaobo@163.com
 * @date 2022-01-06 10:59:21
 * @see <a href="https://zh.wikipedia.org/wiki/Futex">Futex</a>
 */
public abstract class AbstractFutexWaiter extends AbstractAndroidWaiter {

    private final Pointer uaddr;
    private final int val;

    public AbstractFutexWaiter(Pointer uaddr, int val) {
        this.uaddr = uaddr;
        this.val = val;
    }

    @Override
    public boolean canDispatch() {
        if (wokenUp) {
            return true;
        }
        int old = uaddr.getInt(0);
        return old != val;
    }

    @Override
    public final void onContinueRun(Emulator<?> emulator) {
        super.onContinueRun(emulator);

        if (wokenUp) {
            emulator.getBackend().reg_write(emulator.is32Bit() ? ArmConst.UC_ARM_REG_R0 : Arm64Const.UC_ARM64_REG_X0, 0);
        } else {
            onContinueRunInternal(emulator);
        }
    }

    protected void onContinueRunInternal(Emulator<?> emulator) {
    }

    protected boolean wokenUp;

    public boolean wakeUp(Pointer uaddr) {
        if (this.uaddr.equals(uaddr)) {
            this.wokenUp = true;
            return true;
        } else {
            return false;
        }
    }

}
