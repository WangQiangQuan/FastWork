package fast.wq.com.fastandroid.algorithm;

/**
 * https://www.cnblogs.com/vsign/p/7290594.html
 * Java中实现十进制数转换为二进制
 *原码、反码、补码的产生、应用以及优缺点有哪些？
 * https://www.zhihu.com/question/20159860
 */

public class Convert {

    public void binaryToDecimal(int n){
        int t = 0;  //用来记录位数
        int bin = 0; //用来记录最后的二进制数
        int r = 0;  //用来存储余数
        while(n != 0){
            r = n % 2;
            n = n / 2;
//            bin = r *  Math().pow(10,t);
            t++;
        }
        System.out.println(bin);
    }
}
