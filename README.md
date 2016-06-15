![Image](https://github.com/nisrulz/recyclerviewhelper/blob/master/img/github_banner.png)

[![MavenCentral](https://maven-badges.herokuapp.com/maven-central/com.github.nisrulz/recyclerviewhelper/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.nisrulz/recyclerviewhelper) [![API](https://img.shields.io/badge/API-9%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=9)

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-RecyclerViewHelper-green.svg?style=true)](https://android-arsenal.com/details/1/3572)

[![Twitter Follow](https://img.shields.io/twitter/follow/nisrulz.svg?style=social)](https://twitter.com/nisrulz)

RecyclerViewHelper provides the most common functions around recycler view like Swipe
 to dismiss, Drag and Drop, Divider in the ui, events for when item selected and when not 
 selected, on-click listener for items.

#Integration
- RecyclerViewHelper is available in the MavenCentral, so getting it as simple as adding it as a dependency
```gradle
compile 'com.github.nisrulz:recyclerviewhelper:23.4.0'
```
> NOTE : The version here corresponds to the version of underlying recyclerview dependency. 

#Usage
+ Implement the `RHVAdapter` in your recycler view adapter and `RHVViewHolder` in your ItemViewHolder 
```java

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> implements RVHAdapter {
    
         ...
    
        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            swap(fromPosition, toPosition);
            return false;
        }
    
        @Override
        public void onItemDismiss(int position, int direction) {
            remove(position);
        }
    
        public class ItemViewHolder extends RecyclerView.ViewHolder implements RVHViewHolder {
            ...
               
            @Override
            public void onItemSelected(int actionstate) {
                System.out.println("Item is selected");
            }
    
            @Override
            public void onItemClear() {
                System.out.println("Item is unselected");
    
            }
        }
    
        // Helper functions you might want to implement to make changes in the list as an event is fired
        private void remove(int position) {
            dataList.remove(position);
            notifyItemRemoved(position);
        }
    
        private void swap(int firstPosition, int secondPosition) {
            Collections.swap(dataList, firstPosition, secondPosition);
            notifyItemMoved(firstPosition, secondPosition);
        }
    }

```

+ Then implement your recycler view
```java

   public class MainActivity extends AppCompatActivity {
   
   
       RecyclerView myrecyclerview;
       ArrayList<String> data;
       MyAdapter adapter;
   
       @Override
       protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);
   
           myrecyclerview = (RecyclerView) findViewById(R.id.rv_fruits);
   
           data = new ArrayList<>();
           data.add("Apple");
           ...
           data.add("Fig");
   
           // Setup your adapter
           adapter = new MyAdapter(data);
           // Setup 
           myrecyclerview.hasFixedSize();
           myrecyclerview.setLayoutManager(new LinearLayoutManager(this));
           myrecyclerview.setAdapter(adapter);
   
   
           // Setup onItemTouchHandler to enable drag and drop , swipe left or right
           ItemTouchHelper.Callback callback = new RVHItemTouchHelperCallback(adapter, true, true,
                   true);
           ItemTouchHelper helper = new ItemTouchHelper(callback);
           helper.attachToRecyclerView(myrecyclerview);
   
           // Set the divider in the recyclerview
           myrecyclerview.addItemDecoration(new RVHItemDividerDecoration(this, LinearLayoutManager.VERTICAL));
   
           // Set On Click Listener
           myrecyclerview.addOnItemTouchListener(new RVHItemClickListener(this, new RVHItemClickListener.OnItemClickListener() {
               @Override
               public void onItemClick(View view, int position) {
                   String value = "Clicked Item " + data.get(position) + " at " + position;
   
                   Log.d("TAG", value);
                   Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
               }
           }));
   
       }
   }


```

--

# Pull Requests
I welcome and encourage all pull requests. It usually will take me within 24-48 hours to respond to any issue or request. Here are some basic rules to follow to ensure timely addition of your request:
  1. Match coding style (braces, spacing, etc.) This is best achieved using CMD+Option+L (Reformat code) on Mac (not sure for Windows) with Android Studio defaults.
  2. If its a feature, bugfix, or anything please only change code to what you specify.
  3. Please keep PR titles easy to read and descriptive of changes, this will make them easier to merge :)
  4. Pull requests _must_ be made against `develop` branch. Any other branch (unless specified by the maintainers) will get rejected.
  5. Check for existing [issues](https://github.com/nisrulz/qreader/issues) first, before filing an issue.  
  6. Have fun!

### Created & Maintained By
[Nishant Srivastava](https://github.com/nisrulz) ([@nisrulz](https://www.twitter.com/nisrulz))

>Special Credits to Paul Burke and his [article](https://medium
.com/@ipaulpro/drag-and-swipe-with-recyclerview-b9456d2b1aaf#.bk2e4q81b) which got me thinking 
>
>This library contains a modified version of his implementations of ItemTouchHelper.


License
=======

    Copyright 2016 Nishant Srivastava

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
