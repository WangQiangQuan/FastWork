package fast.wq.com.fastandroid.mvp;

import fast.wq.com.fastandroid.mvp.model.IModel;
import fast.wq.com.fastandroid.mvp.view.IViewInterface;

/**
 * Created by wangqiang on 2017/8/22.
 */

public class ImplPresenter extends BasePresenter<IViewInterface>{
    IViewInterface view = getView();
    IModel model = null;
    public void setIModel(IModel model){
        this.model = model;
    }
    public void login(){
        view.showLoading();
        model.loadDatas();
        view.hideLoading();
    }
}
