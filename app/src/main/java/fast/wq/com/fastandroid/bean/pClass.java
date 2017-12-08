package fast.wq.com.fastandroid.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2017/10/31.
 * android parcelable code generator
 */

public class pClass implements Parcelable {
    public String name;
    public String id;
    public pClass(){

    }

    public pClass(Parcel in) {
        name = in.readString();
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<pClass> CREATOR = new Creator<pClass>() {
        @Override
        public pClass createFromParcel(Parcel in) {
            return new pClass(in);
        }

        @Override
        public pClass[] newArray(int size) {
            return new pClass[size];
        }
    };
}
