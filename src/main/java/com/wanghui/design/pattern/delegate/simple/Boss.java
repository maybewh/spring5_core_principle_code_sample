package com.wanghui.design.pattern.delegate.simple;

/**
 * Boss下达命令
 */
public class Boss {

    public void command(String command, Leader leader) {
        leader.doing(command);
    }
}
