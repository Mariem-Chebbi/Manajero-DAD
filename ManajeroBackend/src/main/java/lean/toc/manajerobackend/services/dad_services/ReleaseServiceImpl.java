package lean.toc.manajerobackend.services.dad_services;


import lean.toc.manajerobackend.models.dad_models.Feature;
import lean.toc.manajerobackend.models.dad_models.Release;
import lean.toc.manajerobackend.repositories.dad_repositories.FeatureRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.ProjectRepository;
import lean.toc.manajerobackend.repositories.dad_repositories.ReleaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReleaseServiceImpl implements IReleaseService{
    private ReleaseRepository releaseRepository;
    private ProjectRepository projectRepository;
    private FeatureRepository featureRepository;

    @Override
    public List<Release> getAllReleases(String idProject) {
        for (Release release: releaseRepository.findAllByProject_ProjectId(idProject)){
            this.calculateReleaseProgress(release);
        }
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
                .filter(release -> "Released".equals(release.getStatus()) && release.getIsArchived() == false) // Assuming status contains "On-Time" for successful releases
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

    @Override
    public float calculateReleaseProgress(Release release) {
        List<Feature> features = featureRepository.findAllByRelease_ReleaseId(release.getReleaseId());
        long totalFeatures = features.size();
        long completedFeatures = features.stream().filter(f -> "Done".equals(f.getStatus())).count();

        if (totalFeatures == 0) {
            return 0; // Avoid division by zero
        }

        // Cast to double for accurate division, multiply by 100, and round to 1 decimal place
        float progress = (float) Math.round(((double) completedFeatures / totalFeatures) * 1000) / 10;

        release.setProgres(progress);
        releaseRepository.save(release);
        return progress;
    }
}
