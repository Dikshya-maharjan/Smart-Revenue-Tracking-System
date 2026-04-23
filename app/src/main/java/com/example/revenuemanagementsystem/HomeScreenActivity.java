package com.example.revenuemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeScreenActivity extends AppCompatActivity {

    CardView addTransactionCard, viewCard, reportCard;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    TextView tvTotalSales, tvTotalExpenses, tvProfit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Views
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);

        addTransactionCard = findViewById(R.id.cardAddTransaction);
        viewCard = findViewById(R.id.cardViewTransaction);
        reportCard = findViewById(R.id.cardViewReports);

        // Dashboard TextViews
        tvTotalSales = findViewById(R.id.tvTotalSales);
        tvTotalExpenses = findViewById(R.id.tvTotalExpenses);
        tvProfit = findViewById(R.id.tvProfit);

        // Add Transaction click
        addTransactionCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreenActivity.this, AddTransactionActivity.class);
            startActivity(intent);
        });
        //view report click
        viewCard.setOnClickListener(v->{
            Intent intent=new Intent(HomeScreenActivity.this,ViewTransactionActivity.class);
            startActivity(intent);
        });
        // Toolbar setup
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.open, R.string.close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Navigation menu
        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_home) {

                updateDashboard();

            } else if (id == R.id.nav_add) {

                startActivity(new Intent(this, AddTransactionActivity.class));

            } else if (id == R.id.nav_view) {

                Toast.makeText(this, "View Transaction", Toast.LENGTH_SHORT).show();

            } else if (id == R.id.nav_report) {

                Toast.makeText(this, "Reports", Toast.LENGTH_SHORT).show();

            } else if (id == R.id.nav_logout) {

                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
            }

            drawerLayout.closeDrawers();
            return true;
        });

        // Load dashboard on start
        updateDashboard();
    }

    // 🔥 Dashboard calculation
    private void updateDashboard() {

        double sales = 0;
        double expenses = 0;

        for (Transaction t : DataStore.transactionList) {

            if (t.type.equals("Sales")) {
                sales += t.amount;
            } else if (t.type.equals("Expense")) {
                expenses += t.amount;
            }
        }

        double profit = sales - expenses;

        tvTotalSales.setText("Total Sales: " + sales);
        tvTotalExpenses.setText("Total Expenses: " + expenses);
        tvProfit.setText("Profit: " + profit);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDashboard(); // refresh when coming back from Add screen
    }
}