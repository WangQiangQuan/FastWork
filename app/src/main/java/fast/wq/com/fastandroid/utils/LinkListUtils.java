package fast.wq.com.fastandroid.utils;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by admin on 2017/9/5.
 */

public class LinkListUtils {
    //Calculation Average speed
    private int queueSize = 2;
    private LinkedList<Long> queue = new LinkedList<>();

    public  void add(long speed){
        if (queue == null){
            queue = new LinkedList<>();
        }
        if (queue.size() <= queueSize){
            queue.offer(speed);
        }else {
            queue.pollFirst();
            queue.offer(speed);
        }

    }
    public long cauclate(){
        if (queue ==null || queue.size() == 0){
            return 0;
        }
        long result = 0;
        long total = 0;
        Iterator<Long> iterator = queue.iterator();
        while(iterator.hasNext()){
            long speed = iterator.next();
            total+= speed;
        }
        result = total/queue.size();
        return result;
    }
}
