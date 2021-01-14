import {CulturalOffer} from './offer-mode';

export interface CommentModel{
  commenterEmail: string,
  commenterName: string,
  content: string,
  id: number,
  image: ImageModel | null,
  offer: string | null
}

export interface ImageModel{
  id: number,
  picByte: string
}
