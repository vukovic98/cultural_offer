export class OfferModel {
    name: string = '';
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
