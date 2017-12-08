package fast.wq.com.fastandroid.bean;

import android.os.Parcel;

/**
 * Created by admin on 2017/11/16.
 */

public class SClass extends pClass {
//public class SClass implements Parcelable {
    public String sName;

    public  SClass(){

    }

    protected SClass(Parcel in) {
        super(in);
        sName = in.readString();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest,flags);
        dest.writeString(sName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SClass> CREATOR = new Creator<SClass>() {
        @Override
        public SClass createFromParcel(Parcel in) {
            return new SClass(in);
        }

        @Override
        public SClass[] newArray(int size) {
            return new SClass[size];
        }
    };
}
