package com.example.cs496_week1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment3# newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment3 extends Fragment {
    private ItemTouchHelper mItemTouchHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Log.v("start","hihi Frag1");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_3, container, false);
        RecyclerView recyclerView1 = (RecyclerView) rootView.findViewById(R.id.todo_list);
        recyclerView1.setHasFixedSize(true);

        ArrayList<Todolist> todolist = new ArrayList<>();
        ArrayList<Todolist> didlist = new ArrayList<>();
        DBHelper3 mDBHelper = new DBHelper3(getActivity().getApplicationContext());


        //DBHelper mDBHelper = new DBHelper(getActivity().getApplicationContext());
        //mDBHelper.InsertContact("01000000000","Park","Doyun");
        todolist = mDBHelper.getTododidnot();
        didlist = mDBHelper.getTododid();

        List<Todolist> todol = (List<Todolist>) todolist;
        List<Todolist> didl = (List<Todolist>) didlist;

        todol.addAll(didl);


        //int all_size =todolist.size()+didlist.size();


        TodoAdapter didnot_adapter = new TodoAdapter((ArrayList<Todolist>) todol,false);
        //TodoAdapter did_adapter = new TodoAdapter(didlist,true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView1.setAdapter(didnot_adapter);
        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(didnot_adapter));
        mItemTouchHelper.attachToRecyclerView(recyclerView1);



        rootView.findViewById(R.id.addbutton).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText t_context = (EditText) getActivity().findViewById(R.id.context);
                        String context = t_context.getText().toString();
                        int id = mDBHelper.InsertTodo(context,0);
                        ViewGroup subviewGroup = (ViewGroup) rootView.findViewById(R.id.todo_list);

                        Todolist todolist = new Todolist();
                        todolist.Setting(id,context,0);
                        didnot_adapter.addItemfront(todolist);
                        t_context.setText("");
                        LayoutWrapContentUpdater.wrapContentAgain(subviewGroup);


                        //Log.v("click", "okay");

                    }
                }
        );






        return rootView;
    }
}