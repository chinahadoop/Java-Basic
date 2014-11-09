package cn.chinahadoop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Test the cookie functionalities in servlet way.
 *
 */
public class CookieTest extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String userName = req.getParameter("username");
		String ret = null;
		if (userName != null && userName.length() > 0) {
			Cookie cookie = new Cookie("userName", userName);
			res.addCookie(cookie);
			ret = "Cookie added";
		} else {
			
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("userName".equals(cookie.getName())) {
						ret = "Hello " + cookie.getValue();
					}
				}
			}
		}
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<body>");
		out.print(ret);
		out.print("</body>");
	}
}
