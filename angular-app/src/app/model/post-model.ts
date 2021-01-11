import {CulturalOffer} from './offer-mode';

export interface PostModel{
  id: number,
  content: string,
  offer: CulturalOffer,
  postTime: string,
  title: string
}

export interface AddPostModel{
  id: number,
  title: string,
  content: string,
  culturalOfferId: number
}
