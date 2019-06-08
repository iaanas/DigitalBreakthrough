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
import ru.nativeteam.digitalbreakthrough.data.model.Events;

public class ItemOneFragment extends Fragment {
	
	
	List < Events > eventsList = new ArrayList <>(  );
	
	public static ItemOneFragment newInstance() {
		ItemOneFragment fragment = new ItemOneFragment();
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		
		View view = inflater.inflate( R.layout.fragment_item_one, container, false );
		
		setDateInit();
		RecyclerView recyclerView = (RecyclerView) view.findViewById( R.id.list );
		
		DataAdapter adapter = new DataAdapter(inflater.getContext(), eventsList);
		
		recyclerView.setAdapter( adapter );
		
		return view;
		
	}
	
	private void setDateInit(){
		eventsList.add( new Events( "UserIn", "АКЦИОНЕРНОЕ ОБЩЕСТВО \"БЛАГОВЕЩЕНСКАЯ ТЭЦ\"", "StartDate", "EndDate", "Поставка медицинского оборудования (ультразвуковая система)", "Не выполнена", 12, 123,
				"Description", "Date", "StatusOfWork", "HardwarId", "Place") );
		eventsList.add( new Events( "UserIn", "ГАПОУ НСО \"Новосибирский медицинский колледж\"", "StartDate", "EndDate", "Поставка медицинского оборудования", "Выполнена", 12, 123,
				"Description", "Date", "StatusOfWork", "HardwarId", "Place") );
		eventsList.add( new Events( "UserIn", "МИНИСТЕРСТВО ЗДРАВООХРАНЕНИЯ И ДЕМОГРАФИЧЕСКОЙ ПОЛИТИКИ МАГАДАНСКОЙ ОБЛАСТИ", "StartDate", "EndDate", "Поставка медицинского оборудования (ультразвуковая система) для нужд ГБУЗ «Магаданская областная детская больница»", "Не выполнена", 12, 123,
				"Description", "Date", "StatusOfWork", "HardwarId", "Place") );
		eventsList.add( new Events( "UserIn", "ГОСУДАРСТВЕННОЕ БЮДЖЕТНОЕ УЧРЕЖДЕНИЕ ЗДРАВООХРАНЕНИЯ ПЕРМСКОГО КРАЯ \"ГОРОДСКАЯ КЛИНИЧЕСКАЯ БОЛЬНИЦА №3\"", "StartDate", "EndDate", "Техническое обслуживание медицинского оборудования магнитно-резонансного томографа Airis Mate HITACHI", "Выполнена", 12, 123,
				"Description", "Date", "StatusOfWork", "HardwarId", "Place") );
	
	}
}
