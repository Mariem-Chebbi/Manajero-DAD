package lean.toc.manajerobackend.services.dad_services;


import lean.toc.manajerobackend.models.dad_models.Release;

import java.util.List;

public interface IReleaseService {
    List<Release> getAllReleases(String idProject);
    Release addRelease(Release release,String idProject);
    Release editRelease(Release release);
    void deleteRelease(String id);
    Release getReleaseById (String id);
    double calculateReleasePredictability(String projectId);
    void archiveRelease(String id);
    void restoreRelease(String id);
    float calculateReleaseProgress(Release release);

}
