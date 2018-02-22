package fast.wq.com.fastandroid.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * File -> Settings -> Pugins -> Browse Repositories 如下，输入android parcelable code generator：
 */

public class ParcelableBean implements Parcelable {
    public int id;
    public String name;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    public ParcelableBean() {
    }

    protected ParcelableBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<ParcelableBean> CREATOR = new Parcelable.Creator<ParcelableBean>() {
        @Override
        public ParcelableBean createFromParcel(Parcel source) {
            return new ParcelableBean(source);
        }

        @Override
        public ParcelableBean[] newArray(int size) {
            return new ParcelableBean[size];
        }
    };

    @Override
    public String toString() {
        return super.toString();
    }
}
