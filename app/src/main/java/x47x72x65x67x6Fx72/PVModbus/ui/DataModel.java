package x47x72x65x67x6Fx72.PVModbus.ui;

/**
 * (View)Models that implement this Interface may be updatable and can fetch Data from managed source
 */
public interface DataModel {
    /**
     * Fetches Data from managed source and updates (View)Model
     */
    void fetchData();
}
