package com.example.datajson;

import java.util.ArrayList;

public class PicUrl {

	private ArrayList<PicUrlStr> picUrlSet;
	private String albumsName;
	public ArrayList<PicUrlStr> getPicUrlSet() {
		return picUrlSet;
	}

	public void setPicUrlSet(ArrayList<PicUrlStr> picUrlSet) {
		this.picUrlSet = picUrlSet;
	}

	public String getAlbumsName() {
		return albumsName;
	}

	public void setAlbumsName(String albumsName) {
		this.albumsName = albumsName;
	}
	
}
