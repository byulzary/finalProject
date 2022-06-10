package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

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
import java.util.stream.Collectors;

public class AlgoActivity extends AppCompatActivity {
    Connection connection;
    Bundle bundle;
    int listId;
    List<NaviProduct> naviProducts;
    int numOfCollectedItems = 0;
    String dbMap = "";

    private static final int GRID_SIZE = 100;


    RecyclerView gridRv;
    SupermarketMapAdapter rvAdapter;
    private List<Cell> startMap;
    private List<Cell> path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ConSQL c = new ConSQL();
        connection = c.conclass();
        bundle = new Bundle();
        bundle = getIntent().getExtras();
        listId = bundle.getInt("listId");
        naviProducts = new ArrayList<>();
        System.out.println("listId:" + listId);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            naviProducts = createProductArray(naviProducts, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algo);


        try {
            dbMap = getMapFromDB(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(dbMap);

//        String dbSupermarketMap = "" +
//                "NbNNbNNNbN" +
//                "NbNNbNNNbN" +
//                "NbNNbNNNbN" +
//                "NNNNNNNNNN" +
//                "NbNNbNNNbN" +
//                "NbNNbNNNbN" +
//                "NbNNbNNNbN" +
//                "NbbbbNNNbN" +
//                "NbNNNNNNbN" +
//                "NbNNNNNNbN";

        findViewById(R.id.stamBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numOfCollectedItems++;
                if (numOfCollectedItems <= naviProducts.size()) {


                    Matrix matrix = new Matrix(10, parseMap(dbMap));
                    Cell previousItem = path.get(path.size() - 1);
                    NaviProduct np = naviProducts.get(numOfCollectedItems % naviProducts.size());
                    int targetX = np.loc_x;
                    int targetY = np.loc_y;
                    int startX = previousItem.x;
                    int startY = previousItem.y;
                    prepareMatrix(matrix, startX, startY, targetX, targetY);
                    List<Cell> path = getPath(matrix, previousItem.x, previousItem.y, targetX, targetY);
                    drawPath(path);
                    rvAdapter.setMatrix(matrix);
                } else {

                }
            }

        });


        gridRv = findViewById(R.id.gridViewAlgo);

        // TODO: 05/06/2022 restore
//
//        try {
//            createProductArray(naviProducts,connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        createProductArray(naviProducts,connection);
//        try {
//            createProductArray(naviProducts, connection);
////            createBarrierArray(barriers, connection);
////            for (int i = 0; i < barriers.size(); i++) {
////                isBarrier.add(barriers.get(i).getIsBarrier());
////            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }


        int nColumns = 10;
        gridRv.setLayoutManager(new GridLayoutManager(this, GRID_SIZE / nColumns));


        startMap = parseMap(dbMap);

        Matrix matrix = new Matrix(10, startMap);

        int startX = 9;
        int startY = 5;

        NaviProduct firstItem = naviProducts.get(0);
        int nextItemX = firstItem.loc_x;
        int nextItemY = firstItem.loc_y;

        matrix.get(nextItemX, nextItemY).setType(Cell.Type.Item);

        List<Cell> path = getPath(matrix, startX, startY, nextItemX, nextItemY);

        this.path = path;
        matrix.get(startX, startY).setType(Cell.Type.Start);
        drawPath(path);

//                .collect(Collectors.toList());
//        int r = rand.nextInt(10);
//        int c = rand.nextInt(10);
//        m.set(r,c, );

        rvAdapter = new SupermarketMapAdapter(matrix, nColumns);
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

    private String getMapFromDB(Connection connection) throws SQLException {
        String query = "select map from supermarket where id='1'";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        String map = "";
        while (rs.next()) {
            map = rs.getString("map");
        }
        return map;
    }

    private void prepareMatrix(Matrix matrix, int startX, int startY, int targetX, int targetY) {
        matrix.get(startX, startY).setType(Cell.Type.Start);
        for (int i = 1; i < path.size() - 1; i++) {
            Cell cell = path.get(i);
            if (cell.getType() == Cell.Type.Path) {
                cell.setType(Cell.Type.None);
            }
        }
        matrix.get(targetX, targetY).setType(Cell.Type.Item);
    }

    private void drawPath(List<Cell> path) {
        String pathStr = path.stream().map(c -> "(" + c.x + "," + c.y + ")")
                .reduce((a, b) -> a + ", " + b).get();

        System.out.println("Path:" + pathStr);
        for (int i = 0; i < path.size() - 1; i++) {
            Cell c = path.get(i);
            c.setType(Cell.Type.Path);
        }

    }

    private List<Cell> getPath(Matrix m, int startX, int startY, int targetX, int targetY) {
        NavigationGrid<Cell> grid = new NavigationGrid<>(m.asNativeMatrix(), true);

        GridFinderOptions options = new GridFinderOptions();
        options.allowDiagonal = false;

        AStarGridFinder<Cell> finder = new AStarGridFinder<>(Cell.class, options);

        return finder.findPath(startX, startY, targetX, targetY, grid);
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

    private List<NaviProduct> createProductArray(List<NaviProduct> naviProducts,
                                                 Connection connection) throws SQLException {
//        List<NaviProduct> s = new ArrayList<>();
//        s.add(new NaviProduct("", 0, 0, 0, 0, 0));
//        s.add(new NaviProduct("", 0, 5, 0, 0, 0));
//        s.add(new NaviProduct("", 5, 3, 0, 0, 0));
//        s.add(new NaviProduct("", 9, 9, 0, 0, 0));
//       // return s;
        String query = " select item_id, name, amount, " +
                "loc_x, loc_y,loc_z, itemDesc " +
                "from user_list_items join items" +
                " on user_list_items.item_id=items.itemID" +
                " where list_id=" + listId;

        Statement stmt = connection.createStatement();
//        PreparedStatement stmt = connection.prepareStatement(query);
//        stmt.setInt(1, listId);
        int index = 0;
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
            naviProducts.add(index, np);
            index++;
        }
        System.out.println("Products:");
        for (int i = 0; i < naviProducts.size(); i++) {
            System.out.println(naviProducts.get(i).id);
        }
        return naviProducts;
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