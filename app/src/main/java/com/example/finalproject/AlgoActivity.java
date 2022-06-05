package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import org.xguzm.pathfinding.grid.NavigationGrid;
import org.xguzm.pathfinding.grid.finders.AStarGridFinder;
import org.xguzm.pathfinding.grid.finders.GridFinderOptions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AlgoActivity extends AppCompatActivity {
    Connection connection;
    Bundle bundle;
    int listId;
    ArrayList<NaviProduct> naviProducts;

    private static final int GRID_SIZE = 100;


    GridView gridView;
    RecyclerView gridRv;
    SupermarketMapAdapter rvAdapter;
    //    String[] number = new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        naviProducts = new ArrayList<>();
        bundle = new Bundle();
        bundle = getIntent().getExtras();
        listId = 0;//TODO: bundle.getInt("listId");
        System.out.println("listId:" + listId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algo);
        findViewById(R.id.stamBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Cell> cells = parseMap(randomCells());
                Matrix matrix = new Matrix(10, cells);
                rvAdapter.setMatrix(matrix);
            }

            private String randomCells() {

                return "" +
                        "NNNNNNNNNN" +
                        "NNNNNNBNNN" +
                        "NNNNNNBNNN" +
                        "NNNNNNBNNN" +
                        "NNNNNNNNNN" +
                        "NNNNNNNNNN" +
                        "NNNNNNNNNN" +
                        "NNNNNNNNNN" +
                        "NNNNNNNNNN" +
                        "NNNNNNNNNN";
            }
        });


//        gridView = findViewById(R.id.gridViewAlgo);
        gridRv = findViewById(R.id.gridViewAlgo);

        // TODO: 05/06/2022 restore
//        ConSQL c = new ConSQL();
//        connection = c.conclass();
//        try {
//            createProductArray(naviProducts, connection);
////            createBarrierArray(barriers, connection);
////            for (int i = 0; i < barriers.size(); i++) {
////                isBarrier.add(barriers.get(i).getIsBarrier());
////            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        System.out.println("isBarrier list:");

//        for (int i = 0; i < isBarrier.size(); i++) {
//            System.out.println(isBarrier.get(i));
//        }

//        gridView.setAdapter(new BaseAdapter() {
//            @Override
//            public int getCount() {
//                return number.length;
//            }
//
//            @Override
//            public Object getItem(int position) {
//                return number[position];
//            }
//
//            @Override
//            public long getItemId(int position) {
//                return position;
//            }
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                View v;
//
//                if (convertView != null) {
//                    v = convertView;
//                } else {
//                    v = getLayoutInflater().inflate(R.layout.cell, null);
//
//                    v.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            v.setBackgroundColor(Color.RED);
//                        }
//                    });
//                }
//                v.setMinimumHeight(parent.getHeight()/10);
//
//
//                if (number[position].equals("B")) {
//                    v.setBackgroundColor(Color.BLACK);
//                }
//
//                return v;
//            }
//        });

        String mapWithBarriesItems = "" +
                "NNNNNNNNNN" +
                "NNbNNNNNNN" +
                "NNbNNNNNNN" +
                "NNbNNNNNNN" +
                "NNbNNNNNNN" +
                "NNbNNNNNNN" +
                "NNbNNNNNNN" +
                "NNbNNNNNNN" +
                "NNbNNNNNNN" +
                "NNNNNNNNNN";

        int nColumns = 10;
        gridRv.setLayoutManager(new GridLayoutManager(this, GRID_SIZE / nColumns));

        List<Cell> cells = parseMap(mapWithBarriesItems);

        Matrix m = new Matrix(10, cells);

//        Cell[][] zells = new Cell[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                char symbol = mapWithBarriesItems.charAt(i * 10 + j);
//                zells[i][j] = new Cell(Cell.Type.fromSymbol(symbol));
//            }
//        }

        int startX = 0;
        int startY = 0;

        int nextItemX = 9;
        int nextItemY = 9;
        char symbol = 'I';
        m.get(nextItemX, nextItemY).setType(Cell.Type.fromSymbol(symbol));

        NavigationGrid<Cell> grid = new NavigationGrid<>(m.asMatrix(), true);

        GridFinderOptions options = new GridFinderOptions();
        options.allowDiagonal = true;

        AStarGridFinder<Cell> finder = new AStarGridFinder<>(Cell.class, options);

        List<Cell> path = finder.findPath(startX, startY, nextItemX, nextItemY, grid);

        String pathStr = path.stream().map(c -> "(" + c.x + "," + c.y + ")")
                .reduce((a, b) -> a + ", " + b).get();

        System.out.println("Path:" + pathStr);
//                .collect(Collectors.toList());
//        int r = rand.nextInt(10);
//        int c = rand.nextInt(10);
//        m.set(r,c, );

        rvAdapter = new SupermarketMapAdapter(m, nColumns);
        gridRv.setItemAnimator(null);

        gridRv.setAdapter(rvAdapter);

//        gridView.onview
//        for (int i = 0; i < number.length; i++) {
//            if (number[i].equals("B")) {
//                gridView.getChildAt(i).setBackgroundColor(Color.BLACK);
//            }
//
//        }
    }

    @NonNull
    private List<Cell> parseMap(String fromDb) {
        List<Character> split = Arrays.stream(fromDb.split("")).map(a -> a.charAt(0)).collect(Collectors.toList());
//        List<String> symbols = Arrays.asList(split);

        List<Cell> cells = split
                .stream()
                .map(Cell.Type::fromSymbol)
                .map(Cell::new)
                .collect(Collectors.toList());


        return cells;
    }

    private void createProductArray(ArrayList<NaviProduct> naviProducts, Connection connection) throws SQLException {
        String query = " select item_id, name, amount, " +
                "loc_x, loc_y,loc_z, itemDesc " +
                "from user_list_items join items" +
                " on user_list_items.item_id=items.itemID" +
                " where list_id='39'";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            String name = rs.getString("name");
            int loc_x = rs.getInt("loc_x");
            int loc_y = rs.getInt("loc_y");
            int loc_z = rs.getInt("loc_z");
            int amount = rs.getInt("amount");
            int id = rs.getInt("item_id");
            NaviProduct np = new NaviProduct(name, loc_x, loc_y, loc_z, id,
                    amount);
            naviProducts.add(np);
        }
        System.out.println("Products:");
        for (int i = 0; i < naviProducts.size(); i++) {
            System.out.println(naviProducts.get(i).id);
        }
    }

    private void createBarrierArray(ArrayList<Cell> cells,
                                    Connection connection) {
        for (int i = 0; i < 10; i++) {
            int startX = i;
            int startY = i;
            int endX = i;
            int endY = i;
//            Cell cell = new Cell();
//            Random random = new Random();
//            cell.setIsBarrier(random.nextBoolean());
//            cells.add(cell);
        }
//        System.out.println("Barriers: ");
//        for (int i = 0; i < cells.size(); i++) {
//            System.out.println(cells.get(i).isBarrier);
//        }
    }
}