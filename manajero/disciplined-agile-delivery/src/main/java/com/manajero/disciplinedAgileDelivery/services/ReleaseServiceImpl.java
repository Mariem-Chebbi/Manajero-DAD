package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.Release;
import com.manajero.disciplinedAgileDelivery.repository.ProjectRepository;
import com.manajero.disciplinedAgileDelivery.repository.ReleaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReleaseServiceImpl implements IReleaseService{
    private ReleaseRepository releaseRepository;
    private ProjectRepository projectRepository;

    @Override
    public List<Release> getAllReleases(String idProject) {
        return releaseRepository.findAllByProject_ProjectId(idProject);
    }

    @Override
    public Release addRelease(Release release,String idProject) {
        release.setProject(projectRepository.findById(idProject).orElse(null));
        return releaseRepository.save(release);
    }

    @Override
    public Release editRelease(Release release) {
        return releaseRepository.save(release);
    }

    @Override
    public void deleteRelease(String id) {
        releaseRepository.deleteById(id);
    }

    @Override
    public Release getReleaseById(String id) {
        return releaseRepository.findById(id).orElse(null);
    }
}
