package rain2002kr.techworld.autosmssender.Tel_item;

import android.os.Parcel;
import android.os.Parcelable;

public class TelItem implements Parcelable {
    int ResId; String name,phone;

    public TelItem(int resId, String name, String phone) {
        ResId = resId;
        this.name = name;
        this.phone = phone;
    }

    public int getResId() {
        return ResId;
    }

    public void setResId(int resId) {
        ResId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "TelItem{" +
                "ResId=" + ResId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ResId);
        dest.writeString(this.name);
        dest.writeString(this.phone);
    }

    protected TelItem(Parcel in) {
        this.ResId = in.readInt();
        this.name = in.readString();
        this.phone = in.readString();
    }

    public static final Parcelable.Creator<TelItem> CREATOR = new Parcelable.Creator<TelItem>() {
        @Override
        public TelItem createFromParcel(Parcel source) {
            return new TelItem(source);
        }

        @Override
        public TelItem[] newArray(int size) {
            return new TelItem[size];
        }
    };
}
