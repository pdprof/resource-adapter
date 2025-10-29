package pdprof.ra;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionDefinition;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.ResourceAdapter;
import javax.security.auth.Subject;

// ConnectionDefinition を指定すると ra.xml の指定は必要なくなる。JCA1.7より
@ConnectionDefinition(connectionFactory = PdprofConnectionFactory.class, connectionFactoryImpl = PdprofConnectionFactoryImpl.class, connection = PdprofConnection.class, connectionImpl = PdprofConnectionImpl.class)
public class PdprofManagedConnectionFactory implements ManagedConnectionFactory {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("PdprofManagedConnectionFactory");
	private ResourceAdapter ra;
	private PrintWriter logwriter;
	
	public PdprofManagedConnectionFactory() {
		this.ra = null;
		this.logwriter = null;
	}
	
	public Object createConnectionFactory() throws ResourceException {
		throw new ResourceException("This resource adapter doesn't support non-managed environments");
	}

	public Object createConnectionFactory(ConnectionManager cxManager) throws ResourceException {
		return new PdprofConnectionFactoryImpl(this, cxManager);
	}

	public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo cxRequestInfo)
			throws ResourceException {
		return new PdprofManagedConnection(this);
	}

	public ManagedConnection matchManagedConnections(Set connectionSet, Subject subject,
			ConnectionRequestInfo cxRequestInfo) throws ResourceException {
		ManagedConnection result = null;

		Iterator it = connectionSet.iterator();
		while (result == null && it.hasNext()) {
			ManagedConnection mc = (ManagedConnection) it.next();
			if (mc instanceof PdprofManagedConnection) {
				PdprofManagedConnection hwmc = (PdprofManagedConnection) mc;
				result = hwmc;
			}
		}

		return result;
	}

	public PrintWriter getLogWriter() throws ResourceException {
		return logwriter;
	}

	public void setLogWriter(PrintWriter out) throws ResourceException {
		logwriter = out;
	}

	public ResourceAdapter getResourceAdapter() {
		return ra;
	}

	public void setResourceAdapter(ResourceAdapter ra) {
		this.ra = ra;
	}

	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof PdprofManagedConnectionFactory))
			return false;
		PdprofManagedConnectionFactory obj = (PdprofManagedConnectionFactory) other;
		boolean result = true;
		return result;
	}

}
