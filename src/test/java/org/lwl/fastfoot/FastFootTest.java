package org.lwl.fastfoot;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @Description FastFoot simple.
 * @Author liwenlong
 * @Date 2020-06-07
 */
public class FastFootTest {

    public static void main(String[] args) {

        // create task list
        List<Integer> taskList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        // create function to run task
        Function<Integer, Object> function = count -> {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                try {
                    // Simulate time-consuming operations
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(String.valueOf(i+1));
            }
            return list;
        };
        /*
         * Not use FastFoot
         */
        long start = System.currentTimeMillis();
        Map<Integer, Object> map = new HashMap<>();
        Map<Integer, Object> finalMap = map;
        taskList.forEach(task -> {
            finalMap.put(task, function.apply(task));
        });
        System.out.println("Not use FastFoot, all task complete by costing:" + (System.currentTimeMillis() - start)+"ms");
        System.out.println("result:" + map);
        /*
         * Use FastFoot with 5 threads
         */
        start = System.currentTimeMillis();
        map = FastFoot.runTasks(taskList, function, 5);
        System.out.println("Use FastFoot with 5 threads, all task complete by costing:" + (System.currentTimeMillis() - start)+"ms");
        System.out.println("result:" + map);
        /*
         * Use FastFoot with 20 threads
         */
        start = System.currentTimeMillis();
        map = FastFoot.runTasks(taskList, function);
        System.out.println("Use FastFoot with 20 threads, All task complete by costing:" + (System.currentTimeMillis() - start)+"ms");
        System.out.println("result:" + map);
    }
}
