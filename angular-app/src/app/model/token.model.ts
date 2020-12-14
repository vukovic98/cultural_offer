export interface TokenModel {
  "iss": string,
  "sub": string,
  "aud": string,
  "iat": number,
  "exp": number,
  "user_firstName": string,
  "user_lastName": string,
  "user_id": string,
  "authority": Array<Role>
}

export interface Role {
  "id": number,
  "name": string,
  "authority": string
}
