package x47x72x65x67x6Fx72.PVModbus.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * AbstractClass of ViewModels that manage data that might be updated and fetched
 *
 * @param <DtoClass> managed data (data-class)
 */
public abstract class ViewModelTemplate<DtoClass> extends ViewModel implements DataModel {
    // DATA fetched from API
    protected MutableLiveData<DtoClass> api_data = new MutableLiveData<>();

    // DATA passed to fragment -> observer
    public LiveData<DtoClass> data = api_data;

    // Notification tracker
    protected MutableLiveData<String> api_notify = new MutableLiveData<>();

    // Notification passed to fragment -> observer
    public LiveData<String> notify = api_notify;

    public abstract void fetchData();
}