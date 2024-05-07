import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        boolean canLogin = true;
        String username;
        String password;
        Scanner sc = new Scanner(System.in);

        while (canLogin){
            System.out.println("Input Username:");
            username = sc.nextLine();
            System.out.println("Input Password:");
            password = sc.nextLine();

            CheckLoginCount(username, password);
        }
    }
    public static boolean CheckLoginCount(String username, String password) throws ParseException {
        Login login = new Login();
        AgentCounter agentCounter = new AgentCounter();
        boolean checkPassword = Arrays.asList(login.getPassword()).contains(password);
        Date date = new Date();

        //Check if agentCounts is null (required only in this project)
        if (agentCounter.getAgentCounts() == null){
            checkNullAgentCounts(agentCounter);
        }
        //Check if lockout and unlock is null (required only in this project)
        if (agentCounter.getUnlock_DT() == null && agentCounter.getLockout_DT() == null){
            checkNullAgentDates(agentCounter);
        }
        boolean isLockout = date.after(agentCounter.getLockout_DT()) && date.before(agentCounter.getUnlock_DT());

        if (checkPassword){
            if (isLockout){
                System.out.println("You are still locked until " + agentCounter.getUnlock_DT());
            }
            else {
                System.out.println("Approved");
                agentCounter.setAgentCounts("0");
            }
        }
        else {
            if (isLockout){
                System.out.println("You are still locked until " + agentCounter.getUnlock_DT());
                return false;
            }
            else if (Integer.parseInt(agentCounter.getAgentCounts()) == 0){
                agentCounter.setAgentCounts("1");
                System.out.println("Agent count: " + agentCounter.getAgentCounts());
                return true;
            }
            else if (Integer.parseInt(agentCounter.getAgentCounts()) == 1){
                agentCounter.setAgentCounts("2");
                System.out.println("Agent count: " + agentCounter.getAgentCounts());
                return true;
            }
            else if (Integer.parseInt(agentCounter.getAgentCounts()) == 2) {
                agentCounter.setAgentCounts("3");
                System.out.println("Agent count: " + agentCounter.getAgentCounts());
                return true;
            }
            else if (Integer.parseInt(agentCounter.getAgentCounts()) >= 3){
                System.out.println("Agent count: " + agentCounter.getAgentCounts());
                LockLogin(agentCounter);
                return false;
            }
        }
        return false;
    }
    public static void LockLogin(AgentCounter agentCounter) throws ParseException {
        //Current Date
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Date currentDate = inputFormat.parse(String.valueOf(date));

        //After 3 Days Date
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 3);
        String afterThreeDaysString = inputFormat.format(c.getTime());
        Date afterThreeDays = inputFormat.parse(String.valueOf(afterThreeDaysString));

        //Setting lockout and unlock Date
        agentCounter.setLockout_DT(currentDate);
        System.out.println("Lockout Date: " + String.valueOf(date));
        agentCounter.setUnlock_DT(afterThreeDays);
        System.out.println("Unlock Date: " + afterThreeDaysString);
    }

    //Only in this program
    public static void checkNullAgentCounts(AgentCounter agentCounter){
        agentCounter.setAgentCounts("0");
    }

    public static void checkNullAgentDates(AgentCounter agentCounter) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date mockDate = inputFormat.parse("1900-01-01");
        agentCounter.setUnlock_DT(mockDate);
        agentCounter.setLockout_DT(mockDate);
    }
}