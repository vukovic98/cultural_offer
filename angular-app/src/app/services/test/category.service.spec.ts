import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient } from '@angular/common/http';
import { fakeAsync, getTestBed, TestBed, tick } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { CategoryService } from '../category.service';
import { statusCodeModel } from '../../model/auth-model';
import { CategoryModel, TypeModel } from '../../model/category-model';

describe('CategoryService', () => {

    let injector;
    let service: CategoryService;
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
                CategoryService,
                { provide: Router, useValue: routerMock }
            ]
        });
        injector = getTestBed();
        service = TestBed.inject(CategoryService);
        httpClient = TestBed.inject(HttpClient);
        httpMock = TestBed.inject(HttpTestingController);
        router = TestBed.inject(Router);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });

    it('getCategories() should return categories', fakeAsync(() => {
        let res: any;

        let mockResponse = [
            {
                "id": 17,
                "name": "Institution",
                "types": [
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
                ]
            },
            {
                "id": 18,
                "name": "Manifestacion",
                "types": []
            },
            {
                "id": 19,
                "name": "Landmark",
                "types": [
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
                ]
            },
        ];

        service.getCategories().subscribe((data: any) => {
            res = data;
            expect(data).toEqual(mockResponse);
        });

        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-categories/');
        expect(req.request.method).toBe('GET');
        req.flush(mockResponse);
    }))

    it('getCategoriesByPage() should return categories', fakeAsync(() => {
        let res: any;

        let mockResponse = {
            "content": [
                {
                    "id": 17,
                    "name": "Institution",
                    "types": [
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
                        }
                    ]
                },
                {
                    "id": 18,
                    "name": "Manifestacion",
                    "types": []
                },
                {
                    "id": 19,
                    "name": "Landmark",
                    "types": [
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
                    ]
                }
            ],
            "totalElements": 3,
            "last": true,
            "totalPages": 1,
            "size": 10,
            "number": 0,
            "numberOfElements": 3,
            "first": true,
            "empty": false,
            "pageNumber": 0,
            "pageSize": 10
        };

        service.getCategoriesByPage(1).subscribe((data: any) => {
            res = data;
            expect(data).toEqual(mockResponse);
        });

        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-categories/by-page/1');
        expect(req.request.method).toBe('GET');
        req.flush(mockResponse);

        tick();
        expect(res).toBeTruthy();
        expect(res.empty).toBe(false);
        expect(res.pageNumber).toBe(0);
        expect(res.pageSize).toBe(10);
        expect(res.numberOfElements).toBe(3);
    }));

    it('getByName() should return category by name Institution', fakeAsync(() => {
        let res: any;
        let mockResponse = {
            "id": 17,
            "name": "Institution",
            "types": [
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
                }
            ]
        }

        service.getByName("Institution").subscribe((data: any) => {
            res = data;
            expect(data).toEqual(mockResponse);
        });

        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-categories/name/Institution');
        expect(req.request.method).toBe('GET');
        req.flush(mockResponse);

        tick();
        expect(res).toBeTruthy();
        expect(res.id).toBe(17);
        expect(res.name).toBe("Institution");
        expect(res.types.length).toBe(2);
    }));

    it('getByName() should not return any category', fakeAsync(() => {
        let res: statusCodeModel = {
            statusCode: 0
        };

        let mockResponse: statusCodeModel = {
            statusCode: 404,
        };

        service.getByName("asdf").subscribe((data) => {
            //@ts-ignore
            res = data;
        });

        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-categories/name/asdf');
        expect(req.request.method).toBe('GET');
        req.flush(mockResponse);

        tick();

        expect(res).toBeTruthy();
        expect(res.statusCode).toBe(404);
    }));

    it('deleteCategory() should delete category', fakeAsync(() => {
        let res = null;
        let mockResponse = null;
        service.deleteCategory(10).subscribe((data) => {
            //@ts-ignore
            res = data;
        });

        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-categories/10');
        expect(req.request.method).toBe('DELETE');
        req.flush(mockResponse);
        //tick();
        //expect(res).toBeTruthy();
    }));


    it('deleteCategory() should not delete category with types', fakeAsync(() => {
        let res: any;
        let mockResponse = {
            "headers": {
                "normalizedNames": {},
                "lazyUpdate": null
            },
            "status": 404,
            "statusText": "OK",
            "url": "http://localhost:8080/cultural-offer-categories/17",
            "ok": false,
            "name": "HttpErrorResponse",
            "message": "Http failure response for http://localhost:8080/cultural-offer-categories/17: 404 OK",
            "error": "CulturalOfferCategory is used in CulturalOfferType"
        };
        service.deleteCategory(17).subscribe((data) => {
            //@ts-ignore
            res = data;
        });
        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-categories/17');
        expect(req.request.method).toBe('DELETE');
        req.flush(mockResponse);
        tick();
        expect(res).toBeTruthy();
        //@ts-ignore
        expect(res.status).toBe(404);
        expect(res).toEqual(mockResponse);

    }));

    it('updateCategory() should update category', fakeAsync(() => {
        let updateObj: CategoryModel = {
            "name": "asdadadadsaedqwasa",
            "id": 32,
            "types": []
        };
        let mockResponse = {
            "id": 32,
            "name": "asdadadadsaedqwasa",
            "types": []
        }
        let res: any;

        service.updateCategory(updateObj).subscribe((data) => {
            res = data;
        });

        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-categories/32');
        expect(req.request.method).toBe('PUT');
        req.flush(mockResponse);
        tick();
        expect(res).toBeTruthy();
        //@ts-ignore
        expect(res).toEqual(mockResponse);
        //expect(res.id).toEqual("32");
    }));

    it('updateCategory() should not update category with existing name', fakeAsync(() => {
        let updateObj: CategoryModel = {
            "name": "Institution",
            "id": 32,
            "types": []
        };
        let mockResponse = {
            "headers": {
                "normalizedNames": {},
                "lazyUpdate": null
            },
            "status": 400,
            "statusText": "OK",
            "url": "http://localhost:8080/cultural-offer-categories/32",
            "ok": false,
            "name": "HttpErrorResponse",
            "message": "Http failure response for http://localhost:8080/cultural-offer-categories/32: 400 OK",
            "error": null
        };

        let res: any;

        service.updateCategory(updateObj).subscribe((data) => {
            res = data;
        });

        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-categories/32');
        expect(req.request.method).toBe('PUT');
        req.flush(mockResponse);
        tick();
        expect(res).toBeTruthy();
        //@ts-ignore
        //expect(res).toEqual(mockResponse);
        expect(res.status).toEqual(400);
    }));

    it('addCategory() should add new category', fakeAsync(() => {
        //@ts-ignore
        let addObj: CategoryModel = {
            "name": "asdf"
        };

        let mockResponse = {
            "id": 33,
            "name": "asdf",
            "types": []
        }

        let res: any;

        service.addCategory(addObj).subscribe((data) => {
            res = data;
        });

        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-categories/');
        expect(req.request.method).toBe('POST');
        req.flush(mockResponse);
        tick();
        expect(res).toBeTruthy();
        //@ts-ignore
        //expect(res).toEqual(mockResponse);
        expect(res.name).toEqual("asdf");
    }));

    it('addCategory() should not add category with existing name', fakeAsync(() => {
        //@ts-ignore
        let addObj: CategoryModel = {
            "name": "Institution"
        };

        let mockResponse = {
            "headers": {
                "normalizedNames": {},
                "lazyUpdate": null
            },
            "status": 400,
            "statusText": "OK",
            "url": "http://localhost:8080/cultural-offer-categories/",
            "ok": false,
            "name": "HttpErrorResponse",
            "message": "Http failure response for http://localhost:8080/cultural-offer-categories/: 400 OK",
            "error": null
        }

        let res: any;

        service.addCategory(addObj).subscribe((data) => {
            res = data;
        });

        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-categories/');
        expect(req.request.method).toBe('POST');
        req.flush(mockResponse);
        tick();
        expect(res).toBeTruthy();
        //@ts-ignore
        expect(res).toEqual(mockResponse);
        expect(res.status).toEqual(400);
    }));

    it('addCategory success', () => {
        //@ts-ignore
        let addObj: CategoryModel = {
            "name": "asdf"
        };
        let mockResponse = {
            "id": 33,
            "name": "asdf",
            "types": []
        }
        service.addCategory(addObj).subscribe((res) => {
            // Note that we are expecting "transformed" response with "university" property
            expect(res).toEqual(mockResponse);
        });

        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-categories/');
        expect(req.request.method).toBe('POST');
        req.flush(mockResponse);
    });

    it('addCategory fail', () => {
        //@ts-ignore
        let addObj: CategoryModel = {
            "name": "Institution"
        };

        let mockResponse = {
            "headers": {
                "normalizedNames": {},
                "lazyUpdate": null
            },
            "status": 400,
            "statusText": "OK",
            "url": "http://localhost:8080/cultural-offer-categories/",
            "ok": false,
            "name": "HttpErrorResponse",
            "message": "Http failure response for http://localhost:8080/cultural-offer-categories/: 400 OK",
            "error": null
        }
        service.addCategory(addObj).subscribe((res) => {
            // Note that we are expecting "transformed" response with "university" property
            expect(res).toEqual(mockResponse);
        });

        const req = httpMock.expectOne('http://localhost:8080/cultural-offer-categories/');
        expect(req.request.method).toBe('POST');
        req.flush(mockResponse);
    });
});