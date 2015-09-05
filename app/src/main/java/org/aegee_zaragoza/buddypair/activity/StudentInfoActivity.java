package org.aegee_zaragoza.buddypair.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.data.Erasmus;
import org.aegee_zaragoza.buddypair.data.Peer;
import org.aegee_zaragoza.buddypair.data.Student;

public class StudentInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        Student student = (Student) getIntent().getSerializableExtra("student");

        Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(student.toString());

        ImageView header = (ImageView) findViewById(R.id.header);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.male_avatar);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int mutedColor = palette.getMutedColor(R.attr.colorPrimary);
                collapsingToolbar.setContentScrimColor(mutedColor);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.scrollableview);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        final StudentRecyclerAdapter adapter = new StudentRecyclerAdapter(student);
        recyclerView.setAdapter(adapter);
    }

    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder> {
        private final static int POS_BIRTHDATE = 0;
        private final static int POS_NACIONALITY = 1;
        private final static int POS_EMAIL = 2;
        private final static int POS_PHONE = 3;
        private final static int POS_STUDIES = 4;
        private final static int POS_FACULTY = 5;
        private final static int POS_REG_DATE = 6;
        private final static int POS_NOTES = 8;
        private final static int POS_ERASMUS_LIMIT = 7;
        private final static int POS_ARRIVAL_DATE = 7;
        private Student student;

        public StudentRecyclerAdapter(Student student) {
            this.student = student;
        }

        @Override
        public StudentRecyclerAdapter.StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_student_info_item, parent, false);
            StudentViewHolder viewHolder = new StudentViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(StudentRecyclerAdapter.StudentViewHolder holder, int position) {
            switch (position) {
                case POS_BIRTHDATE:
                    holder.attribute.setText("Birthdate");
                    holder.value.setText(student.getBirthdate().toString());
                    break;
                case POS_NACIONALITY:
                    holder.attribute.setText("Nacionality");
                    holder.value.setText(student.getNacionality());
                    break;
                case POS_EMAIL:
                    holder.attribute.setText("Email");
                    holder.value.setText(student.getEmail());
                    break;
                case POS_PHONE:
                    holder.attribute.setText("Phone");
                    holder.value.setText(student.getPhone());
                    break;
                case POS_STUDIES:
                    holder.attribute.setText("Studies");
                    holder.value.setText(student.getStudies());
                    break;
                case POS_FACULTY:
                    holder.attribute.setText("Faculty");
                    holder.value.setText(student.getFaculty());
                    break;
                case POS_REG_DATE:
                    holder.attribute.setText("Register date");
                    holder.value.setText(student.getRegister_date().toString());
                    break;
                case POS_NOTES:
                    holder.attribute.setText("Notes");
                    holder.value.setText(student.getNotes());
                    break;
                default:
                    if (student instanceof Peer) {
                        switch (position) {
                            case POS_ERASMUS_LIMIT:
                                holder.attribute.setText("Erasmus limit");
                                holder.value.setText(Integer.toString(((Peer) student).getErasmus_limit()));
                                break;
                        }
                    } else if (student instanceof Erasmus) {
                        switch (position) {
                            case POS_ARRIVAL_DATE:
                                holder.attribute.setText("Arrival date");
                                holder.value.setText(((Erasmus) student).getArrival_date().toString());
                                break;
                        }
                    }
            }
            // TODO: adjust CardView height when text overflows
        }

        @Override
        public int getItemCount() {
            return POS_NOTES + 1;
        }

        class StudentViewHolder extends RecyclerView.ViewHolder {
            CardView cardView;
            TextView attribute;
            TextView value;

            public StudentViewHolder(View itemView) {
                super(itemView);

                cardView = (CardView) itemView.findViewById(R.id.cardlist_item);
                attribute = (TextView) itemView.findViewById(R.id.student_attribute);
                value = (TextView) itemView.findViewById(R.id.student_value);
            }
        }
    }
}
