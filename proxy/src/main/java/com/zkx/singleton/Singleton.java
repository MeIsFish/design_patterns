package com.zkx.singleton;

/**
 * 单例模式最完美的写法，不服来辩
 * 1. 避免多线程带来的安全问题
 * 2.节约资源，用的时候再进行加载
 */
public class Singleton {

    private static class LazyLoad {
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton() {
    }

    public static Singleton getInstance() {
        return LazyLoad.INSTANCE;
    }

}
