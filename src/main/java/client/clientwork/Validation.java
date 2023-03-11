package client.clientwork;

public class Validation {

    public boolean validationInt(String str) {
        boolean flag = true;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < 48 || str.charAt(i) > 57)
                flag = false;
        }

        return flag;
    }
}
