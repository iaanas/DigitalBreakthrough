package ru.nativeteam.digitalbreakthrough.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.nativeteam.digitalbreakthrough.R;
import ru.nativeteam.digitalbreakthrough.data.model.Events;
import ru.nativeteam.digitalbreakthrough.data.model.Partners;

public class PartnerAdapter extends RecyclerView.Adapter<PartnerAdapter.ViewHolder> {
	
	private LayoutInflater inflater;
	private List < Partners > partners;
	
	public PartnerAdapter( Context context , List < Partners> partners ) {
		this.partners = partners;
		this.inflater = LayoutInflater.from(context);
	}
	@Override
	public PartnerAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
		
		View view = inflater.inflate( R.layout.list_events, parent, false);
		return new PartnerAdapter.ViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(PartnerAdapter.ViewHolder holder, int position) {
		Partners partner = partners.get(position);
		holder.userOut.setText(partner.getUserOut());
		holder.inn.setText(partner.getInn());
		holder.ogrn.setText( partner.getOgrn() );
		holder.address.setText( partner.getAddress() );
		holder.imgAva.setImageResource(R.drawable.flat_faces_icons_circle_3_768x768);
		
	}
	
	@Override
	public int getItemCount() {
		return partners.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		final TextView userOut , inn, ogrn, address;
		final ImageView imgAva;
		ViewHolder(View view){
			super(view);
			userOut = (TextView) view.findViewById(R.id.userOut);
			inn = ( TextView ) view.findViewById( R.id.inn);
			ogrn = (TextView ) view.findViewById( R.id.ogrn );
			address = (TextView) view.findViewById( R.id.address );
			imgAva = (ImageView) view.findViewById( R.id.imgAva );
			
		}
	}
}
