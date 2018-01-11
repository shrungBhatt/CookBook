package Utility;

/**
 * Created by admin on 25/11/2016.
 */

public class MyEnum {
    public static enum requestError {
        connectionError,
        completeData,
        updateapplication
    }

    public enum ChatLoginStatus
    {
        Connecting,
        Connected,
        ReConnected,
        Disconnected
    }
    public enum SessionLoginStatus
    {
        Disconnected,
        Connecting,
        Connected,
    }
    public enum MediaType
    {
        None,
        Image,
        Video
    }
    public static enum isMasking
    {
        mask,
        notmask
    }
    public enum ShowProgressbar{
        show,
        doNotShow
    }
    public static enum displayProgress
    {
        //Use for showing a ProgressDialog
        Show,
        //Use for not showing a ProgressDialog
        NotShow,
        //Not Currently used
        Backshow
    }
}
