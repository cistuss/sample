import { AxiosResponse } from "axios";

export type HttpErrorResponse = {
  success: boolean
  errorCd: string
}

export class HttpRequestError extends Error {
  readonly response: HttpErrorResponse
  constructor(response: HttpErrorResponse) {
    super()
    this.response = response
  }
}

export class HttpRequestBadRequestError extends HttpRequestError {
  public constructor(response: HttpErrorResponse) {
    super(response);
  }
}




