package pdprof.ra;

import java.io.Serializable;

import javax.resource.Referenceable;
import javax.resource.ResourceException;
import javax.resource.cci.Connection;
import javax.resource.cci.ConnectionFactory;

public interface PdprofConnectionFactory extends Serializable, Referenceable, ConnectionFactory {
	public Connection getConnection() throws ResourceException;
}
