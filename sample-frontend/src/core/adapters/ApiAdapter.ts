import axios, { AxiosPromise, AxiosRequestConfig, AxiosResponse } from "axios";
import merge from "deepmerge";
import queryString from "query-string";
import { HttpErrorResponse, HttpRequestError } from "@/core/models/errors/SampleExceptionTypes";

function dateParseChallenge(key: string, val: any) {
  if (typeof val === "string") {
    const time = Date.parse(val);
    if (!Number.isNaN(time)) {
      return new Date(time);
    }
  }
  return val;
}

export const httpClient = axios.create({
  transformResponse: (data: any) => {
    if (typeof data === "string") {
      try {
        return JSON.parse(data, dateParseChallenge);
      } catch (e) {
        // Ignore error
      }
    }
  }
});

export enum HttpRequestMethod {
  GET = "GET",
  POST = "POST",
}

export type RequestInterceptor = (config: AxiosRequestConfig) => AxiosRequestConfig | Promise<AxiosRequestConfig>;
export type ResponseInterceptor = (config: AxiosResponse) => AxiosResponse | Promise<AxiosResponse>;

export interface ApiAdapterOptions {
  requestInterceptors?: Array<RequestInterceptor>
  responseInterceptors?: Array<ResponseInterceptor>
}

class ApiAdapter {
  constructor(options: ApiAdapterOptions = {}) {
    if (options.requestInterceptors) {
      options.requestInterceptors.forEach(v => httpClient.interceptors.request.use(v));
    }
    if (options.responseInterceptors) {
      options.responseInterceptors.forEach(v => httpClient.interceptors.response.use(v));
    }
  }

  private async request<Req, Res = Record<string, never>>(
    method: HttpRequestMethod,
    url: string,
    config: AxiosRequestConfig
  ): Promise<AxiosResponse<Res>> {
    const requestConfig = merge(this.baseConfig, {method, url, ...config})
    try {
      const res = await httpClient.request<Res>(requestConfig)
      this.checkResponse(res)
      return res
    } catch (e: any) {
      const errorResponse = e.response.data as HttpErrorResponse
      throw new HttpRequestError(errorResponse)
    }
  }

  get<Req, Res>(url: string, params: Req, options: AxiosRequestConfig = {}): AxiosPromise<Res> {
    return this.request(
      HttpRequestMethod.GET,
      url,
      merge(options, {
        params,
        paramsSerializer: (param: Req) => {
          // const queryStr = param.slice(param.indexOf('?'))
          return queryString.stringify(param)
        }
      })
    )
  }

  post<Req, Res>(url: string, data: Req, options: AxiosRequestConfig = {}): AxiosPromise<Res> {
    return this.request<Req, Res>(HttpRequestMethod.POST, url, merge(options, {data}))
  }

  private checkResponse(response: AxiosResponse) {
    if (response.data) {
      if (response.data.success === false || response.data.errorCd) {
        throw {response}
      }
    } else if (response.status !== 200) {
      throw {response}
    }
  }

  get baseConfig(): AxiosRequestConfig {
    return {
      baseURL: "/api",
      headers: {
        "Content-Type": "application/json",
        "X-Request-With": "XMLHttpRequest",
      },
      responseType: "json",
      timeout: 300000,
    };
  }
}

export default ApiAdapter