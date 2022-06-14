package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.nio.channels.InterruptedByTimeoutException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NewListActivity extends AppCompatActivity {
    Connection connection;

    private ArrayList<Product> productsList;
    public ArrayList<UserProduct> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    public Button saveBtn;
    Bundle bundle;
    private int uid;
    String listName = "";
    int choice = 0;
    int listId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getIntent().getExtras();
        uid = bundle.getInt("uid");
        System.out.println(uid + "|uid new list");
        setContentView(R.layout.activity_new_list);
        productsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewProducts);
        ConSQL c = new ConSQL();
        connection = c.conclass();
        saveBtn = findViewById(R.id.saveListButton);

        try {
            setProductInfo(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        setAdapter();
        AlertDialog.Builder builder = new AlertDialog.Builder(NewListActivity.this);
        AlertDialog.Builder builderAlgo = new AlertDialog.Builder(NewListActivity.this);

        findViewById(R.id.saveListButton).setOnClickListener(v -> {

            builderAlgo.setTitle("Navigation");
            builderAlgo.setMessage("Do you wish to navigate now?");
            builderAlgo.setPositiveButton("Navigate", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sendToAlgo(listId);
                }
            });
            builderAlgo.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builderAlgo.show();
            builder.setTitle("Input List Name");
            final EditText input = new EditText(NewListActivity.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listName = input.getText().toString();
                    try {
                        listId = saveListToDb(connection, listName);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();


        });

        System.out.println("choice: " + choice);
    }

    private void sendToAlgo(int listId) {
        Bundle bundle = new Bundle();
        bundle.putInt("listId", listId);
        bundle.putInt("uid", uid);
        Intent intent = new Intent(this, AlgoActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private int saveListToDb(Connection connection, String listName) throws SQLException {


        //count user_lists
        int userListsCount = 0;
        String query = "select * from user_lists";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            userListsCount++;
        }
        System.out.println("user_lists count: " + userListsCount);
        userListsCount++;
        //insert into user_lists
        query = "insert into user_lists(list_id," +
                " name)"
                + " values" +
                "(?,?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, userListsCount);
        pstmt.setString(2, listName);
        pstmt.executeUpdate();

        //count user_lists_rel
        int userListRelCount = 0;
        query = "select * from user_lists_rel where user_id=" + uid;
        stmt = connection.createStatement();
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            userListRelCount++;
        }
        System.out.println("user_lists_rel count: " + userListRelCount);
        userListRelCount++;

        //insert into user_lists_rel
        query = "insert into user_lists_rel(id, user_id, list_id) values (?,?,?)";
        PreparedStatement pStmt = connection.prepareStatement(query);
        pStmt.setInt(1, userListRelCount);
        pStmt.setInt(2, uid);
        pStmt.setInt(3, userListsCount);
        pStmt.executeUpdate();

        //count user_list_items
        int userListItemCount = 0;
        query = "select * from user_list_items";
        stmt = connection.createStatement();
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            userListItemCount++;
        }
        userListItemCount++;
        //insert into user_list_items
        for (int i = 0; i < RecyclerAdapter.userList.size(); i++) {

            query =
                    "insert into user_list_items(id, list_id, item_id, amount) values(" + userListItemCount +
                            "," + userListsCount + "," +
                            RecyclerAdapter.userList.get(i).getId() + "," + RecyclerAdapter.userList.get(i).getAmount() +
                            ")";
            Statement st;
            st = connection.createStatement();
            st.executeUpdate(query);
            userListItemCount++;
        }
        return userListsCount;
    }

    private void setAdapter() {
        RecyclerAdapter adatper = new RecyclerAdapter(productsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adatper);
    }

    private void setProductInfo(Connection connection) throws SQLException {
        String statement = "select it.name, item_id, it.price, it.itemDesc from " +
                "(supermarket super inner join supermarket_items s_i on super.id=s_i.super_id inner join items as it on s_i.item_id=it.itemID) where super.id='1'";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(statement);
        while (rs.next()) {
            String pName = rs.getString("name");
            int pID = rs.getInt("item_id");
            String pDesc = rs.getString("itemDesc");
            float pPrice = rs.getFloat("price");
            Product p = new Product(pName, pDesc, pID, pPrice);
            productsList.add(p);

        }

    }
}