package pdprof.ra;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

public class PdprofXAResource implements XAResource {
	PdprofManagedConnection pdcon = null;
	int transactionTimeout = 0;
	public PdprofXAResource(PdprofManagedConnection pdprofManagedConnection) {
		pdcon = pdprofManagedConnection;
	}

	@Override
	public void commit(Xid xid, boolean onePhase) throws XAException {
		System.out.println("> PdprofXAResource.commit start (sleep time in ms = " + pdcon.getCommitTimeout() + ")");
		try {
			if (pdcon != null) {
				Thread.sleep(pdcon.getCommitTimeout());
			}
		} catch (Exception e) {
		}
		System.out.println("< PdprofXAResource.commit end id = " + xid.getFormatId() + " isOnePhase = " + onePhase);
	}

	@Override
	public void end(Xid xid, int flags) throws XAException {
		System.out.println("= PdprofXAResource.end - " + xid.getFormatId() + " flags = " + flags);
	}

	@Override
	public void forget(Xid xid) throws XAException {
		System.out.println("= PdprofXAResource.forget - " + xid.getFormatId());
	}

	@Override
	public int getTransactionTimeout() throws XAException {
		System.out.println("= PdprofXAResource.getTransactionTimeout value = " + transactionTimeout);
		return transactionTimeout;
	}

	@Override
	public boolean isSameRM(XAResource xares) throws XAException {
		return (xares instanceof PdprofXAResource);
	}

	@Override
	public int prepare(Xid xid) throws XAException {
		System.out.println("> PdprofXAResource.prepare start (sleep time in ms = " + pdcon.getPrepareTimeout() + ")");
		try {
			Thread.sleep(pdcon.getPrepareTimeout());
		} catch (Exception e) {
		}
		System.out.println("< PdprofXAResource.prepare end id = " + xid.getFormatId());
		return XA_OK;
	}

	@Override
	public Xid[] recover(int flag) throws XAException {
		System.out.println("= PdprofXAResource.recover");
		return null;
	}

	@Override
	public void rollback(Xid xid) throws XAException {
		System.out.println("= PdprofXAResource.rollback - " + xid.getFormatId());
	}

	@Override
	public boolean setTransactionTimeout(int seconds) throws XAException {
		System.out.println("= PdprofXAResource.setTransactionTimeout value = " + seconds);
		transactionTimeout = seconds;
		return true;
	}

	@Override
	public void start(Xid xid, int flags) throws XAException {
		System.out.println("= PdprofXAResource.start - " + xid.getFormatId() + " flags = " + flags);
	}

}
