# FastFoot #
Using java multithreading mechanism and Java 8 functional programming technology, multiple tasks are decomposed, and the results are returned after all tasks are executed.

### Use example ###
 * create task list<br/>
 ```
  List<Integer> taskList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
 ```
 * create function to run task<br/>
 ```
  Function<Integer, List<String>> function = count -> {
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
  ```
  
  * Not use FastFoot
  ```  
  long start = System.currentTimeMillis();
  Map<Integer, List<String>> map = new HashMap<>();
  Map<Integer, List<String>> finalMap = map;
  taskList.forEach(task -> {
      finalMap.put(task, function.apply(task));
  });
  System.out.println("Not use FastFoot, all task complete by costing:" + (System.currentTimeMillis() - start)+"ms");
  System.out.println("result:" + map);
  ``` 
  
   * Use FastFoot with 5 threads
  ``` 
  start = System.currentTimeMillis();
  map = FastFoot.runTasks(taskList, function, 5);
  System.out.println("Use Fast Foots with 5 threads, all task complete by costing:" + (System.currentTimeMillis() - start)+"ms");
  System.out.println("result:" + map);
 ``` 
  
   * Use FastFoot with 20 threads
   ```  
  start = System.currentTimeMillis();
  map = FastFoot.runTasks(taskList, function);
  System.out.println("Use Fast Foots with 20 threads, All task complete by costing:" + (System.currentTimeMillis() - start)+"ms");
  System.out.println("result:" + map);
   ``` 
### Run results ###
   ``` 
Not use FastFoot, all task complete by costing:22845ms
result:{1=[1], 2=[1, 2], 3=[1, 2, 3], 4=[1, 2, 3, 4], 5=[1, 2, 3, 4, 5], 6=[1, 2, 3, 4, 5, 6], 7=[1, 2, 3, 4, 5, 6, 7], 8=[1, 2, 3, 4, 5, 6, 7, 8], 9=[1, 2, 3, 4, 5, 6, 7, 8, 9], 10=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10], 11=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11], 12=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12], 13=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13], 14=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14], 15=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15], 16=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16], 17=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17], 18=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18], 19=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19], 20=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]}
Use Fast Foots with 5 threads, all task complete by costing:5135ms
result:{1=[1], 2=[1, 2], 3=[1, 2, 3], 4=[1, 2, 3, 4], 5=[1, 2, 3, 4, 5], 6=[1, 2, 3, 4, 5, 6], 7=[1, 2, 3, 4, 5, 6, 7], 8=[1, 2, 3, 4, 5, 6, 7, 8], 9=[1, 2, 3, 4, 5, 6, 7, 8, 9], 10=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10], 11=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11], 12=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12], 13=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13], 14=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14], 15=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15], 16=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16], 17=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17], 18=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18], 19=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19], 20=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]}
Use Fast Foots with 20 threads, All task complete by costing:2032ms
result:{1=[1], 2=[1, 2], 3=[1, 2, 3], 4=[1, 2, 3, 4], 5=[1, 2, 3, 4, 5], 6=[1, 2, 3, 4, 5, 6], 7=[1, 2, 3, 4, 5, 6, 7], 8=[1, 2, 3, 4, 5, 6, 7, 8], 9=[1, 2, 3, 4, 5, 6, 7, 8, 9], 10=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10], 11=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11], 12=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12], 13=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13], 14=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14], 15=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15], 16=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16], 17=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17], 18=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18], 19=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19], 20=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]}
``` 

### Test conclusion ###
The number of threads and tasks used is the fastest, which can be nearly 10 times faster than single line execution.
The total execution time is equal to the longest single task in the multi tasks. 
