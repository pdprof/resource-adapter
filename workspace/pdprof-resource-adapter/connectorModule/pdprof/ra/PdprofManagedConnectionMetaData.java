package pdprof.ra;

import javax.resource.ResourceException;
import javax.resource.spi.ManagedConnectionMetaData;


public class PdprofManagedConnectionMetaData implements ManagedConnectionMetaData {

	@Override
	public String getEISProductName() throws ResourceException {
		return "Pdprof Resource Adapter";
	}

	@Override
	public String getEISProductVersion() throws ResourceException {
		return "20251028";
	}

	@Override
	public int getMaxConnections() throws ResourceException {
		return 0;
	}

	@Override
	public String getUserName() throws ResourceException {
		return null;
	}

}