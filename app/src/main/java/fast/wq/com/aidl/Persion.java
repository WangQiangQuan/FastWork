package fast.wq.com.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wangqiang on 2018/3/25.
 */

public class Persion implements Parcelable {
    String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public Persion() {
    }

    protected Persion(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Persion> CREATOR = new Parcelable.Creator<Persion>() {
        @Override
        public Persion createFromParcel(Parcel source) {
            return new Persion(source);
        }

        @Override
        public Persion[] newArray(int size) {
            return new Persion[size];
        }
    };
}
