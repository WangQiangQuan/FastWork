package fast.wq.com.fastandroid.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * http://blog.csdn.net/lespace/article/details/52145598
 */

public class SortUtils {

    public static int[] ar = {1, 3, 44, 3, 2, 34, 56, -9};

    /**
     * 1.插入排序算法
     * 插入排序的基本思想是在遍历数组的过程中，
     * 假设在序号 i 之前的元素即 [0..i-1] 都已经排好序，本趟需要找到 i 对应的元素 x 的正确位置 k ，
     * 并且在寻找这个位置 k 的过程中逐个将比较过的元素往后移一位，为元素 x “腾位置”，最后将 k 对应的元素值赋为 x ，
     * 一般情况下，插入排序的时间复杂度和空间复杂度分别为 O(n2 ) 和 O(1)。
     */
    public static int[] sortInsert(int[] array) {
        if (array == null && array.length <= 1) {
            return array;
        }

        //n -1趟遍历
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j;
            //temp 和 之前的所有的 array[i]和前一个比较
            for (j = i - 1; j > 0 && temp < array[j]; j--) {
                array[j + 1] = array[j];
            }
            //把temp 放在正确的位置
            array[j + 1] = temp;
        }

        return array;
    }

    /**
     * 2.选择排序算法
     * 选择排序的基本思想是遍历数组的过程中，以 i 代表当前需要排序的序号，则需要在剩余的 [i…n-1] 中找出其中的最小值，然后将找到的最小值与 i 指向的值进行交换。因为每一趟确定元素的过程中都会有一个选择最大值的子流程，所以人们形象地称之为选择排序。选择排序的时间复杂度和空间复杂度分别为 O(n2 ) 和 O(1) 。
     */
    public int[] sortSelect(int[] array) {
        if (array == null && array.length <= 1) {
            return array;
        }
        //n-1躺排序
        for (int i = 0; i < array.length; i++) {
            int miniPost = i;
            //找到最小的数的位置
            for (int m = i + 1; m < array.length; m++) {
                if (array[m] < array[miniPost]) {
                    miniPost = m;
                }
            }

            //当前的数 和选择到 交换
            if (array[i] > array[miniPost]) {
                int temp;
                temp = array[i];
                array[i] = array[miniPost];
                array[miniPost] = temp;
            }
        }

        return array;
    }

    /**
     * 4快速排序
     *通过一趟排序将待排记录分割成独立的两部分
     * @param int[] 未排序数组
     * @return int[] 排完序数组
     */

    public int[] sortQuick(int[] array) {
        return quickSort(array, 0, array.length - 1);
    }

    private int[] quickSort(int[] arr, int low, int heigh) {
        if (low < heigh) {
            int division = partition(arr, low, heigh);
            quickSort(arr, low, division - 1);
            quickSort(arr, division + 1, heigh);
        }
        return arr;
    }

    // 分水岭,基位,左边的都比这个位置小,右边的都大
    private int partition(int[] arr, int low, int heigh) {
        int base = arr[low]; //用子表的第一个记录做枢轴(分水岭)记录
        while (low < heigh) { //从表的两端交替向中间扫描
            while (low < heigh && arr[heigh] >= base) {
                heigh--;
            }
            // base 赋值给 当前 heigh 位,base 挪到(互换)到了这里,heigh位右边的都比base大
            swap(arr, heigh, low);
            while (low < heigh && arr[low] <= base) {
                low++;
            }
            // 遇到左边比base值大的了,换位置
            swap(arr, heigh, low);
        }
        // now low = heigh;
        return low;
    }

    private void swap(int[] arr, int a, int b) {
        int temp;
        temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    /**
     * 5.合并排序算法
     */


    /**
     * 输出一个集合的所有子集合-Java代码实现
     * //www.zhihu.com/question/29985661/answer/46501393
     */

    public static void main(String[] args) {
        int[] set = new int[]{1,2};
        Set<Set<Integer>> result = getSubSet(set);	//调用方法

        //输出结果
        for(Set<Integer> subSet: result){
            for(Integer num: subSet)
                System.out.print(num);

            System.out.println("");
        }
    }

    public static Set<Set<Integer>> getSubSet(int[] set){
        Set<Set<Integer>> result = new HashSet<Set<Integer>>();	//用来存放子集的集合，如{{},{1},{2},{1,2}}
        int length = set.length;
        int num = length==0 ? 0 : 1<<(length);	//2的n次方，若集合set为空，num为0；若集合set有4个元素，那么num为16.

        //从0到2^n-1（[00...00]到[11...11]）
        for(int i = 0; i < num; i++){
            Set<Integer> subSet = new HashSet<Integer>();

            int index = i;
            for(int j = 0; j < length; j++){
                if((index & 1) == 1){		//每次判断index最低位是否为1，为1则把集合set的第j个元素放到子集中
                    subSet.add(set[j]);
                }
                index >>= 1;		//右移一位
            }

            result.add(subSet);		//把子集存储起来
        }
        return result;
    }
}
