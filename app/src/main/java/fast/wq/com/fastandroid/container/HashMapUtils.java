package fast.wq.com.fastandroid.container;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * HashMap即是采用了链地址法，也就是数组+链表的方式，
 * //http://www.codeweblog.com/hashmap之put-k-key-v-value-学习笔记-jdk8版-一/
 * http://www.importnew.com/22007.html
 */

public class HashMapUtils {

    private void testHashMap(){

        HashMap<String,String> map = new HashMap<>();
        map.put("a","s");
    }

    private void testLinkedHashMap(){
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
    }
}
