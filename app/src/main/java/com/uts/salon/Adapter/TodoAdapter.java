package com.uts.salon.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uts.salon.Database.DatabaseClient;
import com.uts.salon.Model.Todo;
import com.uts.salon.Preferences.UserPreferences;
import com.uts.salon.R;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private List<Todo> todoList;
    private Context context;
    private DatabaseClient databaseClient;
    private UserPreferences userPreferences;
    private ArrayList<String> mResult;

    public TodoAdapter(List<Todo> todoList, Context context) {
        this.todoList = todoList;
        this.context = context;
        this.userPreferences = new UserPreferences(context);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //init view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_todo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Todo todo = todoList.get(position);
        //dari list_todo
        holder.tvTitle.setText("Reservasi : " + todo.getTitle() + "\nTanggal :" + todo.getDate() + "\n\nKegiatan : \n" + todo.getK());
        databaseClient = DatabaseClient.getInstance(context);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseClient.getDatabase()
                        .todoDao()
                        .deleteTodo(todo);
                Toast.makeText(context, "Berhasil menghapus", Toast.LENGTH_SHORT).show();
                todoList.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setLayout(width,height);
                dialog.show();

                EditText edt_todoEdit = dialog.findViewById(R.id.edt_todoEdit);
                CheckBox PR = dialog.findViewById(R.id.PR);
                CheckBox P = dialog.findViewById(R.id.P);
                CheckBox CR = dialog.findViewById(R.id.CR);
                CheckBox W = dialog.findViewById(R.id.W);
                CheckBox S = dialog.findViewById(R.id.S);
                CheckBox M = dialog.findViewById(R.id.M);
                CheckBox Pr = dialog.findViewById(R.id.Pr);
                TextView myDate = dialog.findViewById(R.id.myDate);
                TextView ke = dialog.findViewById(R.id.ke);
                CalendarView calendarView = dialog.findViewById(R.id.calendarV);
                Button btnUpdate = dialog.findViewById(R.id.btnUpdate);

                mResult = new ArrayList<>();


                edt_todoEdit.setText(todo.getTitle());

                PR.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (PR.isChecked())
                            mResult.add("Potong Rambut");
                        else
                            mResult.remove("Potong Rambut");
                    }
                });

                P.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (P.isChecked())
                            mResult.add("Pijat");
                        else
                            mResult.remove("Pijat");
                    }
                });

                CR.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (CR.isChecked())
                            mResult.add("Cat Rambut");
                        else
                            mResult.remove("Cat Rambut");
                    }
                });

                W.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (W.isChecked())
                            mResult.add("Waxing");
                        else
                            mResult.remove("Waxing");
                    }
                });

                S.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (S.isChecked())
                            mResult.add("Smoothing");
                        else
                            mResult.remove("Smoothing");
                    }
                });

                M.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (M.isChecked())
                            mResult.add("Menikur");
                        else
                            mResult.remove("Menikur");
                    }
                });

                Pr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Pr.isChecked())
                            mResult.add("Pedikur");
                        else
                            mResult.remove("Pedikur");
                    }
                });

                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                        String date = (i1 + 1) + " - " + i2 + " - " + i;
                        myDate.setText(date);
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String s : mResult)
                            stringBuilder.append(s).append("\n");
                        ke.setText(stringBuilder.toString());
                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!edt_todoEdit.getText().toString().isEmpty()){
                            todo.setTitle(edt_todoEdit.getText().toString());
                            StringBuilder stringBuilder = new StringBuilder();
                            for (String s : mResult)
                                stringBuilder.append(s).append("\n");
                            todo.setK(ke.getText().toString());
                            todo.setDate(myDate.getText().toString());
                            databaseClient.getDatabase()
                                    .todoDao()
                                    .updateTodo(todo);
                            todoList.clear();
                            todoList.addAll(databaseClient.getDatabase().todoDao().getTodosByUserId(userPreferences.getUserLogin().getId()));
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Berhasil mengubah data", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Ga boleh kosong titlenya isi yaaa", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageButton btnDelete,btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //dari list_todo
            tvTitle = itemView.findViewById(R.id.tvTitle);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);

        }
    }
}
