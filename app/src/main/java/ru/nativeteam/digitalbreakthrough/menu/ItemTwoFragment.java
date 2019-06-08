package ru.nativeteam.digitalbreakthrough.menu;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.nativeteam.digitalbreakthrough.R;
import ru.nativeteam.digitalbreakthrough.data.adapter.DataAdapter;
import ru.nativeteam.digitalbreakthrough.data.adapter.PartnerAdapter;
import ru.nativeteam.digitalbreakthrough.data.model.Events;
import ru.nativeteam.digitalbreakthrough.data.model.Partners;

public class ItemTwoFragment extends Fragment {
	
	
	List < Partners > eventsList = new ArrayList <>(  );
	
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
		View view = inflater.inflate( R.layout.fragment_item_two, container, false );
		
		setDateInit();
		RecyclerView recyclerView = (RecyclerView) view.findViewById( R.id.list );
		
		PartnerAdapter adapter = new PartnerAdapter(inflater.getContext(), eventsList);
		
		recyclerView.setAdapter( adapter );
		
		return view;
	}
	
	private void setDateInit(){
		eventsList.add( new Partners( "Контрагент 1", "ИНН", "ОГРН", "Номер телефона", "Москва, улица номер дома" ) );
		eventsList.add( new Partners( "Контрагент 1", "ИНН", "ОГРН", "Номер телефона", "Москва, улица номер дома" ) );
		eventsList.add( new Partners( "Контрагент 1", "ИНН", "ОГРН", "Номер телефона", "Москва, улица номер дома" ) );
		eventsList.add( new Partners( "Контрагент 1", "ИНН", "ОГРН", "Номер телефона", "Москва, улица номер дома" ) );
	}
}
