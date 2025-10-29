package pdprof.ra;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.resource.ResourceException;
import javax.resource.cci.Connection;
import javax.resource.cci.ConnectionSpec;
import javax.resource.cci.RecordFactory;
import javax.resource.cci.ResourceAdapterMetaData;
import javax.resource.spi.ConnectionManager;

public class PdprofConnectionFactoryImpl implements PdprofConnectionFactory {

	private static final long serialVersionUID = 1L;
	private Reference reference;

	private PdprofManagedConnectionFactory mcf;
	private ConnectionManager connectionManager;
		
	public PdprofConnectionFactoryImpl(PdprofManagedConnectionFactory mcf, ConnectionManager cxManager) {
		this.mcf = mcf;
		this.connectionManager = cxManager;
	}
	
	@Override
	public Connection getConnection() throws ResourceException {
		return (Connection) connectionManager.allocateConnection(mcf, null);
	}
	
	@Override
	public void setReference(Reference arg0) {
		this.reference = reference;
	}

	@Override
	public Reference getReference() throws NamingException {
		return reference;
	}

	@Override
	public Connection getConnection(ConnectionSpec arg0) throws ResourceException {
		return null;
	}

	@Override
	public ResourceAdapterMetaData getMetaData() throws ResourceException {
		return null;
	}

	@Override
	public RecordFactory getRecordFactory() throws ResourceException {
		return null;
	}

}
