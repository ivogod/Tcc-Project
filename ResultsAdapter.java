package br.com.tcccomfirestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ResultsAdapter extends FirestoreRecyclerAdapter<Results, ResultsAdapter.ResultHolder> {

    public ResultsAdapter(FirestoreRecyclerOptions<Results> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull ResultHolder holder, int position,@NonNull Results model) {
        holder.textViewPh.setText(model.getPh());
        holder.textViewDescription.setText(model.getDescription());
        holder.textViewRgb.setText(model.getRgbcode());

    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_item,
                parent, false);
        return new ResultHolder(v);

    }

    static class ResultHolder extends RecyclerView.ViewHolder {
        TextView textViewPh;
        TextView textViewDescription;
        TextView textViewRgb;

        public ResultHolder(View itemView) {
            super(itemView);
            textViewPh = itemView.findViewById(R.id.textViewPhSaved);
            textViewDescription = itemView.findViewById(R.id.textViewDesc);
            textViewRgb = itemView.findViewById(R.id.textViewRgbSaved);

        }
    }
}
