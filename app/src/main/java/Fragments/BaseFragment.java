package Fragments;

import android.support.v4.app.Fragment;

import Listener.CallBackListener;
import Model.BaseModel;

/**
 * Created by jigsaw on 7/1/18.
 */

public abstract class BaseFragment extends Fragment implements CallBackListener {

    @Override
    public void handleSuccessData(BaseModel resModel) {

    }

    @Override
    public void handleZeroData(BaseModel reqModel) {

    }

    @Override
    public void handleErrorDataFromServer(BaseModel errorModel) {

    }

    @Override
    public void networkConnectionError() {

    }

    @Override
    public void handleInvalidData(BaseModel reqModel) {

    }
}
