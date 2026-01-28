package pdprof.ra;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEvent;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;

public class PdprofManagedConnection implements ManagedConnection {

	private static Logger log = Logger.getLogger("PdprofManagedConnection");
	private PdprofManagedConnectionFactory mcf;
	private PrintWriter logWriter;
	private List<ConnectionEventListener> listeners;
	private Object connection;
	private PdprofXAResource xaResource;
	
	public PdprofManagedConnection(PdprofManagedConnectionFactory mcf) {
		this.mcf = mcf;
		this.logWriter = null;
		this.listeners = new ArrayList<ConnectionEventListener>(1);
		this.connection = null;
		this.xaResource = new PdprofXAResource(this);
	}

	public Object getConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
		connection = new PdprofConnectionImpl(this, mcf);
		return connection;
	}

	public void associateConnection(Object connection) throws ResourceException {
		this.connection = connection;
	}

	public void cleanup() throws ResourceException {
	}

	public void destroy() throws ResourceException {
		this.connection = null;
	}

	public void addConnectionEventListener(ConnectionEventListener listener) {
		if (listener == null)
			throw new IllegalArgumentException("Listener is null");
		listeners.add(listener);
	}

	public void removeConnectionEventListener(ConnectionEventListener listener) {
		if (listener == null)
			throw new IllegalArgumentException("Listener is null");
		listeners.remove(listener);
	}

	public PrintWriter getLogWriter() throws ResourceException {
		return logWriter;
	}

	public void setLogWriter(PrintWriter out) throws ResourceException {
		this.logWriter = out;
	}

	public LocalTransaction getLocalTransaction() throws ResourceException {
		throw new NotSupportedException("LocalTransaction not supported");
	}

	public XAResource getXAResource() throws ResourceException {
        return this.xaResource;
	}

	public ManagedConnectionMetaData getMetaData() throws ResourceException {
		return new PdprofManagedConnectionMetaData();
	}

	void closeHandle(PdprofConnection handle) {
		ConnectionEvent event = new ConnectionEvent(this, ConnectionEvent.CONNECTION_CLOSED);
		event.setConnectionHandle(handle);
		for (ConnectionEventListener cel : listeners) {
		
			cel.connectionClosed(event);
		}
	}
	
	public int getPrepareTimeout() {
		 PdprofConnectionImpl con = (PdprofConnectionImpl)this.connection;
		 return con.getPrepareTime();
	}
	
	public int getCommitTimeout() {
		 PdprofConnectionImpl con = (PdprofConnectionImpl)this.connection;
		 return con.getCommitTime();
	}

}
