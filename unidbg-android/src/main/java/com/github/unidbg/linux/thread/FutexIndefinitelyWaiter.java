package com.github.unidbg.linux.thread;

import com.github.unidbg.Emulator;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.unix.UnixEmulator;
import com.sun.jna.Pointer;
import unicorn.Arm64Const;
import unicorn.ArmConst;

public class FutexIndefinitelyWaiter extends AbstractFutexWaiter {

    public FutexIndefinitelyWaiter(Pointer uaddr, int val) {
        super(uaddr, val);
    }

    @Override
    protected void onContinueRunInternal(Emulator<?> emulator) {
        super.onContinueRunInternal(emulator);

        Memory memory = emulator.getMemory();
        memory.setErrno(UnixEmulator.EAGAIN);
        emulator.getBackend().reg_write(emulator.is32Bit() ? ArmConst.UC_ARM_REG_R0 : Arm64Const.UC_ARM64_REG_X0, -1);
    }
}
