package com.imooc.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author xujunchen
 * @date 2023/5/15 21:05
 * @describe todo
 */
public class DeepBeanUtils extends BeanUtils {
    private DeepBeanUtils() {
    }

    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        List list = new ArrayList(sources.size());

        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);
        }
        return list;
    }
}
