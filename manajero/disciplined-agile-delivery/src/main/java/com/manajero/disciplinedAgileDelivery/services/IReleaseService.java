package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.Release;

import java.util.List;

public interface IReleaseService {
    List<Release> getAllReleases(String idProject);
    Release addRelease(Release release,String idProject);
    Release editRelease(Release release);
    void deleteRelease(String id);
    Release getReleaseById (String id);


}
