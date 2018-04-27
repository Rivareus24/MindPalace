package com.okeechobee.rivareus24.mindpalace.C___RecyclerView_Toast_Swipe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.okeechobee.rivareus24.mindpalace.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements RecyclerViewAdapter.ListItemClickListener {

    private Toast mToast;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView rvGroceries;

    private List<Product> groceries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        groceries = new ArrayList<>();
        for (int i = 0; i < 25; i++)
            groceries.add(new Product("Name" + i, "Price" + i, "desc " + i));

        rvGroceries = (RecyclerView) findViewById(R.id.rvGroceries);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        rvGroceries.setLayoutManager(layoutManager);

        mAdapter = new RecyclerViewAdapter(30, this, groceries);

        rvGroceries.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                viewHolder.itemView.setBackgroundColor(Color.GREEN);
            }
        }).attachToRecyclerView(rvGroceries);

        /**
         * Swipe -> elemento sparisce
         * elemento esce dallo schermo
         * elemento rientra nello schermo con il colore verde
         */
        // TODO Nova

    }



    @Override
    public void OnListItemClick(int clickedItemId) {
        // TODO Fragment

        if (mToast != null) mToast.cancel();

        String toastMessage = "Item #" + clickedItemId + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }

    public class Product{
        private String name;
        private String price;
        private String desc;

        public Product(String name, String price, String desc) {
            this.name = name;
            this.price = price;
            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
