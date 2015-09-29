package com.example.basicinfo;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfo implements Parcelable{

	private String userName;
	private String password;
	private String sex;
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	//将写入Parcelable
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(userName);
		dest.writeString(password);
		dest.writeString(sex);
		
	}

	/*
	 * public static final 必须添加，否则报错
	 * 只能用  CREATOR 命名 
	 * */
	public static final Parcelable.Creator<UserInfo>  CREATOR = new Creator<UserInfo>() {
		
		@Override
		public UserInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new UserInfo[size];
		}
		
		@Override
		public UserInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			String name = source.readString();
			String password = source.readString();
			String sex = source.readString();
					
			UserInfo userInfo = new UserInfo();
			userInfo.setUserName(name);
			userInfo.setPassword(password);
			userInfo.setSex(sex);
			
			return userInfo;
		}
	};
}
