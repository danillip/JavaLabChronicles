package com.example;

/**
 * Вспомогательный класс для примера
 * Здесь обходим приватность методов через Reflection,
 * но для простоты и наглядности делают публичными (но не я :D )
 * если нужно тестировать напрямую.
 */
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtils {

    public static long[] invokePrivateAddPerformance(int iterations) {
        return invokeMethod("testAddPerformance", iterations);
    }

    public static long[] invokePrivateRemovePerformance(int iterations) {
        return invokeMethod("testRemovePerformance", iterations);
    }

    public static long[] invokePrivateGetPerformance(int iterations) {
        return invokeMethod("testGetPerformance", iterations);
    }

    @SuppressWarnings("unchecked")
    private static long[] invokeMethod(String methodName, int iterations) {
        try {
            Method method = CollectionWork.class.getDeclaredMethod(methodName, int.class);
            method.setAccessible(true);
            return (long[]) method.invoke(null, iterations);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
