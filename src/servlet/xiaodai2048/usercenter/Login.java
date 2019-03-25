package servlet.xiaodai2048.usercenter;

import Utils.JDBCUtils;
import com.alibaba.fastjson.JSON;
import servlet.BaseResponse;
import servlet.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User2048 user = selectUser(username, password);
        BaseResponse baseResponse = null;
        if (user != null) {
            baseResponse = BaseResponse.success(user);
        } else {
            baseResponse = BaseResponse.error(500);
        }
        String string = JSON.toJSONString(baseResponse);
        out.println(string);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User2048 user = selectUser(username, password);
        BaseResponse baseResponse = null;
        if (user != null) {
            baseResponse = BaseResponse.success(user);
        } else {
            baseResponse = BaseResponse.error(500);
        }
        String string = JSON.toJSONString(baseResponse);
        out.println(string);
    }

    /**
     * 登陆请求接口
     *
     * @param username
     * @param password
     * @return
     */
    private User2048 selectUser(String username, String password) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from user where username=? and password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            User2048 userBean = null;
            while (resultSet.next()) {
                userBean = new User2048();
                userBean.setUsername(resultSet.getString("username"));
                userBean.setPhoneNumber(resultSet.getString("phone_number"));
                userBean.setId(resultSet.getInt("user_id"));
                userBean.setGold(resultSet.getInt("gold"));
            }
            return userBean;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
