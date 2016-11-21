package ru.kpfu.itis.group11501.utkin.Helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.kpfu.itis.group11501.utkin.Configs.JDBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 18.11.2016.
 */
public class Poisk extends javax.servlet.http.HttpServlet {
    private Connection conn;


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String q = request.getParameter("q");
        if (q != null) {
            try {
                PreparedStatement st = JDBConnection.getInstance().getConnection().prepareStatement(
                        "select nickname from users where users.nickname LIKE ?"
                );
                st.setString(1, q + "%");
                ResultSet rs = st.executeQuery();
                List<String> names = new ArrayList<String>();
                JSONArray ja = new JSONArray();
                while (rs.next()) {
                    ja.put(rs.getString("nickname"));
                }
                JSONObject jo = new JSONObject();
                jo.put("result", ja);
                response.setContentType("text/json");
                response.setContentType("text/html; charset=utf-8");
                response.getWriter().println(jo.toString());

            } catch (SQLException | JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
