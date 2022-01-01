package com.example.cs496_week1;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class TodoAdapter  extends RecyclerView.Adapter<TodoAdapter.ViewHolder> implements ItemTouchHelperListener{

    private ArrayList<Todolist> mData = null ;
    //private boolean is_did=true;

    // 아이템 뷰를 저장하는 뷰홀더 클래스. 클릭 이벤트 적용
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1 ;
        CheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.todotext);
            checkBox = itemView.findViewById(R.id.checkBox);

            checkBox.setOnClickListener(new CheckBox.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO : process the click event.
                    int position = getAdapterPosition();
                    Todolist that_data = mData.get(position);
                    DBHelper3 mDBHelper = new DBHelper3(v.getContext());
                    mDBHelper.DeleteTodo(that_data.id);
                    int is_check=0;
                    if (checkBox.isChecked()){is_check=1;};
                    int id = mDBHelper.InsertTodo(that_data.getContext(), is_check);
                    mData.remove(that_data);
                    notifyItemRemoved(position);
                    that_data.id = id;
                    that_data.did = is_check;
                    if (is_check==1){
                        addItemback(that_data);
                    }else{
                        addItemfront(that_data);
                    }
                }
            }) ;
            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:{
                            view.findViewById(R.id.delete2).setVisibility(View.VISIBLE);
                            return true;
                        }
                        case MotionEvent.ACTION_UP:{
                            view.findViewById(R.id.delete2).setVisibility(View.INVISIBLE);
                            return false;
                        }
                        default:
                            return false;
                    }
                }

            });


        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    TodoAdapter(ArrayList<Todolist> list, boolean is_checked) {
        //is_did = is_checked;
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.todoelement, parent, false) ;
        ViewHolder vh = new ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(TodoAdapter.ViewHolder holder, int position) {
        String text = mData.get(position).getContext() ;
        boolean is_did = mData.get(position).getDid()==1;
        holder.textView1.setText(text) ;
        holder.checkBox.setChecked(is_did);
    }


    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    public void addItemfront(Todolist todolist){
        mData.add(0,todolist);
        for (int i=0;i<getItemCount();i++){
            notifyItemChanged(i);
        }
        //notifyDataSetChanged(); //


    }
    public void addItemback(Todolist todolist){
        mData.add(todolist);
        notifyItemChanged(getItemCount());


    }

    @Override
    public void onItemSwipe(int position) {
        Todolist that = mData.get(position);
        mData.remove(position);
        notifyItemRemoved(position);
        MainActivity ma = (MainActivity) MainActivity.activity;
        DBHelper3 mDBHelper = new DBHelper3(ma);
        mDBHelper.DeleteTodo(that.id);
    }

}
