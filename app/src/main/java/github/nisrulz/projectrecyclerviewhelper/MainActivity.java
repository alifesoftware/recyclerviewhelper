/*
 * Copyright (C) 2016 Nishant Srivastava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package github.nisrulz.projectrecyclerviewhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import github.nisrulz.recyclerviewhelper.RVHItemClickListener;
import github.nisrulz.recyclerviewhelper.RVHItemDividerDecoration;
import github.nisrulz.recyclerviewhelper.RVHItemTouchHelperCallback;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView myrecyclerview = findViewById(R.id.rv_fruits);

        data = new ArrayList<>();
        data.add("Apple");
        data.add("Banana");
        data.add("Peach");
        data.add("Pineapple");
        data.add("Orange");
        data.add("Strawberry");
        data.add("Grapes");
        data.add("Apricot");
        data.add("Avocado");
        data.add("Raisin");
        data.add("Guava");
        data.add("Papaya");
        data.add("Pear");
        data.add("Blueberry");
        data.add("Lychee");
        data.add("Date");
        data.add("Fig");

        MyAdapter adapter = new MyAdapter(data);
        myrecyclerview.hasFixedSize();
        myrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        myrecyclerview.setAdapter(adapter);

        // Setup onItemTouchHandler
        ItemTouchHelper.Callback callback = new RVHItemTouchHelperCallback(adapter, true, true, true);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(myrecyclerview);

        // Set the divider
        myrecyclerview.addItemDecoration(
                new RVHItemDividerDecoration(this, LinearLayoutManager.VERTICAL));

        // Set On Click
        myrecyclerview.addOnItemTouchListener(
                new RVHItemClickListener(this, new RVHItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String value = "Clicked Item " + data.get(position) + " at " + position;

                        Log.d("TAG", value);
                        Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
                    }
                }));

        // Show Toast
        Toast.makeText(MainActivity.this, "Swipe items left/right\nLong press and drag and drop",
                Toast.LENGTH_LONG).show();
    }
}
