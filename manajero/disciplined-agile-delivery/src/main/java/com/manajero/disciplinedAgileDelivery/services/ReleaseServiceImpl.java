package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.Feature;
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
        release.setIsArchived(false);
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

    @Override
    public double calculateReleasePredictability(String projectId) {
        List<Release> releases = releaseRepository.findAllByProject_ProjectId(projectId);
        long onTimeReleases = releases.stream()
                .filter(release -> "Released".equals(release.getStatus())) // Assuming status contains "On-Time" for successful releases
                .count();

        if (releases.isEmpty()) {
            return 0.0;
        }

        return (double) onTimeReleases / releases.size() * 100;
    }

    @Override
    public void archiveRelease(String id) {
        Release release = releaseRepository.findById(id).orElse(null);
        release.setIsArchived(true);
        releaseRepository.save(release);
    }

    @Override
    public void restoreRelease(String id) {
        Release release = releaseRepository.findById(id).orElse(null);
        release.setIsArchived(false);
        releaseRepository.save(release);
    }
}
