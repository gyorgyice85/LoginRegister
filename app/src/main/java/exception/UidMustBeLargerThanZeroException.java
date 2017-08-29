package exception;

/**
 * Created by Joshi on 11.08.2017.
 */

public class UidMustBeLargerThanZeroException extends Throwable {
    public UidMustBeLargerThanZeroException(String str) {
        super(str);
    }
}
