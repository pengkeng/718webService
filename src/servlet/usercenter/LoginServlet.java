package servlet.usercenter;

import Utils.JDBCUtils;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends javax.servlet.http.HttpServlet {

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserBean user = selectUser(username, password);
        BaseResponse baseResponse = null;
        if (user != null) {
            baseResponse = BaseResponse.success(user);
        } else {
            baseResponse = BaseResponse.error("没有此用户");
        }
        JSONObject json = JSONObject.fromObject(baseResponse);
        out.println(json.toString());
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserBean user = selectUser(username, password);
        BaseResponse baseResponse = null;
        if (user != null) {
            baseResponse = BaseResponse.success(user);
        } else {
            baseResponse = BaseResponse.error("没有此用户");
        }
        JSONObject json = JSONObject.fromObject(baseResponse);
        out.println(json.toString());
    }

    /**
     * 登陆请求接口
     * @param username
     * @param password
     * @return
     */
    private UserBean selectUser(String username, String password) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from user_info where username=? and password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserBean userBean = null;
            while (resultSet.next()) {
                userBean = new UserBean();
                userBean.setUsername(resultSet.getString("username"));
                userBean.setPhoneNumber(resultSet.getString("phone_num"));
                userBean.setId(resultSet.getInt("id"));
            }
            return userBean;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
