package ru.nativeteam.digitalbreakthrough.menu;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.nativeteam.digitalbreakthrough.R;

public class ItemTwoFragment extends Fragment {
	public static ItemTwoFragment newInstance() {
		ItemTwoFragment fragment = new ItemTwoFragment();
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_item_two, container, false);
	}
}