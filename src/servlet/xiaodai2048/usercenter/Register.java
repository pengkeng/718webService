package servlet.xiaodai2048.usercenter;

import Utils.JDBCUtils;
import com.alibaba.fastjson.JSON;
import servlet.BaseResponse;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "Register")
public class Register extends HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phone_number");
        BaseResponse baseResponse = null;
        if (username == null) {
            baseResponse = BaseResponse.error(502);
        } else if (password == null) {
            baseResponse = BaseResponse.error(503);
        } else if (phoneNumber == null) {
            baseResponse = BaseResponse.error(504);
        } else {
            User2048 user = regisUser(username, password, phoneNumber);
            if (user != null) {
                baseResponse = BaseResponse.success(user);
            } else {
                baseResponse = BaseResponse.error(501);
            }
        }
        String string = JSON.toJSONString(baseResponse);
        out.println(string);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phone_number");
        BaseResponse baseResponse = null;
        if (username == null) {
            baseResponse = BaseResponse.error(502);
        } else if (password == null) {
            baseResponse = BaseResponse.error(503);
        } else if (phoneNumber == null) {
            baseResponse = BaseResponse.error(504);
        } else {
            User2048 user = regisUser(username, password, phoneNumber);
            if (user != null) {
                baseResponse = BaseResponse.success(user);
            } else {
                baseResponse = BaseResponse.error(501);
            }
        }
        String string = JSON.toJSONString(baseResponse);
        out.println(string);
    }

    /**
     * 登陆请求接口
     *
     * @param username
     * @param password
     * @param phoneNumber
     * @return
     */
    private User2048 regisUser(String username, String password, String phoneNumber) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "SELECT * FROM user WHERE phone_number=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            User2048 userBean = null;
            while (resultSet.next()) {
                return null;
            }
            String InsertSql = "INSERT INTO user(username,password,gold,phone_number)VALUES (?,?,?,?)";
            PreparedStatement InsertPreparedStatement = connection.prepareStatement(InsertSql);
            InsertPreparedStatement.setString(1, username);
            InsertPreparedStatement.setString(2, password);
            InsertPreparedStatement.setInt(3, 0);
            InsertPreparedStatement.setString(4, phoneNumber);
            InsertPreparedStatement.executeUpdate();
            userBean = new User2048();
            userBean.setUsername(username);
            return userBean;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
