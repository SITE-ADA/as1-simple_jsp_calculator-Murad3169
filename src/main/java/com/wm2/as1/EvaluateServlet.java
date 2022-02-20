package com.wm2.as1;

import com.sun.org.apache.regexp.internal.RE;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EvaluateServlet", value = "/Evaluate-Servlet")
public class EvaluateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> data = getBrowserAndOS(request);
        request.setAttribute("infoB",data.get(0));
        request.setAttribute("infoO",data.get(1));
        RequestDispatcher rd=request.getRequestDispatcher("information.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int answer = 0;
        String n1 = request.getParameter("N1");
        String n2 = request.getParameter("N2");
        if (n1==null||n2==null&&n1.equals("")||n2.equals("")){
            request.setAttribute("e","Fill all empty inputs.....");
            RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
        try {
            int n1Int = Integer.parseInt(n1);
            int n2Int = Integer.parseInt(n2);
            String symbol = request.getParameter("symbol");
            if (symbol.equals("+"))
                answer = n1Int + n2Int;
            else if (symbol.equals("-"))
                answer = n1Int - n2Int;

            else if (symbol.equals("*"))
                answer = n1Int * n2Int;
            else if (symbol.equals("/")&&n2Int!=0)
                answer = n1Int / n2Int;
            else {
                request.setAttribute("e","Number 2 must be zero.....");
                RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }

            request.setAttribute("answer",answer);
            RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
        catch (Exception exception){
            request.setAttribute("e","Only integer number.....");
            RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }

    public List<String> getBrowserAndOS(HttpServletRequest request){

        List<String> data = new ArrayList<>();
        String  browserDetails  =   request.getHeader("User-Agent");
        String  userAgent       =   browserDetails;
        String  user            =   userAgent.toLowerCase();

        String os = "";
        String browser = "";

        //=================OS=======================
        if (userAgent.toLowerCase().indexOf("windows") >= 0 )
        {
            os = "Windows";
        } else if(userAgent.toLowerCase().indexOf("mac") >= 0)
        {
            os = "Mac";
        } else if(userAgent.toLowerCase().indexOf("x11") >= 0)
        {
            os = "Unix";
        } else if(userAgent.toLowerCase().indexOf("android") >= 0)
        {
            os = "Android";
        } else if(userAgent.toLowerCase().indexOf("iphone") >= 0)
        {
            os = "IPhone";
        }else{
            os = "UnKnown, More-Info: "+userAgent;
        }
        //===============Browser===========================
        if (user.contains("msie"))
        {
            String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if ( user.contains("opr") || user.contains("opera"))
        {
            if(user.contains("opera"))
                browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            else if(user.contains("opr"))
                browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
        } else if (user.contains("chrome"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) )
        {
            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            browser = "Netscape-?";

        } else if (user.contains("firefox"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if(user.contains("rv"))
        {
            browser="IE-" + user.substring(user.indexOf("rv") + 3, user.indexOf(")"));
        } else
        {
            browser = "UnKnown, More-Info: "+userAgent;
        }

        data.add(browser);
        data.add(os);
        return data;
    }

}
