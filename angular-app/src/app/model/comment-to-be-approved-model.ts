import {Image} from "./offer-mode";

export interface CommentToBeApprovedModel{
  id: number,
  commenterName: string,
  offer: string,
  content: string,
  image: Image,
  commenterEmail: string
}
