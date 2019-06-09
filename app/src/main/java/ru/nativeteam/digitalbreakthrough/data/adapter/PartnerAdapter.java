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
		
		View view = inflater.inflate( R.layout.partner_list, parent, false);
		return new PartnerAdapter.ViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(PartnerAdapter.ViewHolder holder, int position) {
		Partners partner = partners.get(position);
		holder.nameCompany.setText(partner.getUserOut());
		holder.innCompany.setText( partner.getInn());
		holder.ogrnCompany.setText( partner.getOgrn() );
		holder.addressCompany.setText( partner.getAddress());
		holder.imgAva.setImageResource(R.drawable.flat_faces_icons_circle_3_768x768);
		
	}
	
	@Override
	public int getItemCount() {
		return partners.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		final TextView nameCompany, innCompany, ogrnCompany, addressCompany, telCompany;
		final ImageView imgAva;
		ViewHolder(View view){
			super(view);
			nameCompany = (TextView) view.findViewById(R.id.nameCompany);
			innCompany = ( TextView ) view.findViewById( R.id.innCompany);
			ogrnCompany = (TextView ) view.findViewById( R.id.ogrnCompany );
			addressCompany = (TextView) view.findViewById( R.id.addressCompany );
			telCompany = (TextView ) view.findViewById( R.id.telCompany );
			imgAva = (ImageView) view.findViewById( R.id.imgAva );
			
		}
	}
}
