package lean.toc.manajerobackend.services.toc_services;

import lean.toc.manajerobackend.models.toc_models.TocInstanceProject;

public interface ITocInstanceProjectService {
    public TocInstanceProject GetTocInstanceProject_By_ProjectId(String refProjectId1);

    public TocInstanceProject UpdateTocInstanceProject(TocInstanceProject tocInstanceProject1);

   public TocInstanceProject UpdateTocInstanceProjectAfterAddingTask(String TocInstanceProject_Id);

}
