package com.example.cs496_week1;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SimpleTextAdapter  extends RecyclerView.Adapter<SimpleTextAdapter.ViewHolder> {

    private ArrayList<Contact> mData = null ;

    // 아이템 뷰를 저장하는 뷰홀더 클래스. 클릭 이벤트 적용
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1 ;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.text1) ;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(),Contact_info.class);
                    intent.putExtra("name", mData.get(position).getName());
                    intent.putExtra("phone_number", mData.get(position).getPhone_number());
                    v.getContext().startActivity(intent);
                }
            });
            itemView.findViewById(R.id.delete).setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            int position = getAdapterPosition();
                            //mDBHelper.InsertContact("01000000000","Park","Doyun");
                            //Intent intent = new Intent(v.getContext(), addContact.class);
                            //intent.putExtra("dbHelper",mDBHelper);
                            DBHelper mDBHelper = new DBHelper(v.getContext());
                            int id = mData.get(position).id;
                            mDBHelper.DeleteContact(id);
                            mData.remove(position);
                            notifyItemRemoved(position);
                            //Log.v("click", "okay");

                        }
                    });

        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    SimpleTextAdapter(ArrayList<Contact> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.element1, parent, false) ;
        ViewHolder vh = new ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(SimpleTextAdapter.ViewHolder holder, int position) {
        String text = mData.get(position).getName() ;
        holder.textView1.setText(text) ;
    }


    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

}
