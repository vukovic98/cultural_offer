import {CulturalOffer} from './offer-mode';

export interface PostModel{
  id: number,
  content: string,
  offer: CulturalOffer,
  postTime: string,
  title: string
}
