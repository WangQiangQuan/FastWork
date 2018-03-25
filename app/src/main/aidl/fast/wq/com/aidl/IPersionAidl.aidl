// IPersionAidl.aidl
package fast.wq.com.aidl;
//import fast.wq.com.fastandroid.bean.ParcelableBean;
import fast.wq.com.aidl.Persion;
//指向的新定义的ParcelableBean
// Declare any non-default types here with import statements

interface IPersionAidl {
     List<Persion> addbean(in Persion bean);
}
