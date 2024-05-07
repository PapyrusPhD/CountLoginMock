import java.util.Date;

public class AgentCounter {
    private String agentCounts;
    private Date Lockout_DT;
    private Date Unlock_DT;

    public String getAgentCounts() {
        return agentCounts;
    }
    public void setAgentCounts(String agentCounts) {
        this.agentCounts = agentCounts;
    }
    public Date getLockout_DT() {
        return Lockout_DT;
    }
    public void setLockout_DT(Date lockout_DT) {
        Lockout_DT = lockout_DT;
    }
    public Date getUnlock_DT() {
        return Unlock_DT;
    }
    public void setUnlock_DT(Date unlock_DT) {
        Unlock_DT = unlock_DT;
    }
}
