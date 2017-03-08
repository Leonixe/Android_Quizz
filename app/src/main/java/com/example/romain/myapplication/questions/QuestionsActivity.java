package com.example.romain.myapplication.questions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.romain.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    final ArrayList<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Bundle extras = getIntent().getExtras();
        this.fetchQuestions(extras.getInt("SeekValue"));


    }

    void displayQuestion(ArrayList<Question> questions){

    }

    void fetchQuestions(final int numberOfQuestions){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://opentdb.com/api.php?amount="+ numberOfQuestions;
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");
                    for(int i = 0; i < numberOfQuestions; i++){
                        JSONObject item = results.getJSONObject(i);

                        String question = convertCharacter(item.getString("question"));
                        String answer = convertCharacter(item.getString("correct_answer"));
                        String difficulty = convertCharacter(item.getString("difficulty"));
                        String category = convertCharacter(item.getString("category"));


                        questions.add(new Question(question, difficulty, answer, category));
                    }
                    CreateUi(questions);


                } catch(JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonRequest);
    }

    protected void CreateUi(ArrayList<Question> questions) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.question_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new QuestionsAdapter(questions));
    }

    protected String convertCharacter(String string) {
        String result;
        result = string.replace("&quot;", "\"");
        return result.replace("&#039;", "'");
    }

}

