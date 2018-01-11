package Listener;

import Model.BaseModel;

/**
 * Created by jigsaw on 7/1/18.
 */

public interface CallBackListener {
    void handleSuccessData(BaseModel resModel);
    void handleZeroData(BaseModel reqModel);
    void handleErrorDataFromServer(BaseModel errorModel);
    void networkConnectionError();
    void handleInvalidData(BaseModel reqModel);
}
