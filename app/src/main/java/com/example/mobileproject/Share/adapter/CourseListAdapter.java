package com.example.mobileproject.Share.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.Share.data.CourseList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder> {

    private List<CourseList> courseList;
    private ArrayList<Integer> countList;
    private FirebaseFirestore db;

    public CourseListAdapter(List<CourseList> courseList) {
        this.courseList = courseList;
        this.countList = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            countList.add(0); // 초기 조회수를 0으로 설정
        }
        db = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseList course = courseList.get(position);

        holder.title.setText(course.getName());

        Integer counter_see = course.getCount_see();
        if (counter_see != null) {
            holder.count_see.setText(Integer.toString(counter_see));
        } else {
            holder.count_see.setText("0"); // 값이 null인 경우 기본값으로 0을 표시하거나 다른 처리를 수행할 수 있습니다.
        }

        Integer counter_commend = course.getCount_like();
        if (counter_commend != null) {
            holder.commend_see.setText(Integer.toString(counter_commend));
        } else {
            holder.commend_see.setText("0"); // 값이 null인 경우 기본값으로 0을 표시하거나 다른 처리를 수행할 수 있습니다.
        }

        Integer counter_bookmark = course.getCount_bookmark();
        if (counter_bookmark != null) {
            holder.bookmark_see.setText(Integer.toString(counter_bookmark));
        } else {
            holder.bookmark_see.setText("0"); // 값이 null인 경우 기본값으로 0을 표시하거나 다른 처리를 수행할 수 있습니다.
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentCount = course.getCount_see();
                int newCount = currentCount + 1;
                course.setCount_see(newCount);
                notifyDataSetChanged();

                Log.d("click" ,  course.getName() +"클릭");

            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView count_see;
        private TextView commend_see;
        private TextView bookmark_see;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            count_see = itemView.findViewById(R.id.count_see);
            commend_see = itemView.findViewById(R.id.count_commend);
            bookmark_see = itemView.findViewById(R.id.count_bookmark);
        }
    }

    private void updateCount(String Id, int newCount) {

        CollectionReference colref = db.collection("share_course");

        Query update = colref.whereEqualTo(FieldPath.documentId(), Id);

        update.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){

                        DocumentReference doc = colref.document(documentSnapshot.getId());

                        Map<String, Object> updates = new HashMap<>();
                        updates.put("count_see", newCount);

                        doc.update(updates).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
                    }
                } else{

                }
            }
        });

    }

}


