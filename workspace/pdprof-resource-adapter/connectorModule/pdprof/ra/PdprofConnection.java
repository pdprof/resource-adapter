package pdprof.ra;

import javax.resource.cci.Connection;

public interface PdprofConnection extends Connection {
	public String hello();
	public String hello(String msg);
	public void close();
	public void setPrepareTime(int prepareTimeout);
	public int getPrepareTime();
	public void setCommitTime(int commitTimeout);
	public int getCommitTime();
}
