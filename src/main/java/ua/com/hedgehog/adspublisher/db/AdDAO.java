package ua.com.hedgehog.adspublisher.db;

import ua.com.hedgehog.adspublisher.model.Ad;

public interface AdDAO {
    void insert(Ad ad);

    void update(Ad ad);

    void delete(int adId);

    Ad find(int adId);
}
