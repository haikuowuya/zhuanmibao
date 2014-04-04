/**
 * 
 */
package com.zhuanmibao.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author cainli 2014年2月7日下午2:15:07
 * 
 */
public class Item implements Parcelable {
	public String name, desc;
	public int id, typeId, resId;

	/**
	 * @param name
	 * @param desc
	 * @param id
	 * @param resId
	 */
	public Item(int typeId, int id, int resId, String name, String desc) {
		super();
		this.typeId = typeId;
		this.name = name;
		this.desc = desc;
		this.id = id;
		this.resId = resId;
	}

	/**
	 * @param source
	 */
	public Item(Parcel source) {
		typeId = source.readInt();
		id = source.readInt();
		resId = source.readInt();
		name = source.readString();
		desc = source.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(typeId);
		dest.writeInt(id);
		dest.writeInt(resId);
		dest.writeString(name);
		dest.writeString(desc);

	}
	
    public static final Parcelable.Creator<Item> CREATOR = new Creator<Item>() {  
          
        @Override  
        public Item[] newArray(int size) {  
            return new Item[size];  
        }  
          
        //将Parcel对象反序列化为ParcelableDate  
        @Override  
        public Item createFromParcel(Parcel source) {  
            return new Item(source);  
        }  
    };  

}