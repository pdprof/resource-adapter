package pdprof.ra;

import java.util.logging.Logger;

import javax.resource.ResourceException;
import javax.resource.cci.ConnectionMetaData;
import javax.resource.cci.Interaction;
import javax.resource.cci.LocalTransaction;
import javax.resource.cci.ResultSetInfo;

public class PdprofConnectionImpl implements PdprofConnection {

	private static Logger log = Logger.getLogger("PdprofConnectionImpl");
	private PdprofManagedConnection mc;
	private PdprofManagedConnectionFactory mcf;
	private int prepareTime = 0;
	private int commitTime= 0;

	public PdprofConnectionImpl(PdprofManagedConnection mc, PdprofManagedConnectionFactory mcf) {
		this.mc = mc;
		this.mcf = mcf;
	}
	
	@Override
	public String hello() {
		System.out.println("= PdprofConnectionImpl.hello()");
		return "Hello!";
	}

	@Override
	public String hello(String msg) {
		System.out.println("= PdprofConnectionImpl.hello(msg)");
		return msg;
	}

	@Override
	public void setPrepareTime(int prepareTime) {
		this.prepareTime = prepareTime;
	}

	@Override
	public int getPrepareTime() {
		return prepareTime;
	}
	
	@Override
	public void setCommitTime(int commitTime) {
		this.commitTime = commitTime;
	}
	
	@Override
	public int getCommitTime() {
		return commitTime;
	}
	@Override
	public void close() {
		System.out.println("= PdprofConnectionImpl.close()");
	}

	@Override
	public Interaction createInteraction() throws ResourceException {
		return null;
	}

	@Override
	public LocalTransaction getLocalTransaction() throws ResourceException {
		return null;
	}

	@Override
	public ConnectionMetaData getMetaData() throws ResourceException {
		return null;
	}

	@Override
	public ResultSetInfo getResultSetInfo() throws ResourceException {
		return null;
	}

}
