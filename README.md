![Image](/img/github_banner.png)

### Specs
[ ![Download](https://api.bintray.com/packages/nisrulz/maven/com.github.nisrulz%3Arecyclerviewhelper/images/download.svg) ](https://bintray.com/nisrulz/maven/com.github.nisrulz%3Arecyclerviewhelper/_latestVersion) [![API](https://img.shields.io/badge/API-9%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=9)

### Badges/Featured In
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-RecyclerViewHelper-green.svg?style=true)](https://android-arsenal.com/details/1/3572) [![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23221-blue.svg)](http://androidweekly.net/issues/issue-221) [![AndroidDev Digest](https://img.shields.io/badge/AndroidDev%20Digest-%2399-blue.svg)](https://www.androiddevdigest.com/digest-99/)

**Also included in**
+ [Awesome Android Newsletter #Issue 21](https://android.libhunt.com/newsletter/21)

### Show some :heart:
[![GitHub stars](https://img.shields.io/github/stars/nisrulz/recyclerviewhelper.svg?style=social&label=Star)](https://github.com/nisrulz/recyclerviewhelper) [![GitHub forks](https://img.shields.io/github/forks/nisrulz/recyclerviewhelper.svg?style=social&label=Fork)](https://github.com/nisrulz/recyclerviewhelper/fork) [![GitHub watchers](https://img.shields.io/github/watchers/nisrulz/recyclerviewhelper.svg?style=social&label=Watch)](https://github.com/nisrulz/recyclerviewhelper) [![GitHub followers](https://img.shields.io/github/followers/nisrulz.svg?style=social&label=Follow)](https://github.com/nisrulz/recyclerviewhelper)  
[![Twitter Follow](https://img.shields.io/twitter/follow/nisrulz.svg?style=social)](https://twitter.com/nisrulz) 

RecyclerViewHelper provides the most common functions around recycler view like Swipe
 to dismiss, Drag and Drop, Divider in the ui, events for when item selected and when not 
 selected, on-click listener for items.

![Walkthrough](https://github.com/nisrulz/recyclerviewhelper/blob/develop/img/walkthrough1.gif)

#Integration
RecyclerViewHelper is available in the Jcenter, so getting it as simple as adding it as a dependency
```gradle
// Required , so make sure your support libs are updated in the android sdk
compile "com.android.support:appcompat-v7:{latest version}'
compile "com.android.support:recyclerview-v7:{latest version}'

// RecyclerViewHelper
compile 'com.github.nisrulz:recyclerviewhelper:{latest version}'
```
where `{latest version}` corresponds to published version in [ ![Download](https://api.bintray.com/packages/nisrulz/maven/com.github.nisrulz%3Arecyclerviewhelper/images/download.svg) ](https://bintray.com/nisrulz/maven/com.github.nisrulz%3Arecyclerviewhelper/_latestVersion)

> NOTE : The version here corresponds to the version of recyclerview dependency.

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

>Special Credits to Paul Burke and his [article](https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-b9456d2b1aaf) which got me thinking
>
>This library contains a modified version of his implementations of ItemTouchHelper.


> If you found this library helpful or you learned something from the source code and want to thank me, onsider [buying me a cup of](https://www.paypal.me/nisrulz) :coffee:


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

