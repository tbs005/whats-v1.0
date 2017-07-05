package com.sunder.xie.whats.sso.context;

/**
 * Created by xieshengrong on 2017/5/24.
 */

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 线程范围内的上下文容器
 * @author Zhaogq
 */
public class ThreadLocalContext {
    private static final ThreadLocal<ThreadLocalContext> context = new ThreadLocal<ThreadLocalContext>();
    private Map<String, Object> pairs = new HashMap<String, Object>();

    /**
     * 获取Context实例
     * @return Context Instance
     */
    public static final ThreadLocalContext getContext() {
        ThreadLocalContext threadLoacalContext = context.get();
        if (threadLoacalContext == null) {
            threadLoacalContext = new ThreadLocalContext();
            context.set(threadLoacalContext);
        }
        return context.get();
    }

    public Object get(String name) {
        return pairs.get(name);
    }

    public void set(String name, Object value) {
        pairs.put(name, value);
    }

    public void remove(String name) {
        pairs.remove(name);
    }

    /**
     * 批量设置键值对到Context中
     * @param kvPairs
     */
    public void setAll(Map<String, Object> kvPairs) {
        for (Map.Entry<String, Object> entry : kvPairs.entrySet()) {
            set(entry.getKey(), entry.getValue());
        }
    }

    public Set<String> keySet() {
        return pairs.keySet();
    }

    public Collection<Object> values() {
        return pairs.values();
    }

    public void clear() {
        pairs.clear();
    }

    @Override
    public String toString() {
        return "ThreadLocal<" + this.hashCode() + ">:" + pairs.toString();
    }
}

