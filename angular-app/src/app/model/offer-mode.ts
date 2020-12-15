export class OfferModel {
    name: string = '';
}

export interface CulturalOffer{
  "id": number,
  "name": string,
  "images": Array<object>,
  "location": Location,
  "description": string
}

export interface Location{
  "locationId": number,
  "latitude": number,
  "longitude": number,
  "place": string
}
