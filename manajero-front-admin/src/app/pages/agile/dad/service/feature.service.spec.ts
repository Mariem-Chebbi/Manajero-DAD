import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { FeatureService } from './feature.service';

describe('FeatureService', () => {
    let service: FeatureService;
    let httpMock: HttpTestingController;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
            providers: [FeatureService]
        });

        service = TestBed.inject(FeatureService);
        httpMock = TestBed.inject(HttpTestingController);
    });

    afterEach(() => {
        httpMock.verify();
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });

    it('should add a feature', () => {
        const mockFeature = { name: 'New Feature' };
        const projectId = '123';
        const mockResponse = { success: true };

        service.add(mockFeature, projectId).subscribe(response => {
            expect(response).toEqual(mockResponse);
        });

        const req = httpMock.expectOne(`${service.apiurl}/add?idProject=${projectId}`);
        expect(req.request.method).toBe('POST');
        req.flush(mockResponse);
    });

    it('should get all features', () => {
        const mockId = '123';
        const mockFeatures = [{ name: 'Feature1' }, { name: 'Feature2' }];

        service.getAll(mockId).subscribe(features => {
            expect(features).toEqual(mockFeatures);
        });

        const req = httpMock.expectOne(`${service.apiurl}/getAll/${mockId}`);
        expect(req.request.method).toBe('GET');
        req.flush(mockFeatures);
    });

    it('should delete a feature', () => {
        const mockId = '123';
        const mockResponse = { success: true };

        service.delete(mockId).subscribe(response => {
            expect(response).toEqual(mockResponse);
        });

        const req = httpMock.expectOne(`${service.apiurl}/delete/${mockId}`);
        expect(req.request.method).toBe('DELETE');
        req.flush(mockResponse);
    });

    it('should edit a feature', () => {
        const mockFeature = { name: 'Updated Feature' };
        const mockResponse = { success: true };

        service.edit(mockFeature).subscribe(response => {
            expect(response).toEqual(mockResponse);
        });

        const req = httpMock.expectOne(`${service.apiurl}/edit`);
        expect(req.request.method).toBe('PUT');
        req.flush(mockResponse);
    });

    it('should get features by release', () => {
        const mockId = '123';
        const mockFeatures = [{ name: 'Feature1' }, { name: 'Feature2' }];

        service.getByRelease(mockId).subscribe(features => {
            expect(features).toEqual(mockFeatures);
        });

        const req = httpMock.expectOne(`${service.apiurl}/get/release/${mockId}`);
        expect(req.request.method).toBe('GET');
        req.flush(mockFeatures);
    });
});
