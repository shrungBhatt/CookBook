package Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 29/12/2017.
 */

public class ErrorModel extends BaseModel {

        @SerializedName("ERROR_CD")
        private String errorCode;
        @SerializedName("ERROR_TITLE")
        private String errorTitle;
        @SerializedName("ERROR_MSG")
        private String errorMessage;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorTitle() {
            return errorTitle;
        }

        public void setErrorTitle(String errorTitle) {
            this.errorTitle = errorTitle;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

}
