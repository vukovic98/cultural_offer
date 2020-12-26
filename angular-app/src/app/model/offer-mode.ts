import {PostModel} from './post-model';
import {CommentModel} from './comment-model';

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
  "grades": Array<object>,
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
  "images": Array<object>,
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
