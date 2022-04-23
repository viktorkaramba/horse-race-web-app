package unicyb.horseracingservice.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import unicyb.horseracingservice.database.DatabaseConnection;
import unicyb.horseracingservice.entity.Bet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@WebServlet(urlPatterns ="/bets")
public class RestTaskServlet extends HttpServlet {
    private Map<Integer, Bet> bets;

//    @Override
//    public void init() throws ServletException {
//        final Object bets = getServletContext().getAttribute("bets");
//        if (!(bets instanceof ConcurrentHashMap)) {
//            throw new IllegalStateException("...");
//        }else {
//            this.bets = (ConcurrentHashMap<Integer, Bet>) bets;
//        }
//    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        try {
            log.info("Get request {}, on URI {}", req.getMethod(), req.getRequestURI());
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement st = con
                    .prepareStatement("insert into SECTIONS values(?, ?)");
            st.setInt(1, Integer.valueOf(req.getParameter("ID")));
            st.setString(2, req.getParameter("NAME"));
            st.executeUpdate();
            st.close();
            con.close();
            PrintWriter out = resp.getWriter();
            out.println("<html><body><b>Successfully Inserted"
                    + "</b></body></html>");
        }
        catch (Exception e){
            e.printStackTrace();
        }
//        log.info("Get request {}, on URI {}", req.getMethod(), req.getRequestURI());
//        String pathInfo = req.getPathInfo();
//        String[] parts = pathInfo.split("/");
//        String param1 = parts[1];
//        log.info("Id {}", param1);
//        int betId = Integer.parseInt(param1);
//        req.setCharacterEncoding("UTF-8");
//        log.info("int id {}", betId);
//        Bet bet = bets.get(betId);
//        log.info("bet {}", bet);
//        final String jsonTask = new ObjectMapper().writeValueAsString(bet);
//        log.info("string bet {}", jsonTask);
//        resp.setContentType("application/json; charset=UTF-8");
//        resp.setStatus(200);
//        PrintWriter out = resp.getWriter();
//        out.write(jsonTask);
    }
}
