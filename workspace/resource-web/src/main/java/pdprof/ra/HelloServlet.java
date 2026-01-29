package pdprof.ra;

import java.io.IOException;

import javax.annotation.Resource;
import javax.resource.ResourceException;
import javax.resource.cci.ConnectionFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource(name="ra/pdprofcf")
	ConnectionFactory cf;
	
	@Resource(name="ra/secondcf")
	ConnectionFactory secondcf;
	
    @Resource
    private UserTransaction ut;
    
    private int sleepTime = 0;
    private int prepareTime = 0;
    private int commitTime = 0;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sleepTime = getTime(request.getParameter("sleepTime"));
		prepareTime = getTime(request.getParameter("prepareTime"));
		commitTime = getTime(request.getParameter("commitTime"));
		System.out.println("> HelloServlet.doGet start");
		try {
			System.out.println("= HelloServlet.doGet call   > ut.begin()");
			ut.begin();
			System.out.println("= HelloServlet.doGet called < ut.begin()");
			System.out.println("= HelloServlet.doGet call   > sleep time in ms = " + sleepTime);
			Thread.sleep(sleepTime);
			System.out.println("= HelloServlet.doGet called < sleep");
			PdprofConnection con = (PdprofConnection)cf.getConnection();
			con.setPrepareTime(prepareTime);
			con.setCommitTime(commitTime);
			response.getWriter().append("Message from Resource Adapter : ").append(con.hello()).append("\n");
			PdprofConnection con2 = (PdprofConnection)secondcf.getConnection();
			con2.setPrepareTime(prepareTime);
			con2.setCommitTime(commitTime);
			response.getWriter().append("Message from Resource Adapter : ").append(con2.hello("!!!!"));
			System.out.println("= HelloServlet.doGet call   > ut.commit()");
			ut.commit();
			System.out.println("= HelloServlet.doGet called < ut.commit()");
		} catch (ResourceException | NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException | InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("< HelloServlet.doGet end");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private int getTime(String timeStr) {
		if (timeStr != null) {
			try {
				return Integer.parseInt(timeStr);
			} catch (Exception e) {
				return 0;
			}
		} else {
			return 0;
		}
	}
	
}
