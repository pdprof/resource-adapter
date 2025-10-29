package pdprof.ra;

import java.util.logging.Logger;

import javax.resource.ResourceException;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.BootstrapContext;
import javax.resource.spi.ConfigProperty;
import javax.resource.spi.Connector;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.resource.spi.TransactionSupport;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.transaction.xa.XAResource;

@Connector(reauthenticationSupport = false, transactionSupport = TransactionSupport.TransactionSupportLevel.NoTransaction)
public class PdprofResourceAdapter implements ResourceAdapter {
	
	private static Logger log = Logger.getLogger("PdprofResourceAdapter");
	@ConfigProperty(defaultValue = "Liberty", supportsDynamicUpdates = true)
	private String name;

	public PdprofResourceAdapter() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void endpointActivation(MessageEndpointFactory endpointFactory, ActivationSpec spec)
			throws ResourceException {
	}

	public void endpointDeactivation(MessageEndpointFactory endpointFactory, ActivationSpec spec) {
	}

	public void start(BootstrapContext ctx) throws ResourceAdapterInternalException {
	}

	public void stop() {
	}

	public XAResource[] getXAResources(ActivationSpec[] specs) throws ResourceException {
		return null;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof PdprofResourceAdapter))
			return false;
		PdprofResourceAdapter obj = (PdprofResourceAdapter) other;
		boolean result = true;
		if (result) {
			if (name == null)
				result = obj.getName() == null;
			else
				result = name.equals(obj.getName());
		}
		return result;
	}

}
