export interface loginResponse {
  authenticationToken: string;
  expiresAt: number;
  email: string;
  verified: boolean;
}
export interface signupResponse {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
}
export interface verifyResponse {
  code: string;
  userEmail: string;
}

export interface statusCodeModel{
  statusCode:  number;
}
