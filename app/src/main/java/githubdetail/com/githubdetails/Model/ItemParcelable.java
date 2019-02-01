package githubdetail.com.githubdetails.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

/**
 * Created by mohamed Rafeek on 27/1/2019.
 */

public class ItemParcelable implements Parcelable{
    private Item item;

    public ItemParcelable(Item item){
        this.item = item;
    }

    private ItemParcelable(Parcel in){
        item=new Gson().fromJson(in.readString(),Item.class);
    }

    public Item getItem(){
        return item;
    }

    public static final Creator<ItemParcelable> CREATOR = new Creator<ItemParcelable>() {
        @Override
        public ItemParcelable createFromParcel(Parcel in) {
            return new ItemParcelable(in);
        }

        @Override
        public ItemParcelable[] newArray(int size) {
            return new ItemParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(new Gson().toJson(item));
    }
}
