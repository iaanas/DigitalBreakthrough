package ru.nativeteam.digitalbreakthrough.data.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import ru.nativeteam.digitalbreakthrough.EventsActivity;
import ru.nativeteam.digitalbreakthrough.R;
import ru.nativeteam.digitalbreakthrough.data.model.Events;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
	
	private LayoutInflater inflater;
	private List < Events > events;
	private Context mcon;
	
	public DataAdapter( Context context , List < Events > events ) {
		mcon = context;
		this.events = events;
		this.inflater = LayoutInflater.from(context);
	}
	@Override
	public DataAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
		
		View view = inflater.inflate( R.layout.list_events, parent, false);
		return new ViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder( final DataAdapter.ViewHolder holder, int position) {
		Events event = events.get(position);
		holder.nameOfIvent.setText(event.getNameOfEvent());
		holder.userOut.setText(event.getUserOut());
		holder.statusOfIvent.setText( event.getStatusOfEvent() );
		
		if(event.getStatusOfEvent().equals( "Не выполнена" )){
			holder.imgStatus.setImageResource(R.drawable.red_oval);
		} else {
			holder.imgStatus.setImageResource( R.drawable.green_oval );
		}
		
		holder.button.setOnClickListener( new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				mcon.startActivity( new Intent( mcon, EventsActivity.class ) );
			}
		} );
		
	}
	
	@Override
	public int getItemCount() {
		return events.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		final TextView nameOfIvent , userOut , statusOfIvent;
		final ImageView imgStatus;
		final Button button;
		ViewHolder(View view){
			super(view);
			nameOfIvent  = (TextView) view.findViewById(R.id.nameOfEvent);
			userOut = (TextView) view.findViewById(R.id.userOut);
			statusOfIvent = ( TextView ) view.findViewById( R.id.statusOfIvent);
			imgStatus = (ImageView) view.findViewById( R.id.imgStatus );
			button = (Button) view.findViewById( R.id.button );
			
		}
	}
}
