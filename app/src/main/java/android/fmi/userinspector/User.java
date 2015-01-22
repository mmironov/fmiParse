package android.fmi.userinspector;

/**
 * Created by mmironov on 1/22/15.
 */
public class User {
    private String email;

    private int messageCount;

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final int atIndex = email.indexOf('@');

        return email.substring(0, atIndex);
    }
}
