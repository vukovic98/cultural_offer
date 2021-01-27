import {PostModel} from './post-model';
import {CommentModel} from './comment-model';
import {userDto} from './userDto';

export class OfferModel {
    name: string = '';
}
export interface OfferDetailsModel{
  "id": string,
	"name": string,
  "images": Array<Image>,
  "location": Location,
  "description": string,
  "posts": Array<PostModel>,
  "comments": Array<CommentModel>,
  "grades": Array<GradeModel>,
  "type": OfferType
  "avgGrade": number,
  "subscribersCount": number,
  "postsCount": number,
  "commentsCount": number
}

export interface Post{
  "title": string,
  "content": string
}
export interface Image{
  "id": string,
  "picByte": string
}
export interface OfferType{
  "id": number,
  "name": string,
  "categoryName": string
}

export interface CulturalOffer{
  "id": number,
  "name": string,
  "images": Array<Image>,
  "location": Location,
  "description": string,
  "avgGrade": number,
  "subscribersCount": number
}

export interface Location{
  "locationId": number,
  "latitude": number,
  "longitude": number,
  "place": string
}

export interface GradeModel{
  "culturalOffer": CulturalOffer,
  "id": number,
  "value": number,
  "user": userDto
}

export interface PageObject<T> {
  content: Array<T>,
  totalElements: number,
  last: boolean,
  totalPages: number,
  size: number,
  number: number,
  numberOfElements: number,
  first: boolean,
  empty: boolean,
  pageNumber: number,
  pageSize: number
}
