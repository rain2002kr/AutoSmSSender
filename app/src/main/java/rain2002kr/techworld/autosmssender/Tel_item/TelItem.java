package rain2002kr.techworld.autosmssender.Tel_item;

public class TelItem {
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
}
