package org.lwl.fastfoot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * Use the thread pool and java8 function interface features
 * to split tasks and run fast.
 *
 * @author liwenlong
 * @date 2020-05-31
 * @since JDK1.8
 */
public class FastFoot {
    /**
     * Run Task by by default 20 threads，and wait all task completed to get all result。
     *
     * @param taskList task list
     * @param function function for run every task
     * @param <T>      task class type
     * @param <R>      result class type
     * @return task and result map
     */
    public static <T, R> Map<T, R> runTasks(List<T> taskList, Function<T, R> function) {
        return runTasks(taskList, function, 20);
    }

    /**
     * Run Task by multi thread，and wait all task completed to get all result。
     *
     * @param taskList task list
     * @param function function for run every task
     * @param feet     setup thread pool number when running the tasks
     * @param <T>      task class type
     * @param <R>      result class type
     * @return task and result map
     */
    public static <T, R> Map<T, R> runTasks(List<T> taskList, Function<T, R> function, int feet) {
        return runTasks(taskList, function, Executors.newFixedThreadPool(feet));
    }
    /**
     * @param taskList        task list
     * @param function        function for run every task
     * @param executorService thread pool
     * @param <T>             task class type
     * @param <R>             result class type
     * @return task and result map
     */
    public static <T, R> Map<T, R> runTasks(List<T> taskList, Function<T, R> function, ExecutorService executorService) {
        Map<T, R> map = new HashMap<>(20);
        Map<T, Future<R>> futureMap = new HashMap<>();
        for (T t : taskList) {
            Future<R> future = executorService.submit(new RunTask<>(t, function));
            futureMap.put(t, future);
        }
        executorService.shutdown();
        try {
            boolean loop;
            do {
                // Block until all tasks in the thread pool end
                loop = !executorService.awaitTermination(1, TimeUnit.SECONDS);
            } while (loop);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        futureMap.forEach(
                (k, v) -> {
                    try {
                        map.put(k, v.get());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        return map;
    }

    private static class RunTask<T, R> implements Callable<R> {
        private T t;
        private Function<T, R> function;

        public RunTask(T t, Function<T, R> function) {
            this.t = t;
            this.function = function;
        }

        @Override
        public R call() {
            return function.apply(t);
        }
    }
}
