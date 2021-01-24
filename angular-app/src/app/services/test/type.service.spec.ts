import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient } from '@angular/common/http';
import { fakeAsync, getTestBed, TestBed, tick } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { TypeService } from '../type.service';
import { statusCodeModel } from '../../model/auth-model';
import { AllTypesModel, TypeModel } from '../../model/type-model';

describe('TypeService', () => {

    let injector;
    let service: TypeService;
    let httpMock: HttpTestingController;
    let httpClient: HttpClient;
    let router: any;

    let routerMock = {
        navigate: jasmine.createSpy('navigate')
    };

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule, RouterTestingModule],
            providers: [
                TypeService,
                { provide: Router, useValue: routerMock }
            ]
        });
        injector = getTestBed();
        service = TestBed.inject(TypeService);
        httpClient = TestBed.inject(HttpClient);
        httpMock = TestBed.inject(HttpTestingController);
        router = TestBed.inject(Router);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });

    it('save() should create new type', () => {
        //@ts-ignore
        let addObj: AllTypesModel = {
            "id": -1,
            "name": "asdasdsasa",
            "categoryId": 17,
            "categoryName": "Empty"
        }
        let mockResponse = {
            "id": 45,
            "name": "asdasdsasa",
            "categoryId": 17,
            "categoryName": "Institution"
        }
        service.save(addObj).subscribe((res) => {
            expect(res).toEqual(mockResponse);
        });

        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-types/');
        expect(req.request.method).toBe('POST');
        req.flush(mockResponse);
    });

    it('save() should not create type with existing name', () => {
        //@ts-ignore
        let addObj: AllTypesModel = {
            "id": -1,
            "name": "Museum",
            "categoryId": 17,
            "categoryName": "Empty"
        }

        let mockResponse = {
            "headers": {
                "normalizedNames": {},
                "lazyUpdate": null
            },
            "status": 400,
            "statusText": "OK",
            "url": "http://localhost:8080/cultural-offer-types/",
            "ok": false,
            "name": "HttpErrorResponse",
            "message": "Http failure response for http://localhost:8080/cultural-offer-types/: 400 OK",
            "error": null
        }
        service.save(addObj).subscribe((res) => {
            expect(res).toEqual(mockResponse);
        });

        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-types/');
        expect(req.request.method).toBe('POST');
        req.flush(mockResponse);
    });


    it('getByPage() should return types by page', () => {
        let mockResponse = {
            "content": [
                {
                    "id": 9,
                    "name": "Parliament",
                    "categoryId": 17,
                    "categoryName": "Institution"
                },
                {
                    "id": 10,
                    "name": "Museum",
                    "categoryId": 17,
                    "categoryName": "Institution"
                },
                {
                    "id": 11,
                    "name": "Tower",
                    "categoryId": 19,
                    "categoryName": "Landmark"
                },
                {
                    "id": 12,
                    "name": "Stadium",
                    "categoryId": 19,
                    "categoryName": "Landmark"
                }
            ],
            "totalElements": 4,
            "last": true,
            "totalPages": 1,
            "size": 10,
            "number": 0,
            "numberOfElements": 4,
            "first": true,
            "empty": false,
            "pageNumber": 0,
            "pageSize": 10
        };
        service.getByPage(1).subscribe((res: any) => {
            expect(res).toEqual(mockResponse);
        });
        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-types/byPage/1');
        expect(req.request.method).toBe('GET');
        req.flush(mockResponse);
    });

    it('getTypesForCategory() should return types for given category', () => {
        let typesObj = "17";
        let mockResponse = [
            {
              "id": 9,
              "name": "Parliament",
              "categoryId": 17,
              "categoryName": "Institution"
            },
            {
              "id": 10,
              "name": "Museum",
              "categoryId": 17,
              "categoryName": "Institution"
            },
            {
              "id": 47,
              "name": "asdasdsa",
              "categoryId": 17,
              "categoryName": "Institution"
            }
          ];

          service.getTypesForCategory(typesObj).subscribe((res: any) => {
            expect(res).toEqual(mockResponse);
        });
        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-types/byCategory/'+typesObj);
        expect(req.request.method).toBe('GET');
        req.flush(mockResponse);
    });

    it('deleteType() should delete type', fakeAsync(() => {
        let deleteObj = {
            "id": 46,
            "name": "asdsasasa",
            "categoryId": 17,
            "categoryName": "Institution"
        };
        let mockResponse = null;
        let res: any;

        res = service.deleteType(deleteObj.id).subscribe((data) => {
          res = data;
        })

        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-types/46');
        expect(req.request.method).toBe('DELETE');
        req.flush(mockResponse);

        tick();

        expect(res).toBeNull();
    }));

    it('deleteType() should not delete used type', () => {
        let deleteObj = {
            "id": 10,
            "name": "Museum",
            "categoryId": 17,
            "categoryName": "Institution"
        }

        let mockResponse = {
            "headers": {
                "normalizedNames": {},
                "lazyUpdate": null,
                "headers": {}
            },
            "status": 400,
            "statusText": "OK",
            "url": "http://localhost:8080/cultural-offer-types/10",
            "ok": false,
            "name": "HttpErrorResponse",
            "message": "Http failure response for http://localhost:8080/cultural-offer-types/10: 400 OK",
            "error": null
        }

        service.deleteType(deleteObj.id).subscribe((res: any) => {
            expect(res).toEqual(mockResponse);
        });
        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-types/' + deleteObj.id);
        expect(req.request.method).toBe('DELETE');
        req.flush(mockResponse);
    });

    it('getByName() should return type by name', () => {
        let searchObj = "Museum";
        let mockResponse = {
            "id": 10,
            "name": "Museum",
            "categoryId": 17,
            "categoryName": "Institution"
        };

        service.getByName(searchObj).subscribe((res: any) => {
            expect(res).toEqual(mockResponse);
        });
        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-types/name/' + searchObj);
        expect(req.request.method).toBe('GET');
        req.flush(mockResponse);
    });

    it('getByName() should not return type that does not exist', () => {
        let searchObj = "adasas";
        let mockResponse = {
            "headers": {
                "normalizedNames": {},
                "lazyUpdate": null,
                "headers": {}
            },
            "status": 404,
            "statusText": "OK",
            "url": "http://localhost:8080/cultural-offer-types/name/asdas",
            "ok": false,
            "name": "HttpErrorResponse",
            "message": "Http failure response for http://localhost:8080/cultural-offer-types/name/asdas: 404 OK",
            "error": null
        };
        service.getByName(searchObj).subscribe((res: any) => {
            expect(res).toEqual(mockResponse);
        });
        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-types/name/' + searchObj);
        expect(req.request.method).toBe('GET');
        req.flush(mockResponse);
    });

    it('updateType() should update type', () => {
        let updateObj = {
            "name": "asdasdsa",
            "id": 47,
            "categoryId": 17,
            "categoryName": "Institution"
        };

        let mockResponse = {
            "id": 47,
            "name": "asdasdsa",
            "categoryId": 17,
            "categoryName": "Institution"
        };

        service.updateType(updateObj).subscribe((res: any) => {
            expect(res).toEqual(mockResponse);
        });
        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-types/' + updateObj.id);
        expect(req.request.method).toBe('PUT');
        req.flush(mockResponse);
    });

    it('updateType() should not update type with existing name', () => {
        let updateObj = {
            "name": "Museum",
            "id": 47,
            "categoryId": 17,
            "categoryName": "Institution"
          };

        let mockResponse = {
            "headers": {
              "normalizedNames": {},
              "lazyUpdate": null
            },
            "status": 400,
            "statusText": "OK",
            "url": "http://localhost:8080/cultural-offer-types/47",
            "ok": false,
            "name": "HttpErrorResponse",
            "message": "Http failure response for http://localhost:8080/cultural-offer-types/47: 400 OK",
            "error": null
          };

        service.updateType(updateObj).subscribe((res: any) => {
            expect(res).toEqual(mockResponse);
        });
        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-types/' + updateObj.id);
        expect(req.request.method).toBe('PUT');
        req.flush(mockResponse);
    });
});
