package Controller;

import org.json.JSONObject;

import Listener.CallBackListener;
import Model.BaseModel;
import Parser.BaseParser;

/**
 * Created by jigsaw on 7/1/18.
 */

public class Controller_SearchQuery extends BaseController {


    @Override
    public void onPopulate(JSONObject jsonObject, BaseParser baseParser) {

    }

    @Override
    public void startFetching(CallBackListener callBackListener, BaseModel baseModel) {
        super.startFetching(callBackListener, baseModel);

        this.mCallBackListener = callBackListener;
        this.mBaseModel = baseModel;

    }
}
